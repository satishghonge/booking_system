package com.chirpn.bookingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.chirpn.bookingsystem.utilities.AuditorAwareImpl;
/**
 * @author Satish Ghonge
 *
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

	@Bean
	AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}
