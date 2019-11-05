package myMath;

import java.util.Comparator;

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
