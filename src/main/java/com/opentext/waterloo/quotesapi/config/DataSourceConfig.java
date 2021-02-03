package com.opentext.waterloo.quotesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile({"cloud"})
public class DataSourceConfig extends AbstractCloudConfig {
    @Bean
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
}
