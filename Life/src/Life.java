/*
 * This program is an implementation of Conway's Life Simulation
 * The user clicks on squares which places a "house" on them. Clicking again removes the house
 * Rules: 
 * If alive
 * 0 or 1 neighbors - dies of loneliness
 * 2 or 3 neighbors - survives to next round
 * 4 or more neighbors - dies of overcrowding
 * 
 * If dead
 * 3 neighbors - becomes populated
 * 
 * Author: Abdelrahman Elkasaby
 * Date: 10/16/2020
 */

//import packages
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Life implements MouseListener, ActionListener, Runnable {
	
	//variables and objects
	boolean[][] cells = new boolean[25][25]; //25 x 25, cells of grid
	JFrame frame = new JFrame("Life simulation"); //frame of game
	LifePanel panel = new LifePanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false; //to control while loop for "start"
	
	//constructor
	public Life() { //sets size and layout of interface and includes code that listens for mouse clicks
		frame.setSize(800,800); //sets size of frame
		frame.setLayout(new BorderLayout()); //layout of interface
		frame.add(panel, BorderLayout.CENTER); //adds center
		panel.addMouseListener(this); //"listens" for mouse clicks
		//south container
		south.setLayout(new GridLayout(1,3));
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits program on close
		frame.setVisible(true); //allows interface to be visible
	}

	public static void main(String[] args) { //main
		new Life();

	}

	//mouse clicked methods, only one I used was mouseReleased
	public void mouseClicked(MouseEvent event) {
		
		
	}


	public void mousePressed(MouseEvent event) {
		
		
	}

	
	public void mouseEntered(MouseEvent event) {
		
		
	}

	
	public void mouseExited(MouseEvent event) {
		
		
	}
	
	public void mouseReleased(MouseEvent event) {// what happens when mouse is released
		double width = (double)panel.getWidth() / cells[0].length; //width of cell
		double height = (double)panel.getHeight() / cells.length; //height of cell
		int column = Math.min(cells[0].length - 1,(int)(event.getX() / width));
		int row = Math.min(cells.length - 1, (int)(event.getY() / height));
		cells[row][column] = !cells[row][column]; //If boolean is false, it becomes true. If true, it becomes false
		frame.repaint(); //redraws everything
	}

	public void actionPerformed(ActionEvent event) { //this implemented method makes it so when the south buttons are clicked, they do what they are supposed to
		if (event.getSource().equals(step)) { //does one step which represents a generation in the simulation. This calls step method
			step();
		}
		if (event.getSource().equals(start)) {//starts simulation and keeps going until program is closed or stop is clicked
			if (running == false) {
				running = true;
					Thread t = new Thread(this);
					t.start();
			}
		}
		if (event.getSource().equals(stop)) {//stops simulation
			 running = false;
		}
	}
	public void run() { //implemented method
		while (running == true) {
			step();
			try {
				Thread.sleep(500); //500 milliseconds between steps
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * row-1,column-1		row-1,column		row-1,column+1
	 * row,column-1			row,column			row,column+1
	 * row+1,column-1		row+1,column		row+1,column+1
	 */
	public void step() {
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		//this for loop checks for neighbors
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				int neighborCount = 0;
				if (row > 0 && column > 0 && cells[row-1][column-1] == true) {//up left
					neighborCount++;
				}
				if (row > 0 && cells[row-1][column] == true) {//up
					neighborCount++;
				}
				if (row > 0 && column < cells[0].length-1 && cells[row-1][column+1] == true) {//up right
					neighborCount++;
				}
				if (column > 0 && cells[row][column-1] == true) {//left
					neighborCount++;
				}
				if (column < cells[0].length-1 && cells[row][column+1] == true) {//right
					neighborCount++;
				}
				if (row < cells.length-1 && column > 0 && cells[row+1][column-1] == true) {//bottom left
					neighborCount++;
				}
				if (row < cells.length-1 && cells[row+1][column] == true) {//bottom
					neighborCount++;
				}
				if (row < cells.length-1 && column < cells[0].length-1 && cells[row+1][column+1] == true) {//bottom right
					neighborCount++;
				}
				
				//rules of life
				if (cells[row][column] == true) {//If I am alive
					if (neighborCount == 2 || neighborCount == 3) {
						nextCells[row][column] = true; //alive next time
					}
					else {
						nextCells[row][column] = false; //dead next time
					}
				}
				else { //I'm currently dead
					if (neighborCount == 3) {
						nextCells[row][column] = true; //alive next time
					}
					else {
						nextCells[row][column] = false; //dead next time
					}
				}
			}
		}
		//redraws cells
		cells = nextCells;
		panel.setCells(nextCells);
		frame.repaint();
	}


	
}
