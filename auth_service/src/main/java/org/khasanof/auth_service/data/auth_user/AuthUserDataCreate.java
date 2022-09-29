package org.khasanof.auth_service.data.auth_user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.entity.auth_follower.AuthFollowerEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_user.AuthUserStatusEnum;
import org.khasanof.auth_service.repository.auth_follower.AuthFollowerRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.auth_follower.AuthFollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@Slf4j
public class AuthUserDataCreate implements CommandLineRunner {

    @Autowired
    private AuthUserRepository userRepository;
    @Autowired
    private AuthFollowerService authFollowerService;

    @Override
    public void run(String... args) throws Exception {
        /*
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<AuthUserEntity>> reference = new TypeReference<>() {
        };

        InputStream inputStream = getClass().getResourceAsStream("/data/auth_user/AUTH_USER_MOCK_DATA.json");
        List<AuthUserEntity> list = objectMapper.readValue(inputStream, reference);
        list.forEach((user) -> {
            user.setStatus(AuthUserStatusEnum.ACTIVE.getValue());
            userRepository.save(user);
        });

        log.info(">>>>>>> " + list.size() + " Users Saved!");
        */

        // need to run once!
    }

}
