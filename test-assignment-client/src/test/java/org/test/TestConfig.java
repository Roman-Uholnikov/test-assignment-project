package org.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = "org.test.service")
@PropertySource(value = "classpath:application.properties")
public class TestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return mock(RestTemplate.class);
    }
}
