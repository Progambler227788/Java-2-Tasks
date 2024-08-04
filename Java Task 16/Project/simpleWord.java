/*
 * Author: Cao
 * Date: 2024-05-15
 */
 
// Import Scanner library for input reading
import java.util.Scanner;

// Declare class name 
public class simpleWord {
    // Main function, entry point of program
    public static void main(String[] args) {
        // It is used to red input from user
        Scanner scanner_object = new Scanner(System.in);

        // player_guess will be stored here in this string
        String player_guess;
        
        
        //A String where the word of the day is stored
        String word_of_day = "DATA"; 
        
       
        // Round 1 of game
        System.out.println("Round 1:");
        // Print message to user screen
        System.out.print("Enter your guess: ");
        // Take input from user
        player_guess = scanner_object.nextLine().toUpperCase(); // Convert input to uppercase
        
        // This part of the code checks if all characters entered by user as guesses are equal to word of day
        if (player_guess.equals(word_of_day)) {
            System.out.println("Hey, congratulations you won in first round");
            System.exit(0); // Exit the game because user has won game
        }
        
        // Check each character entered by user
        System.out.println("Result:");
        // This line of code will check for first character for first round with first character for word of day
        if (player_guess.charAt(0) == word_of_day.charAt(0)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for first character for first round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(0) + "")) {
            System.out.println("YELLOW");
        } 
        // This line of code will check for first character for first round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        
        // Character 2
        // This line of code will check for second character for first round with second character for word of day
        if (player_guess.charAt(1) == word_of_day.charAt(1)) {
            System.out.println("GREEN");
        }
        // This line of code will check for second character for first round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(1) + "")) {
            System.out.println("YELLOW");
        }
         // This line of code will check for second character for first round that it is not in word of day
         else {
            System.out.println("GRAY");
        }
        // Character 3
        // This line of code will check for third character for first round with third character for word of day
        if (player_guess.charAt(2) == word_of_day.charAt(2)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for third character for first round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(2) + "")) {
            System.out.println("YELLOW");
        } 
        // This line of code will check for third character for first round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 4
        // This line of code will check for fourth character for first round with second character for word of day
        if (player_guess.charAt(3) == word_of_day.charAt(3)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for fourth character for first round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(3) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for fourth character for first round that is it in word of day
        else {
            System.out.println("GRAY");
        }
        
        // Round 2
        System.out.println("\nRound 2:");
        // Print message to user screen
        System.out.print("Enter your guess: ");
        // Take user input
        player_guess = scanner_object.nextLine().toUpperCase(); // Convert input to uppercase
        
        // This part of the code checks if guesses for round 2 are correct 
        if (player_guess.equals(word_of_day)) {
            System.out.println("Hey, congratulations you won in second round");
            System.exit(0); // Exit the game because user has won game
        }
        
        // Check each character
        System.out.println("Result:");
        // Character 1
         // This line of code will check for first character for second round with first character for word of day
        if (player_guess.charAt(0) == word_of_day.charAt(0)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for first character for second round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(0) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for first character for second round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 2
        // This line of code will check for second character for second round with second character for word of day
        if (player_guess.charAt(1) == word_of_day.charAt(1)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for second character for second round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(1) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for second character for second round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 3
        // This line of code will check for third character for second round with third character for word of day
        if (player_guess.charAt(2) == word_of_day.charAt(2)) {
            System.out.println("GREEN");
        }
        // This line of code will check for third character for second round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(2) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for third character for second round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 4
        // This line of code will check for fourth character for second round with fourth character for word of fourth
        if (player_guess.charAt(3) == word_of_day.charAt(3)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for fourth character for second round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(3) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for fourth character for second round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        
        // Round 3
        System.out.println("\nRound 3:");
        // Print message to user screen
        System.out.print("Enter your guess: ");
        // Take user input
        player_guess = scanner_object.nextLine().toUpperCase(); // Convert input to uppercase
        
        // This part of the code checks if guesses for round 2 are correct 
        if (player_guess.equals(word_of_day)) {
            System.out.println("Hey, congratulations you won in third round");
            System.exit(0); // Exit the game because user has won game
        }
        
        // Check each character
        System.out.println("Result:");
        // Character 1
         // This line of code will check for first character for third round with first character for word of day
        if (player_guess.charAt(0) == word_of_day.charAt(0)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for first character for third round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(0) + "")) {
            System.out.println("YELLOW");
        } 
        // This line of code will check for first character for third round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 2
         // This line of code will check for second character for third round with second character for word of day
        if (player_guess.charAt(1) == word_of_day.charAt(1)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for second character for third round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(1) + "")) {
            System.out.println("YELLOW");
        } 
        // This line of code will check for second character for third round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 3
         // This line of code will check for third character for third round with third character for word of day
        if (player_guess.charAt(2) == word_of_day.charAt(2)) {
            System.out.println("GREEN");
        } 
        // This line of code will check for third character for third round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(2) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for third character for third round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        // Character 4
         // This line of code will check for fourth character for third round with fourth character for word of day
        if (player_guess.charAt(3) == word_of_day.charAt(3)) {
            System.out.println("GREEN");
        }
        // This line of code will check for fourth character for third round that is it in word of day
        else if (word_of_day.contains(player_guess.charAt(3) + "")) {
            System.out.println("YELLOW");
        }
        // This line of code will check for fourth character for third round that it is not in word of day
        else {
            System.out.println("GRAY");
        }
        
        // Clear memory leakage
        scanner_object.close();
    }
}
