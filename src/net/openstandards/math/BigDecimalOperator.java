package net.openstandards.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.openstandards.util.NumberUtils;

public class BigDecimalOperator implements MathOperator<BigDecimal> {
	
	public static final int DEFAULT_SCALE = -1;

	private int scale = DEFAULT_SCALE;

	public BigDecimalOperator() { }
	
	public BigDecimalOperator(int scale) { 
		this.scale = scale;
	}
	
	@Override
	public BigDecimal zero() {
		return NumberUtils.BD_ZERO;
	}

	@Override
	public BigDecimal add(BigDecimal first, BigDecimal second) {
		return first == null ? (second == null ? null : second) : (second == null ? first : first.add(second));
	}

	@Override
	public BigDecimal subtract(BigDecimal first, BigDecimal second) {
		return first == null ? null : (second == null ? first : first.subtract(second));
	}

	@Override
	public BigDecimal divide(BigDecimal numerator, int denominator) {
		return divide(numerator, new BigDecimal(denominator));
	}

	@Override
	public BigDecimal newInstance(int value) {
		return new BigDecimal(value);
	}

	@Override
	public BigDecimal divide(BigDecimal numerator, BigDecimal denominator) {
		if (scale > -1)
			return numerator.divide(denominator, scale, RoundingMode.HALF_UP).setScale(scale, RoundingMode.HALF_UP);
		else
			return numerator.divide(denominator);
//		return numerator.divide(denominator).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
	}

	@Override
	public BigDecimal multiply(BigDecimal first, BigDecimal second) {
		return first.multiply(second);
	}

	@Override
	public BigDecimal scale(BigDecimal value) {
		return scale > -1 ? value.setScale(scale, RoundingMode.HALF_UP) : value;
	}

}
