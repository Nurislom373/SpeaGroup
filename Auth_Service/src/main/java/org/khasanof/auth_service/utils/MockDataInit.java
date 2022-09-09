package org.khasanof.auth_service.utils;

import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.repository.auth_block.AuthBlockRepository;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MockDataInit implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AuthRoleRepository roleRepository = applicationContext.getBean(AuthRoleRepository.class);
        BlockedForRepository blockedForRepository = applicationContext.getBean(BlockedForRepository.class);
        AuthBlockRepository authBlockRepository = applicationContext.getBean(AuthBlockRepository.class);

        BlockedForEntity blockedForEntity = blockedForRepository.save(new BlockedForEntity("LOGIN", "Login Block", 20));

        authBlockRepository.save(new AuthBlockEntity("sfdsfds", Instant.now(), blockedForEntity));

        roleRepository.save(new AuthRoleEntity("fdsfs", "ADMIN"));
    }

}
