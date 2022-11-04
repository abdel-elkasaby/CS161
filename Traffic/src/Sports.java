import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Sports extends Vehicle{

	Image myimage;
	
	public Sports(int newx, int newy) {
		super(newx, newy);
		//size and speed of sports
		width = 65;
		height = 20;
		speed = 12;
		try {
			myimage = ImageIO.read(new File("Sports.png")); //takes image of car from files
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public void paintMe(Graphics g) {
		g.drawImage(myimage, x, y, null);
	}
}
