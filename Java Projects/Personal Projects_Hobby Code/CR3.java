import java.util.*;

public class CR3 {
	
	static int n = 30;
	
	static ArrayList<Boolean> rep = new ArrayList<Boolean>();
	
	static ArrayList<Double> weights = new ArrayList<Double>();
	
	
	public static void main (String args[]) {
		
		ScalesFitness(rep, weights);
		generateBinaryString(n);
	}

	//question 1
	public static double ScalesFitness(ArrayList<Boolean> rep, ArrayList<Double> weights) {

		double leftscale = 0, rightscale = 0;

		if(rep == null || weights == null || rep.size() == 0 || weights.size() == 0 || rep.size() > weights.size()) {
			System.out.println("-100");
			return -100;
		}

		for(int i = 0; i < weights.size(); i++) {
			
			if(rep.get(i)) {
				
				leftscale = leftscale + weights.get(i);
			}
			else {
				
				rightscale = rightscale + weights.get(i);
			}
		}
		System.out.println(Math.abs(leftscale - rightscale));
		return Math.abs(leftscale - rightscale); 
	}


	//question 2
	public static String generateBinaryString(int n) {

		if(n <= 0) {
			
			return "";
		}
		
		StringBuilder binaryString = new StringBuilder();

		for(int i = 0; i < n; i++) {
			
			binaryString.append(Math.random() < 0.5 ? "0" : "1");
		}
		
		System.out.println("This is the result: " + binaryString.toString());
		return binaryString.toString();
	}
}
