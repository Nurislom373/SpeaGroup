package org.khasanof.post_service.service.post_report;

import org.khasanof.post_service.criteria.post_report.PostReportCriteria;
import org.khasanof.post_service.dto.post_report.PostReportCreateDTO;
import org.khasanof.post_service.dto.post_report.PostReportDetailDTO;
import org.khasanof.post_service.dto.post_report.PostReportGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_report.PostReportEntity;
import org.khasanof.post_service.entity.report.ReportEntity;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_report.PostReportMapper;
import org.khasanof.post_service.repository.post_report.PostReportRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_report.PostReportValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostReportServiceImpl extends AbstractService<PostReportRepository, PostReportMapper, PostReportValidator> implements PostReportService {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostReportServiceImpl(PostReportRepository repository, PostReportMapper mapper, PostReportValidator validator, PostService postService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostReportCreateDTO dto) {
        validator.validCreateDTO(dto);
        Optional<PostReportEntity> optional = repository.findByPostIdQuery(dto.getReportPostId());
        PostReportEntity postReportEntity;
        if (optional.isPresent()) {
            postReportEntity = optional.get();
            LinkedList<ReportEntity> reports = postReportEntity.getReports();
            ReportEntity reportEntity = new ReportEntity(dto.getUserId(), dto.getReportCode(), dto.getMessage());
            reports.add(reportEntity);
            postReportEntity.setReports(reports);
            postReportEntity.setLastReportTime(Instant.now());
            postReportEntity.setCountReports(reports.size());
            postReportEntity.setTotalPointReports(postReportEntity.getTotalPointReports() + reportEntity.getPoint());
        } else {
            postReportEntity = mapper.toCreateDTO(dto);
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getReportPostId()));
            postReportEntity.setPostId(postEntity);
            LinkedList<ReportEntity> list = new LinkedList<>();
            ReportEntity reportEntity = new ReportEntity(dto.getUserId(), dto.getReportCode(), dto.getMessage());
            list.add(reportEntity);
            postReportEntity.setReports(list);
            postReportEntity.setLastReportTime(Instant.now());
            postReportEntity.setCountReports(list.size());
            postReportEntity.setTotalPointReports(reportEntity.getPoint());
        }
        repository.save(postReportEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Report not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void checkReports(PostReportEntity entity) {
        entity.getCountReports();
    }

    @Override
    public PostReportGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Report not found");
                        })
        );
    }

    @Override
    public PostReportDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Report not found");
                        })
        );
    }

    @Override
    public List<PostReportGetDTO> list(PostReportCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private void checkPointReportsAndBlock(PostReportEntity entity) {
//        TODO writing logic with block post check
    }

    private PostReportGetDTO returnToGetDTO(PostReportEntity entity) {
        PostReportGetDTO postReportGetDTO = mapper.fromGetDTO(entity);
        postReportGetDTO.setReportPostId(entity.getPostId().getId());
        return postReportGetDTO;
    }
}
