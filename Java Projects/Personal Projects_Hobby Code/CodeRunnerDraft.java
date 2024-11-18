import java.text.*;
import java.util.*;

public class CodeRunnerDraft {

	public static void main (String Args[])
	{
		System.out.println(DaysInAMonthC(4));
	
	}
	
	
	public static String ThreeDecimalPlaces(double Number) //4a.1 PASSED
	 {

		DecimalFormat number_format = new DecimalFormat("0.000");
		String formatted_string = number_format.format(Number);
			 
		return formatted_string;
		 

	}
	
	public static short HowManyDigitsBeforeF(float Number){

		String s = String.valueOf(Number);
		String[] part = s.split("\\.");
		String part1 = part[0];
		String part2 = part[1];
		
		short value = (short) part1.length(); //Manual casting to short???
		return value;
	}
	
	public static short DaysInAMonthC(int Month){

	    short days = 0;
	    
	    switch (Month){
	    	
	    case 1:
	    	days = 30;
	    	break;
	    case 2:
	    	days = 28;
	    	break;
	    case 3:
	    	days = 31;
	    	break;
	    case 4:
	    	days = 30;
	    	break;
	    case 5:
	    	days = 31;
	    	break;
	    case 6:
	    	days = 30;
	    	break;
	    case 7:
	    	days = 31;
	    	break;
	    case 8:
	    	days = 31;
	    	break;
	    case 9:
	    	days = 30;
	    	break;
	    case 10:
	    	days = 31;
	    	break;
	    case 11:
	    	days = 30;
	    	break;
	    case 12:
	    	days = 31;
	    	break;
	    }
	    
	    if(days == 0) {
	    	
	    	NotAMonth();
	    	return -1;
	    }
	    
	    return days;
	}
	
	public static void NotAMonth() {
		
		System.out.println("Invalid month. Try again.");
	}
	
}
