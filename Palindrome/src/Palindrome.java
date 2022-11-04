import java.util.Scanner;

public class Palindrome {
	
	public Palindrome() {
		//scanner and prompt
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a Sentence: ");
		String input = scanner.nextLine();
		//replaces everything other than letters with blank
		input = input.replaceAll("[^a-zA-Z]", "");		
		//puts letters in string to a character array
	    char[] inputArray = input.toCharArray();
	    //length of array
		int length = input.length();
		for (int i = 0; i < length/2; i++) {//divides by 2 because the number of swaps is letters/2. (since its an int variable, decimals will round)
			//uses temporary variable to swap values
			char temp = inputArray[length - 1 - i];
			inputArray[length - i - 1] = inputArray[i];
			inputArray[i] = temp;
		}
		System.out.println(inputArray);
    }
	
	public static void main(String[] args) {
		new Palindrome();
	}

}
