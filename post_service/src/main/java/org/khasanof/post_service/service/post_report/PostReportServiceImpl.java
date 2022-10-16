package org.khasanof.post_service.service.post_report;

import org.khasanof.post_service.criteria.post_report.PostReportCriteria;
import org.khasanof.post_service.dto.post_block.PostBlockCreateDTO;
import org.khasanof.post_service.dto.post_report.PostReportCreateDTO;
import org.khasanof.post_service.dto.post_report.PostReportDetailDTO;
import org.khasanof.post_service.dto.post_report.PostReportGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.entity.post_report.PostReportEntity;
import org.khasanof.post_service.entity.report.ReportEntity;
import org.khasanof.post_service.enums.blocked_for.BlockedForEnum;
import org.khasanof.post_service.enums.rating.RatingTypeEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_report.PostReportMapper;
import org.khasanof.post_service.repository.post_report.PostReportRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_block.PostBlockService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_report.PostReportValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.*;

@Service
public class PostReportServiceImpl extends AbstractService<PostReportRepository, PostReportMapper, PostReportValidator> implements PostReportService {

    private final PostService postService;
    private final PostRatingService postRatingService;
    private final PostMapper postMapper;
    private final PostBlockService postBlockService;
    private final MongoTemplate mongoTemplate;

    public PostReportServiceImpl(PostReportRepository repository, PostReportMapper mapper, PostReportValidator validator, PostService postService, PostRatingService postRatingService, PostMapper postMapper, PostBlockService postBlockService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postRatingService = postRatingService;
        this.postMapper = postMapper;
        this.postBlockService = postBlockService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostReportCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostReportEntity postReport = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getReportPostId()))),
                PostReportEntity.class);
        if (Objects.nonNull(postReport)) {
            LinkedList<ReportEntity> reports = postReport.getReports();
            List<ReportEntity> reportEntities = reports.stream()
                    .filter(f -> f.getUserId().equals(dto.getUserId()))
                    .toList();
            long count = reportEntities.stream()
                    .filter(f -> f.getReportCode().equalsIgnoreCase(dto.getReportCode()))
                    .count();
            if (count <= 2) {
                ReportEntity reportEntity = new ReportEntity(dto.getUserId(), dto.getReportCode(), dto.getMessage());
                reports.add(reportEntity);
                postReport.setReports(reports);
                postReport.setLastReportTime(Instant.now());
                postReport.setCountReports(reports.size());
                postReport.setTotalPointReports(postReport.getTotalPointReports() + reportEntity.getPoint());
                repository.save(postReport);
                BaseUtils.EXECUTOR_SERVICE.execute(() -> checkReports(postReport));
            }
        } else {
            PostReportEntity postReportEntity = mapper.toCreateDTO(dto);
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getReportPostId()));
            postReportEntity.setPostId(postEntity);
            LinkedList<ReportEntity> list = new LinkedList<>();
            ReportEntity reportEntity = new ReportEntity(dto.getUserId(), dto.getReportCode().toUpperCase(), dto.getMessage());
            list.add(reportEntity);
            postReportEntity.setReports(list);
            postReportEntity.setLastReportTime(Instant.now());
            postReportEntity.setCountReports(list.size());
            postReportEntity.setTotalPointReports(reportEntity.getPoint());
            repository.save(postReportEntity);
        }
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
        Integer totalPointReports = entity.getTotalPointReports();
        PostRatingEntity ratingEntity = postRatingService.getEntity(entity.getPostId().getId());
        Integer point = RatingTypeEnum.getPoint(ratingEntity.getRatingType());
        if (point.equals(totalPointReports)) {
            postBlockService.create(new PostBlockCreateDTO(entity.getPostId().getId(),
                    BlockedForEnum.REPORT.getValue(), true));
        }
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

    private PostReportGetDTO returnToGetDTO(PostReportEntity entity) {
        PostReportGetDTO postReportGetDTO = mapper.fromGetDTO(entity);
        postReportGetDTO.setReportPostId(entity.getPostId().getId());
        return postReportGetDTO;
    }
}
