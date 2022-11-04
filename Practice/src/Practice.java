import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Practice implements ActionListener{
	
	JFrame frame;
	JButton button;
	JTextField inputField;
	JLabel outputField;
	int total = 0;
	
	public Practice() {
		frame = new JFrame();
		frame.setSize(400, 300);
		frame.setLayout(new GridLayout(3, 1));
		inputField = new JTextField("Enter your numbers (seperate each number with a space)");
		frame.add(inputField);
		button = new JButton("Click");
		button.addActionListener(this);
		frame.add(button);
		outputField = new JLabel(" ");
		frame.add(outputField);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Practice();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button)) {
			String input = inputField.getText();
			String[] inputArray = input.split(" ");
			int[] intArray = new int[inputArray.length];
			for (int i = 0; i < inputArray.length; i++) {
				intArray[i] = Integer.parseInt(inputArray[i]);
			}
			for (int i = 0; i < intArray.length; i++) {
				if (intArray[i] > 10) {
					total += intArray[i];
				}
			}
			String totalString = Integer.toString(total);
			outputField.setText(totalString);
		}
	}

}
