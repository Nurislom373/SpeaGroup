package org.khasanof.post_service.controller.post_comment;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.dto.post_comment.PostCommentCreateDTO;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_comment.PostCommentService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
