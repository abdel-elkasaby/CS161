import java.util.Scanner;

public class LetterCounter {

	public LetterCounter() {
		/*ASCII has 256 characters. lower case letters go from 97(a) to 122(z) on the ASCII table
		 * 	any number greater than or equal to 123 works because 122 is the index of "z" on the ascii table
		 */
        int letterCount[] = new int[123];
		//prompts user to input a string and converts it to lower case
		System.out.print("Enter a string: ");
		Scanner scanner = new Scanner(System.in);
	    String input = scanner.nextLine(); 
		input = input.toLowerCase();
		//length of input
		int length = input.length();
		
		for (int i = 0; i < length; i++) {//loops through every letter in loop	
			if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {	//only does it for letters
				letterCount[input.charAt(i)]++; //adds 1 to letter count for that character 
			boolean found = false; //flag
			for (int j = i+1; j < length; j++) { //loops through every other letter to check for multiple of one letter
				if (input.charAt(i) == input.charAt(j)) {//if it finds one that is equal to current letter it will set flag to true and break this loop
					found = true;
					break;
				}	
			}
			if (!found) {//if no more of each letter is found it prints each letter with the number of its occurrences
				System.out.print(input.charAt(i) + ":" + letterCount[input.charAt(i)] + ", ");
				scanner.close(); //closes scanner
			}
			}		
		}
    }
	public static void main(String[] args) {
		new LetterCounter();

	}

}
