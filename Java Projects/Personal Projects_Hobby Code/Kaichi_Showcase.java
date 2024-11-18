import java.util.*;

public class Kaichi_Showcase {

	static Random randint = new Random();

	static int n = randint.nextInt(6);

	public static void main (String Args[]) {

		System.out.println("Enter number of Dice: ");
		
		Scanner input = new Scanner(System.in);
		int numberOfDice = input.nextInt();
		
		Random Num = new Random();
		
		System.out.println("You rolled: ");
		int total = 0;
		int randomNum = 0;
		
		for(int i = 0; i < numberOfDice; i++) {
			
			randomNum = Num.nextInt(6) + 1;
			total = total + randomNum;
			System.out.println(randomNum);
			System.out.print(" ");
		}
		
		System.out.println("");
		System.out.println("Total: " + total);
		input.close();
	}
}
