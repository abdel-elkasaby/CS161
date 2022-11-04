import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FinalPractice implements ActionListener{
	
	JFrame frame = new JFrame();
	JTextField inputTF = new JTextField("Enter text here");
	JButton convert = new JButton("Convert");
	JLabel label = new JLabel("Output: ");
	
	public FinalPractice() {
		frame.setSize(600, 400);
		frame.setLayout(new GridLayout(1,3));
		
		frame.add(inputTF);
		frame.add(convert);
		convert.addActionListener(this);
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new FinalPractice();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(convert)) {
			String input = inputTF.getText();
		    String output = input.replaceAll("[^aAeEiIoOuU]", "5");
		    label.setText(output);
		}
	}

}
