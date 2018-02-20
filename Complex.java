package eecs2030.lab3;

import java.util.Arrays;
import java.util.List;

/**
 * A class that represents immutable complex numbers.
 * 
 * @author EECS2030 Fall 2017-18
 * 
 */
public final class Complex {

	private final double imaginary;
	private final double real;

	static Complex I = new Complex(0, 1);
	static Complex ONE = new Complex(1, 0);

	/**
	 * Initializes this complex number to <code>0 + 0i</code>.
	 * 
	 */
	public Complex() {
		this(0, 0);

	}

	/**
	 * Initializes this complex number so that it has the same real and
	 * imaginary parts as another complex number.
	 * 
	 * @param other
	 *            the complex number to copy.
	 */
	public Complex(Complex other) {
		this(other.real, other.imaginary);

	}

	/**
	 * Initializes this complex number so that it has the given real and
	 * imaginary components.
	 * 
	 * @param re
	 *            the real part of the complex number.
	 * @param im
	 *            the imaginary part of the complex number.
	 */
	public Complex(double re, double im) {
		real = re;
		imaginary = im;

	}

	/**
	 * A static factory method that returns a new complex number whose real part
	 * is equal to re and whose imaginary part is equal to 0.0
	 * 
	 * @param re
	 *            the desired real part of the complex number
	 * @return a new complex number whose real part is equal to re and whose
	 *         imaginary part is equal to 0.0
	 */
	public static Complex real(double re) {
		Complex c = new Complex(re, 0);

		return c;
	}

	/**
	 * A static factory method that returns a new complex number whose real part
	 * is equal to 0.0 and whose imaginary part is equal to im
	 * 
	 * @param im
	 *            the desired imaginary part of the complex number
	 * @return a new complex number whose real part is equal to 0.0 and whose
	 *         imaginary part is equal to im
	 */
	public static Complex imag(double im) {
		Complex c = new Complex(0, im);

		return c;
	}

	/**
	 * Get the real part of the complex number.
	 * 
	 * @return the real part of the complex number.
	 */
	public double re() {

		return this.real;
	}

	/**
	 * Get the imaginary part of the complex number.
	 * 
	 * @return the imaginary part of the complex number.
	 */
	public double im() {

		return this.imaginary;
	}

	/**
	 * Add this complex number and another complex number to obtain a new
	 * complex number. Neither this complex number nor c is changed by this
	 * method.
	 * 
	 * @param c
	 *            The complex number to add to this complex number.
	 * @return a new Complex number equal to the sum of this complex number and
	 *         c.
	 */
	public Complex add(Complex c) {
		Complex newc = new Complex(c.real + this.real, c.imaginary + this.imaginary);

		return newc;
	}

	/**
	 * Multiply this complex number with another complex number to obtain a new
	 * complex number. Neither this complex number nor c is changed by this
	 * method.
	 * 
	 * <p>
	 * This is not an industrial strength implementation of complex number
	 * multiplication. In particular, issues related to the differences between
	 * <code>-0.0</code> and <code>0.0</code> and infinite real or imaginary
	 * parts are not taken into account.
	 * 
	 * @param c
	 *            The complex number to multiply by.
	 * @return a new Complex number equal to this complex number multiplied by
	 *         c.
	 */
	public Complex multiply(Complex c) {
		Complex newc = new Complex(this.real * c.real - this.imaginary * c.imaginary,
				this.imaginary * c.real + this.real * c.imaginary);

		return newc;
	}

	/**
	 * Compute the magnitude of this complex number.
	 * 
	 * @return the magnitude of this complex number.
	 */
	public double mag() {

		return Math.hypot(this.real, this.imaginary);
	}

	/**
	 * Return a hash code for this complex number.
	 * 
	 * <p>
	 * This implementation uses a very crude algorithm to compute the hash code;
	 * the hash code is computed as follows:
	 * 
	 * <ol>
	 * <li>compute the value equal to <code>9999</code> times the real part of
	 * this complex number
	 * <li>compute the value equal to <code>99</code> times the imaginary part
	 * of this complex number
	 * <li>compute the sum of the values computed in Steps 1 and 2
	 * <li>casts the value computed in Step 3 to an <code>int</code>
	 * <li>returns the value computed in Step 4
	 * </ol>
	 * 
	 * <p>
	 * In production code, consider implementing hashCode() using
	 * {@link java.util.Objects#hash}
	 * 
	 * @return a hash code value for this complex number.
	 */
	@Override
	public int hashCode() {
		double val1 = 9999 * this.real;
		double val2 = 99 * this.imaginary;

		return (int) (val1 + val2);
	}

