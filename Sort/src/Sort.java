import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/*
 * This program uses 4 different sorting algorithms to sort through numbers
 * the algorithms are bubbles, separation, table, and quick sort
 * it also prints the time in milliseconds that the program took to sort the numbers
 * 
 * Name: Abdelrahman Elkasaby
 * Date: Oct 29 2020
 * 
 * I got the information on quick sort from these links:
 * https://www.youtube.com/watch?v=MZaf_9IZCrc
 * https://www.youtube.com/watch?v=uXBnyYuwPe8&t=903s
 * https://www.youtube.com/watch?v=vxENKlcs2Tw
 * https://www.geeksforgeeks.org/quick-sort/
 */
public class Sort {
	//variables
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public Sort() { //choose text file by entering corresponding number
		System.out.println("Enter a number for the input file");
		System.out.println("1: input1.txt   2: input2.txt   3: input3.txt   4: input4.txt");
		input = consoleInput.nextLine();
		System.out.println(input);
		//prompts user to enter a valid number
		while (!input.equals("1") && !input.equals("2")
				&& !input.equals("3") && !input.equals("4")) {
			System.out.println("Please enter 1,2,3, or 4.");
			input = consoleInput.next();
		}
		try { //to prevent program from crashing if the file is not found
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		//prints each number in file separately
		String inFile = fileInput.nextLine();
		String[] inputStringArray = inFile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		//choose which type of sort to use
		System.out.println("Enter a number for the sort you want to use");
		System.out.println("1: Bubble   2: Selection   3: Table   4. Quick Sort");
		input = consoleInput.nextLine();
		//prompts user to enter a valid number
		while (!input.equals("1") && !input.equals("2")
				&& !input.equals("3") && !input.equals("4")) {
			System.out.println("Please enter 1,2,3, or 4.");
			input = consoleInput.next();
		}
		startTime = System.currentTimeMillis();
		//for calling different sorting algorithms
		if (input.equals("1")) {//bubble
			inputArray = bubbleSort(inputArray);
		}
		if (input.equals("2")) {//selection
			inputArray = selectionSort(inputArray);
		}
		if (input.equals("3")) {//table sort
			inputArray = tableSort(inputArray);
		}
		if (input.equals("4")) {//quick sort
			inputArray = quickSort(inputArray, 0, inputArray.length - 1);
		}
		
		//keeps track of time
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total time: " + totalTime + " milliseconds.");
		PrintWriter pw;
		
		try {
			//prints sorted numbers to a new file
			pw = new PrintWriter(new FileWriter("output.txt"));
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
				output += inputArray[i] + ",";
			}
			output += "\nTotal Time: " + totalTime + " milliseconds.";
			pw.write(output);
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

	}
	//compare each pair of numbers and move the larger to the right
	int[] bubbleSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				//if the one on the left is larger
				if (array[i] > array[i+1]) {
					//swaps numbers using a temporary variable
					int temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
		}	
		return array;
	}
	//find the smallest and move it to the front
	int[] selectionSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) {
				if (array[i] < smallestNumber) {
					smallestNumber = array[i];
					smallestIndex = i;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
		}
		return array;
	}
	//tally how often you see each number, print out number of times
	int[] tableSort(int[] array) {
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) {
			tally[array[i]]++;
		}
		int count = 0;
		//i keeps track of the actual number
		for (int i = 0; i < tally.length; i++) {
			//j keeps track of how many times we've seen the number
			for (int j = 0; j < tally[i]; j++) {
				array[count] = i;
				count++;
			}
		}
				return array;
	}
	/*quick sort method gets a pivot point (i used the last point in the data set)
	 * then it goes through all the points and splits it into partitions
	 * one partition is numbers smaller than the pivot and the other is larger numbers
	 * the same process is done for each partition until the numbers are in order
	 */
	int[] quickSort(int[] array, int first, int last) {	
		if (first < last) {	
			int pivotIndex = partition(array, first, last);
			quickSort(array, first, pivotIndex - 1); //sorts first partition
			quickSort(array, pivotIndex + 1, last); //sorts second partition
		}		
		return array;
	}
	/*this seperates the numbers smaller than the pivot from the numbers greater than the pivot by
	 * putting the smaller numbers before it and the larger numbers after it
	 * i loops through to check each number
	 * j is placed at the index of the last number that has been found to be smaller than the partition
	 */	
	int partition(int[] array, int first, int last) {
		int pivot = array[last];
		int j = first;
			for (int i = first; i < last ; i++) {				
				if (array[i] < pivot) {
					//swaps numbers using a temporary variable
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
					j++;					
				}
			}
			/*
			 * swaps numbers using a temporary variable 
			 * this swaps with the pivot number when all the smaller numbers are on one side
			 * and the bigger numbers are on the other side
			 */
			int temp2 = array[j];
			array[j] = pivot;
			array[last] = temp2;		
		return j; //this is the point where the pivot is
	}
		
	public static void main(String[] args) {
		new Sort();
		
	}
	
	
}
