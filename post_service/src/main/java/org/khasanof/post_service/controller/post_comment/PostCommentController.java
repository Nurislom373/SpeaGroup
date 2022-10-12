package org.khasanof.post_service.controller.post_comment;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post_comment.PostCommentCriteria;
import org.khasanof.post_service.dto.post_comment.*;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_comment.PostCommentService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_comment/*")
public class PostCommentController extends AbstractController<PostCommentService> {

    public PostCommentController(PostCommentService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostCommentCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Post Comment"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> addComment(@RequestBody PostCommentCreateDTO dto) {
        service.addComment(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Post Comment"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/postId={postId}&commentId={commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String postId, @PathVariable String commentId) {
        service.deleteComment(postId, commentId);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Comment"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Comment"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "addLikeToComment", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> addLike(@RequestBody PostCommentAddLikeDTO dto) {
        service.addCommentToLike(dto);
        return new ResponseEntity<>(new Data<>("Successfully Add Like - Post Comment"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "removeLikeToComment", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> removeLike(@RequestBody PostCommentRemoveLikeDTO dto) {
        service.deleteCommentToLike(dto);
        return new ResponseEntity<>(new Data<>("Successfully Remove Like - Post Comment"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "getCommentCount/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostCommentCount>> getCommentCount(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.getCommentsCount(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostCommentGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostCommentDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostCommentGetDTO>>> list(@Valid PostCommentCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
