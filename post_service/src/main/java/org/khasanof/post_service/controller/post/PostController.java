package org.khasanof.post_service.controller.post;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post.PostCatCriteria;
import org.khasanof.post_service.criteria.post.PostCriteria;
import org.khasanof.post_service.criteria.post.PostRatingCriteria;
import org.khasanof.post_service.criteria.post.PostUseCriteria;
import org.khasanof.post_service.dto.post.*;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post/")
public class PostController extends AbstractController<PostService> {
    public PostController(PostService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Post"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@RequestBody PostUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Post"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detailAndComments/postId={id}&count={count}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<PostDetWComDTO>> detailAndComment(@PathVariable String id, @PathVariable Integer count) {
        return new ResponseEntity<>(new Data<>(service.getByPostIdDetailAndComments(id, count)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostGetDTO>>> list(@Valid PostCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }

    @RequestMapping(value = "listWithCategory", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostGetDTO>>> listWithCategory(@Valid PostCatCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.listWithCategory(criteria)), HttpStatus.OK);
    }

    @RequestMapping(value = "listWithRating", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostGetDTO>>> listWithRating(@Valid PostRatingCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.listWithRating(criteria)), HttpStatus.OK);
    }

    @RequestMapping(value = "listWithUserId", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostGetDTO>>> listWithUserId(@Valid PostUseCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.listWithUserId(criteria)), HttpStatus.OK);
    }

    @RequestMapping(value = "list/createdBy/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostGetDTO>>> listCreatedBy(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.getAllWithCreatedBy(id)), HttpStatus.OK);
    }

//    TODO write random post when following or top posts api

}
