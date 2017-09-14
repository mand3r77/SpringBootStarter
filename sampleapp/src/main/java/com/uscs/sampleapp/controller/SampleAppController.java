package com.uscs.sampleapp.controller;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uscs.common.handler.mail.MailHandler;
import com.uscs.common.utils.exception.ExceptionUtils;
import com.uscs.sampleapp.model.DoStuffRequestVO;
import com.uscs.sampleapp.model.DoStuffResponseVO;
import com.uscs.sampleapp.model.SomeMessage;
import com.uscs.sampleapp.service.DoStuffService;

/**
 * Sample App Controller
 * 
 * @author hqrsingh
 *
 */
@RestController
public class SampleAppController {
    
	private static Logger logger = LoggerFactory.getLogger(SampleAppController.class);
	
	@Autowired
	private MailHandler mailHandler;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private DoStuffService doStuffService;

    
    /**
     * Spring RequestMapping to invoke the sample app service
     * 
     * @param customerSyncRequest The unwrapped request payload (i.e. without an envelope) to be sent through the Gateway
     * @return customerSyncResponse The unwrapped response payload
     */	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/dostuff", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public DoStuffResponseVO doStuff(@RequestBody DoStuffRequestVO doStuffRequest) {
    	DoStuffResponseVO doStuffResponse = null;
    	
    	SomeMessage<DoStuffRequestVO> requestMessage = 
    			SomeMessage.getBuilder()
    				.id(UUID.randomUUID().toString())
    				.payload("SOME-PAYLOAD")
    				.createDt(new Date()).build();
    	
    	logger.debug("DoStuffRequestVO: " + doStuffRequest.toString());
    	
    	try {
    		doStuffService.handle(requestMessage);
    		doStuffResponse = generateDoStuffResponse("SUCCESS-RESPONSE-CODE", null);
		} catch (Exception exception) {
			doStuffResponse = generateDoStuffResponse("FAILURE-RESPONSE-CODE", exception);
			logger.error("Sample app operation failed.", exception);
			
			mailSender.send((SimpleMailMessage) mailHandler.process(exception));
		}    	
    	
    	return doStuffResponse;
    }
	
    private DoStuffResponseVO generateDoStuffResponse(String responseCode, Throwable responseMessage) {
    	DoStuffResponseVO doStuffResponse = new DoStuffResponseVO();
		doStuffResponse.setResponseCode(responseCode);
		if (responseMessage != null) {
			doStuffResponse.setResponseMessage(ExceptionUtils.convertToString(responseMessage));
		}
    	return doStuffResponse;
    }

}