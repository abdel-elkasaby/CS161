import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Final implements ActionListener {
	
	JFrame frame;
	JButton button;
	JTextField inputField;
	JLabel outputField;
	
	public Final() {
		frame = new JFrame();
		frame.setSize(400, 300);
		frame.setLayout(new GridLayout(3, 1));
		inputField = new JTextField("This is where your input goes.");
		frame.add(inputField);
		button = new JButton("This is a button. You press it.");
		button.addActionListener(this);
		frame.add(button);
		outputField = new JLabel("This is where your output goes.");
		frame.add(outputField);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Final();
	}

	public void actionPerformed(ActionEvent e) {
		int consonants = 0;
		int vowels = 0;
		if (e.getSource().equals(button)) {
			String input = inputField.getText();
		    char[] inputArray = input.toCharArray();
			for (int i = 0; i < input.length(); i++) {
				if (inputArray[i] != 'A' && inputArray[i] != 'a' && inputArray[i] != 'E' && inputArray[i] != 'e' 
						&& inputArray[i] != 'I' && inputArray[i] != 'i' && inputArray[i] != 'O' && inputArray[i] != 'o' &&
								inputArray[i] != 'U' && inputArray[i] != 'u' && inputArray[i] != ' ') {
					consonants++;
				}
				if (inputArray[i] == 'A' || inputArray[i] == 'a' || inputArray[i] == 'E' || inputArray[i] == 'e' 
						|| inputArray[i] == 'I' || inputArray[i] == 'i' || inputArray[i] == 'O' || inputArray[i] == 'o' || 
								inputArray[i] == 'U' || inputArray[i] == 'u') {
					vowels++;
				}
			}
			int total = consonants * vowels;
			String totalString = Integer.toString(total);
			outputField.setText(totalString);
		}
	}

}