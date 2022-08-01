package com.proj.shop.config;


import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConfigurationPropertiesScan("com.proj.shop")
@EnableConfigurationProperties
@Configuration
@EnableScheduling
public class SpringConfig {
}