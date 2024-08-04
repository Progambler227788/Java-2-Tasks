/**
 * A class representing a node in a graph.
 */
public class GraphNode {
	private final int name; // The name of the node
	private boolean marked; // Indicates if the node has been marked during graph traversal

	/**
	 * Constructs a graph node with a specified name.
	 *
	 * @param name The name of the node.
	 */
	public GraphNode(int name) {
		this.name = name;
		this.marked = false; // By default, node is not marked
	}

	/**
	 * Marks or unmarked the node.
	 *
	 * @param mark True if the node should be marked, false otherwise.
	 */
	public void mark(boolean mark) {
		this.marked = mark;
	}

	/**
	 * Checks if the node is marked.
	 *
	 * @return True if the node is marked, false otherwise.
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * Retrieves the name of the node.
	 *
	 * @return The name of the node.
	 */
	public int getName() {
		return name;
	}
}
