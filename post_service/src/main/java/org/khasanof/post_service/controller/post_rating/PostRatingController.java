package org.khasanof.post_service.controller.post_rating;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_rating/*")
public class PostRatingController extends AbstractController<PostRatingService> {
    public PostRatingController(PostRatingService service) {
        super(service);
    }
}
