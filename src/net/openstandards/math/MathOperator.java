package net.openstandards.math;

/**
 * This works around the limitation of BigDecimal and Double not descending from a common math class
 * despite having many of the same operations.  This creates a common interface that can be used 
 * to provide code that supports both. 
 * 
 * @author erik
 *
 * @param <T>
 */
public interface MathOperator<T> {
    T newInstance(int value);
    
    T zero(); // ZERO
    
    T add(T first, T second); // Adding two items
    
    /**
     * Subtracts the second from the first (A - B).
     */
    T subtract(T first, T second); // Subtract second from first (A - B)
    T multiply(T first, T second); // Subtract second from first (A - B)
    
    T divide(T numerator, int value); // Divide by an int
    T divide(T numerator, T denominator); // Divide by an int
    
    T scale(T value);  // Will reduce a number to its scale if one is defined
    
}