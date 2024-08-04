/*
Author:  Cao
Date: 10-05-2024
Project: Complex Word
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class complexWord {
    // word of day
    private static String wordOfDay = "BINGO";

    // Enum to hold color
    enum letterColor {
        GREEN,
        YELLOW,
        GRAY
    }

    // Function to check word length is of 5 and check either all uppercase letters are there
    public static boolean isAlphaWord(String word) {
    	if (!isValidWord(word.toLowerCase())) {
            return false;
        }
        int five = 5;
        if (word.length() != five)
            return false;
        // Iterate over string characters
        for (int iter=0; iter < word.length(); iter++) {
            char c = word.charAt(iter);
            boolean compare = !Character.isAlphabetic(c);
            if (compare)
                return false;
        }
        return true;
    }
    // This function will pick a random word from word-bank csv file 
    public static String pickWordOfTheDay() {
    	// Array List to hold 
        ArrayList<String> wordsObtained = new ArrayList<>();
        try {
        	// File Reader Class
            FileReader fileReader = new FileReader("word-bank.csv");
            // Buffered Reader class
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            // Read until line is not null in given file
            while ((line = reader.readLine()) != null) {
            	// Add line to arraylist
                wordsObtained.add(line);
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Return empty string in case of an error
        }
        // Using random library here to get random index
        Random randomValue = new Random();
        // Limit is 0 to words Obtained list size
        int randomIndex = randomValue.nextInt(wordsObtained.size());
        return wordsObtained.get(randomIndex).toUpperCase(); // Capitalize the word before returning
    }
    
 // Function to validate player guesses using valid-words.csv
    public static boolean isValidWord(String guess) {
        ArrayList<String> validWords = new ArrayList<>();
        try {
        	// File Reader Class
            FileReader fileReader = new FileReader("valid-words.csv");
            // Buffered Reader class
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            // Read until line is not null in given file
            while ((line = reader.readLine()) != null) {
            	// Add Line to arraylist
                validWords.add(line);
            }
            // Close file reader object in order to avoid memory loss
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }

        return validWords.contains(guess.toLowerCase()); // Convert guess to lowercase for case-insensitive comparison
    }


    // Function to match characters like a==a, a!=b
    public static boolean letterMatches(char c1, char c2) {
        return c2 == c1;
    }
    // Function to check either character exist in word, will return true if index is not equal to -1
    // indexOf(character) returns -1 in case letter is not found in string and value >=0 if found that value is index

    public static boolean letterFound(char c, String word) {
        return word.indexOf(c) != -1;
    }

    // Function to play game with rounds
    public static boolean playRound(int roundNumber) {
        System.out.println("Round " + roundNumber + ":");
        // Library to take input from user
        Scanner scanner = new Scanner(System.in);
        boolean playerWon = true;
        boolean always = true;
        while (always) {
            System.out.println("Enter your guess or type quit/exit to exit/end the game:");
            // Convert entered word into upper case like abcd into ABCD
            String guess = scanner.nextLine().toUpperCase();
            // if user enters quit or exit, program will terminate
            if (guess.equals("QUIT") || guess.equals("EXIT")) {
                System.exit(0);
            }
            // Function to check either word is letter or uppercase letter
            if (isAlphaWord(guess)) {
                int conditionalLength = guess.length()-1;
                // Iterate over entered guess
                for (int iterate = 0; iterate  <=  conditionalLength; iterate ++) {
                    // Fetch character from guess
                    char guessChar = guess.charAt(iterate );
                    // Fetch character from word of day
                    char targetChar = wordOfDay.charAt(iterate );
                    // If letter matches with wordOfDay character, GREEN will be displayed
                    if (letterMatches(guessChar, targetChar)) {
                        System.out.println(guessChar + " " + letterColor.GREEN);
                    }
                    // Else If letter does not matches with wordOfDay character but exists in wordOfDay, YELLOW will be displayed
                    else if (letterFound(guessChar, wordOfDay)) {
                        System.out.println(guessChar + " " + letterColor.YELLOW);
                        playerWon = false;
                    }
                    // Else letter does not exists in wordOfDay, GRAY will be displayed
                    else {
                        System.out.println(guessChar + " " + letterColor.GRAY);
                        playerWon = false;
                    }
                }
                break;
            }
            // In case user enters word of length less than 5 or greater than 5
            else {
                System.out.println("Invalid input. Please enter a 5-letter alphabetic word.");
            }
        }
        return playerWon;
    }

    // Function to play game from round 1 to round 6
    public static void playGame() {
        int roundNumber = 1;
        // Integrating pickWordOfTheDay function 
        wordOfDay = pickWordOfTheDay();
        while (roundNumber <= 6) {
            if (playRound(roundNumber)) { // In case user won, display you won
                System.out.println("Congratulations! You won the game!");
                return;
            }
            roundNumber++;
        }
        System.out.println("Oh Sorry, you lost the game :(. The word of the day was: " + wordOfDay);
    }


    
    
    // driver code
    public static void main(String[] args) {
//   System.out.println(letterColor.GREEN); //GREEN
//   System.out.println(letterColor.YELLOW); //YELLOW
//   System.out.println(letterColor.GRAY); //GRAY
//   System.out.println(isAlphaWord("ALLOW")); //true
//   System.out.println(isAlphaWord("ALL")); //false
//   System.out.println(isAlphaWord("Allow")); //false
//   System.out.println(isAlphaWord("ALLOw")); //false
//   System.out.println(isAlphaWord("ALL!!")); //false
//   System.out.println(letterMatches('C', 'C')); //true
//   System.out.println(letterMatches('C', 'D')); //false
//   System.out.println(letterFound('B', "TABLE")); //true
//   System.out.println(letterFound('B', "ALLOW")); //false
//   System.out.println(playRound(4));
    	
    	// Manual testing of words
    	/* System.out.println(isValidWord("aahed")); //true
     	System.out.println(isValidWord("zymic")); //true
     	System.out.println(isValidWord("abcde")); //false
     	*/
     	
     	Scanner scanner = new Scanner(System.in);
        do {
        	System.out.println("Hey there, Warm welcome to the Word Guess Game!");
            System.out.println("You have to guess the word of the day by entering a 5-letter alphabetic word.");
            System.out.println("In each round, feedback will be given to you based on your made guess");
            System.out.println("If you want to exit from game, you can type'quit' or 'exit' to exit from game at any point of game.");

            playGame(); // Call the playGame() function
            System.out.println("Hey there, do you want to play again? (You have to type 'play' to play again or 'quit' to exit)");
            String input = scanner.nextLine().toLowerCase(); // Convert to Lower Case to avoid case sensitivity problems
            // It will exit from do while loop in case input is not equal to string play
            if (!input.equals("play")) {
                break;
            }
            // Iterate with true mean infinite loop but will break from body when input is not equal to play string
        } while (true);
        
        // Thanks the user for playing this game
        System.out.println("Thank you for playing! Goodbye!");
        
        // Close scanner object to avoid memory leaks
        scanner.close();
    }
}

