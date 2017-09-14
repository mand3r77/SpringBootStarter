package com.uscs.sampleapp.service;

import com.uscs.sampleapp.model.SomeMessage;

/**
 * Auditing service interface
 * 
 * @author hqrsingh
 *
 */
public interface DoStuffService {
	public void handle(SomeMessage<?> message);
}
