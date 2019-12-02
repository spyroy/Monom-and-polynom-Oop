package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;


import Ex1.Monom;
import Ex1.Monom_Comperator;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Matan & Or
 *
 */
public class Polynom implements Polynom_able {

	private ArrayList<Monom> p = new ArrayList<Monom>();
	private static final Monom_Comperator comp = new Monom_Comperator();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		Monom m = new Monom(0,0);
		//p.add(m);
		this.add(m);
	}

	public Polynom(Polynom p) {
		this.add(p);
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x",
	 * "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		if(s.isEmpty()) 
		{
			System.err.println("Matan is the man");
			return;
//			System.err.println("Please insert Monom: ax^b (a,b parameters)");
//			return ;
		}
		s = s.replaceAll("\\-", "+-"); // fix the '-' problem for a_1x^b_1-a_2x^b_2
		s = s.replaceAll(" ", "");
		String[] S = s.split("[+]| ");
		for (int i = 0; i < S.length; i++) {
			if (!S[i].isEmpty()) {
				Monom m = new Monom(S[i]);
				if(!m.isZero())
					add(m);
			}
		}
		this.sort();
	}

	@Override
	public double f(double x) {
		double answer = 0;
		Iterator<Monom> t = this.p.iterator();
		while (t.hasNext()) {
			Monom tmp = new Monom(t.next());
			answer += tmp.f(x);
		}
		return answer;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> t = p1.iteretor();
		while (t.hasNext()) {
			Monom tmp = new Monom(t.next());
			this.add(tmp);
		}

	}

	@Override
	public void add(Monom m1) {
		if (p.size() != 0) {
			boolean flag = true;
			Iterator<Monom> t = p.iterator();
			while (t.hasNext()) {
				Monom tmp = t.next();
				if (m1.get_power() == tmp.get_power()) {
					if (m1.get_coefficient() + tmp.get_coefficient() == 0)
						t.remove(); // if its from the form 0x then remove it from the list
					else
						tmp.add(m1);
					flag = false;
				}
			}
			if (flag && m1.get_coefficient() != 0)
				p.add(m1);
		} else if (m1.get_coefficient() != 0)
			p.add(m1); // if its from the form 0x^n when n in N then no need to add zero, so it's ok
		// for the list to remain empty
//		else if(m1.isZero()) {
//			p.add(new Monom(0,0));
//		}
		p.sort(comp);
//		Monom last=new Monom(p.get(p.size() - 1));
//		
//		if(last.isZero()&&p.get(0) == last)
//			p.remove(p.get(p.size() - 1));
	}

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> t = p1.iteretor();
		if (p1 == this) {
			this.p.clear();
			p.add(new Monom(0, 0));
		} else
			while (t.hasNext()) {
				Monom tmp = t.next();
				double nc = -(tmp.get_coefficient());
				Monom nm = new Monom(nc, tmp.get_power());
				add(nm);
			}
	}
	// When the loop ends the upper polynom will be substracted by given
	// Polynom_able p1

	@Override
	public void multiply(Polynom_able p1) {
		Polynom pol = new Polynom();// This is the new object we would like to update to the list
		Iterator<Monom> t = p1.iteretor();
		while (t.hasNext()) {
			Monom m = t.next();
			for (int i = 0; i < p.size(); i++) {
				Monom tmp = new Monom(m);
				tmp.multipy(p.get(i));
				pol.add(tmp);
			}
		}
		p.clear();
		add(pol);// Finished to insert the new element
		this.sort();
	}

	public boolean equals(Polynom_able p1) {
		Iterator<Monom> t = p1.iteretor();
		Iterator<Monom> t2 = p.iterator();
		Monom_Comperator comp = new Monom_Comperator();
		while (t.hasNext() && t2.hasNext()) {
			Monom m = new Monom(t.next());
			Monom m1 = new Monom(t2.next());
			if (Math.abs(m.get_coefficient() - m1.get_coefficient()) > 0
					&& Math.abs(m.get_coefficient() - m1.get_coefficient()) < 0.0000001) {
				if (m.get_power() == m1.get_power())
					return true;
			}
			if (!comp.compare4(m1, m))
				return false;
		}
		return true;
	}

	@Override
	public boolean isZero() {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).get_coefficient() != 0)
				return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// Infitisimaly 1
		// using binary search method
		// can give only one root between specific range
		try {
			if (f(x0) * f(x1) > 0) {
				throw new Exception("");
			} else {
				double mid = (x1 + x0) / 2;
				while (Math.abs(x1 - x0) >= eps) {
					mid = (x1 + x0) / 2;
					if (f(mid) == 0)
						return mid;
					else if (f(mid) * f(x0) < 0) // need to go left from middle
						x1 = mid;
					else // need to go right from middle
						x0 = mid;
				}
				return mid;
			}
		} catch (Exception error) {
			System.err.println("not a good range, the range most be: f(x0)*f(x1)>0");
		}
		return eps;
	}

	@Override
	public Polynom_able copy() {
		Polynom pol = new Polynom();
		Iterator<Monom> t = p.iterator();
		while (t.hasNext()) {
			Monom tmp = new Monom(t.next());
			pol.add(tmp);
		}
		pol.sort();
		return pol;
	}

	@Override
	public Polynom_able derivative() {
		Polynom pol = new Polynom();
		Iterator<Monom> t = p.iterator();
		while (t.hasNext()) {
			Monom m = new Monom(t.next().derivative());
			pol.add(m);
		}
		return pol;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// returns 0 if x1<x0
		if (x0 > x1) {
			double tmp = x1;
			x1 = x0;
			x0 = tmp;
		}
		double r = (x1 - x0) / eps;
		double answer = 0;
		int j = 0;
		while (j < r) {
			double summary = f(x0 + j * eps);
			if (summary > 0)
				answer += summary;
			j++;
		}
		return answer * eps;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return p.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> t = p.iterator();
		Polynom pol = new Polynom();
		while (t.hasNext()) {
			Monom tp = new Monom(t.next());
			Monom tmp = new Monom(tp.get_coefficient() * m1.get_coefficient(), tp.get_power() + m1.get_power());
			pol.add(tmp);
		}
		this.p = pol.p;
		sort();
	}

	private void sort() {
		p.sort(comp);
	}

	// example: F(x)=3x^2+5
	public function initFromString(String s) {
		//		try {
		//			s = s.replaceAll(" ", "");
		//			if (s.charAt(0) > 90 || s.charAt(0) < 65 || s.charAt(1) != '(' || s.charAt(2) != 'x' || s.charAt(3) != ')'
		//					|| s.charAt(4) != '=') {
		//				throw new Exception("please enter function at the form of: \"capital\" (x) = \"Monom\" ");
		//			}
		//			function f = new Polynom(s.substring(5));
		//			return f;
		//		} catch (Exception e) {
		//			System.err.println("please enter function at the form of: \"capital\" (x) = \"Monom\" ");
		//		}
		//		return null;
		Polynom pol = new Polynom(s);
		return pol;

	}

	public boolean equals(Object obj)
	{
		
		if(obj instanceof Polynom)
		{
			Iterator<Monom> t = ((Polynom) obj).iteretor();
			Iterator<Monom> t2 = p.iterator();
			Monom_Comperator comp = new Monom_Comperator();
			if(!t.hasNext()||!t2.hasNext())
				return false;
			while (t.hasNext() && t2.hasNext())
			{
				Monom m = new Monom(t.next());
				Monom m1 = new Monom(t2.next());
				if (!comp.compare4(m1, m))
					return false;
			}
			return true;
		}
		else if(obj instanceof Monom)
		{
			if(p.size()==1)
			{
				Monom_Comperator mc=new Monom_Comperator();
				Monom check=p.get(0);
				return mc.compare4((Monom)obj, check);
			}
		}
		else
			System.err.println(obj.toString()+" not a polynom or monom!");
		return false;
	}

	// public function copy() {return null;}

	public String toString() {
		if (p.size() != 0) {
			String s = "";
			sort();
			for (int i = 0; i < p.size(); i++) {
				boolean flag = false;
				if (p.get(i).get_coefficient() < 0)
					flag = true;
				if (!flag) {
					if (i == 0)
						s += p.get(i);
					else {
						s += "+" + p.get(i);
					}
				} else // its negative
					s += p.get(i);
			}
			return s;
		} else
			return "0";
	}
}