	/**
	 * Compares this complex number with the specified object. The result is
	 * <code>true</code> if and only if the argument is a <code>Complex</code>
	 * number with the same real and imaginary parts as this complex number.
	 * 
	 * @param obj
	 *            the object to compare this Complex number against.
	 * @return true if the given object is a Complex number equal to this
	 *         complex number, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj != null && obj instanceof Complex) {
			Complex c = (Complex) (obj);
			if (this.real == c.real && this.imaginary == c.imaginary) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Returns a string representation of this complex number.
	 * 
	 * <p>
	 * The returned string is the real part of the complex number, followed by a
	 * space, followed by a <code>+</code> or <code>-</code> sign, followed by a
	 * space, followed by the absolute value of the imaginary part of the
	 * complex number, followed by an <code>i</code>. The sign is <code>+</code>
	 * if the imaginary part of the complex number is positive, and
	 * <code>-</code> if the imaginary part of the complex number is negative.
	 * 
	 * For example the complex number with real and imaginary parts equal to
	 * zero has the string representation <code>0.0 + 0.0i</code>. The complex
	 * number with real part equal to zero and imaginary part equal to
	 * <code>-1</code> has the string representation <code>0.0 - 1.0i</code>.
	 * 
	 * @return a string representation of this complex number.
	 * 
	 */
	@Override
	public String toString() {
		String sign = "+";
		if (Math.abs(this.imaginary) / this.imaginary == -1) {
			sign = "-";
		}

		return this.real + " " + sign + " " + Math.abs(this.imaginary) + "i";
	}

	/**
	 * Returns a complex number holding the value represented by the given
	 * string.
	 * 
	 * <p>
	 * <code>valueOf</code> tries to create a complex number from a string
	 * representation of the complex number. Strings that can interpreted as
	 * complex numbers are those strings returned by
	 * <code>Complex.toString</code>.
	 * 
	 * @param s
	 *            a string representation of a complex number.
	 * @return a Complex number equal to the complex number represented by the
	 *         given string.
	 * @throws IllegalArgumentException
	 *             if the string cannot be interpreted as a complex
	 *             number. @pre. s has a space before and after the + or - sign
	 *             separating the real and imaginary parts of the complex number
	 */
	public static Complex valueOf(String s) {
		Complex result = null;
		String t = s.trim();
		List<String> parts = Arrays.asList(t.split("\\s+"));

		if (parts.size() != 3) {
			throw new IllegalArgumentException("not a complex number");
		} else if (!parts.get(1).equals("+") && !parts.get(1).equals("-")) {
			throw new IllegalArgumentException("not a complex number");
		} else if (!parts.get(2).contains("i")) {
			throw new IllegalArgumentException("not a complex number");
		}

		double r = Double.valueOf(parts.get(0));
		double sign = 1;
		if (parts.get(1).equals("-")) {
			sign = -1;
		}

		String str = parts.get(2).substring(0, parts.get(2).length() - 1);

		double i = Double.valueOf(str);

		result = new Complex(r, sign * i);

		// split splits the string s by looking for spaces in s.
		// If s is a string that might be interpreted as a complex number
		// then parts will be a list having 3 elements. The first
		// element will be a real number, the second element will be
		// a plus or minus sign, and the third element will be a real
		// number followed immediately by an i.
		//
		// To complete the implementation of this method you need
		// to do the following:
		//
		// -check if parts has 3 elements
		// -check if the second element of parts is "+" or "-"
		// -check if the third element of parts ends with an "i"
		// -if any of the 3 checks are false then s isn't a complex number
		// and you should throw an exception
		// -if all of the 3 checks are true then s might a complex number
		// -try to convert the first element of parts to a double value
		// (use Double.valueOf); this might fail in which case s isn't
		// a complex number
		// -remove the 'i' from the third element of parts and try
		// to convert the resulting string to a double value
		// (use Double.valueOf); this might fail in which case s isn't
		// a complex number
		// -you now have real and imaginary parts of the complex number
		// but you still have to account for the "+" or "-" which
		// is stored as the second element of parts
		// -once you account for the sign, you can return the correct
		// complex number

		return result;
	}

}
