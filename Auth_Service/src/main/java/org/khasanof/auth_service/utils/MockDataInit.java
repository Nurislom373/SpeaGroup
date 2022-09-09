package org.khasanof.auth_service.utils;

import org.khasanof.auth_service.entity.authRole.AuthRoleEntity;
import org.khasanof.auth_service.repository.authRole.AuthRoleRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MockDataInit implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AuthRoleRepository roleRepository = applicationContext.getBean(AuthRoleRepository.class);
        roleRepository.save(new AuthRoleEntity("fdsfs", "ADMIN"));
    }

}
