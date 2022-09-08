package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor(Environment environment) {
        return beanFactory -> {

            log.info("BeanFactoryPostProcessor#postProcessBeanFactory");

            final PropertiesWrapper wrapper = Binder.get(environment)
                    .bind(PropertiesWrapper.PREFIX, PropertiesWrapper.class)
                    .orElseGet(PropertiesWrapper::new);

            wrapper.getProperties().forEach((s, o) -> {
                log.info("Registering bean {}: {} as singleton", s, o);
                beanFactory.registerSingleton(s, o);
            });

        };
    }

}
