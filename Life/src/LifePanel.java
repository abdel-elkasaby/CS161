/*
 * Panel for Conway's Life simulation. Draws grid and cells
 * 
 * Author: Abdelrahman Elkasaby
 * Date: 10/16/2020
 */

//imported packages
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class LifePanel extends JPanel{ //allows LifePanel to do all the things JPanel does
	
	//variables used
	boolean[][] cells;
	double width;
	double height;
	
	public LifePanel(boolean[][] in) { //constructor
		cells = in;
	}
	
	public void setCells(boolean[][] newCells) {//sets new cells every generation
		cells = newCells;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells.length;
		
		//draw cells
		g.setColor(Color.BLUE);	
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if (cells[row][column] == true) {
					g.fillRect((int)Math.round(column*width),
							(int)Math.round(row*height), 
							(int)width+1, (int)height+1);
				}
			}
			
		}
		
		//draw the grid
		g.setColor(Color.BLACK); // sets color to black
		for (int x = 0; x < cells[0].length + 1; x++) { // adds vertical lines
			g.drawLine((int)Math.round(x*width),0,(int)Math.round(x*width),this.getHeight());
		}
		for (int y = 0; y < cells.length + 1; y++) { // adds horizontal lines
			g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
			
		}
		
	}
	
}
