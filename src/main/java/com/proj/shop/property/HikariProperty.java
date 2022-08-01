package com.proj.shop.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ToString
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.hikari", ignoreUnknownFields = false)
public class HikariProperty {
    private final int maximumPoolSize;
    private final int minimumIdle;
}

