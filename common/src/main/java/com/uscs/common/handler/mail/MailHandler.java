package com.uscs.common.handler.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.uscs.common.handler.Handler;
import com.uscs.common.utils.exception.ExceptionUtils;

/**
 * Delivers a response {@link MailMessage} based on the provided
 * input {@link Throwable} type in the case of a downstream exception.
 * 
 * @author hqrsingh
 *
 */
@Service
public class MailHandler implements Handler<Throwable, MailMessage> {

	private final Logger logger = LoggerFactory
			.getLogger(MailHandler.class);

	private SimpleMailMessage templateMessage;
 
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	@Override
	public MailMessage process(Throwable throwable) {

		// Create a thread safe "copy" of the template message and customize it
		MailMessage msg = new SimpleMailMessage(this.templateMessage);

		logger.debug("Setting MailMessage text with exception detail...");
		msg.setText(ExceptionUtils.convertToString(throwable));

		return msg;
	}

}