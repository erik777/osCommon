package net.openstandards.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleOperatorTest {

	@Test
	public void testAdd() {
		Double first = new Double("555");
		MathOperator<Double> operator = new DoubleOperator();
		assertEquals(new Double("560.5"), operator.add(first, new Double("5.5")));
		assertEquals(new Double("566"), operator.add(first, new Double("11")));
	}

	@Test
	public void testDivide() {
		Double first = new Double("555");
		MathOperator<Double> operator = new DoubleOperator();
		assertEquals(new Double("111"), operator.divide(first, 5));
		assertEquals(new Double("92.5"), operator.divide(first, 6));
		assertTrue(operator.divide(first, 42).toString().startsWith("13.214"));
	}

}
