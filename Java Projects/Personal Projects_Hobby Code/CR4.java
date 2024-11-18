import java.util.*;

public class CR4 {

	//question 1
	public static double OneMaxFitness(ArrayList<Integer> rep) {
		
		int counter = 0;
		
		if(rep == null || rep.size() == 0) {
			return -1;
		}
		
		for(int i = 0; i < rep.size(); i++) {
			
			if(rep.get(i) == 1) {
				
				counter++;
			}
			else if(rep.get(i) != 0) {
				
				return -2;
			}
		}
		return counter;
	}
	
}
