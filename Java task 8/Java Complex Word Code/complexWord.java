/*
Author: Cao
Date: April 23, 2024
Project: Wordle Game
*/

import java.util.Scanner;

public class complexWord {
    // word of day
    private static final String wordOfDay = "BINGO";
    
    // Enum to hold color 
    enum letterColor {
        GREEN,
        YELLOW,
        GRAY
    }
    
    // Function to check word length is of 5 and check either all uppercase letters are there
    public static boolean isAlphaWord(String word) {
        int five = 5;
        if (word.length() != five)
            return false;
        // Iterate over string characters
        for (int iter=0; iter < word.length(); iter++) {
            char c = word.charAt(iter);
            boolean compare = !Character.isAlphabetic(c) || !Character.isUpperCase(c);
            if (compare)
                return false;
        }
        return true;
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

        playGame();
    }
}
