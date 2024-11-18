public class Palindrome_Exercise_Tutorial {


	//palindrome number, a program that takes an integer and returns a boolean value

	public static void main (String args[]) {//Main function

		System.out.println(IsPalindrome(45654));
		System.out.println(IsPalindrome(451254));

	}

	public static boolean IsPalindrome (int Palindrome) { //Palindrome function set to return a boolean value

		int originalNum = Palindrome;
		int reverse = 0;

		while(Palindrome != 0) {

			int remainder = Palindrome % 10;
			reverse = reverse * 10 + remainder;
			Palindrome = Palindrome / 10;
		}

		return originalNum == reverse;
	}
}



