package com.thigorqueiroz.fry.port.adapter.config;

import com.thigorqueiroz.fry.FryApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackageClasses = FryApplication.class)
public class JdbcConfig extends AbstractJdbcConfiguration {
}
