package com.uscs.sampleapp.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;

import com.uscs.common.utils.character.Char;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Spring configuration for the Spring Boot sample app. 
 * 
 * @author hqrsingh
 *
 */
@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:spring/sampleapp-config.xml" })
public class SampleAppConfig {

	private static Logger logger = LoggerFactory
			.getLogger(SampleAppConfig.class);
	
	private static final String DEFAULT_SAMPLE_APP_FAILURE_MESSAGE = "Failed to process sample app message.";
	private static final String CONNECT_TEST_QUERY = "SELECT 1 FROM SYSIBM.SYSDUMMY1";

	private @Value("$db{jdbc.audit.driverClassName}") String driverClassName;
	private @Value("$db{jdbc.audit.db.url}") String jdbcUrl;
	private @Value("$db{jdbc.audit.db.user}") String username;
	private @Value("$db{jdbc.audit.db.pwd}") String password;
	
	private @Value("$mail{noreply.address}") String noReply;
	private @Value("$mail{recipient.list}") String recipient;

	public SampleAppConfig() {
		logger.debug("Bootstrapping audit-config.xml");
	}
	
	@Bean
	public MessageSource messageSource() {
	    final ResourceBundleMessageSource messageSource;

	    messageSource = new ResourceBundleMessageSource();
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setBasename("messages/web");

	    return messageSource;
	}	
	
	
	@Primary
	@Bean
	public DataSource auditDataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setConnectionTestQuery(CONNECT_TEST_QUERY);
		HikariDataSource dataSource = new HikariDataSource(config);
		
		return dataSource;
	}			
	
	@Bean
	public SimpleMailMessage templateMessage() {

		SimpleMailMessage template = new SimpleMailMessage();
		template.setFrom(noReply);
		String[] recipients = recipient.split(Char.COMMA.toString()); 	
		template.setTo(recipients);	
		template.setSubject(DEFAULT_SAMPLE_APP_FAILURE_MESSAGE);

		return template;
	}	
	
	
}
