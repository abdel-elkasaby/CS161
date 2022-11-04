/* A number is chosen 1-50 and the user is asked to guess it.
   If the user guesses incorrectly, they will be given a hint to guess lower or higher.
   If the user guesses correctly, they will be shown the number of guesses it took and asked if they want to play again.*/

import java.util.Scanner;


public class GuessingGame {
	
	public GuessingGame() {
		//If user is still playing or not
		boolean stillPlaying = true;
		
		//User's number of guesses
		int guessCount = 0;		
		
		//Generates random number
		int randomNum = (int)(Math.random() * 51);
		
		while (stillPlaying == true) {
			//If user guessed correctly or not
			boolean correctAnswer = false;
			
			//Scanner class and prompt
			System.out.print("Enter a guess between 1 and 50: ");
			Scanner scanner = new Scanner(System.in);
	        String input = scanner.nextLine();

	        try {
	        	//Converts input from string to integer
	        	int guess = Integer.parseInt(input);	        
	        	String playAgain = "";
	        
	        	while (correctAnswer == false) {	        	
	        		if (guess < 1 || guess > 50) {
	        			System.out.println("Please enter a valid number(1-50)");
						break;
	        		}
	        		else {	        			
	        			if (guess == randomNum) {							
							correctAnswer = true;
							
							//Number of guesses increases by one
							guessCount += 1;
							
							//Displays that the user guessed correctly and displays number of guesses
							System.out.println("Correct!");
							System.out.println("It took you " + guessCount + " guesses");
							
							//Asks user if they want to play again and takes input using a scanner.
							System.out.print("Enter any key to play again. Type \"no\" or \"n\" to exit:");
						    playAgain = scanner.nextLine();
						    
						    /*If user enters 'no' or 'n', game will end and scanner will close. 
						      If they enter anything else, another round will start. */   
						    if (playAgain.equals("no")||playAgain.equals("n")) {
						    	stillPlaying = false;
						    	scanner.close();
						    }
						    else
						    	stillPlaying = true;
							    guessCount = 0;
							    randomNum = (int)(Math.random() * 51);
						}
						else if (guess > randomNum) {
							//Number of guesses increases by one
							guessCount += 1;
							
							//Gives the user a hint and repeats the prompt so the user can input another guess
							System.out.println("Too high! Go lower.");
							break;
						}
						else if (guess < randomNum) {
							//Number of guesses increases by one
							guessCount += 1;
							
							//Gives the user a hint and repeats the prompt so the user can input another guess
							System.out.println("Too low! Go higher.");
							break;
							
						}
						else
							//Asks the user to enter a valid number and repeats the prompt so the user can input another guess
							System.out.println("Please enter a valid number(1-50).");
							break;
	        			}
						
	        	}	
	        } catch (Exception e) {
	        		// If user enters a non-integer value, this error message will show up 
    				System.out.println("Only integers allowed.");
    		}
	    	System.exit(0);
		}										
	}
	
	public static void main(String[] args) {
		new GuessingGame();
	}

}
