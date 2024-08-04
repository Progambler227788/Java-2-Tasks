/*
// TODO: Write your name
// TODO: Write the date
Lab 6: Array Bounds
*/

import java.util.Scanner;

public class arrayBounds {

	// global desserts array for prevented()
	public static String[] desserts = new String[1];
	
	// Define counter as a class variable
    private static int counter = 0;

	// helper procedure for prevented() to add items to an array and double the size of the array if necessary
	private static void addDessert(String currentDessert) {
		//TODO 5. be proactive and only create a new desserts array if it is too big
		// Check if the current desserts array needs to be resized
        if (counter >= desserts.length) {
        // If it's too big, create a new array with increased capacity
        String[] newDesserts = new String[desserts.length * 2];
        // Copy the elements from the old array to the new one
        System.arraycopy(desserts, 0, newDesserts, 0, desserts.length);
        // Update the reference to point to the new array
        desserts = newDesserts;
        }
        
        desserts[counter++] = currentDessert;

	}

	// procedure that uses a proactive approach to adding items to an array and never encounters an exception
	private static void prevented() {
		Scanner scan = new Scanner(System.in);
		String currentDessert;
		while (true) {
			// print all items in global desserts array
			for (String dessert : desserts) {
				if (dessert != null) {
					System.out.print(dessert + ", ");
				}
			}
			System.out.println("\nEnter a dessert: ");
			currentDessert = scan.next();

			addDessert(currentDessert);

		}
	}

	// helper procedure for caught() that will double the size of an array that is too small
	public static String[] makeBigger(String[] original) {
		//TODO #3. the function should create a new array called tempArray that is double the length of original[]
        String[] tempArray = new String[original.length * 2];
		//TODO #4. the function should copy the contents of original[] to tempArray[]
		System.arraycopy(original, 0, tempArray, 0, original.length);
		return tempArray; //FIXME return tempArray.
	}

	// procedure that catches when array reaches it's bound limit with an exception
	public static void caught() {
		Scanner scan = new Scanner(System.in);
		String[] foods = new String[1];
		int counter = 0;
		String currentFood;
		while (true) {
			// print all items in foods array
			for (String food : foods) {
				if (food != null) {
					System.out.print(food + ", ");
				}
			}
			System.out.println("\nEnter a food: ");
			currentFood = scan.next();

			// TODO #2. implement a try-catch, where an ArrayIndexOutOfBoundsException is caught
			//  your try branch should be similar to unhandled()
			//  your catch branch should call a helper function makeBigger() (see to-do #3)
			//  assign the result of makeBigger to foods[]
			//  don't forget to increment the counter
			try{
			foods[counter] = currentFood;
			counter++;
			}
			catch (ArrayIndexOutOfBoundsException e) {
             foods = makeBigger(foods); 
            }
			    
			}

		}

	// procedure that will crash after user enters more than 3 foods
	public static void unhandled() {
		Scanner scan = new Scanner(System.in);
		String[] foods = new String[3];
		int counter = 0;
		String currentFood;
		while (true) {
			// print all items in foods array
			for (String food : foods) {
				if (food != null) {
					System.out.print(food + ", ");
				}
			}
			System.out.println("\nEnter a food: ");
			currentFood = scan.next();
			foods[counter] = currentFood;
			//TODO #1. insert currentFood at index position counter of foods[].
			counter++;

		}
	}

	// main procedure for program
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// query user with menu
		System.out.println("Pick a mode:");
		System.out.println("1 - Unhandled exception");
		System.out.println("2 - Caught exception (reactive)");
		System.out.println("3 - Prevented exception (proactive)");
		// process user's choice
		switch (scan.nextInt()) {
			case 1:
				unhandled();
				break;
			case 2:
				caught();
				break;
			case 3:
				prevented();
				break;
		}
	}
}
