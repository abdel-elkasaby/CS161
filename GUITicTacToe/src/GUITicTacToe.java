import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/* This program is a GUI interface tic tac toe game with 9 buttons representing each tile. It uses basic tic tac toe rules.
 * This program also allows features change name button which allows users to choose their name. 
 * It also indicates the number of wins both players have
 */

/*Abdelrahman Elkasaby
* Date last worked on: 10/8/2020
*/

public class GUITicTacToe implements ActionListener {

	JFrame frame = new JFrame(); // the frame of the game
	JButton[][] button = new JButton[3][3]; // an array of buttons for every spot in the game 
	int[][] board = new int [3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = X_TURN;
	Container center = new Container(); // class used to contain grid layout in border layout
	JLabel xLabel = new JLabel("X   wins: 0"); // name and number of wins X has
	JLabel oLabel = new JLabel("O   wins: 0"); // name and number of wins O has
	JButton xChangeName = new JButton("Change X's name"); // button allows x to change their name
	JButton oChangeName = new JButton("Change O's name"); // button allows o to change their name
	JTextField xChangeField = new JTextField(); // this is where user enters name they want to change to for X
	JTextField oChangeField = new JTextField(); // this is where user enters name they want to change to for O
	Container north = new Container(); // container used for north section
	String xPlayerName = "X";
	String oPlayerName = "O";
	int xWins = 0;
	int oWins = 0;
	
	public GUITicTacToe() {
		frame.setSize(400,400); // sets the size of the frame
		// center grid container
		frame.setLayout(new BorderLayout()); // layout of the game
		center.setLayout(new GridLayout(3,3)); // the center is a 3 by 3 grid for game
		
		for (int i = 0; i < button.length; i++) { // for loop that allows a button to be printed for every row
			for (int j = 0; j < button[0].length; j++) { // for loop that allows a button to be printed for every column
				button[j][i] = new JButton(j+","+i);
				center.add(button[j][i]); //adds a button with its coordinates in each spot
				button[j][i].addActionListener(this); // runs action listener for this
				
			}
		}
		frame.add(center, BorderLayout.CENTER);
		
		// north container
		north.setLayout(new GridLayout(3,2));
		north.add(xLabel);
		north.add(oLabel);
		north.add(xChangeName);
		xChangeName.addActionListener(this); // makes it so when user clicks change name button, something actually happens
		north.add(oChangeName);
		oChangeName.addActionListener(this); // makes it so when user clicks change name button, something actually happens
		north.add(xChangeField);
		north.add(oChangeField);
		frame.add(north, BorderLayout.NORTH);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the program on close
		frame.setVisible(true); // allows the frame to be visible
	}
	
	public static void main(String[] args) {
		new GUITicTacToe();
	}

	public void actionPerformed(ActionEvent event) { // makes it so clicking changes tile into X or O
		JButton current;
		boolean gridButton = false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if (event.getSource().equals(button[j][i])) {
					gridButton = true;
					current = button[j][i];
					//if spot is blank, it places an x or o depending on whos turn it is
					if (board[j][i] == BLANK) {
						if (turn == X_TURN) {
							current.setText("X");
							board[j][i] = X_MOVE;
							current.setEnabled(false);
							turn = O_TURN;
						}
						else {
							current.setText("O");
							board[j][i] = O_MOVE;
							current.setEnabled(false);
							turn = X_TURN;
						}
						//check for wins and ties and clears board 
						if (checkWin(X_MOVE)== true) {
							//X wins
							xWins++;
							xLabel.setText(xPlayerName + "  wins:" + xWins);
							clearBoard();
							resetBoard();
						}
						else if (checkWin(O_MOVE)== true) {
							//O wins
							oWins++;
							oLabel.setText(oPlayerName + "  wins:" + oWins);
							clearBoard();
							resetBoard();
						}
						else if (checkTie() == true) {
							//tie
							clearBoard();
							resetBoard();
						}
					}
				}
			}
		}
		if (gridButton == false) {// allows both users to change name
			if (event.getSource().equals(xChangeName) == true) {
				xPlayerName = xChangeField.getText();
				xLabel.setText(xPlayerName + "  wins:" + xWins);
			}
			else if (event.getSource().equals(oChangeName) == true) {
				oPlayerName = oChangeField.getText();
				oLabel.setText(oPlayerName + "  wins:" + oWins);
			}
				
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
	
	public boolean checkTie() { // checks for ties
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				if (board[row][column] == BLANK) {
					return false;
				}
			}	
		}
		return true;
	}
	
	public void clearBoard() { // clears board
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[0].length; b++) {
				board[a][b] = (BLANK);
				button[a][b].setText("");	
			}			
		}	
	}

	
	public void resetBoard() { // resets board after it is cleared
		for (int a = 0; a < board.length; a++) {
			for (int b = 0; b < board[0].length; b++) {
				//board[a][b] = (BLANK);
				button[a][b].setText(a+","+b);	
				button[a][b].setEnabled(true);
			}			
		}	
	}
}

