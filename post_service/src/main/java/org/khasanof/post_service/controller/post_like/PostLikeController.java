package org.khasanof.post_service.controller.post_like;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post_like.PostLikeCriteria;
import org.khasanof.post_service.dto.post_like.*;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_like.PostLikeService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_like/*")
public class PostLikeController extends AbstractController<PostLikeService> {

    public PostLikeController(PostLikeService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostLikeCreateDTO dto) {
        service.addLike(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Post Like"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Like"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete/{postId}/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteLike(@PathVariable String postId, @PathVariable String userId) {
        service.deleteLike(new PostLikeDeleteDTO(postId, userId));
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Like"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostLikeGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "getLikeCount/postId={id}&type={type}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostLikeTypeCount>> getLikeCount(@PathVariable String id, @PathVariable String type) {
        return new ResponseEntity<>(new Data<>(service.getByPostIdAndTypeLikeCount(id, type)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostLikeDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostLikeGetDTO>>> list(@Valid PostLikeCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
