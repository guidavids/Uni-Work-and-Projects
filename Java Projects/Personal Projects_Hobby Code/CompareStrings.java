
public class CompareStrings {
	
	public static void main(String args[])
	{
	//create string array with four strings
	String sArr[] = {"ABC","abc","DEF","def"};
	
	for(int i = 0; i < sArr.length; i++)
	{
		for(int j = 0; j < sArr.length; j++)
		{
			//compare each string - call method
			DoCompareString(sArr[i], sArr[j]);
		}
	}
	}
	//user defined method
	private static void DoCompareString(String x, String y)
	{
		//compareTo method is case sensitive
		//see what happens using compareToIgnoreCase()
		if (x.compareTo(y) == 0)
		{
			System.out.println(x + " equal to " + y);
		}
		if (x.compareTo(y) < 0)
		{
			System.out.println(x + " less than " + y);
		}
		if (x.compareTo(y) > 0)
		{
			System.out.println(x + " greater than " + y);
		}
	}
}
