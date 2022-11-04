import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class BinaryTranslator {
	
	public BinaryTranslator() {
		//this boolean keeps the first while loop going until user enters valid input
		boolean continueLoop = true;
		
		//this is the decimal or binary received from the file or user input
		String numberInput = "";
		
		while (continueLoop == true) {
			//scanner class and prompt
			System.out.print("Please enter \"file\" to enter a file or \"input\" to use the console: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();

				if (input.equals("file")) { //input from file
			try { //asks user for file name
				System.out.print("Enter file name: ");
				input = scanner.nextLine();
				Scanner fileScanner = new Scanner(new File(input));
				numberInput = fileScanner.nextLine();
			} catch (IOException ex) { //tells the user that a file under that name couldn't be found and ends program
				System.out.print("File not found");
				scanner.close();
				System.exit(1);
				}
				continueLoop = false;
				}	
				else if (input.equals("input")){ //input from console
					System.out.print("Enter a decimal or a binary number: ");
					numberInput = scanner.nextLine();	
					continueLoop = false;
				}
		}
		//keeps the second while loop going until task is complete
		boolean continueLoop2 = true;
		
		while (continueLoop2 == true) {
			//asks user what they are translating from and to
			System.out.println("If translating from decimal to binary, enter \"dtb\"");
			System.out.println("If translating from binary to decimal, enter \"btd\"");
			Scanner scanner = new Scanner(System.in);
			String type = scanner.nextLine();
			
				if (type.equals("dtb")) { //decimal to binary
					String answer = "";
					int number = Integer.parseInt(numberInput);
					
					/*this while loop converts the decimal into a binary by dividing each digit by 2  
					  until it is down to only 1s and 0s.*/		
					while (number > 0) {
						int remainder = number % 2;																																																											
					    answer = remainder + answer;
						number = number / 2;
					}
					//prints answer, ends while loop, closes scanner, and exits program
					System.out.println(answer);
					continueLoop2 = false;
					scanner.close();
					System.exit(0);
				}
				else if (type.equals("btd")) { //binary to decimal
					int answer = 0;
					
					/*this for loop converts the binary into a decimal by multiplying each  
					  digit by 2 to the power of it's location starting from the end at power 0*/
					for (int a = numberInput.length() - 1; a >= 0; a--) {
						if (numberInput.charAt(numberInput.length()-a-1) == '1') {
							answer = answer + (int)(Math.pow(2, a));		
						}
					}
					//prints answer, ends while loop, closes scanner, and exits program
					System.out.println(answer);
					continueLoop2 = false;
					scanner.close();
					System.exit(0);
				}
				else { //if user enters something other than "dtb" or btd"
					System.out.println("Please enter \"dtb\" or \"btd\": ");
				}
		}
	}

	public static void main(String[] args) {
		new BinaryTranslator();
	}

}
