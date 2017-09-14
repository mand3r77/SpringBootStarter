package com.uscs.common.handler;

/**
 * A common interface for handling an arbitrary request object
 * and providing a response.
 * 
 * @author hqrsingh
 *
 * @param <T1>
 * @param <T2>
 */
public interface Handler<T1, T2> {

	T2 process(T1 object);
}
