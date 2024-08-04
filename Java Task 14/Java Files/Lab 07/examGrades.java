/*
Author:  Cao
Date: 10-05-2024
Project: Complex Word
*/
import java.util.Scanner;

public class examGrades {

	static int[][] classGrades = new int[5][3];

	// Function to display all grades for a class
	public static void displayAllGrades() {
		for (int i = 0; i < classGrades.length; i++) {
			lookupGrade(i + 1); // Calling lookupGrade for each student
		}
	}

	// This method or function or module will update grade of student
	// using student as row and exam as column in 2D array named as
	// classGrades as member of examGrades class
	public static void updateGrade( int grade, int student, int exam) {
		classGrades[student - 1][exam - 1] = grade; // Modify the grade of student
	}

	// This method will display exams scores and average of exams scores for individual student only
	public static void lookupGrade(int student) {
		double sumOfMarks = 0;
		System.out.println("Grades for Student " + student + ":");
		for (int iteration = 0; iteration < 3; iteration+=1) {
			// (iteration+1) after Exam: is done because in real life numbering is usually from 1 to onwards
			// student-1 is done because indexing starts from 0.
			System.out.println("Exam: " + (iteration + 1) + ": " + classGrades[student - 1][iteration]);
			sumOfMarks += classGrades[student - 1][iteration]; //Add up the marks of exams
		}
		// Total exams are 3 for each students (1-5)
		double totalExams =  3;
		String average = String.format("%.2f", sumOfMarks / totalExams);
		System.out.println("Average of exams marks of student number: " + student + " is -> " + average  );
	}

	public static void main(String[] args) {
        
		// Make scanner class object
		Scanner scanner = new Scanner(System.in);
        // Iterate for continuous input
		while (true) {
			System.out.println("Hey there, Please choose an option:");
			System.out.println("1. Press 1 to Update a grade of Student");
			System.out.println("2. Press 2 to View all grades of Students");
			System.out.println("3. Press 3 to View grades for only individual student");
			System.out.println("4. Press 4 to Quit from program");
			System.out.println("Enter choice: ");
           
			// Get user entered choice
			int choice = scanner.nextInt();
           
			// Use switch case statement for each user entered choice
			switch (choice) {
			// In case of 1, update grade will be executed
				case 1:
					// Get grade, student and exam number
					System.out.println("Hey, Please Enter student number (1-5): ");
					int student = scanner.nextInt();
					System.out.println("Hey, Please Enter exam number (1-3): ");
					int exam = scanner.nextInt();
					System.out.println("Hey, Please Enter grade: ");
					int grade = scanner.nextInt();
					updateGrade(grade, student, exam);
					break;
				case 2:
					// Display all grades of each student
					displayAllGrades();
					break;
				case 3:
					// Display only specific student grade
					System.out.println("Enter student number (1-5):");
					int studentNumber = scanner.nextInt();
					lookupGrade(studentNumber);
					break;
				case 4:
					// Exit from program
					System.out.println("Hey, Thank you for using. Have a good day. Exit Success done.");
					System.exit(0);
					break;
				default:
					// Handle invalid input case
					System.out.println("): Oh, you have entered invalid choice. Please enter a valid option so that you can use program. Thank you for understanding.");
			}
		}
	}
}

