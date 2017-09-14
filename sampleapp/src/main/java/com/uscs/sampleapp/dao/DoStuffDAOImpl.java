package com.uscs.sampleapp.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uscs.sampleapp.model.SomeMessage;

/**
 * Auditing data access implementation class.
 * Persists all {@link SomeMessage} related entities.
 * 
 * @author hqrsingh
 *
 */
@Component
public class DoStuffDAOImpl implements DoStuffDAO {
	
	private static final String AUDIT_SCHEMA_KEY = "#AUDIT_SCHEMA#"; 
	
	private static final String MESSAGE_INSERT_QUERY = "insert into " + AUDIT_SCHEMA_KEY + ".TESTMESSAGE " + 
			"(TESTMESSAGE_ID, PAYLOAD, CREATED_DATE) " +
			"values (:TESTMESSAGE_ID, :PAYLOAD, :CREATED_DATE)";

	private @Value("$db{jdbc.audit.schema}") String auditSchema;
	
	@Resource
	private DataSource auditDataSource;
	private NamedParameterJdbcTemplate  auditJdbcTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(DoStuffDAOImpl.class);
	
	public NamedParameterJdbcTemplate getAuditJdbcTemplate() {
		if (auditJdbcTemplate == null) {
			this.auditJdbcTemplate = new NamedParameterJdbcTemplate(auditDataSource);
		}
		return auditJdbcTemplate;
	}	
	
	/**
	 * Main persistence method for framework UscsMessage information
	 * {@link Propagation.MANDATORY} is used to ensure that this implementation class is not
	 * inadvertently used directly. 
	 */
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void save(SomeMessage<?> message) {
		logger.debug("Message: " + message);
		
		Map<String, Object> messageNamedParameters = new HashMap<String, Object>();
		messageNamedParameters.put("TESTMESSAGE_ID", message.getId());
		messageNamedParameters.put("PAYLOAD", message.getPayload());
		messageNamedParameters.put("CREATED_DATE", message.getCreateDt());
		
		getAuditJdbcTemplate().update(MESSAGE_INSERT_QUERY.replace(AUDIT_SCHEMA_KEY, auditSchema), messageNamedParameters);
		
	}

}
