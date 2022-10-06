package org.khasanof.post_service.service.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.khasanof.post_service.criteria.post.PostCriteria;
import org.khasanof.post_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostDetailDTO;
import org.khasanof.post_service.dto.post.PostGetDTO;
import org.khasanof.post_service.dto.post.PostUpdateDTO;
import org.khasanof.post_service.entity.auth_user.AuthUserEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.repository.post.PostRepository;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post.PostValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl extends AbstractService<PostRepository, PostMapper, PostValidator> implements PostService {

    public PostServiceImpl(PostRepository repository, PostMapper mapper, PostValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(PostCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (!PostVisibilityEnum.hasVisibility(dto.getVisibility()))
            throw new RuntimeException("Visibility Invalid!");
        PostEntity postEntity = mapper.toCreateDTO(dto);
        AuthUserEntity userEntity = new AuthUserEntity();
        BeanUtils.copyProperties(getAuthUserDTO(dto.getPostUserId()), userEntity);
        postEntity.setUserId(userEntity);
        repository.save(postEntity);
    }

    @Override
    public void update(PostUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        PostEntity post = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Post not found");
                });
        if (!PostVisibilityEnum.hasVisibility(dto.getVisibility()))
            throw new RuntimeException("Visibility Invalid!");
        BeanUtils.copyProperties(dto, post, "id");
        repository.save(post);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostGetDTO get(String id) {
        validator.validKey(id);
        return entityParseDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        }));
    }

    @Override
    public PostDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        }));
    }

    @Override
    public List<PostGetDTO> list(PostCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getDirection(),
                                criteria.getFieldsEnum().getValue()
                        )).stream()
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public List<PostGetDTO> getAllWithCreatedBy(String userId) {
        return repository.findAllByCreatedBy(userId)
                .stream()
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public boolean existById(String postId) {
        validator.validKey(postId);
        return repository.existsById(postId);
    }

    private PostGetDTO entityParseDTO(PostEntity entity) {
        PostGetDTO dto = mapper.fromGetDTO(entity);
        dto.setPostUserId(entity.getUserId().getId());
        return dto;
    }

    private AuthUserGetDTO getAuthUserDTO(String id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpGet httpGet = new HttpGet(BaseUtils.AUTH_SERVICE + "/auth_user/get/" + id);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200)
                throw new NotFoundException("User not found");
            return objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Data<AuthUserGetDTO>>() {
            }).getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
