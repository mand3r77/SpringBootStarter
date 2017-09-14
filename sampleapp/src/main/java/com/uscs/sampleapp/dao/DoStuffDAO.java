package com.uscs.sampleapp.dao;

import com.uscs.sampleapp.model.SomeMessage;

/**
 * Auditing data access interface.
 * 
 * @author hqrsingh
 *
 */
public interface DoStuffDAO {
	public void save(SomeMessage<?> message);
}
