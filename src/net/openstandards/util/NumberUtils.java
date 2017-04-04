package net.openstandards.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * NumberUtils.java
 * 
 * Copyright @2016 OpenStandards.net
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/

public class NumberUtils {
	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
	
	public static final BigDecimal BD_ZERO = new BigDecimal("0");
	public static final BigDecimal BD_100 = new BigDecimal("100");

	public static double[] toPrimitive(Double[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}
		final double[] result = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].doubleValue();
		}
		return result;
	}

	/**
	 * Converts a String to BigDecimal. Checks for null or empty string, returning
	 * null if either is passed. 
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(String value) {
		BigDecimal result = null;
		if (value != null && !value.trim().isEmpty()) {
			try {
				result = new BigDecimal(value);
			} catch (NumberFormatException e) {
				System.err.println("Cannot convert to BigDecimal: " + value);
				
				// Do nothing... returns 0
			}
		}
		return result;
	}

	/**
	 * Safely converts a String to an int.  Returns 0 if it cannot convert cleanly. 
	 * 
	 * @param value
	 * @return
	 */
	public static int toInt(String value) {
		int result = 0;
		if (value != null) {
			try {
				result = new Integer(value).intValue();
			} catch (NumberFormatException e) {
				// Do nothing... returns 0
			}
		}
		return result;
	}

	/**
	 * Safely converts a String to an int.  Returns 0 if it cannot convert cleanly. 
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(String value) {
		long result = 0;
		if (value != null) {
			try {
				result = new Long(value).longValue();
			} catch (NumberFormatException e) {
				// Do nothing... returns 0
			}
		}
		return result;
	}
	
	/**
	 * Generates values starting with min, and incrementing by skip, until it is beyond max.  
	 * If input values are invalid, then it returns an empty array.
	 * To be valid, max must be greater than min if skip is positive, or less than if skip is negative,
	 * to prevent an endless loop.  skip cannot be 0.
	 * 
	 * @param min
	 * @param max
	 * @param skip
	 * @return
	 */
	public static double[] generateValues(Double min, Double max, Double skip) {
		double[] result = EMPTY_DOUBLE_ARRAY;
		List<Double> lInput = new ArrayList<Double>();
		// Ensure this does not result in endless loop
		if ( (max >= min && skip > 0) || (max <= min && skip < 0)) {
			for (Double cur = min; cur <= max; cur += skip) {
				lInput.add(cur);
			}
			
			result = toPrimitive( lInput.toArray(new Double[lInput.size()]) );
		}
		return result; 
	}
	
//	public static StringBuilder outMatrix(StringBuilder sb, double[][] dMatrix) throws IOException {
//		for (int row = 0; row < dMatrix.length; row++) {
//			for (int col = 0; col < dMatrix[row].length; col++)
//				sb.append( String.format("%s\t", dMatrix[row][col]) );
//			sb.append( "\n" );
//		}
//		return sb;
//	}
	
	public static Appendable outMatrix(Appendable out, double[][] dMatrix) throws IOException {
		for (int row = 0; row < dMatrix.length; row++) {
			for (int col = 0; col < dMatrix[row].length; col++)
				out.append( String.format("%s\t", dMatrix[row][col]) );
			out.append( "\n" );
		}
		return out;
//		return out.append(outMatrix(new StringBuilder(), dMatrix));
	}

	public static boolean isAscending(BigDecimal start, BigDecimal end) {
		return start.compareTo(end) < 0;
	}

	public static boolean isDescending(BigDecimal start, BigDecimal end) {
		return start.compareTo(end) > 0;
	}
	
	public static boolean isPositive(BigDecimal num) {
		return num.compareTo(NumberUtils.BD_ZERO) > 0;
	}

	public static boolean isNegative(BigDecimal num) {
		return num.compareTo(NumberUtils.BD_ZERO) < 0;
	}

}
