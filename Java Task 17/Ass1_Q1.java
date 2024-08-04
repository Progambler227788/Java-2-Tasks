import java.util.*;

// Ass1_Q1 Class
public class Ass1_Q1{
    private int length;
    private LinkedList front, rear;
 
    //  This will create an empty que with length 0
    public Ass1_Q1(){
         length = 0;
         front = rear = null;
    }

    //  Adds the specified data to the rear of the queue.
    public void enQueue (int data){
         LinkedList node = new LinkedList(data);
         if (isEmpty())
              front = node;
         else
              rear.setNextPointer (node);
         rear = node;
         length++;
    }

    //  Removes the data at the front of the queue and returns a
    //  reference to it. Throws an Exception if the
    //  queue is empty.

    public int deQueue() throws Exception{
        if (isEmpty())
              throw new Exception ("Que is Empty");
        int resultOfNode = front.getDataOfNode();
        // Replace front node of que with next node of previous front node
        front = front.getNextPointer();
        // Decrease length of que
        length--;
        // If que becomes empty then rear will be null, front=null is not used because above line will already will give it null 
        if (isEmpty())
             rear = null;
        // Return data of removed node
        return resultOfNode;
    }

    // Function to return first node data
    public int first() throws Exception{
        if (isEmpty())
            throw new Exception("Que is Empty"); 

        return front.getDataOfNode();
    }
    //  Returns true if this queue is empty and false otherwise. 
    public boolean isEmpty(){
         return (length == 0);
    }
   //  Returns the number of elements in this queue.
    public int size(){
        return length;
    }
    
    
      //  Returns a string representation of this queue. 
    public int getmin() throws Exception{

        if (isEmpty())
            throw new Exception("Que is Empty"); 

        int resultOfNode = Integer.MAX_VALUE;
        // Store first node
        LinkedList currentNode = front;
        // Iterate till last node
        while (currentNode != null){
            if(currentNode.getDataOfNode() < resultOfNode)
              resultOfNode = currentNode.getDataOfNode();
              
            currentNode = currentNode.getNextPointer(); // Move to next node
        }
        return resultOfNode;
    }
 

    //  It is used to print que
    public String toString(){
    	if (isEmpty())
            return ("Que is Empty"); 
    	
        String resultOfNode = "";
        LinkedList currentNode = front;
        while (currentNode != null){
            resultOfNode = resultOfNode + currentNode.toString() + "\n";
            currentNode = currentNode.getNextPointer();
        }
        return resultOfNode;
    }
 
    public static void main(String[] args) {
    	  // Creates scannerObj object
    	  Scanner scannerObj = new Scanner(System.in);
    	  // Creating object on heap memory 
          Ass1_Q1 queElements = new Ass1_Q1();
          // To store user menu choice
          int choice;
          do {
        	  // Display information of menu to console screen
              System.out.println("\nWelcome !!! Que Menu:");
              System.out.println("Press 1. -> Enqueue the Element");
              System.out.println("Press 2. -> Dequeue the Element");
              System.out.println("Press 3. -> Get Minimum from Que");
              System.out.println("Press 4. -> Print Que Elements");
              System.out.println("Press 5. -> Exit from our Program (:");
              System.out.print("Hey there, Please Enter your choice: ");
              
              // Get user choice
              choice = scannerObj.nextInt();

              switch (choice) {
              // Add element to que using enque operation
                  case 1:
                      System.out.print("Enter element to enqueue: ");
                      int element = scannerObj.nextInt();
                      queElements.enQueue(element);
                      System.out.println("Element enqueued successfully.");
                      break;
             // Delete element to que using deque operation
                  case 2:
                      try {
                          queElements.deQueue();
                          System.out.println("Element dequeued successfully.");
                      } catch (Exception e) {
                          System.out.println(e.getMessage());
                      }
                      break;
              // Print smallest element from getmin() function
                  case 3:
                      try {
                          System.out.println("Smallest Element of Que: " + queElements.getmin());
                      } catch (Exception e) {
                          System.out.println(e.getMessage());
                      }
                      break;
                      
                  case 4:
                	  System.out.println("Elements given below: ");
                	  System.out.println(queElements.toString());
                      break;
                  case 5:
                      System.out.println("Thank you for using!!! Exiting...");
                      break;
                  default:
                      System.out.println("Ooops!!! Invalid choice. Try again!!!. ->>>> Please enter a valid option.");
              }

          } while (choice != 5); // Iterate til choice is not 4
          
          // Clear memory leakages
          scannerObj.close();
      
    }
}