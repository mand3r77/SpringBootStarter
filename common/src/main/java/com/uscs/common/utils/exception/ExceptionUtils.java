package com.uscs.common.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A utility class which 'stringifies' the inbound {@link Throwable}.
 * 
 * @author hqrsingh
 *
 */
public class ExceptionUtils {

	public static String convertToString(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		throwable.printStackTrace(printWriter);
		return stringWriter.toString();
	}

}
