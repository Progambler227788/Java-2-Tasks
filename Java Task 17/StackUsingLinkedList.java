public class StackUsingLinkedList {
    private LinkedListNode top;
    private int size;
    private final int MAX;

    public StackUsingLinkedList(int capacity) {
        MAX = capacity;
        size = 0;
    }

    public boolean full() {
        return size == MAX;
    }

    public boolean empty() {
        return size == 0;
    }

    // Function to push elements into stack
    public void push(char newitem) {
        if (full()) {
            System.out.println("STACK IS FULL");
            return;
        }
        LinkedListNode newNode = new LinkedListNode(newitem);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Function to pop element from stack
    public char pop() {
        if (empty()) {
            System.out.println("STACK EMPTY");
            return (char) 0; // Return a default value for char
        }
        char data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Function to print all stack elements
    public void print() {
        System.out.print("Stack: ");
        LinkedListNode current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public char peek() {
        if (!empty())
            return top.data;
        return (char) 0; // Return a default value for char
    }
    
    
    private class LinkedListNode {
        char data;
        LinkedListNode next;

        LinkedListNode(char data) {
            this.data = data;
            this.next = null;
        }
    }

}
