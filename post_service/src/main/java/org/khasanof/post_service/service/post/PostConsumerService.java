package org.khasanof.post_service.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostConsumerService {

    private final PostService postService;

    @KafkaListener(topics = "auth", groupId = "0", containerFactory = "apacheKafkaListenerContainerFactory")
    public void consume(Object id) {
        postService.deleteByUserIdAllPost((String) id);
    }

}
