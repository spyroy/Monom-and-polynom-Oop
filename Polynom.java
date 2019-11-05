package myMath;

import java.util.*;
import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */

public class Polynom implements Polynom_able
{

	/**
	 * Zero (empty polynom)
	 */
	private static final Monom_Comperator comp=new Monom_Comperator();
	private ArrayList<Monom>list;
	public Polynom() 
	{
		list=new ArrayList<Monom>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	
	/**
	 * Constructor to build polynom from a given string
	 * @param s - represents a valid polynom
	 */
	public Polynom(String s)
	{
		try 
		{
			s = s.replaceAll("\\-", "+-"); // fix the '-' problem for a_1x^b_1-a_2x^b_2
			s = s.replaceAll(" ", "");
			String[] split = s.split("[+]| ");
			for(int i=0; i<split.length; i++)
			{
				if(!split[i].isEmpty())
				{
					Monom mon = new Monom(split[i]);
					add(mon);
				}
			}
		}
		catch(Exception E)
		{ 
			throw new RuntimeException("The string "+s+" contains illegal characters!");
		}
	}
	@Override
	public double f(double x)
	{
		double sum = 0;
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom temp = it.next();
			sum+= temp.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1)
	{
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom temp = new Monom(it.next());
			this.add(temp);
		}
	}

	@Override
	public void add(Monom m1)
	{
		if(list.size()!=0)
		{
			boolean hasNewPower = true;
			Iterator<Monom> it = list.iterator();
			while(it.hasNext())
			{
				Monom temp = it.next();
				if(m1.get_power() == temp.get_power())
				{
					if(m1.get_coefficient() + temp.get_coefficient()==0)
						it.remove(); // if its from the form 0*x then remove it from the list
					else 
						temp.add(m1);
					hasNewPower = false;
				}
			}
			if(hasNewPower && m1.get_coefficient()!=0) 
				list.add(m1);
		}
		else
			if(m1.get_coefficient() != 0) 
				list.add(m1); //if its from the form 0*x^n when n in N then no need to add zero, so it's ok for the list to remain empty
	}

	@Override
	/**
	 * Substract takes pair of monom and change the coefficient to be MINUS the contemporary coefficient
	 */
	public void substract(Polynom_able p1)
	{
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom temp = it.next();
			double newcoef = -(temp.get_coefficient());
			Monom newmonom = new Monom(newcoef,temp.get_power());
			add(newmonom);
		}
		//When the loop ends the upper polynom will be substracted by Polynom_able p1
	}

	@Override
	public void multiply(Polynom_able p1)
	{
		Polynom c = new Polynom();//This is the new object we would like to update to the list
		Iterator<Monom> input_It = p1.iteretor();
		while(input_It.hasNext())
		{
			Monom inputMonom = input_It.next();
			for(int i=0; i<list.size();i++)
			{
				Monom p1_temp_monom = new Monom(inputMonom);
				p1_temp_monom.multiply(list.get(i));
				c.add(p1_temp_monom);
			}
		}
		list.clear();
		add(c);//Finish
	}
	@Override
	public void multiply(Monom m1)
	{
		for(int i=0;i<list.size();i++)
			list.get(i).multiply(m1);
	}
	@Override
	public boolean equals(Polynom_able p1)
	{
		String s1 = p1.toString();
		String s2 = toString();
		return s1.equals(s2);
	}

	@Override
	public boolean isZero()
	{
		return list.size()==0;
	}

	@Override
	public double root(double x0, double x1, double eps)
	{
		//Infitisimaly 1
		if(f(x0)*f(x1)>0) 
		{
			throw new RuntimeException("Wrong input, f(x0)*f(x1)>0");
		}
		else
		{
			double middle = (x1 + x0) / 2; 
			while(Math.abs(x1 - x0) >= eps)
			{
				middle = (x1 + x0) / 2;
				if(f(middle)==0) return middle;
				else if(f(middle)*f(x0) < 0 ) //root lies between x0 and middle. So we recur for x0 and middle
					x1 = middle;
				else //root lies between middle and x1. So we recur for middle and x1
					x0 = middle;
			}
			return middle; 
		}
	}

	@Override
	public Polynom_able copy() 
	{
		Polynom pol = new Polynom();
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom temp = it.next();
			pol.add(temp);
		}
		return pol;
	}

	@Override
	public Polynom_able derivative() 
	{
		Polynom pol = new Polynom();
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom mon = new Monom(it.next().derivative());
			pol.add(mon);
		}
		return pol;
	}

	@Override
	public double area(double x0, double x1, double eps)
	{
		double ans=0;
		for(double i=x0;i<=x1;i+=eps)
		{
			ans+=f(i);
		}
		return ans;
	}

	@Override
	public Iterator<Monom> iteretor()
	{
		return list.iterator();	
	}
	/**
	 * This function is responsible for sorting the polynom by power
	 */
	private void sort()
	{
		list.sort(comp);	
	}
	
	public String toString() 
	{
		if(list.size() != 0)
		{
			String s = "";
			sort();
			for(int i=0; i<list.size();i++)
			{
				boolean neg = false;
				if(list.get(i).get_coefficient() < 0 )
					neg = true;
				if(!neg)
				{  
					if(i==0)
						s+= list.get(i); 
					else
						s+= "+"+list.get(i);
				}
				else //its negative
					s+= list.get(i);
			}
			return s;
		}
		else
			return "0"; 
	}
}
