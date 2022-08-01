package com.proj.shop.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;


@ToString
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.main", ignoreUnknownFields = false)
public class MainDataSourceProperty {
    private final String username;
    private final String password;
    private final Property master;
    private final List<Property> slaves;

    @Getter
    @RequiredArgsConstructor
    public static class Property {
        private final String name;
        private final String url;
    }
}

