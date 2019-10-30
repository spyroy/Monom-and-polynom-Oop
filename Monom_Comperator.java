package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {;}
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}

	// ******** add your code below *********
	private double cofficient;
	private int power;
	
	
	public double compare2(Monom o1, Monom o2) {
		double dc = o2.get_coefficient() - o1.get_coefficient();
		return dc;
	}
	
	public boolean equals (Monom m1) {
		return true;
	}
	
	
	

}
