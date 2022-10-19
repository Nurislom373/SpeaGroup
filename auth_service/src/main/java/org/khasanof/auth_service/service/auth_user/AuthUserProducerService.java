package org.khasanof.auth_service.service.auth_user;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserProducerService {

    private static final String TOPIC = "auth";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String id) {
        kafkaTemplate.send(TOPIC, id);
    }

}
