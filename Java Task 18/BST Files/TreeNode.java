
// Class to hold nodes
public class TreeNode {
	// Key as data
    String key;
    // Left and Right Child
    TreeNode left, right;
    
    // Function to store data and assign children as null by default
    public TreeNode(String item) {
        key = item;
        left = right = null;
    }
}
