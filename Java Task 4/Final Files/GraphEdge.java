/**
 * A class representing an edge in a graph.
 */
public class GraphEdge {
	private final GraphNode u; // The first endpoint of the edge
	private final GraphNode v; // The second endpoint of the edge
	private int type; // The type of the edge
	private String label; // The label of the edge

	/**
	 * Constructs an edge between two nodes with a specified type and label.
	 *
	 * @param u     The first endpoint of the edge.
	 * @param v     The second endpoint of the edge.
	 * @param type  The type of the edge.
	 * @param label The label of the edge.
	 */
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.u = u;
		this.v = v;
		this.type = type;
		setType(type);
		this.label = label;
		setLabel(getLabel());
	}

	/**
	 * Retrieves the first endpoint of the edge.
	 *
	 * @return The first endpoint of the edge.
	 */
	public GraphNode firstEndpoint() {
		return u;
	}

	/**
	 * Retrieves the second endpoint of the edge.
	 *
	 * @return The second endpoint of the edge.
	 */
	public GraphNode secondEndpoint() {
		return v;
	}

	/**
	 * Retrieves the type of the edge.
	 *
	 * @return The type of the edge.
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type of the edge to a new value.
	 *
	 * @param newType The new type of the edge.
	 */
	public void setType(int newType) {
		this.type = newType;
	}

	/**
	 * Retrieves the label of the edge.
	 *
	 * @return The label of the edge.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label of the edge to a new value.
	 *
	 * @param newLabel The new label of the edge.
	 */
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}
}
