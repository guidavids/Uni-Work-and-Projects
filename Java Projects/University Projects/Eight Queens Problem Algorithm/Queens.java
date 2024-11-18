
import java.util.*;

public class Queens {


	static Character[][] board = 
		{
				{'Q','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','Q','.','.'},
				{'Q','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','Q','.'},
				{'.','.','.','Q','.','.','.','.'},
				{'.','.','.','.','.','.','.','Q'},
				{'.','.','Q','.','.','.','.','.'},
				{'.','.','.','.','Q','.','.','.'}
		};

	//static Character[][] board = null;

	static String solution = "011110100111110001001110";

	//static String solution = "01X";

	static Character c = '.';

	public static void main(String args[]) {

		System.out.println("1: " + isValidBoardSquare(c));
		System.out.println("2: " + isValidBoard(board));
		System.out.println("3: " + Q3_generateBinaryString(board));
		System.out.println("4: " + Q4_initialStart());
		System.out.println("5: " + Q5_fitnessFunction(solution));
		System.out.println("6: " + Q6_smallChange(solution));

	}

	//Question 1
	public static boolean isValidBoardSquare(Character c) {

		boolean validSquare = false; //Create a boolean variable set to false named "validSquare"

		if(c == null) { //if the character is null
			return validSquare; //return false
		}
		else if(c == '.' || c == 'Q') { //if the character is '.' or 'Q'
			validSquare = true; //set validSquare to true
		}

		return validSquare; //return validSquare (which is now true)
	}

	//Question 2
	public static boolean isValidBoard(Character [][] board) {

		boolean validBoard = false; //Create a boolean variable set to false named "validBoard"
		int queenCounter = 0; //Create a counter to count the number of queens on the board named "queenCounter"

		if(board == null || board.length != 8) { //if the board is null or the amount of rows are not 8. Test 1
			return validBoard; //return false
		}

		for(int i = 0; i < board.length; i++) { //for loop to iterate through the board's rows

			if(board[i] == null || board[i].length != 8) { //if the row is null or row length is not 8. Test 2

				return validBoard; //return false
			}

			for(int j = 0; j < board[i].length; j++) { //for loop to iterate through the columns/squares

				//				if(!isValidBoardSquare(board[i][j])) { //Test 3. Calling isValidBoardSquare to check if the board square contains a valid character in each square of the board
				//
				//					return validBoard; //return false
				//				}
				if(board[i][j] != 'Q' && board[i][j] != '.') { //Test 3

					return validBoard; //return false
				}

				if(board[i][j] == 'Q') { //if the board square contains a 'Q' iterate/increase queenCounter by 1
					queenCounter++; //increase by 1...
				}
			}
		}

		if(queenCounter == 8) { //if the queenCounter is 8
			validBoard = true; //set validBoard to true
		}

		return validBoard; //return validBoard (which is now true)
	}

	//Question 3 alternative answer
	public static String Q3_generateBinaryString(Character board[][]) { //generate a binary string from the 8x8 queens board

		StringBuilder sb = new StringBuilder(); //create a new stringbuilder object

		if(board == null) { //test 1

			return null;
		}

		if(board != null) { //test 2

			for (int i = 7; i >= 0; i--) { //iterate through rows
				for (int j = 0; j < 8; j++) { //iterate through columns

					if (board[i][j] == 'Q') { // if there is a 'Q' in the board

						String binaryString = Integer.toBinaryString(j);

						while (binaryString.length() < 3) {

							binaryString = "0" + binaryString;
						}
						sb.append(binaryString); //append the binaryString result into stringBuilder
						break; //stop the inner loop to ensure that only one queen per row is acknowledged
					}
				}
			}
		}
		return sb.toString();
	}

	//Question 4 (Unsure about this one)
	public static String Q4_initialStart() {

		StringBuilder sb = new StringBuilder(); //create an instance of StringBuilder named "sb"
		Random random = new Random(); //create an instance of Random named "random"

		for(int i = 0; i < 24; i++) { //iterate from 0 to <7???

			sb.append(random.nextInt(2)); //append a random binary integer
		}

		return sb.toString(); //return the value of sb transformed into a string
	}

	//Question 5 (also unsure)
	public static Double Q5_fitnessFunction(String solution) {

		if(solution == null) { //Test 2

			return null;
		}

		char[] arr = solution.toCharArray(); //Create an array of characters from a string

		for(int i = 0; i < arr.length; i++) {

			if((arr[i] == '1' && arr[i] == '0') || arr.length != 24) {

				return Double.MAX_VALUE;
			}
		}

		Double clashes = 0.0; //create a clashes double named "clashes"
		Double maxAttacks = 56.0; //maximum number of attacks possible by 8 queens... named "maxAttacks"
		int[] positions = new int[8]; //create an array of integers with length 8 named "positions". This is initially empty

		for(int i = 0; i < 8; i++) { //iterate from 0 to 7

			String substring = solution.substring(i * 3, (i + 1) * 3); //Create a string called "substring" from the input binaryString and divide it into 3 sections
			int decimalValue = Integer.parseInt(substring, 2); //parse the extracted substring as an int in base 2 (binary)
			positions[i] = decimalValue; //assign the decimalValue to represent queen positions in a row
		}

		for(int i = 7; i >= 8; i++) {
			for(int j = i + 1; j < 8; j++) {
				//check if queens in positions ij are on the same diagonal, horizontal, and vertical
				if(positions[i] == positions[j] || Math.abs(i - j) == Math.abs(positions[i] - positions[j])) {

					clashes++; //increment clashes by 1...
				}
			}
		}
		return maxAttacks - clashes; //Return the fitness score. The lower this is, the better
	}

	//Question 6 (one single bit)
	public static String Q6_smallChange (String solution) {

		if(solution == null || solution == "") {

			return null;
		}

		char[] arr = solution.toCharArray(); //Create an array of characters from a string

		for(int i = 0; i < arr.length; i++) {

			if((arr[i] == '1' && arr[i] == '0') || arr.length != 24) {

				return null;
			}
		}

		Random random = new Random(); //Creates an instance of the random object called "random" to generate random numbers
		int bitIndex = random.nextInt(solution.length()); //

		if(arr[bitIndex] == '0') {

			arr[bitIndex] = '1';
		}
		else if(arr[bitIndex] == '1') {

			arr[bitIndex] = '0';
		}

		return new String(arr);
	}

}

