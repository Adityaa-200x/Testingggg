import java.util.Arrays;

public class HamiltonianPath {

    private final int numVertices;
    private final int[] path;
    private final int[][] graph;

    public HamiltonianPath(int[][] graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph adjacency matrix cannot be null.");
        }
        if (graph.length == 0) {
            throw new IllegalArgumentException("Graph must contain at least one vertex.");
        }
        // Ensure the matrix is square
        for (int[] row : graph) {
            if (row == null || row.length != graph.length) {
                throw new IllegalArgumentException("Graph adjacency matrix must be square and non-null rows.");
            }
        }
        this.graph = graph;
        this.numVertices = graph.length;
        this.path = new int[numVertices];
        Arrays.fill(this.path, -1);
    }

    // Solves the Hamiltonian Path problem
    public boolean solve() {
        // Try starting the path from every vertex
        for (int startVertex = 0; startVertex < numVertices; startVertex++) {
            path[0] = startVertex;
            if (findPath(1)) {
                return true;
            }
            // Reset path array if search from startVertex fails
            Arrays.fill(path, -1);
        }
        return false;
    }

    private boolean findPath(int pos) {
        // Base Case: All vertices are included in the path
        if (pos == numVertices) {
            return true;
        }

        // Try candidate vertices for the current position in the path
        for (int v = 0; v < numVertices; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;

                // Recur to build the rest of the path
                if (findPath(pos + 1)) {
                    return true;
                }

                // Backtrack
                path[pos] = -1;
            }
        }

        return false;
    }

    private boolean isSafe(int v, int pos) {
        // Check if there is an edge from the last added vertex to vertex v
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if vertex v has already been visited in the current path
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    public void displayPath() {
        if (solve()) {
            System.out.print("Hamiltonian Path Found: ");
            for (int i = 0; i < numVertices; i++) {
                System.out.print(path[i] + (i < numVertices - 1 ? " -> " : ""));
            }
            System.out.println();
        } else {
            System.out.println("No Hamiltonian Path exists for the given graph.");
        }
    }

    public static void main(String[] args) {
        /* Example Graph represented as an Adjacency Matrix:
           (0)---(1)---(2)
            |   / \   /
            |  /   \ /
           (3)------(4)
        */
        int[][] graph1 = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
        };

        HamiltonianPath hp = new HamiltonianPath(graph1);
        hp.displayPath();
    }
}