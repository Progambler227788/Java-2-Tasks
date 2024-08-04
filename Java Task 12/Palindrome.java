// Java palindrome project
import java.util.Scanner;

public class Palindrome {

	// reverses a string of characters
	public static String reverse(String a) {
		if (a.length()<=1)
		 return a;
		// hel -> el + h -> l + e + h -> l e h
		return reverse(a.substring(1)) + a.charAt(0);
	}


	//main procedure
	public static void main(String[] args) {
		// prompts the user for input
		Scanner scan = new Scanner(System.in);
		while(true){
		System.out.println("Enter a palindrome word:");
		String myString = scan.nextLine();
		// prints out the reverse of the inputted word and integer
		System.out.println(myString + "-" + reverse(myString));
		
		}
	}

}


