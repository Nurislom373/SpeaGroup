package org.khasanof.post_service.service;

import org.junit.jupiter.api.Test;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.khasanof.post_service.service.post.PostService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.List;

public class PostServiceTest {


    @Mock
    PostService postService;


    @Test
    void createTest() {
        postService = Mockito.mock(PostService.class);
        postService.create(new PostCreateDTO("633d91140843c91e7b648639", "hello guys",
                "fhdsgfugudgfuydgfdgyufdgfdgfd", List.of("fugduysgfygdsyhfgdsfdsfds", "fudsyfgudsygfydsgfds"),
                PostVisibilityEnum.PUBLIC, List.of("uutgreyertre")));
        PostEntity entity = postService.getEntity("633d91140843c91e7b648639");
        Assert.isNull(entity, "not null");
    }

}
