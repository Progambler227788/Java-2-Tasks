/*
// TODO: Write your name
// TODO: Write the date
Lab 5: Overloading
*/

import java.util.Scanner;

public class reverser {

	// reverses a string of characters
	public static String reverse(String a) {
		String b = "";
		//TODO: reverse String "a" into "b" using a for loop
		for (int  i =  a.length() -1;  i>=0 ; i--){
		    b += a.charAt(i);
		}
		return b;
	}

	// reverses an integer
	public static int reverse(int a) {
		//FIXME: convert the number toString in the Integer wrapper class
		String b = "";
		b = Integer.toString(a);
		//FIXME: call reverse() using the String
		String c = "";
		c = new StringBuilder(b).reverse().toString();
		//FIXME: parse the reversed String using the Integer wrapper class
		int d = 0;
		d = Integer.parseInt(c);
		return d;
	}

	//main procedure
	public static void main(String[] args) {
		// prompts the user for input
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a word and an integer:");
		String myString = scan.next();
		int myInt = scan.nextInt();
		// prints out the reverse of the inputted word and integer
		System.out.println(reverse(myInt) + " " + reverse(myString));
	}

}


