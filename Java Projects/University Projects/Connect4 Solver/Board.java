import java.util.*;
import java.io.*;

public class Board {

	static char[][] board = 
		{
				{'.','.','.','.','.','.','Y'},
				{'.','.','.','.','.','Y','.'},
				{'.','.','.','.','Y','.','.'},
				{'.','.','.','Y','.','.','.'},
				{'.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.'}
		};

	//	static char board[][] = new char[6][7];

	//	static char board[][] = null;
	
	static char input = 'R';
	
	static char[] inputs = {'Y','R','.'};

	//static char[][] board = new char[6][7];

	public static void main (String args[]) {

		System.out.println(ValidInput(input)); //Good
		//System.out.println(ValidBoard2(board)); //Good
		//System.out.println(ValidMove(1, board)); //Good
		//System.out.println(Q4_ValidMoves(board)); //Good
		//System.out.println(WhoseMove(board)); //Good

		System.out.println(WinnerCheck(board)); //Not correct
	}

	public static boolean ValidInput(char input) { //This method answers question 1??

		boolean isValidInput = false;

		if (input == 'Y')
		{
			isValidInput = true;
		}

		if (input == 'R')
		{
			isValidInput = true;
		}

		if (input == '.')
		{
			isValidInput = true;
		}

		//System.out.println(isValidInput); //Print statement here for testing purposes
		return isValidInput;
	}

	public static boolean ValidBoard2(char board[][]) { //This method answers question 2. HECK YES

		boolean isValidBoard = false; //This is the return variable
		boolean floatingPieces = false; //This variable will be switched to true if there are floating pieces
		boolean correctSize = false; //This variable will be used to validate the size of the board
		boolean correctPieces = true; //This variable will be used to check if the board only has 'Y', 'R', or '.' 

		int redAmount = 0; //This int variable is used to count the amount of red pieces in the board
		int yelAmount = 0; //This int variable is used to count the amount of yellow pieces in the board

		int r = 0; // r is for number of rows
		int c = 0; // c is for number of columns

		int sumcol = 0;

		if(board != null && board.length == 6 && board[0].length == 7) //This line checks if the board exists and has the correct dimensions
		{

			for(r=0; r < board.length; r++) 
			{
				for(c=0; c < board[r].length; c++) 
				{
					sumcol = sumcol + 1; //I used this as a counter for all the cells of the board (6x7 = 42)

					if(sumcol == 42 && board.length == 6 && board[r].length == 7)
					{
						correctSize = true; //Boolean variable to identify when a board is valid
					}

					if(board[r][c] == 'R' || board[r][c] == 'r') //This statement looks at the board to see if there are any 'R' pieces
					{
						redAmount += 1; //Adds 1 for each 'R' in the board
					}

					if(board[r][c] == 'Y' || board[r][c] == 'y') //This statement looks at the board to see if there are any 'Y' pieces
					{
						yelAmount += 1; //Adds 1 for each 'Y' in the board
					}

					if(board[r][c] != 'Y' && board[r][c] != 'y' && board[r][c] != 'R' && board[r][c] != 'r' && board[r][c] != '.')
					{
						correctPieces = false;
					}

					if(board[r] != board[5])
					{
						if(board[r][c] != '.' && board[r + 1][c] == '.') //This statement checks if there are any '.' below an 'R' or 'Y'. It wont check if a '.' exists below the pieces at the last row
						{	
							floatingPieces = true; //Boolean variable to identify when a piece is floating
						}
					}
				}

			}
			if(floatingPieces == false && correctSize == true && correctPieces == true && (redAmount == yelAmount || redAmount == yelAmount + 1)) //This statement checks if the other boolean variables are set properly
			{
				isValidBoard = true; //Boolean variable to identify when a board is valid
			}
		}
		return isValidBoard; //Main return statement
	}


	public static boolean ValidMove(int col, char board[][]) { //This method answers question 3

		boolean isValidMove = false; //This is the return variable
		int redAmount = 0; //This int variable is used to count the amount of red pieces in the board
		int yelAmount = 0; //This int variable is used to count the amount of yellow pieces in the board

		if(ValidBoard2(board) == true && (col >= 0 && col <= 6))
		{

			for(int r=0; r < board.length; r++) //This loop will iterate through the rows 
			{
				for(int c=0; c < board[r].length; c++) //This loop will iterate through the columns (row length)
				{
					if(board[r][c] == 'R') //This statement looks at the board to see if there are any 'R' pieces
					{
						redAmount += 1; //Adds 1 for each 'R' in the board
					}

					if(board[r][c] == 'Y') //This statement looks at the board to see if there are any 'Y' pieces
					{
						yelAmount += 1; //Adds 1 for each 'Y' in the board
					}

					if(board[r][col] == '.' && redAmount + yelAmount < 6) //This statement checks if there are any '.' in a specific column
					{	
						isValidMove = true; //Boolean variable to identify which column can 
					}
				}
			}
		}
		return isValidMove; //Return statement for this method
	}

