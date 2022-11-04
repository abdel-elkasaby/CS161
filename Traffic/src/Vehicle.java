import java.awt.Graphics;

public class Vehicle {
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;
	
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void paintMe(Graphics g) {
		
	}
	
	//getters and setters are used in this project to retrive and update the value of a variable
	
	public int getX() { //x value
		return x;
	}
	
	public void setX(int newx) { //new x value
		x = newx;
	}
	
	public int getSpeed() { //speed of car
		return speed;
	}
	
	public int getY() { //y value
		return y;
	}
	
	public void setY(int newy) { //new y value
		y = newy;
	}
	public int getWidth() { //width of car
		return width;
	}
}
