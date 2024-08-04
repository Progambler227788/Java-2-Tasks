import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
// Maze Class
public class Maze {
	// Maze Class Variables
	private Graph graph;
	private int startNode;
	private int endNode;
	private int numCoins;

	// Maze Class Constructor
	/**
	 * Constructs a maze object
	 *
	 * @param inputFile The file name
	 */
	public Maze(String inputFile) throws MazeException {
		try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFile))) {
			readInput(inputReader);

		} catch (IOException | GraphException e) {
			throw new MazeException("Error reading input file: " + e.getMessage());
		}
	}

   // Function to Return graph
	public Graph getGraph() {
		return graph;
	}

	// Function to return iterator of solution of maze

	/**
	 * Solves the maze
	 * Used DFS function
	 */
	public Iterator<GraphNode> solve() {
		try {
            // Solution Found
            // No solution
            return DFS(numCoins, getGraph().getNode(startNode));
		} catch (GraphException e) {
			System.err.println("Error while solving maze: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Inserts an edge between two nodes in the graph.
	 *
	 * @param k Coins
	 * @param currentNode CurrentNode being used
	 * @throws GraphException if any issue occurs inside graph
	 */
	private Iterator<GraphNode> DFS(int k, GraphNode currentNode) throws GraphException {
		// If available coins less than 0 or node has visited
		if (k < 0 || currentNode == null || currentNode.isMarked()) {
			return null;
		}
       // Mark node as visited
		currentNode.mark(true);
	   // Check if End Node is there
		if (currentNode.getName() == endNode) {
			// Make Path
			List<GraphNode> path = new ArrayList<>();
			// Add end node there
			path.add(currentNode);
			return path.iterator();
		}

		// Make iterator of edge of current node
		Iterator<GraphEdge> edges = graph.incidentEdges(currentNode);
		// Iterate till next edge if exists
		while (edges.hasNext()) {
			// Fetch Edge
			GraphEdge edge = edges.next();
			int edgeCost = edge.getType();
			// -1 means or < k means, cost is less than available coins
			if (edgeCost == -1 || edgeCost <= k) {
				// Add Node to Path
				GraphNode nextNode = edge.firstEndpoint().getName() == currentNode.getName() ? edge.secondEndpoint() : edge.firstEndpoint();
				Iterator<GraphNode> nextPath = DFS(k - edgeCost, nextNode);
				if (nextPath != null) {
					List<GraphNode> path = new ArrayList<>();
					path.add(currentNode);
					while (nextPath.hasNext()) {
						path.add(nextPath.next());
					}
					return path.iterator();
				}
			}
		}
       // Mark as false and return null
		currentNode.mark(false);
		return null;
	}
	/**
	 * Function to read input from file to make graph
	 *
	 * @param inputReader BufferReader Object
	 * @throws IOException,GraphException if any issue occurs inside graph or in input file
	 */
private void readInput(BufferedReader inputReader) throws IOException, GraphException {


	// Read the scale factor, width, length, and number of coins
	int scale = Integer.parseInt(inputReader.readLine());
	int width = Integer.parseInt(inputReader.readLine());
	int length = Integer.parseInt(inputReader.readLine());
	numCoins = Integer.parseInt(inputReader.readLine());


	// Create a graph with total number of rooms
	graph = new Graph(width * length);


	List<String> linesArray = new ArrayList<>();
	int lengthOfArray = 0; // Initialize row counter to 0
	String line;

	// Store maze data lines
	while ((line = inputReader.readLine()) != null) {
		linesArray.add(line); // Store the current line in the array
		lengthOfArray ++;

	}
	// Initialize Variables

	int lengthString = linesArray.get(0).length();
	Integer[][] roomsAssigned = new Integer[lengthOfArray][lengthString + scale];
	int i = 0;
	int roomNumber= 0;

	//Making horizontal connections between nodes
	while ( lengthOfArray > 0) {
		line = linesArray.get(i); // Store the current line in the array

			for (int j = 0; j < lengthString; j++) {
				char cell = line.charAt(j);

				if (cell == 's') {
					startNode = roomNumber; // Set start node

				} else if (cell == 'x') {
					endNode = roomNumber; // Set end node
				}
                // Character is coin

					if (Character.isDigit(cell)) {
						int coinsRequired = Character.getNumericValue(cell);
						roomsAssigned[i][j - 1] = roomNumber;
						roomsAssigned[i][j + 1] = roomNumber + 1;

						insertEdges(roomNumber, ++roomNumber, coinsRequired, "door"); // Connect to room horizontal

                // Character is corridor
					} else if (cell == 'c') {
						roomsAssigned[i][j - 1] = roomNumber;
						roomsAssigned[i][j + 1] = roomNumber + 1;
						insertEdges(roomNumber, ++roomNumber, 0, "corridor"); // Connect to corridor horizontal
					}
					// wall case
					else if (cell == 'w') {
						++roomNumber;
					}

			}
		i+=2; // Move to the next row
		++roomNumber;
		lengthOfArray-=2;


	}

	// Re-Initialize variables

	i = 1;
	lengthString = linesArray.get(0).length();
	lengthOfArray = linesArray.size() - 1 ;

	//Making vertical connections between nodes
	while (lengthOfArray > 0) {

		line= linesArray.get(i); // Store the current line in the array


			for (int j = 0; j < lengthString; j+=2) {
				char cell = line.charAt(j);


					if (Character.isDigit(cell)) {
						int coinsRequired = Character.getNumericValue(cell);

						insertEdges(roomsAssigned[i-1][j], roomsAssigned[i+1][j], coinsRequired, "door"); // Connect to room horizontal


					} else if (cell == 'c') {
						insertEdges(roomsAssigned[i-1][j], roomsAssigned[i+1][j],  0, "corridor"); // Connect to corridor horizontal
					}
				}
		i+=2; // Move to the next row
		lengthOfArray-=2;

			}

}
	/**
	 * Inserts an edge between two nodes in the graph.
	 *
	 * @param node1 The first node.
	 * @param node2 The second node.
	 * @param linkType The type of the edge.
	 * @param label The label of the edge.
	 * @throws GraphException if one or both nodes do not exist in the graph, or if the edge already exists.
	 */
	private void insertEdges(int node1, int node2, int linkType, String label) throws GraphException {
		graph.insertEdge(graph.getNode(node1), graph.getNode(node2), linkType, label);
	}
}
