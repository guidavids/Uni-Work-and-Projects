
public class Correct_Maybe {
	
	public static void main (String args[]) {
		
		
		
	}
	
	public static boolean Q2_ValidBoard(char board[][]) {

     	boolean isValidBoard = false; //This is the return variable
		boolean floatingPieces = false; //This variable will be switched to true if there are floating pieces
		boolean correctSize = false; //This variable will be used to validate the size of the board
		boolean correctPieces = true; //This variable will be used to check if the board only has 'Y', 'R', or '.' 

		//int redAmount = 0; //This int variable is used to count the amount of red pieces in the board
		//int yelAmount = 0; //This int variable is used to count the amount of yellow pieces in the board

		int r = 0; // r is for number of rows
		int c = 0; // c is for number of columns

		int sumcol = 0;

		if(board != null) //This line checks if the board exists and has the correct dimensions
		{
			correctSize = true; //Boolean variable to identify when a board is valid

			for(r=0; r < board.length; r++) 
			{
				for(c=0; c < board[r].length; c++) 
				{
					sumcol = sumcol + 1; //I used this as a counter for all the cells of the board (6x7 = 42)


					//					if(board[r][c] == 'R') //This statement looks at the board to see if there are any 'R' pieces
					//					{
					//						redAmount += 1; //Adds 1 for each 'R' in the board
					//					}
					//
					//					if(board[r][c] == 'Y') //This statement looks at the board to see if there are any 'Y' pieces
					//					{
					//						yelAmount += 1; //Adds 1 for each 'Y' in the board
					//					}



					if(board[r][c] != 'Y' && board[r][c] != 'R' && board[r][c] != '.')
					{
						correctPieces = false;
					}
					//
					//									try { //Try statement to define a block of code to be tested for errors
					if(board[r][c] < board[5][c]) //This statement prevents the code form running past row number 6 (index 5 / r = 5)
					{	
						if((board[r][c] == 'Y' || board[r][c] == 'R') && board[r + 1][c] == '.') //This statement checks if there are any '.' below an 'R' or 'Y'. It wont check if a '.' exists below the pieces at the last row
						{	
							floatingPieces = true; //Boolean variable to identify when a piece is floating
						}
					}
					//									} 
					//									catch(ArrayIndexOutOfBoundsException e) //Catch statement to define a block of code to be executed, if an error occurs in the try block. ArrayIndexOutOfBoundsException in this case
					//									{
					//										correctSize = false;
					//									}
				}
				if(floatingPieces == false && correctSize == true) //This statement checks if the other boolean variables are set properly
				{
					isValidBoard = true; //Boolean variable to identify when a board is valid
				}
			}
		}
		return isValidBoard; //Main return statement
	}

}
