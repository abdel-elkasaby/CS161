import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/*
 * This program uses three different encryption ciphers (scytale, caeser, and vigenere).
 * It uses a GUI interface to allow the users to encrypt or decrypt sentences using any of the 3 types
 * How to use:
 * User must first select a cipher type (scytale, caeser, or vigenere)
 * Then they enter the row, shift, or key depending on which cipher they picked.
 * Then they enter the input on encrypted side or decrypted side depending on what they want to do
 * Then they hit encrypt or decrypt and the encrypted or decrypted text will appear
 * 
 * Author: Abdel Elkasaby
 * Date: 10/24/2020
 * 
 * Works Cited:
 * I used this link to learn how to make the option panes after an error
 * https://www.tutorialspoint.com/swingexamples/show_error_message_dialog.htm
 */

public class Cryptography implements ActionListener {
	JFrame frame = new JFrame(); //frame of the game
	
	//text fields
	JTextField decrypted = new JTextField(); //text field for decrypted text
	JTextField encrypted = new JTextField(); //text field for encrypted text
	JTextField scytaleRows = new JTextField(); //text field for entering number of rows in scytale
	JTextField caeserShift = new JTextField(); //text field for entering number of letters to shift by for caeser
	JTextField vigenereKey = new JTextField(); //text field for keyword for vigenere
	
	//buttons
	JButton encrypt = new JButton("Encrypt");
	JButton decrypt = new JButton("Decrypt");
	
	//labels
	JLabel decryptedLabel = new JLabel("Decrypted"); 
	JLabel placeholder = new JLabel("\t"); //just to make the layout nicer
	JLabel encrypedLabel = new JLabel("Encrypted");
	JLabel scytaleRowsLabel = new JLabel(" # of Rows (scytale):");
	JLabel caeserShiftLabel = new JLabel(" Shift by (caeser):");
	JLabel vigenereKeyLabel = new JLabel(" Key word (vigenere):");
	
	//radio buttons (when you select one, the other gets deselected)
	JRadioButton scytaleButton = new JRadioButton("Scytale Cipher");
	JRadioButton caeserButton = new JRadioButton("Caeser Cipher");
	JRadioButton vigenereButton  = new JRadioButton("Vignere Cipher");
 
	//containers
	Container center = new Container(); // container for text fields and converter container
	Container converter = new Container(); // contains converter buttons
	Container north = new Container(); // contains buttons that allow user to choose which way to encrypt message
	Container south = new Container(); // contains labels in the south section
	Container scytaleContainer = new Container();
	Container caeserContainer = new Container();
	Container vigenereContainer = new Container();
	
