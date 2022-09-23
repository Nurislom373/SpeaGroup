package org.khasanof.auth_service.data.auth_role;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
@Slf4j
public class AuthRoleDataCreate implements CommandLineRunner {

    @Autowired
    private AuthUserRepository userRepository;
    @Autowired
    private AuthRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
//        List<AuthUserEntity> all = userRepository.findAll();
//        all.forEach((entity) -> {
//            roleRepository.save(new AuthRoleEntity(entity, random(randomNum(1, 2))));
//        });
//        log.info(">>>>>>> " + all.size() + " Auth Roles Saved!");

        // need to run once!
    }

    private String random(int var) {
        if (var == 1)
            return AuthRoleEnum.USER.getValue();
        else
            return AuthRoleEnum.ADMIN.getValue();
    }

    private int randomNum(int var1, int var2) {
        return var1 + (int) (Math.random() * var2);
    }
}
