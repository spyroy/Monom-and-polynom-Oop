
package Ex1;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a simple "Monom" of shape a*x^b (can't be empty), where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Matan & Or
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		if (this.get_coefficient() == 0) {
			this.set_power(0);
		} else
			this.set_power(b);
	}
	
	//Default constructor
	public Monom() {
		this.set_coefficient(0);
		this.set_power(0);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	public Monom(String s) {
		s = s.toLowerCase();

		try {
			if(s.isEmpty()) {
				this.set_coefficient(0);
				this.set_power(0);
				return;
			}

			if (s.charAt(0) == '+' && s.charAt(1) == 'x')
				s = "1" + s.substring(1);

			if (s.charAt(0) == 'x')
				s = "1" + s;

			if (s.charAt(0) == '-' && s.charAt(1) == 'x')
				s = "-1" + s.substring(1);

			if (s.charAt(s.length() - 1) == 'x')
				s += "^1";

			String[] S = s.split("x\\^");

			if (!(s.contains("x"))) {
				this.set_coefficient(Double.parseDouble(S[0]));
				this.set_power(0);
				return;
			}

			if (Double.parseDouble(S[1]) < 0)
				throw new RuntimeException();

			if (S[0] == "0") {
				this.set_coefficient(0);
				this.set_power(0);
			}

			else {
				this.set_coefficient(Double.parseDouble(S[0]));
				this.set_power(Integer.parseInt(S[1]));
			}

		}

		catch (Exception error) {
			throw new RuntimeException("Wrong format of monom!");
//			System.err.println("Please insert Monom: ax^b (a,b parameters) Matan");
		}

	}

	public void add(Monom m) {
		if (m.get_power() != this._power) {
			try {
				throw new Exception("can't add two different powers of monoms");
			} catch (Exception e) {
				System.err.println("can't add two different powers of monoms");
			}
		}
		this.set_coefficient(m._coefficient + this._coefficient);
	}

	public void multipy(Monom d) {
		this.set_coefficient(d._coefficient * this._coefficient);
		this.set_power(d._power + this._power);
	}

	public String toString() {
		if (this._coefficient == 0)
			return 0 + "";
		if (this._power == 0)
			return this._coefficient + "";
		if (this._power == 1)
			return this._coefficient + "x";
		String ans = this._coefficient + "x" + "^" + this._power;
		return ans;
	}
	
	// example: F(x)=5x^3
	public function initFromString(String s) {
//		try {
//			s = s.replaceAll(" ", "");
//			if(s.charAt(0) > 90 || s.charAt(0) < 65 || s.charAt(1) != '(' || s.charAt(2) != 'x' || s.charAt(3) != ')' || s.charAt(4) != '=') {
//				throw new Exception("please enter function at the form of: \"capital\" (x) = \"Monom\" ");
//			}
//			function f = new Monom(s.substring(5));
//			return f;
//		}
//		catch (Exception e) {
//			System.err.println("please enter function at the form of: \"capital\" (x) = \"Monom\" ");
//		}
//		return null;
		Monom m = new Monom(s);
		return m;
		
	
	}
	
	public function copy() {
		Monom o = new Monom(this);
		function f = new Monom(o);
		return f;
	}
	
	public boolean equals(Object obj)
	{
		Monom_Comperator mc=new Monom_Comperator();
		if(obj instanceof Monom)
			{
				return mc.compare4((Monom)obj, this);
			}
		return false;
	}

	// ****************** Private Methods and Data *****************

	void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;
	

}

