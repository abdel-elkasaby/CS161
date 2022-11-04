import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * This program is a traffic simulation that uses Java Swing to show a 4-lane one way road
 * The user can add 3 different types of cars (semi, suv, sports) each with different sizes and speeds
 * The user can use start and stop buttons to start and stop the simulation
 * Also, the throughput is displayed and updated during the simulation
 * 
 * Author: Abdelrahman Elkasaby
 * Date: 12/17/2020
 * 
 */
public class Traffic implements ActionListener, Runnable {

	JFrame frame = new JFrame("Traffic Simulation"); //frame of the simulation
	
	//South container
	Road road = new Road();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JLabel throughput = new JLabel("Throughput: 0");
	Container south = new Container();
	
	//West container
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sports = new JButton("Add Sports");
	Container west = new Container();
	
	boolean running = false; //whether or not we are still running while loop
	int carCount = 0;
	long startTime = 0;
	
	public Traffic() {
		frame.setSize(1000, 550); //sets size of frame
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		//south
		south.setLayout(new GridLayout(1,3));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(throughput);
		frame.add(south, BorderLayout.SOUTH);
		
		//west
		west.setLayout(new GridLayout(3,1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.repaint();
	}
	public static void main(String[] args) {
		new Traffic();

	}

	public void actionPerformed(ActionEvent event) { //makes it so clicking actually does something
		if (event.getSource().equals(start)) { //cars start moving
			if (running == false) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop))	{ //stops cars
			running = false;
		}
		if (event.getSource().equals(semi)) { //adds semi
			Semi semi = new Semi(0, 30);
			road.addCar(semi);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if (road.collision(x, y, semi) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(suv)) { //adds suv
			SUV suv = new SUV(0, 30);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
					if (road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(sports)) { //adds sports car
			Sports sports = new Sports(0, 30);
			road.addCar(sports);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					sports.setX(x);
					sports.setY(y);
					if (road.collision(x, y, sports) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
	}

	public void run() { //updated during simulation (while running)
		while (running == true) {
			road.step();
			carCount = road.getCarCount();
			double throughputCalc = carCount / (1000 * (double)(System.currentTimeMillis() - startTime)); //calculates throughput and converts to seconds
			throughput.setText("Throughput: " + throughputCalc);
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
