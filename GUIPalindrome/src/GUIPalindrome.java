import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIPalindrome implements ActionListener {
	JFrame frame = new JFrame();
	JTextField inputTF = new JTextField("Enter text here");
	JButton checkPal = new JButton("Check Palindrome");
	JLabel label = new JLabel("Output: ");
	
	public GUIPalindrome() {
		frame.setSize(600, 400);
		frame.setLayout(new GridLayout(1,3));
		frame.add(inputTF);
		frame.add(checkPal);
		checkPal.addActionListener(this);
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUIPalindrome();
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(checkPal)) {
			String input = inputTF.getText();
			input = input.replaceAll("[^a-zA-Z]", "");		
		    char[] inputArray = input.toCharArray();
			int length = input.length();
			for (int i = 0; i < length/2; i++) {
				char temp = inputArray[length - 1 - i];
				inputArray[length - i - 1] = inputArray[i];
				inputArray[i] = temp;
			}
		    String output = new String(inputArray);
			if (input.equals(output)) {
				label.setText("Output: true");
			}
			else {
				label.setText("Output: false");
			}
		}
	}

}
