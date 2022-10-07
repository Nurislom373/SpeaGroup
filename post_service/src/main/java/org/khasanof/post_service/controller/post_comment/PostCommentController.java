package org.khasanof.post_service.controller.post_comment;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.dto.post_comment.PostCommentCreateDTO;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_comment.PostCommentService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "delete/postId={postId}&commentId={commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String postId, @PathVariable String commentId) {
        service.deleteComment(postId, commentId);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Comment"), HttpStatus.NO_CONTENT);
    }
}
