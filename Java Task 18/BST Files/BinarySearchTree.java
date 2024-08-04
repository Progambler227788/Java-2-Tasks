
// Binary Search Tree Implementation
public class BinarySearchTree {
	// Make root node
    TreeNode root;
    
    // Assign root node as null when object created on heap
    public BinarySearchTree () {
        root = null;
    }

    // Function to insert key in tree
    public void insert(String key) {
        root = insertRecursive(root, key);
    }
    
    public boolean checkEmpty() {
    	return root==null;
    }
   
    // Recursively move from tree to find position for key
    private TreeNode insertRecursive(TreeNode root, String key) {
    	// If root is null, then key with node will be our new root as first node
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        // If value is less than current node key then it will be as left child
        if (key.compareTo(root.key) < 0)
        	// Check further for left nodes
            root.left = insertRecursive(root.left, key);
        // If value is greater than current node key then it will be as right child
        else if (key.compareTo(root.key) > 0)
        	// Check further for right nodes
            root.right = insertRecursive(root.right, key);
     // Return updated root node with chain of leaf nodes
        return root;
    }

   
    //Function to return count of nodes
    public int count() {
    	// Recursively calculating count of nodes
        return countRecursive(root);
    }
   
    // Checking count 
    private int countRecursive(TreeNode root) {
    	// If no node, then count is 0
        if (root == null)
            return 0;
         // 1 is for root, left recursive call for left sub tree, right for right sub tree
       return 1 + countRecursive(root.left) + countRecursive(root.right);
    }
   
    // Function to get height of tree ( root to last leaf node)
    public int height() {
        return heightRecursive(root)+1;
    }

    private int heightRecursive(TreeNode root) {
    	// If root is null, no height
        if (root == null)
            return -1;
        // It will check either left subtree has more leaf nodes or right subtree for given root node and then add 1 for root
        return 1 + Math.max(heightRecursive(root.left), heightRecursive(root.right));
    }


    // Calling Tree Print method to print elements of tree in shape of tree
    public void treePrint() {
        treePrintRecursive(root,"");
    }
    
    // indent will be appended by tab spaces for space between child nodes
    private void treePrintRecursive(TreeNode root, String indent) {
        if (root != null) {
            treePrintRecursive(root.right, indent + "\t");
            System.out.println(indent + root.key);
            treePrintRecursive(root.left, indent + "\t");
        }
    }

    // Function to Print levels of BST
    public void levelPrint() {
        int h = height();
        for (int i = 0; i < h; i++) {
            levelPrintRecursive(root, i);
            System.out.println();
        }
    }
    // Using breadth traversing to print all levels
    private void levelPrintRecursive(TreeNode root, int level) {
    	// No node is there
        if (root == null)
            return;
        // Starting level means only root node
        if (level == 0)
            System.out.print(root.key + " ");
        else if (level > 0) {
        	// Check subtrees as well
            levelPrintRecursive(root.left, level - 1);
            levelPrintRecursive(root.right, level - 1);
        }
    }
    // Function to delete a key from subtree
    public void delete(String key) {
        root = deleteRecursive(root, key);
    }

    private TreeNode deleteRecursive(TreeNode root, String key) {
    	// Key not found case
        if (root == null) {
        	System.out.println(key +" does not exist in the tree!");
   
            return root;
        }
        // Key is in left sub tree
        if (key.compareTo(root.key) < 0)
            root.left = deleteRecursive(root.left, key);
       // Key is in right sub tree
        else if (key.compareTo(root.key) > 0)
            root.right = deleteRecursive(root.right, key);
     // Key found case
        else {
        	// Check leaf nodes for key to be deleted
        	
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // This line of Code is used to find node smallest in the right subtree
            root.key = minValue(root.right);

            // This line of Code is used to Delete the inorder successor
            root.right = deleteRecursive(root.right, root.key);
        }

        return root; // return updated root node
    }
   
    // Fetch smallest node
    private String minValue(TreeNode root) {
    	// Store root key 
        String minimumNode = root.key;
        // Iterate until left node is not null
        while (root.left != null) {
        	// Get left node key
        	minimumNode = root.left.key;
            // Move to left for continue iteration
            root = root.left;
        }
        // Return minimumNode
        return minimumNode;
    }


    public static void main(String[] args) {
    	BinarySearchTree  bst = new BinarySearchTree ();

        // Sample Data Usage
        bst.insert("every");
        bst.insert("boy");
        bst.insert("good");
        bst.insert("eats");
        bst.insert("breakfast");

        System.out.println("Count: " + bst.count());
        System.out.println("Height: " + bst.height());

        System.out.println("Tree Print:");
        bst.treePrint();

        System.out.println("Level Print:");
        bst.levelPrint();
        
        bst.delete("every");

        bst.delete("boy");
        
        bst.delete("fine");
        
        System.out.println("Tree Print:");
        bst.treePrint();

        System.out.println("Level Print:");
        bst.levelPrint();
    }
}

