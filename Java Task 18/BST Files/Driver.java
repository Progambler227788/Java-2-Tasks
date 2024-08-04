import java.util.Scanner;

// Class to test functionality of BST with Commands
public class Driver {
    public static void main(String[] args) {
    	// Create object on heap memory
        BinarySearchTree bst = new BinarySearchTree();
        // Use scanner object to utilize input functionality of system calls
        Scanner scanner = new Scanner(System.in);
        // get command from user
        String command;
       // Using do while loop
        do {
        	// Print list of commands to user
            System.out.println("Enter command (emptyTest, insert <key>, count, height, treePrint, levelPrint, delete <key>, stop): ");
            command = scanner.next(); // Get command

            switch (command) {
            // Check which command entered by user 
                case "emptyTest":
                	// Tree is empty
                    if (bst.checkEmpty())
                        System.out.println("The tree is empty.");
                    else
                        System.out.println("The tree is not empty.");
                    break;
                   
                case "insert":
                	// Insert data in tree
                    String key = scanner.next();
                    bst.insert(key);
                    System.out.println("\"" + key + "\" has been placed in the tree.");
                    break;
                case "count":
                	// Get count
                    System.out.println("The tree contains " + bst.count() + " elements.");
                    break;
                case "height":
                	// Get height
                    System.out.println("The height of the tree is: " + bst.height());
                    break;
                case "treePrint":
                	// Print tree
                    System.out.println("The tree looks like this:");
                    bst.treePrint();
                    break;
                case "levelPrint":
                	// Print levels
                    System.out.println("The levels of the tree look like this:");
                    bst.levelPrint();
                    break;
                case "delete":
                	// Delete the key
                    String keyToDelete = scanner.next();
                    bst.delete(keyToDelete);
                    System.out.println("\"" + keyToDelete + "\" has been removed from the tree.");
                    break;
                case "stop":
                	// Exit from program
                    System.out.println("All done. Goodbye.");
                    break;
                default:
                    System.out.println("OOPS!!! Hey It is Invalid command. So, Please enter a valid command.");
                    break;
            }

        } 
        while (!command.equals("stop"));

        scanner.close(); // Clear memory leakages by closing scanner object
    }
}

