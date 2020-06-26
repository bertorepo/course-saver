package com.fujitsu.ph.tsup;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories
public class TsupJdbcConfiguration extends AbstractJdbcConfiguration {

}
