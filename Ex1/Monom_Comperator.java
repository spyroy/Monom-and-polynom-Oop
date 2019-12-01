package Ex1;

import java.util.Comparator;

/** This implements stands to compare two monoms 
 * 1) compare is the function that returns 0 if both coefficient and power are equal,
 *  positive number o2 is bigger, and negative number else
 * 2) compare2 is the function that returns 0 if the coefficient is equal, positive number
 *  if o2 coefficient is bigger, negative number else
 * 3) compare3 is the function that returns 0 if the power is equal, positive number
 *  if o2 power is bigger, negative number else
 * 4) compare4 is the function that returns true if both coefficient and power are equal
 *  false else.
**/

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {;}
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		if(dp == 0)
			dp = (Double.compare(o1.get_coefficient(), o2.get_coefficient()));
		return dp;
	}

	// ******** add your code below *********
	
	public Double compare2(Monom o1, Monom o2) {
		Double dc = o2.get_coefficient() - o1.get_coefficient();
		return dc;
	}
	
	public int compare3(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}
	
	public boolean compare4 (Monom m1 , Monom m2) {
		if (compare(m1,m2) == 0 && compare2(m1,m2) == 0) {
			return true;
		}
		return false;
	}

	
	
	
	

}
