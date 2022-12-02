package org.khasanof.post_service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostDetailDTO;
import org.khasanof.post_service.dto.post.PostGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.enums.post.PostStatusEnum;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.khasanof.post_service.repository.post.PostRepository;
import org.khasanof.post_service.service.post.PostService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Autowired
    private PostService service;

    @Mock
    private PostRepository repository;

//    @Test
    void createTest() {
        PostEntity entity = new PostEntity("633d91140843c91e7b648639", "hello guys",
                "fhdsgfugudgfuydgfdgyufdgfdgfd", PostStatusEnum.ACTIVE,
                List.of("fugduysgfygdsyhfgdsfdsfds", "fudsyfgudsygfydsgfds"), PostVisibilityEnum.PUBLIC);
        var dto = new PostCreateDTO("633d91140843c91e7b648639", "hello guys",
                "fhdsgfugudgfuydgfdgyufdgfdgfd", List.of("fugduysgfygdsyhfgdsfdsfds", "fudsyfgudsygfydsgfds"),
                PostVisibilityEnum.PUBLIC, List.of("uutgreyertre"));
        service.create(dto);
        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    public void getMethodTest() {
        var dto = new PostGetDTO("63878db5ae230f1379f039bb", "fdsudfsfd", "fdsfdsfds",
                List.of("fduisgfudys.png", "fdishfuidhsuf.png"), "PRIVATE");
        Mockito.when(service.get(ArgumentMatchers.any())).thenReturn(dto);
        PostGetDTO testDTO = service.get("63878db5ae230f1379f039bb");
        Assertions.assertEquals(dto.getId(), testDTO.getId());
    }

    public void detailMethodTest() {
        var dto = new PostDetailDTO("63878db5ae230f1379f039bb", "fdsudfsfd", "fdsfdsfds", "ACTIVE",
                List.of("fduisgfudys.png", "fdishfuidhsuf.png"), 12, 120,
                2, 1, "PRIVATE", Instant.now(), Instant.now());
    }

}
