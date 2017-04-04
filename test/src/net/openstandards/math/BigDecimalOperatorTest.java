package net.openstandards.math;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalOperatorTest {

	@Test
	public void testAdd() {
		BigDecimal first = new BigDecimal("555");
		MathOperator<BigDecimal> operator = new BigDecimalOperator();
		assertEquals(new BigDecimal("560.5"), operator.add(first, new BigDecimal("5.5")));
		assertEquals(new BigDecimal("566"), operator.add(first, new BigDecimal("11")));
		assertEquals(new BigDecimal("555"), operator.add(first, null));
		assertEquals(new BigDecimal("555"), operator.add(null, first));
	}

	@Test
	public void testDivide() {
		BigDecimal first = new BigDecimal("555");
		MathOperator<BigDecimal> operator = new BigDecimalOperator();
		assertEquals(new BigDecimal("111"), operator.divide(first, 5));
		assertEquals(new BigDecimal("92.5"), operator.divide(first, 6));
		try {
			assertEquals(new BigDecimal("92.5"), operator.divide(first, 114));
			fail("This shoudl require a scale to be set in constructor");
		} catch (Exception e) {
		}
		operator = new BigDecimalOperator(0);
		assertEquals(new BigDecimal("5"), operator.divide(first, 114));
		operator = new BigDecimalOperator(2);
		assertEquals(new BigDecimal("4.87"), operator.divide(first, 114));
		operator = new BigDecimalOperator(4);
		assertEquals(new BigDecimal("4.8684"), operator.divide(first, 114));
	}
	
	@Test public void testNewInstance() {
		MathOperator<BigDecimal> operator = new BigDecimalOperator();
		BigDecimal three = operator.newInstance(3);
		BigDecimal four = operator.newInstance(4);
		BigDecimal value = operator.divide(three, four);
		assertEquals(new BigDecimal("0.75"), value);
		value = operator.subtract(three, value);
		assertEquals(new BigDecimal("2.25"), value);
		assertEquals(new BigDecimal("9.00"), operator.multiply(value, four));
	}

}
