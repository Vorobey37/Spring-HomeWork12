package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeAutoConfiguration {
    @Bean
    public TimeAspect timeAspect(){
        return new TimeAspect();
    }
}
