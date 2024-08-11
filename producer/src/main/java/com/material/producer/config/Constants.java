package com.material.producer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Constants {

    @Value("${material.authorization-app}")
    public String authServicePath;
}
