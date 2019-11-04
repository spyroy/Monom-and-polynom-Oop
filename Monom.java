package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function 
{
	private double _coefficient;
	private int _power;
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp()
	{
		return _Comp;
	}
	//***CONSTRUCTORS***

	/**	
	 * Construct monom from coefficient and power
	 * @param a represents the coefficient
	 * @param b represents the power
	 */
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		if (this.get_coefficient() == 0)
			this.set_power(0);
		else
			this.set_power(b);
	}

	public Monom(Monom ot)//Build monom from another monom 
	{
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() 
	{
		return this._coefficient;
	}

	public int get_power() 
	{
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() 
	{
		if (get_power()==0 || get_coefficient()==0)
			return getNewZeroMonom();
		//else
		double coef = this._coefficient*this._power;
		int pow = get_power() - 1;
		return new Monom(coef,pow);
	}

	public double f(double x)
	{
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************
	public Monom(String s) 
	{
		if(s.isEmpty()) // Empty string is valid. ""
			this.set_coefficient(0);
		//Any Int/Double Number - ([+-]?[0-9]*\.?[0-9]*)
		else if(s.matches("([+-]?[0-9]*\\.?[0-9]*\\*?[xX](\\^[0-9]+)?)|([+-]?[0-9]*\\.?[0-9]*?)"))
		{
			s = s.replaceAll("X", "x");
			s = s.replaceAll(" ", "");

			if(!s.contains("^") && !s.contains("x"))
				// Real number [not(^) and not(x)]
			{
				this.set_coefficient(Double.parseDouble(s));
				this.set_power(0);	
			}
			else if(!s.contains("^") && s.contains("x")) 
				//its from the form a_1 * x || a_1x [not(^) and (x)]
			{
				String[] temp = s.split("[*x]");
				if(s.equals("x")) // its only "x"
					this.set_coefficient(1);
				else if(temp[0].equals("-")) 
					this.set_coefficient(-1);
				else
					this.set_coefficient(Double.parseDouble(temp[0]));					
				this.set_power(1);	
			}
			else 
			{
				String[] temp = s.split("[*x^]");
				if(temp[0].isEmpty() && !s.contains("*")) 
					this.set_coefficient(1); // its from the form x^b_1
				else if(temp[0].isEmpty() && s.contains("*")) // wrong format , *x^b_1
					throw new RuntimeException(s);
				else if(temp[0].equals("-")) 
					this.set_coefficient(-1); // its from the form -x^b_1
				else 
					this.set_coefficient(Double.parseDouble(temp[0])); // its from the form a_1x^b_1
				this.set_power(Integer.parseInt(temp[temp.length-1]));
			}
		}
		else
		{ 
			throw new RuntimeException("Wrong input From Monom class: "+s); 
		}
	}

	public void add(Monom m) 
	{
		if (m.get_power() == this._power)
			this._coefficient = m._coefficient + this._coefficient;
		else
			throw new RuntimeException("can't add two different powers of monoms");
	}

	public void multipy(Monom d) 
	{
		this._coefficient = d._coefficient * this._coefficient;
		this._power = d._power + this._power;
	}
	public boolean equals(Monom m1)
	{
		String s1 = m1.toString();
		String s2 = this.toString();
		return s1.equals(s2);
	}
	public String toString()
	{
		if (this._coefficient == 0)
			return 0 + "";
		if (this._power == 0)
			return this._coefficient + "";
		if (this._power == 1)
			return this._coefficient + "x";
		String ans = this._coefficient + "x" + "^" + this._power;
		return ans;
	}
	// you may (always) add other methods.

	// ****************** Private Methods and Data *****************

	private void set_coefficient(double a) 
	{
		this._coefficient = a;
	}

	private void set_power(int p) 
	{
		if (p < 0) 
		{
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() 
	{
		return new Monom(ZERO);
	}
}
