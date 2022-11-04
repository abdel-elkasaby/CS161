import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Semi extends Vehicle{

	Image myimage;
	
	public Semi(int newx, int newy) {
		super(newx, newy);
		//size and speed of semi
		width = 100;
		height = 40;
		speed = 5;
		try {
			myimage = ImageIO.read(new File("Semi.png")); //takes image of car from files
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void paintMe(Graphics g) {
		g.drawImage(myimage, x, y, null);
	}
}
