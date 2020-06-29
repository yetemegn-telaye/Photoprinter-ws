package com.photoprinter.app;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.photoprinter.app.model" })
@EnableJpaRepositories(basePackages = { "com.photoprinter.app" })
@EnableTransactionManagement
public class RepositoryConfiguration {
}
