package com.uscs.common.utils.character;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CharTests {

	@Before
	public void setup() {
	}

	@After
	public void after() {
	}

	@Test
    public void validateCodePoints() {
		
		assertEquals(Char.APOSTROPHE, Char.fromCodePoint(39));
		assertEquals(Char.COMMA, Char.fromCodePoint(44));
		assertEquals(Char.SEMICOLON, Char.fromCodePoint(59));
		assertEquals(Char.EXCLAMATION_SIGN, Char.fromCodePoint(33));
	
    }
	
	@Test
	public void validateToString() {
		
		assertEquals(Char.APOSTROPHE.toString(), "'");
		assertEquals(Char.COMMA.toString(), ",");
		assertEquals(Char.SEMICOLON.toString(), ";");
		assertEquals(Char.EXCLAMATION_SIGN.toString(), "!");
	}
}
