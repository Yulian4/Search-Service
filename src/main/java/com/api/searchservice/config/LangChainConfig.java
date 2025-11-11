package com.api.searchservice.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(Properties.class)
public class LangChainConfig {

}
