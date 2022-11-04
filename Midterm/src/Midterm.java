import java.util.Scanner;

//change every 4th letter to #

public class Midterm {
	int size = 21;
	public int returnWholeNumber(char[] array[21]) {}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a Sentence: ");
		String input = scanner.nextLine();
		
		char[] inputArray = input.toCharArray();
		for (int i = 0; i < input.length(); i++) {
			if ((i+1)%4 == 0 && i != 0) {
				inputArray[i] = '#';
			}
		}
		System.out.println(inputArray);
	}

}
