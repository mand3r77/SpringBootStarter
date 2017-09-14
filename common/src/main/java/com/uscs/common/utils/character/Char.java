package com.uscs.common.utils.character;

import java.util.HashMap;
import java.util.Map;

/**
 * A common set of constants to be used for string manipulation
 * purposes.
 * 
 * @author hqrsingh
 *
 */
public enum Char {
	APOSTROPHE('\''), COMMA(','), SEMICOLON(';'), EXCLAMATION_SIGN('!'), HYPHEN('-');

	private final int charIndex;
	private static Map<Integer, Char> map = new HashMap<>();
	static {
		for (Char c : Char.values()) {
			map.put(c.charIndex, c);
		}
	}

	private Char(int charIndex) {
		this.charIndex = charIndex;
	}

	public int toCodePoint() {
		return charIndex;
	}

	public static Char fromCodePoint(int cp) {
		return map.get(cp);
	}

	@Override
	public String toString() {
		return new String(new int[] { charIndex }, 0, 1);
	}
}
