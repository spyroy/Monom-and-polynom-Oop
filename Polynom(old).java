package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {

	private ArrayList<Monom> p = new ArrayList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.add(new Monom(0, 0));
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x",
	 * "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		try {
			s = s.replaceAll(" ", "");
			s = s.replaceAll("\\+", " +");
			s = s.replaceAll("\\-", " -");

			if (s.charAt(0) == ' ')
				s = s.substring(1);

			String[] S = s.split(" ");

			for (int i = 0; i < S.length; i++) {
				String tmp = "";

				if (S[i].length() == 1) {

					if (S[i].charAt(0) == '+' || S[i].charAt(0) == '-') {
						i++;
					}

				}

				tmp = S[i];

				if (tmp.charAt(0) == 'x')
					tmp = "1" + tmp;

				if (tmp.charAt(0) == '+' && tmp.charAt(1) == 'x')
					tmp = "1" + tmp.substring(1);

				if (tmp.charAt(0) == '-' && tmp.charAt(1) == 'x')
					tmp = "-1" + tmp.substring(1);

				if (tmp.charAt(tmp.length() - 1) == 'x')
					tmp += "^1";

				if (!(tmp.contains("x"))) {
					double a = Double.parseDouble(tmp);
					int b = 0;
					this.p.add(new Monom(a, b));
					return;
				}

				String[] m = tmp.split("x\\^");
				double a = 0;
				int b = 0;

				a = Double.parseDouble(m[0]);
				b = Integer.parseInt(m[1]);

				this.p.add(new Monom(a, b));
			}
		}

		catch (Exception error) {
			System.err.println("Please insert polyonym: ax^b (a,b parameters)");
		}

	}

	@Override
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> iter = this.p.iterator();
		while (iter.hasNext()) {
			Monom tmp = new Monom(iter.next());
			ans += tmp.f(x);
		}
		return ans;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext()) {
			p.add(new Monom(iter.next()));
		}

	}

	@Override
	// not working  if p.cofficient = 1/0.
	public void add(Monom m1) {
		try {

			int i = 0;
			boolean flag = false;
			Iterator<Monom> iter = p.iterator();
			double a = 0;
			int b = 0;
			Monom o = new Monom (0,0);
			while (iter.hasNext()) {
				o = new Monom (iter.next());
				if (o.get_power() == m1.get_power()) {
					//if(iter.next().get_power() == 1)
					
					a = o.get_coefficient() + m1.get_coefficient();
					b = m1.get_power();
					Monom m2 = new Monom(a, b);
					p.set(i, m2);
					flag = true;
				}
				i++;
			}

			if (!flag)
				p.add(m1);
		}

		catch (Exception error) {
			System.err.println("please add Monom: ax^b (a,b parameters)");
		}

	}

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext()) {
			Monom tmp = new Monom(iter.next().get_coefficient()*-1, iter.next().get_power() );
			p.add(tmp);
		}

	}

	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		Iterator<Monom> iter2 = p.iterator();
		Polynom New = new Polynom();
		while(iter2.hasNext()) {
			while(iter.hasNext()) {
				double a = iter2.next().get_coefficient()*iter.next().get_coefficient();
				int b = iter2.next().get_power() + iter.next().get_power();
				Monom tmp = new Monom(a,b);
				New.add(tmp);
			}
		}
		this.p = New.p;
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		Iterator<Monom> iter2 = p.iterator();
		Monom_Comperator comp = new Monom_Comperator();
		while (iter.hasNext() && iter2.hasNext())
		{
			
			if (comp.compare(iter.next(), iter2.next())!=0)
				return false;
		}
		return true;
	}

	@Override
	public boolean isZero() {
		for(int i = 0; i < p.size(); i++) {
			if(p.get(i).get_coefficient() !=0)
				return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return p.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
				if (isZero() == true)
					return "0";

				String forPrint = "";
				Iterator<Monom> it = p.iterator();
				boolean flagNotFirst = false;
				//first monom in polynom
				if (it.hasNext()) {		
					Monom mPrintFirst = new Monom(it.next());
					if (mPrintFirst.get_coefficient() == 0)
						forPrint+="";
					else {
						flagNotFirst = true;
						forPrint+= mPrintFirst;
					}
				}

				while (it.hasNext()) {

					Monom mPrint = new Monom (it.next());
					if (mPrint.get_coefficient() == 0)
						continue;

					if (mPrint.get_coefficient() > 0)
					{
						if (flagNotFirst)	
							forPrint+=" + ";
						else
							flagNotFirst = true;	//not first anymore
					}

					else
					{
						mPrint.set_coefficient(-mPrint.get_coefficient());
						if (flagNotFirst)
							forPrint+=" - ";
						else
						{
							forPrint+="-";
							flagNotFirst = true;	//not first anymore
						}	
					}

					forPrint+=mPrint.toString();

				}
				return forPrint;
	}

	public static void main(String[] args) {
		Polynom m = new Polynom("0");
		m.isZero();
		Polynom m2 = new Polynom("6x^5+x^4+2x");
		//m.multiply(m2);
		String s = m.toString();
		double fx = m.f(2);
		Monom m1 = new Monom(2, 1);
		m2.add(m1);
		s = m.toString();
		//Polynom_able p1 = null;
		//m.substract(p1);
		m = new Polynom(s);
		System.out.println(2 + ") " + m2 + "    \tisZero: " + m.isZero() + "\t f(" + 2 + ") = " + fx);
	}

}
