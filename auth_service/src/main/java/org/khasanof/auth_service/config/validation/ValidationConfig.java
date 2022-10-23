package org.khasanof.auth_service.config.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(factoryBean());
    }

    @Bean
    public LocalValidatorFactoryBean factoryBean() {
        return new LocalValidatorFactoryBean();
    }

}
