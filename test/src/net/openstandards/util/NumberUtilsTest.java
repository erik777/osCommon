package net.openstandards.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testAscending() {
		assertTrue(NumberUtils.isAscending(new BigDecimal("5"), new BigDecimal("6")));
		assertFalse(NumberUtils.isAscending(new BigDecimal("7"), new BigDecimal("6")));
		assertFalse(NumberUtils.isDescending(new BigDecimal("5"), new BigDecimal("6")));
		assertTrue(NumberUtils.isDescending(new BigDecimal("7"), new BigDecimal("6")));
	}

	@Test
	public void testPositive() {
		assertTrue(NumberUtils.isPositive(new BigDecimal("5")));
		assertFalse(NumberUtils.isPositive(new BigDecimal("-5")));
		assertTrue(NumberUtils.isNegative(new BigDecimal("-5")));
		assertFalse(NumberUtils.isNegative(new BigDecimal("5")));
		assertFalse(NumberUtils.isPositive(new BigDecimal("0")));
		assertFalse(NumberUtils.isNegative(new BigDecimal("0.0")));
	}

}
