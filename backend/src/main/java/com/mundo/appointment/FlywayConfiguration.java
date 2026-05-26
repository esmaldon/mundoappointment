package com.mundo.appointment;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class FlywayConfiguration {

	@Bean(initMethod = "migrate")
	Flyway flyway(DataSource dataSource) {
		return Flyway.configure()
				.dataSource(dataSource)
				.load();
	}
}
