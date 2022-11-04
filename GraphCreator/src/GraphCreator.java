import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * 
 * This program is a graph creator with a GUI interface that allows users to place labeled nodes on a graph and edges between them.
 * The user is able to check if nodes are connected by using the entering the labels of the nodes and then clicking "Test Connected"
 * The "Shortest Path" button is an implementation of the traveling salesman problem which searches for the cheapest path to go to all of the nodes.
 * The cost of traveling is the label of the edges.
 * 
 * Work Cited:
 * https://www.geeksforgeeks.org/min-and-max-in-a-list-in-java/
 * To learn how to get the minimum value of an ArrayList.
 * 
 * Author: Abdelrahman Elkasaby
 * Date: 1/10/21
 */

public class GraphCreator implements ActionListener, MouseListener{
	
	//variables and objects
	JFrame frame = new JFrame(); //frame of the program
	GraphPanel panel = new GraphPanel();
	JButton nodeButton = new JButton("Node"); 
	JButton edgeButton = new JButton("Edge");
	JTextField labels = new JTextField("A");
	JTextField firstNode = new JTextField("First");
	JTextField secondNode = new JTextField("Second");
	JButton connectedButton = new JButton("Test Connected");
	JTextField salesmanStartTF = new JTextField("A");
	JButton salesmanButton = new JButton("Shortest Path");
	Container west = new Container(); //container for node and edge buttons
	Container east = new Container(); //container for testing connections
	Container south = new Container(); //traveling salesman stuff
	final int NODE_CREATE = 0;
	final int EDGE_FIRST = 1;
	final int EDGE_SECOND = 2;
	int state = NODE_CREATE;
	Node first = null;
	ArrayList<ArrayList<Node>> completed = new ArrayList<ArrayList<Node>>();
	ArrayList<Integer> costs = new ArrayList<Integer>();
	boolean skipPath = false;
	public GraphCreator() {
		frame.setSize(800, 600); //sets size of frame
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		//west container
		west.setLayout(new GridLayout(3,1));
		west.add(nodeButton);
		nodeButton.addActionListener(this);
		nodeButton.setBackground(Color.GREEN);
		west.add(edgeButton);
		edgeButton.addActionListener(this);
		edgeButton.setBackground(Color.LIGHT_GRAY);
		west.add(labels);
		frame.add(west, BorderLayout.WEST);
		
		//east container
		east.setLayout(new GridLayout(3,1));
		east.add(firstNode);
		east.add(secondNode);
		east.add(connectedButton);
		connectedButton.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		
		//south container
		south.setLayout(new GridLayout(1,2));
		south.add(salesmanStartTF);
		south.add(salesmanButton);
		salesmanButton.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		panel.addMouseListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GraphCreator();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {	
		if (state == NODE_CREATE) {
			panel.addNode(e.getX(),e.getY(), labels.getText());
		}
		//checks that there is a circle where you are clicking
		else if (state == EDGE_FIRST) {
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null) {
				first = n;
				state = EDGE_SECOND;
				n.setHighlighted(true); //adds highlighting
			}
		}
		//checks that there is a circle where you are clicking
		else if (state == EDGE_SECOND) {
			Node n = panel.getNode(e.getX(), e.getY());
			if (n != null && !first.equals(n)) {
				String s = labels.getText();
				boolean valid = true;
				for (int a = 0; a < s.length(); a++) {
					if (Character.isDigit(s.charAt(a)) == false) {
						valid = false;
					}
				}
				if (valid == true) {
					first.setHighlighted(false); //removes highlighting
					panel.addEdge(first, n, labels.getText());
					first = null;
					state = EDGE_FIRST;
				}
				else {
					JOptionPane.showMessageDialog(frame, "Can only have digits in edge labels.");
				}
			}
		}
		frame.repaint(); //repaints with adjustments
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nodeButton)) { //changes color of button from gray to green when selected
			nodeButton.setBackground(Color.GREEN);
			edgeButton.setBackground(Color.LIGHT_GRAY);
			state = NODE_CREATE;
		}
		if (e.getSource().equals(edgeButton)) { //changes color of button from gray to green when selected
			edgeButton.setBackground(Color.GREEN);
			nodeButton.setBackground(Color.LIGHT_GRAY);
			state = EDGE_FIRST;
			panel.stopHighlighting();
			frame.repaint();
		}
		if (e.getSource().equals(connectedButton)) { //if user checks connected nodes but the nodes dont exist on the graph, one of these messages will pop up
			if (panel.nodeExists(firstNode.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "First Node is not in your graph.");
			}
			else if (panel.nodeExists(secondNode.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "Second Node is not in your graph.");

			}
			else {
				Queue queue = new Queue();
				ArrayList<String> connectedList = new ArrayList<String>();
				connectedList.add(panel.getNode(firstNode.getText()).getLabel());
				
				ArrayList<String> edges = panel.getConnectedLabels(firstNode.getText());
				for (int a = 0; a < edges.size(); a++) { //check each connected node
					queue.enqueue(edges.get(a));
				}
				while (queue.isEmpty() == false) {
					String currentNode = queue.dequeue();
					if (connectedList.contains(currentNode) == false) {
						connectedList.add(currentNode); 
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int a = 0; a < edges.size(); a++) {
						if (connectedList.contains(edges.get(a)) == false) {
							queue.enqueue(edges.get(a));
						}
					}
				}
				if (connectedList.contains(secondNode.getText())) {
					JOptionPane.showMessageDialog(frame, "Connected!");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Not Connected.");
				}
			}
		}
		if (e.getSource().equals(salesmanButton)) {
			if (panel.getNode(salesmanStartTF.getText()) != null) {
				//Loop for the number of edges to get all different paths
				for (int a = 1; a <= panel.edgeList.size(); a++) {	
					traveling(panel.getNode(salesmanStartTF.getText()), new ArrayList<Node>(), 0, 1);	
				}
				ArrayList<Integer> sortedCosts = new ArrayList<>(costs);
				Collections.sort(sortedCosts);		
				for (int i = 0; i < costs.size(); i++) { 
					if (costs.get(i) == sortedCosts.get(0)) {	
						System.out.print("\nThe least cost path: ");
						for (int b = 0;b < completed.get(i).size(); b++) {
							System.out.print(completed.get(i).get(b).getLabel()+" ");				
						}			
						
						System.out.println("\nThe least cost: " + sortedCosts.get(0)); 
						break;
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "Not a valid starting node!");
			}
		}
	}
	
	public void traveling(Node n, ArrayList<Node> path, int cost, int pathNodes) {
		//Add the first node to the path
		if (path.contains(n) == false) {
			path.add(n);	
		}
		//get the full path and calculate the cost
		if (panel.nodeList.size() != pathNodes ) {
			for (int a = 0; a < panel.edgeList.size(); a++) {
				skipPath =  false;
				Edge e = panel.edgeList.get(a);				
				Node nextNode =  e.getOtherEnd(n);				
				if (nextNode != null) {						
					if (path.contains(nextNode) == false) { 						
						for (int p = 0; p < completed.size(); p++) {
							if (completed.get(p).get(pathNodes).getLabel().equals(nextNode.getLabel())) {
								skipPath = true;
								break;
							}
						}
						if (!skipPath) {
							path.add(nextNode);
							pathNodes ++;
							cost += Integer.parseInt(e.getLabel());
							traveling(nextNode, path, cost , pathNodes);
							break;	
							
						}
					} 
				}
			}

		} 
		//Print the possible paths and their costs
		else {	
			System.out.println("Possible Path:");
			for (int b = 0;b < path.size(); b++) {
				System.out.print(path.get(b).getLabel()+", ");				
			}
			System.out.print("Cost: " + cost + "\n");			
			completed.add(path);
			costs.add(cost);
		}
		
	}

}
