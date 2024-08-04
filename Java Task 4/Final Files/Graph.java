import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A class representing a graph.
 */
public class Graph implements GraphADT {

	private final Map<Integer, GraphNode> nodes;
	private final List<List<List<GraphEdge>>> adjacencyList;

	/**
	 * Constructs a graph with a specified number of nodes.
	 *
	 * @param n The number of nodes in the graph.
	 */
	public Graph(int n) {
		nodes = new HashMap<>();
		adjacencyList = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			List<List<GraphEdge>> nodeAdjList = new ArrayList<>(n);
			for (int j = 0; j < n; j++) {
				nodeAdjList.add(new ArrayList<>());
			}
			adjacencyList.add(nodeAdjList);
		}
		// Creating nodes with names 0 to n-1
		for (int i = 0; i < n; i++) {
			nodes.put(i, new GraphNode(i));
		}
	}

	/**
	 * Inserts an edge between two nodes in the graph.
	 *
	 * @param nodeu The first node.
	 * @param nodev The second node.
	 * @param type  The type of the edge.
	 * @param label The label of the edge.
	 * @throws GraphException if one or both nodes do not exist in the graph, or if the edge already exists.
	 */
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
		if (!nodes.containsValue(nodeu) || !nodes.containsValue(nodev)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		int u = nodeu.getName();
		int v = nodev.getName();

		if (!adjacencyList.get(u).get(v).isEmpty() || !adjacencyList.get(v).get(u).isEmpty()) {
			throw new GraphException("Edge already exists between the given nodes.");
		}
		GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);
		adjacencyList.get(u).get(v).add(edge);
		adjacencyList.get(v).get(u).add(edge);
	}

	/**
	 * Retrieves a node from the graph based on its name.
	 *
	 * @param u The name of the node.
	 * @return The node with the specified name.
	 * @throws GraphException if the node with the specified name does not exist.
	 */
	@Override
	public GraphNode getNode(int u) throws GraphException {
		if (!nodes.containsKey(u)) {
			throw new GraphException("Node with the specified name does not exist.");
		}
		return nodes.get(u);
	}

	/**
	 * Retrieves an iterator over the incident edges of a node.
	 *
	 * @param u The node.
	 * @return An iterator over the incident edges of the node.
	 * @throws GraphException if the node is not part of the graph.
	 */
	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
		if (!nodes.containsValue(u)) {
			throw new GraphException("Node is not part of the graph.");
		}
		int index = u.getName();
		List<GraphEdge> incidentEdges = new ArrayList<>();
		for (List<GraphEdge> edgeList : adjacencyList.get(index)) {
			incidentEdges.addAll(edgeList);
		}
		return incidentEdges.iterator();
	}

	/**
	 * Retrieves the edge between two nodes.
	 *
	 * @param u The first node.
	 * @param v The second node.
	 * @return The edge between the two nodes.
	 * @throws GraphException if one or both nodes do not exist in the graph, or if no edge exists between them.
	 */
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		if (!nodes.containsValue(u) || !nodes.containsValue(v)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		int i = u.getName();
		int j = v.getName();
		if (adjacencyList.get(i).get(j).isEmpty()) {
			throw new GraphException("No edge exists between the given nodes.");
		}
		return adjacencyList.get(i).get(j).get(0);
	}

	/**
	 * Checks if two nodes are adjacent.
	 *
	 * @param u The first node.
	 * @param v The second node.
	 * @return true if the nodes are adjacent, false otherwise.
	 * @throws GraphException if one or both nodes do not exist in the graph.
	 */
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		if (!nodes.containsValue(u) || !nodes.containsValue(v)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		int i = u.getName();
		int j = v.getName();
		return !adjacencyList.get(i).get(j).isEmpty();
	}
}
