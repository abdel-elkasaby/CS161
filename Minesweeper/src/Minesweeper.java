import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * This program is an implementation of Minesweeper
 * The game uses a 2D array of buttons to make a 20 X 20 grid of buttons (400 buttons total).
 * When a user clicks on a button, that button will be revealed.
 * If the button is a 0, that means that there are no adjacent mines and all adjacent 0s will also be highlighted using recursion.
 * Other buttons will have a number other than 0. This shows the number of adjacent mines to this button.
 * 30 of the 400 buttons are mines. When a user clicks on one, the rest of the buttons are revealed and the game is over.
 * If the user manages to select all of the non-mine buttons, a message will pop up saying that they won.
 * There is a reset button at the top that clears the board and lets the user play again.
 * 
 * Author: Abdelrahman Elkasaby
 * Date: 12/6/2020
 */
public class Minesweeper implements ActionListener{

	JFrame frame = new JFrame("Minesweeper"); //frame of game
	JButton reset = new JButton("Reset"); //reset button
	JButton[][] buttons = new JButton[20][20]; //button 2d array
	int[][] counts = new int[20][20]; //number on each each square
	Container grid = new Container();
	final int MINE = 10;
	
	public Minesweeper() {
		frame.setSize(1000,700); //sets size
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);		
		//button grid
		grid.setLayout(new GridLayout(20,20));
		for (int a = 0; a < buttons.length; a++) {
			for (int b = 0; b < buttons[0].length; b++) {
				buttons[a][b] = new JButton();
				buttons[a][b].addActionListener(this);
				grid.add(buttons[a][b]);
			}
		}
		frame.add(grid, BorderLayout.CENTER);
		createRandomMines();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits program on close
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Minesweeper();
	}
	
	public void createRandomMines() { //creates all the mines
		//initialize list of random pairs
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				list.add(x*100+y);
			}
		}
		//reset counts, picks 30 new mines
		counts = new int[20][20];
		for (int a = 0; a < 30; a++) {
			int choice = (int)(Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
		/*
		 * row-1,column-1		row-1,column		row-1,column+1
		 * row,column-1			row,column			row,column+1
		 * row+1,column-1		row+1,column		row+1,column+1
		 */
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				if (counts[x][y] != MINE) {
					int neighborCount = 0; //number of neighbors, displayed on each square
					if (x > 0 && y > 0 && counts[x-1][y-1] == MINE) { //up left
						neighborCount++;
					}
					if (x > 0 && counts[x-1][y] == MINE) { //up
						neighborCount++;
					}
					if (x > 0 && y < counts[0].length - 1 && counts[x-1][y+1] == MINE) {//up right
						neighborCount++;
					}
					if (y > 0 && counts[x][y-1] == MINE) {//left
						neighborCount++;
					}
					if (y < counts[0].length-1 && counts[x][y+1] == MINE) {//right
						neighborCount++;
					}
					if (x < counts.length - 1 && y > 0 && counts[x+1][y-1] == MINE) {//bottom left
						neighborCount++;
					}
					if (x < counts.length - 1 && counts[x+1][y] == MINE) {//bottom
						neighborCount++;
					}
					if (x < counts.length - 1 && y < counts[0].length - 1 && counts[x+1][y+1] == MINE) {//bottom right
						neighborCount++;
					}
					counts[x][y] = neighborCount++;
				}
			}
		}
	}
	
	public void lostGame() { //when mine is clicked, everything is revealed
		for (int x = 0; x < buttons.length; x++) {
			for (int y = 0; y < buttons[0].length; y++) {
				if (buttons[x][y].isEnabled()) {
					if (counts[x][y] != MINE) {
						buttons[x][y].setText(counts[x][y] + "");
						buttons[x][y].setEnabled(false);
					}
					else { //mines are shown as an "X"
						buttons[x][y].setText("X");
						buttons[x][y].setEnabled(false);
					}
				}
			}
		}
	}
	public void clearZeros(ArrayList<Integer> toClear) {
		if (toClear.size() == 0) {
			return;
		}
		else { //when a 0 tile is clicked, all adjacent 0s are highlighted until there are no more adjacent 0s. uses recursion
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
			if (x > 0 && y > 0 && buttons[x-1][y-1].isEnabled() && buttons[x-1][y-1].getText() != "10") { //up left
				buttons[x-1][y-1].setText(counts[x-1][y-1] + "");
				buttons[x-1][y-1].setEnabled(false);
				if (counts[x-1][y-1] == 0) {
					toClear.add((x-1) * 100 + (y-1));
				}
			}
			if (x > 0 && buttons[x-1][y].isEnabled() && buttons[x-1][y].getText() != "10") { //up
				buttons[x-1][y].setText(counts[x-1][y] + "");
				buttons[x-1][y].setEnabled(false);
				if (counts[x-1][y] == 0) {
					toClear.add((x-1) * 100 + y);
				}
			}
			if (x > 0 && y < counts[0].length - 1 && y > 0 && buttons[x-1][y+1].isEnabled() && buttons[x-1][y+1].getText() != "10") { //up right
				buttons[x-1][y+1].setText(counts[x-1][y+1] + "");
				buttons[x-1][y+1].setEnabled(false);
				if (counts[x-1][y+1] == 0) {
					toClear.add((x-1) * 100 + (y+1));
				}
			}
			if (y > 0 && buttons[x][y-1].isEnabled() && buttons[x][y-1].getText() != "10") { //left
				buttons[x][y-1].setText(counts[x][y-1] + "");
				buttons[x][y-1].setEnabled(false);
				if (counts[x][y-1] == 0) {
					toClear.add(x * 100 + (y-1));
				}
			}
			if (y < counts[0].length-1 && buttons[x][y+1].isEnabled() && buttons[x][y+1].getText() != "10") { //right
				buttons[x][y+1].setText(counts[x][y+1] + "");
				buttons[x][y+1].setEnabled(false);
				if (counts[x][y+1] == 0) {
					toClear.add(x * 100 + (y+1));
				}
			}
			if (x < counts.length - 1 && y > 0 && buttons[x+1][y-1].isEnabled() && buttons[x+1][y-1].getText() != "10") { //bottom left
				buttons[x+1][y-1].setText(counts[x+1][y-1] + "");
				buttons[x+1][y-1].setEnabled(false);
				if (counts[x+1][y-1] == 0) {
					toClear.add((x+1) * 100 + (y-1));
				}
			}
			if (x < counts[0].length - 1 && buttons[x+1][y].isEnabled() && buttons[x+1][y].getText() != "10") { //bottom
				buttons[x+1][y].setText(counts[x+1][y] + "");
				buttons[x+1][y].setEnabled(false);
				if (counts[x+1][y] == 0) {
					toClear.add((x+1) * 100 + (y));
				}
			}
			if (x < counts.length - 1 && y < counts[0].length - 1 && buttons[x+1][y+1].isEnabled() && buttons[x+1][y+1].getText() != "10") { //bottom right
				buttons[x+1][y+1].setText(counts[x+1][y+1] + "");
				buttons[x+1][y+1].setEnabled(false);
				if (counts[x+1][y+1] == 0) {
					toClear.add((x+1) * 100 + (y+1));
				}
			}
			clearZeros(toClear);
		}
	}
	
	public void checkWin() { //checks if game is won yet
		boolean won = true;
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[0].length; y++) {
				if (counts[x][y] != MINE && buttons[x][y].isEnabled() == true) {
					won = false;
				}
			}
		}
		if (won == true) { //if user wins, a panel pops up
			JOptionPane.showMessageDialog(frame, "You Win!");
		}
	}
	
	public void actionPerformed(ActionEvent event) { //makes it so clicking buttons actually does something
		if (event.getSource().equals(reset)) { //resets board
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					buttons[x][y].setEnabled(true);
					buttons[x][y].setText("");
				}
			}
			createRandomMines();
		}
		else  {
			for (int x = 0; x < buttons.length; x++) {
				for (int y = 0; y < buttons[0].length; y++) {
					if (event.getSource().equals(buttons[x][y])) {
						if (counts[x][y] == MINE) { //if mine is clicked, game over
							buttons[x][y].setText("X");
							lostGame();
						}
						else if (counts[x][y] == 0) { //if 0 is clicked, clearZeros() is called
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							ArrayList<Integer> toClear = new ArrayList<Integer>();
							toClear.add(x*100+y);
							clearZeros(toClear);
							checkWin();
						}
						else { //if any other tile is clicked, that tile becomes disabled
							buttons[x][y].setText(counts[x][y] + "");
							buttons[x][y].setEnabled(false);
							checkWin();
						}
					}
				}
			}
		}
	}

}
