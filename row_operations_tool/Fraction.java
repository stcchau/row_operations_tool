/**
 * Please do not steal.
 */
package row_operations_tool;

/**
 * @author San Chau
 *
 */
public class Fraction {
	
	private int numerator, denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public int getNumerator() {
		return numerator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public Fraction add(Fraction n) {
		int lcm = getLCM(this, n);
		Fraction  sum = new Fraction(numerator * (lcm / denominator) + n.getNumerator() * (lcm / n.getDenominator()), lcm);
		sum.reduceFraction();
		return sum;
	}
	
	public Fraction multiply(Fraction n) {
		Fraction product = new Fraction(numerator * n.getNumerator(), denominator * n.getDenominator());
		product.reduceFraction();
		return product;
	}
	
	public Fraction divide(Fraction n) {
		Fraction quotient = new Fraction(numerator * n.getDenominator(), denominator * n.getNumerator());
		quotient.reduceFraction();
		return quotient;
	}
	
	public static Fraction parseFraction(int n) {
		return new Fraction(n, 1);
	}
	
	public static Fraction parseFraction(String n) {
		int num;
		int den;
		try {
			int count = 0;
			String stringNum = "";
			String stringDen = "";
			for(int i = 0; i < n.length(); i++) {
				if(Character.isDigit(n.charAt(i)) || n.substring(i, i + 1).equals("-"))
					stringNum += n.charAt(i);
				else if(n.substring(i,i + 1).equals("/")) {
					count = i + 1;
					break;
				}
				count = i + 1;
			}
			num = Integer.parseInt(stringNum);
			for(int i = count; i < n.length(); i++) {
				if(Character.isDigit(n.charAt(i)) || n.substring(i, i + 1).equals("-"))
					stringDen += n.charAt(i);
				else if(n.substring(i,i + 1).equals("/"))
					break;
			}
			den = Integer.parseInt(stringDen);
		}
		catch(Exception e) {
			num = Integer.parseInt(n);
			den = 1;
		}
		if(den < 0) {
			num = (-1) * num;
			den = (-1) * den;
		}
		return new Fraction(num, den);
	}
	
	@Override
	public String toString() {
		reduceFraction();
		if(denominator == 1)
			return Integer.toString(numerator);
		return String.format("%s/%s", numerator, denominator);
	}

	private int getLCM(Fraction n1, Fraction n2) {
		int den1 = Math.abs(n1.getDenominator());
		int den2 = Math.abs(n2.getDenominator());
		int min = Math.min(den1, den2);
		int max = Math.max(den1, den2);
		for(int i = 1; i <= max; i++) {
			if((i * min) % max == 0)
				return i * min;
		}
		return (min * max);
	}
	
	private void reduceFraction() {
		for(int i = Math.abs(numerator); i > 0; i--) {
			if(numerator % i == 0 && denominator % i == 0) {
				numerator /= i;
				denominator /= i;
				break;
			}
		}
		if(numerator == 0)
			denominator = 1;
		if(numerator < 0 && denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
	}
}
