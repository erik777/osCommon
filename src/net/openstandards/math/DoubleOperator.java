package net.openstandards.math;

public class DoubleOperator implements MathOperator<Double> {
	
	public static final Double ZERO = new Double(0);
	
	public DoubleOperator() { }
	
	@Override
	public Double newInstance(int value) {
		return new Double(value);
	}

	@Override
	public Double zero() {
		return ZERO;
	}

	@Override
	public Double add(Double first, Double second) {
		return first + second;
	}

	@Override
	public Double subtract(Double first, Double second) {
		return first - second;
	}

	@Override
	public Double divide(Double numerator, int value) {
		return numerator / value;
	}

	@Override
	public Double divide(Double numerator, Double value) {
		return numerator / value;
	}

	@Override
	public Double multiply(Double first, Double second) {
		return first * second;
	}

	@Override
	public Double scale(Double value) {
		// TODO Should create scale for Double
		return value;
	}

}