	public static ArrayList<Integer> Q4_ValidMoves(char board[][]) {

		ArrayList<Integer> availableColumnsList = new ArrayList<Integer>(); //Creating an ArrayList of type "Integer" to store the list for column numbers

		if (ValidBoard2(board) == true)
		{
			for(int playableColumns = 0; playableColumns < board[0].length; playableColumns++) //Loop that iterates through the columns
			{
				if(board[0][playableColumns] == '.') //This statement verifies if there exists a '.' in the column
				{
					availableColumnsList.add(playableColumns); //This line will add the number of the column a play can be made into
				}
			}
		}
		else if(ValidBoard2(board) == false)
		{
			availableColumnsList = null;
		}
		return availableColumnsList; //Return statement for this method
	}

	public static char WhoseMove(char[][] board) { //This method answers question 5

		char currentPiece = 0; //This variable will be used to store whose turn it is
		int redAmount = 0; //This int variable is used to count the amount of red pieces in the board
		int yelAmount = 0; //This int variable is used to count the amount of yellow pieces in the board
		int r = 0; //Rows
		int c = 0; //Columns

		for(r=0; r < board.length; r++) //This loop will iterate through the rows
		{
			for(c=0; c < board[r].length; c++) //This loop will iterate through the columns (row length)
			{
				if(board[r][c] == 'R') //This statement looks at the board to see if there are any 'R' pieces
				{
					redAmount += 1; //Adds 1 for each 'R' in the board
				}

				if(board[r][c] == 'Y') //This statement looks at the board to see if there are any 'Y' pieces
				{
					yelAmount += 1; //Adds 1 for each 'Y' in the board
				}
			}
		}

		if(redAmount == yelAmount) //This statement checks if it's Red's turn
		{
			currentPiece = 'R'; //This will return if it is Red's turn 
		}

		if(redAmount > yelAmount) //This statement checks if it's Yellow's turn
		{
			currentPiece = 'Y'; //This will return if it is Yellow's turn
		}

		if(redAmount + yelAmount == 42 || ValidBoard2(board) == false) //This statement checks if the board is valid for a play to happen (i.e. if the board is full or if the board is valid)
		{
			currentPiece = '.'; //This will return when no more plays can be made
		}
		return currentPiece; //Return statement for this method
	}

	public static Character WinnerCheck(char[][] board) { //This is my best attempt at the challenge exercise

		char winner = '.';

		int r, c; //r for rows, c for columns

		if(ValidBoard2(board) == true)
		{
			for(r = 0; r < board.length; r++) //This loop iterates through rows
			{
				for(c = 0; c < board[r].length; c++) //This loop iterates through columns (row length)
				{

					//Begin checking for 'Y'
					if(board[r][c] == 'Y') 
					{

						//Vertical 'Y' check
						if((r <= 4 && board[r + 1][c] == 'Y') || (r <= 4 && board[r + 1][c] == 'y'))
						{
							if((r <= 3 && board[r + 2][c] == 'Y') || (r <= 3 && board[r + 2][c] == 'y'))
							{
								if((r <= 2 && board[r + 3][c] == 'Y') || (r <= 2 && board[r + 3][c] == 'y'))
								{
									winner = 'Y';
								}
							}
						}
						//Horizontal 'Y' check
						if(c <= 5 && board[r][c + 1] == 'Y')
						{
							if(c <= 4 && board[r][c + 2] == 'Y')
							{
								if(c <= 3 && board[r][c + 3] == 'Y')
								{
									winner = 'Y';
								}
							}
						}
						//Diagonal up-right 'Y' check
						if((c <= 5 && r != 0) && board[r - 1][c + 1] == 'Y')
						{
							if(c <= 4 && board[r - 2][c + 2] == 'Y')
							{
								if(c <= 3 && board[r - 3][c + 3] == 'Y')
								{
									winner = 'Y';
								}
							}
						}
						//Diagonal up-left 'Y' check
						if((c > 0 && r != 0) && board[r - 1][c - 1] == 'Y')
						{
							if((c > 1 && r != 1) && board[r - 2][c - 2] == 'Y')
							{
								if((c > 2 && r != 2) && board[r - 3][c - 3] == 'Y')
								{
									winner = 'Y';
								}
							}
						}
					}

					//Begin checking for 'R'
					if(board[r][c] == 'R') 
					{

						//Vertical 'R' check
						if(r <= 4 && board[r + 1][c] == 'R')
						{
							if(r <= 3 && board[r + 2][c] == 'R')
							{
								if(r <= 2 && board[r + 3][c] == 'R')
								{
									winner = 'R';
								}
							}
						}
						//Horizontal 'R' check
						if(c <= 5 && board[r][c + 1] == 'R')
						{
							if(c <= 4 && board[r][c + 2] == 'R')
							{
								if(c <= 3 && board[r][c + 3] == 'R')
								{
									winner = 'R';
								}
							}
						}
						//Diagonal up-right 'R' check
						if((c <= 5 && r != 0) && board[r - 1][c + 1] == 'R')
						{
							if(c <= 4 && board[r - 2][c + 2] == 'R')
							{
								if(c <= 3 && board[r - 3][c + 3] == 'R')
								{
									winner = 'R';
								}
							}
						}
						//Diagonal up-left 'R' check
						if((c > 0 && r != 0) && board[r - 1][c - 1] == 'R')
						{
							if((c > 1 && r != 0) && board[r - 2][c - 2] == 'R')
							{
								if((c > 2 && r != 0) && board[r - 3][c - 3] == 'R')
								{
									winner = 'R';
								}
							}
						}
					}
				}
			}
		}
		else if(ValidBoard2(board) == false)
		{
			winner = '.';
		}
		return winner; 
	}
}