	public Cryptography() {
		frame.setSize(600,400); // sets the size of the frame		

		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		
		//creates button group for radio buttons and puts the cipher types in it
		ButtonGroup ciphers = new ButtonGroup();
		ciphers.add(scytaleButton);
		ciphers.add(caeserButton);
		ciphers.add(vigenereButton);
		
		//adds north container and sets layout
		frame.add(north, BorderLayout.NORTH);
		north.setLayout(new GridLayout(1,3));
		//containers for encryption types (each contains radio button, label, and text field for input)
		north.add(scytaleContainer);
		north.add(caeserContainer);
		north.add(vigenereContainer);
		
		//scytale container
		scytaleContainer.setLayout(new GridLayout(3,1));
		scytaleContainer.add(scytaleButton);
		scytaleContainer.add(scytaleRowsLabel);
		scytaleContainer.add(scytaleRows);
		
		//caeser container
		caeserContainer.setLayout(new GridLayout(3,1));
		caeserContainer.add(caeserButton);
		caeserContainer.add(caeserShiftLabel);
		caeserContainer.add(caeserShift);
		
		//vigenere container
		vigenereContainer.setLayout(new GridLayout(3,1));
		vigenereContainer.add(vigenereButton);
		vigenereContainer.add(vigenereKeyLabel);
		vigenereContainer.add(vigenereKey);
		
		//adds converter buttons to converter container
		converter.setLayout(new GridLayout(2,1));
        converter.add(encrypt);
		converter.add(decrypt);
		
		//adds center container and sets layout
		center.setLayout(new GridLayout(1,3)); 
		frame.add(center, BorderLayout.CENTER);
		//adds text fields and converter container
		center.add(decrypted);
		center.add(converter);
		center.add(encrypted);
		
		//south
		south.setLayout(new GridLayout(1,3));
		frame.add(south, BorderLayout.SOUTH);
		south.add(decryptedLabel);
		south.add(placeholder);
		south.add(encrypedLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the program on close
		frame.setVisible(true);//making the frame visible  

	}
	public static void main(String[] args) {
		new Cryptography();
	}

	public void actionPerformed(ActionEvent event) { //calls functions for each encrypt or decrypt
		if (scytaleButton.isSelected()) {
			if (event.getSource().equals(encrypt)) {//scytale encrypt
				ScytaleEncrypt();
			}
			if (event.getSource().equals(decrypt)) {//scytale decrypt
				ScytaleDecrypt();
			}
		}
		if (caeserButton.isSelected()) {
			if (event.getSource().equals(encrypt)) {//caeser encrypt
				CaeserEncrypt();
			}
			if (event.getSource().equals(decrypt)) {//caeser decrypt
				CaeserDecrypt();
			}
		}
		if (vigenereButton.isSelected()) {
			if (event.getSource().equals(encrypt)) {//caeser encrypt
				VigenereEncrypt();
			}
			if (event.getSource().equals(decrypt)) {//caeser decrypt
				VigenereDecrypt();
			}
		}
		if (event.getSource().equals(encrypt) && !scytaleButton.isSelected() && !caeserButton.isSelected() && !vigenereButton.isSelected()) {//if a cipher type is not selected
			JOptionPane.showMessageDialog(frame, "You must select scytale, caeser, or vigenere","Select Cipher", JOptionPane.ERROR_MESSAGE); //to show error message
		}
		if (event.getSource().equals(decrypt) && !scytaleButton.isSelected() && !caeserButton.isSelected() && !vigenereButton.isSelected()) {//if a cipher type is not selected
			JOptionPane.showMessageDialog(frame, "You must select scytale, caeser, or vigenere","Select Cipher", JOptionPane.ERROR_MESSAGE); //to show error message
		}
	}
	public void ScytaleEncrypt() {//scytale encrypt
		//variables used in this method
		String input = decrypted.getText(); //takes whatever is in decrypted text field and puts it in a string
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase();
		int length = input.length(); 
		int row = 0;
		try {//checks if number of rows is an int
			row = Integer.parseInt(scytaleRows.getText()); //gets number of rows as input from user
			if (row > 0) {
				int mod = length % row;
				int column = (length/row);
				
				if (mod != 0) {
					column++;
				}
				char[] inputArray = input.toCharArray();  //array to hold letters in string so they can be passed to 2d array
				char[][] scytaleEncrypt = new char [row][column]; //2D array that takes letters from inputArray and puts it in a grid
				int k = 0; //counter that moves up after each letter is placed in array
				for (int i = 0; i < row; i++) { //reads array left to right
					for (int j = 0; j < column; j++) { 
						if ((row*column) == length) { //if input is the same length as array
							if (k < inputArray.length) { //if k is less than array length, the next character is added
								scytaleEncrypt[i][j] = inputArray[k];
								k++;	
							}
						}
						else if ((row*column) > length) { //if input is less than array
							if (i < mod) { //if row is less than mod, row is filled completely
								scytaleEncrypt[i][j] = inputArray[k];
								k++;	
							}
							else {
								if (j == column - 1) { //fill last column in row with @
									scytaleEncrypt[i][j] = '@';	
								}
								else { //fill everything except last column in row
									scytaleEncrypt[i][j] = inputArray[k];
									k++;	
								}
							}
						}
					}
				}
				String output = "";
				for (int j = 0; j < column; j++) { //reads array up to down instead of left to right
					for (int i = 0; i < row; i++) {
						output += (scytaleEncrypt[i][j]);//adds characters to output string
					}
				}
				encrypted.setText(output.replaceAll("@", ""));
			}
			else {
				JOptionPane.showMessageDialog(frame, "Number of rows must be an integer greater than 0","Scytale Encrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Number of rows must be an integer","Scytale Encrypt", JOptionPane.ERROR_MESSAGE); //to show error message
		}
	}
	public void ScytaleDecrypt() { //scytale decrypt
		//variables used in this method
		String input = encrypted.getText(); //takes whatever is in encrypted text field and puts it in a string
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase();
		int length = input.length(); 
		int row = 0;	
		try {//checks if number of rows is an int
			row = Integer.parseInt(scytaleRows.getText()); //gets number of rows as input from user

			if (row > 0) {		
				int mod = length % row;
				int column = (length/row);
		
				if (mod != 0) {//if the length of the word is not equal to row*column exactly, add one more column so there is enough space for the letters
					column++;
				}
				char[] inputArray = input.toCharArray(); //array to hold letters in string so they can be passed to 2d array
				char[][] scytaleDecrypt = new char [row][column]; //2D array that takes letters from inputArray and puts it in a grid
				int k = 0; //counter that moves up after each letter is placed in array
				for (int j = 0; j < column; j++) { //reads up to down
					for (int i = 0; i < row; i++) { 
						if ((row*column) == length) { //if input is the same length as array
							if (k < inputArray.length) { //if k is less than array length, the next character is added
								scytaleDecrypt[i][j] = inputArray[k];
								k++;	
							}
						}
						else if ((row*column) > length) { //if input is less than array
							if (i < mod) { //if row is less than mod, row is filled completely
								scytaleDecrypt[i][j] = inputArray[k];
								k++;	
							}
							else {
								if (j == column - 1) { //fill last column in row with @
									scytaleDecrypt[i][j] = '@';	
								}
								else { //fill everything except last column in row
									scytaleDecrypt[i][j] = inputArray[k];
									k++;	
								}
							}
						}
					}
				}
				String output = "";
				for (int i = 0; i < row; i++) { //reads array left to right
					for (int j = 0; j < column; j++) {
						output += (scytaleDecrypt[i][j]);//adds characters to output string
					}
				}
				decrypted.setText(output.replaceAll("@", ""));
			}

			else {
				JOptionPane.showMessageDialog(frame, "Number of rows must be an integer greater than 0","Scytale Decrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Number of rows must be an integer","Scytale Decrypt", JOptionPane.ERROR_MESSAGE); //to show error message
		}
	}
	public void CaeserEncrypt() {//caeser encrypt
		//variables used in this method
		String input = decrypted.getText();
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase();
		char[] inputArray = input.toCharArray(); //puts input in a character array 
		int shift = 0; //value it shifts each letter by. takes it from user input
		try {//checks if shift value is an int
			shift = Integer.parseInt(caeserShift.getText());
			if (shift <= 25 && shift > -1) { //anything less than or greater than this will just repeat because the alphabet only has 26 letters
				String output = "";
				for (int i = 0; i < inputArray.length; i++) {
					int charNumber = inputArray[i]; 
					charNumber = charNumber + shift; //shifts letter
					int Z = 'Z'; //to get ascii values of 'A' and 'Z'
					int A = 'A';
					if (charNumber > Z) {//if value of character is greater than 'Z' after shift
						charNumber = charNumber - Z + A - 1;
					}
					char newLetter = (char) charNumber;
					output += Character.toString(newLetter);//adds characters to output string
				}
				encrypted.setText(output);
			} 
			else {
				JOptionPane.showMessageDialog(frame, "Shift must be an integer from 0-25","Caeser Encrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Shift must be an integer","Caeser Encrypt", JOptionPane.ERROR_MESSAGE); //to show error message
		}
	}
	public void CaeserDecrypt() { //caeser decrypt
		//variables used in this method
		String input = encrypted.getText();
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase(); 	
		char[] inputArray = input.toCharArray(); //puts input in a character array 
		int shift = 0;  //value it shifts each letter by. takes it from user input
		try {//checks if shift value is an int
			shift = Integer.parseInt(caeserShift.getText());
			if (shift <= 25 && shift > -1) {//anything less than or greater than this will just repeat because the alphabet only has 26 letters
				String output = "";
				for (int i = 0; i < inputArray.length; i++) {
					int charNumber = inputArray[i];
					charNumber = charNumber - shift; //shifts letter
					int Z = 'Z'; //to get ascii values of 'A' and 'Z'
					int A = 'A';
					if (charNumber < A) {//if value of character is less than 'A' after shift
						charNumber = charNumber - A + Z + 1;
					}
					char newLetter = (char) charNumber;
					output += Character.toString(newLetter);//adds characters to output string
				}
				decrypted.setText(output);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Shift must be an integer from 0-25","Caeser Decrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
			}
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Shift must be an integer","Caeser Decrypt", JOptionPane.ERROR_MESSAGE); //to show error message
		}
	}
	public void VigenereEncrypt() { //vignere encrypt
		//variables used in this method
		String input = decrypted.getText();
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase();
		int length = input.length();
		char[] inputArray = input.toCharArray();
		String key = vigenereKey.getText();
		key = key.replaceAll("[^a-zA-Z]", "");
		key = key.toUpperCase();
		if (key.equals("")) {
			JOptionPane.showMessageDialog(frame, "Key must be a string","Vigenere Encrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
		} 
		else {	
			char[] keyArray = key.toCharArray();
			String output = "";
			int Z = 'Z'; //to get ascii values of 'A' and 'Z'
			int A = 'A';
			
			int k = 0; //index of the key word
			for (int i = 0; i < length; i++) {//loops input array
				int shift = keyArray[k] - A;	
				int charNumber = inputArray[i] + shift; //shifts integer value of character
				if (charNumber > Z) {//if value of character is greater than 'Z' after shift
					charNumber = charNumber - Z + A - 1;
				}
				char newLetter = (char) charNumber;
				output += Character.toString(newLetter);//adds characters to output string
				k++;
				if (k == key.length()) { //if counter reaches the end, it will start at 0 again
					k = 0;
				}
			}
			
			encrypted.setText(output);
		}
	}
	public void VigenereDecrypt() { //vigenere decrypt
		//variables used in this method
		String input = encrypted.getText();
		input = input.replaceAll("[^a-zA-Z]", "");
		input = input.toUpperCase();
		int length = input.length();
		char[] inputArray = input.toCharArray();
		String key = vigenereKey.getText();
		key = key.replaceAll("[^a-zA-Z]", "");
		key = key.toUpperCase();
		if (key.equals("")) {
			JOptionPane.showMessageDialog(frame, "Key must be a string","Vigenere Decrypt", JOptionPane.ERROR_MESSAGE); //to show error message			
		} 
		else {
			key = key.replaceAll("[^a-zA-Z]", "");
			key = key.toUpperCase();
			char[] keyArray = key.toCharArray();
			String output = "";
			int Z = 'Z'; //to get ascii values of 'A' and 'Z'
			int A = 'A';
			
			int k = 0; //index of the key word
			for (int i = 0; i < length; i++) {//loops input array
				int shift = keyArray[k] - A;	
				int charNumber = inputArray[i] - shift; //shifts integer value of character
				if (charNumber < A) {//if value of character is less than 'A' after shift
					charNumber = charNumber - A + Z + 1;
				}
				char newLetter = (char) charNumber;
				output += Character.toString(newLetter);//adds characters to output string
				k++;
				if (k == key.length()) { //if counter reaches the end, it will start at 0 again
					k = 0;
				}
			}
			
			decrypted.setText(output);
		}
	}
}
