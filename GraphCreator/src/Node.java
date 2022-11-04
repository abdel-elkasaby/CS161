
public class Node {
	
	int x; //coordinates for where the circle will be drawn and its label
	int y;
	String label;
	boolean highlighted;
	
	public Node(int newx, int newy, String newlabel) {
		x = newx;
		y = newy;
		label = newlabel;
		highlighted = false;
	}

	//getters and setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean getHighlighted() {
		return highlighted;
	}
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
}
