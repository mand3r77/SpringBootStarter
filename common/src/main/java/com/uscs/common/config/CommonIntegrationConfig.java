package com.uscs.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Spring configuration for the common utilities infrastructure.
 * 
 * @author hqrsingh
 *
 */
@Configuration
@ImportResource({ "classpath:spring/mail-config.xml" })
public class CommonIntegrationConfig {

	private static Logger logger = LoggerFactory
			.getLogger(CommonIntegrationConfig.class);

	private @Value("$mail{primary.server}") String host;

	public CommonIntegrationConfig() {
		logger.debug("Bootstrapping mail-config.xml");
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);

		return mailSender;
	}

}
