/* This program is a 2-player tic tac toe game that uses a two-dimensional array to store moves and display the board.
 * The program will tell the user whose turn it is so they can enter coordinates to the tile they want to play on.
 * Once the round ends (Someone wins or they tie), a point will be added to the winner.
 * The users will be asked if they want to play again.
 * If yes, the board will reset. If no, the scores will be displayed and the program will end.
 */

/* Code by Abdelrahman Elkasaby
 * Date last worked on: 9/30
 */

import java.util.Scanner;

public class TicTacToe {
	
	// This two dimensional array stores moves and displays the board
	int[][] board = new int [3][3];
	
	// Constants used in this program
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	
	// Some variables used in this program
	int turn = X_TURN;
	int X_points = 0;
	int O_points = 0;
	Scanner scanner;
	String input = "";
	String playAgain = "";
	
	/* This method allows the users to enter a move by entering a letter than a number. 
	 * After the round is finished, the users will be asked if they want to play again.
	 */
	public TicTacToe() {
		scanner = new Scanner(System.in);
		boolean stillPlaying = true;
		
		// This while loop keeps going until the players finish playing as many rounds as they want and then it reveals the scores.
		while (stillPlaying) {
			printBoard();
			/* This while loop keeps going until someone wins a round, or they tie. 
			 * It takes input from user and stores it if it is a valid input. 
			 * If not valid, it prompts the user to enter a valid number
			 */
			while (checkWin(X_MOVE) == false && checkWin(O_MOVE) == false && checkTie() == false) {
					//Prints out whose turn it is
					if (turn == X_TURN) {	
						System.out.print("\nX Turn: ");
					}
					else {	
						System.out.print("\nO Turn: ");
					}
					//Scans input
					input = scanner.nextLine();
					System.out.println("\n");
					if (input.length() != 2) {
						System.out.println("Enter a leter followed by a number");
					}
					else if (input.charAt(0) != 'a' &&
							 input.charAt(0) != 'b' &&
							 input.charAt(0) != 'c') {
						System.out.println("Row must be an a, b, or c.");
					}
					else if (input.charAt(1) != '1' &&
							 input.charAt(1) != '2' &&
							 input.charAt(1) != '3') {
						System.out.println("Column must be a 1, 2, or 3.");
					}				
					else {//If the input is valid, it goes through here and allows the users to actually play by entering the coordinates
						int row = input.charAt(0) - 'a';
						int column = input.charAt(1) - '1';
						if(board[row][column] == BLANK) {
							if (turn == X_TURN) {	
								board[row][column] = X_MOVE;
								turn = O_TURN;
							}
							else if (turn == O_TURN) {	
								board[row][column] = O_MOVE;
								turn = X_TURN;
							}
						}
						else {
							System.out.println("That spot is taken! ");
						}
					}
					// Prints the board at the end so users can see final play
					printBoard();
				}
				//prints the winner and adds points to them
				if (checkWin(X_MOVE) == true) {
					System.out.println("\nX wins!");
					X_points ++;					
				}
				else if (checkWin(O_MOVE) == true) {
					System.out.println("\nO wins!");
					O_points ++;					
				}
				//asks the user if they want to play again. If not it will print out the scores and end the game.
				System.out.print("Would you like to play again? (y or n): ");
				String playAgain = scanner.nextLine();
				System.out.println("\n");
				if ( playAgain.equals("n")) {
					stillPlaying = false;
					System.out.println("X won " + X_points + " times.");
					System.out.println("O won " + O_points + " times.");
					scanner.close();
					System.exit(0);
					break;
				}
				else { //if users decide to play again it will print a new board
					stillPlaying = true;
					board = new int [3][3];
				}
			}
	}

	public static void main(String[] args) {
		new TicTacToe();
	}
	
	public void printBoard() { // This method prints the board with coordinates that users can use to enter a move
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++) {
			String output = (char)('a' + row) + "\t";
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					output += " \t";
				}
				else if (board[row][column] == X_MOVE) {
					output += "X\t";
				}
				else if (board[row][column] == O_MOVE) {
					output += "O\t";
				}
			}
			System.out.println(output);
		}
	}
	
	public boolean checkWin(int player) {// This method checks each row, column, and diagonal to check if a player has gotten 3 in a row
		//top row
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {
			return true;
		}
		// middle row
		if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {
			return true;
		}
		//bottom row
		if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {
			return true;
		}
		//left column
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {
			return true;
		}
		//middle column
		if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
			return true;
		}
		//right column
		if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {
			return true;
		}
		//Diagonal (top left to bottom right)
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		//Diagonal (bottom left to top right)
		if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
			return true;
		}
		return false;
	}
	// This method checks to see if the players tied or not
	public boolean checkTie() {
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					return false;
				}
			}	
		}
		System.out.println("Tie!");
		return true;
	}
	
}
