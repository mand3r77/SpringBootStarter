package com.uscs.sampleapp.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.uscs.common.utils.rest.RestTemplateHelper;
import com.uscs.sampleapp.dao.DoStuffDAO;
import com.uscs.sampleapp.model.SomeMessage;

/**
 * Auditing service implementation.
 * Constructs a valid {@link UscsMessage} instance to be used
 * for persistence.
 * 
 * @author hqrsingh
 *
 */
@Service
public class DoStuffServiceImpl implements DoStuffService {

	private static Logger logger = LoggerFactory.getLogger(DoStuffServiceImpl.class);
	
	@Autowired
	RestTemplateHelper restTemplateHelper;
	
	@Autowired 
	RestTemplate restTemplate;

	@Autowired
	private DoStuffDAO auditDao;
	
	private @Value("${ewms.username}") String userName;
	private @Value("${ewms.password}") String password;	
	private @Value("${ewms.someurl}") String targetUrl;
	
	public DoStuffServiceImpl() {
	}
	
	@Override
	@Transactional("auditTransactionManager")
	public void handle(SomeMessage<?> message) {
		logger.debug("Message: " + message.getId() + ":" + message.getPayload() + ":" + message.getCreateDt());
		
		//ResponseEntity<SomeDetail> someDetailResponse = null;
		//SomeDetail someDetail = null;
		Map<String, Object> uriAttributes = new HashMap<String, Object>();
	    uriAttributes.put("attrib1", "1");
	    uriAttributes.put("attrib2", "2");
	    
		HttpHeaders httpHeaders = restTemplateHelper.createHeaders(userName,
				password);
		
		//someDetailResponse = restTemplate.exchange(targetUrl, HttpMethod.GET,
		//		new HttpEntity<Object>(httpHeaders), SomeDetail.class, uriAttributes);
		//someDetail = someDetailResponse.getBody();
		auditDao.save(message);
	}

}
