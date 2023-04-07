import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int K;   // Anzahl an Knoten
    private boolean[][] adj;   // Adjazenzmatrix

    // Konstruktor
    public Graph(int K) {
        this.K = K;
        adj = new boolean[K][K];
    }

    // fügt eine Kante zur Adjazenzmatrix hinzu
    public void addEdge(int k, int l) {
        adj[k][l] = true;
        adj[l][k] = true;
    }

    // löscht eine Kante aus der Adjazenzmatrix
    public void removeEdge(int k, int l) {
        adj[k][l] = false;
        adj[l][k] = false;
    }

    // prüft nach einer Kante zwischen zwei Knoten
    public boolean hasEdge(int k, int l) {
        return adj[k][l];
    }

    
    
    // bestimmt die Nachbarn eines Knotens
    public List<Integer> getNeighbors(int k) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < K; i++) {
            if (adj[k][i] && k != i) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
    
    public List<Integer> getNeighborsUpdated(int k, boolean[] visited) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < K; i++) {
            if (adj[k][i] && k != i && !visited[i]) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    
    // gibt die Adjazenzmatrix in der Konsole aus
    public void print() {
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                System.out.print(adj[i][j] ? "1 " : "0 ");  // 1 -> Kante
            }
            System.out.println();
        }
    }

    public List<Integer> getMaximumIndependentSet() {
        
        List<Integer> independentSet = new ArrayList<Integer>();
        List<Integer> degrees = new ArrayList<Integer>();
        boolean[] visited = new boolean[K];
        
        // Initialize the degrees and visited arrays
        for (int i = 0; i < K; i++) {
            degrees.add(getNeighbors(i).size());    // the degree is the number of neighbors
            visited[i] = false;                     // array to check if a vertex has been visited
        }
        
        System.out.println("checkpoint");

        // I call it the Minimal Cardinality Search
        for (int i = 0; i < K; i++) {

            // Find the vertex with the lowest degree that has not been visited
            int minDegree = Integer.MAX_VALUE;
            int minVertex = -1;
            for (int j = 0; j < K; j++) {
                int currentDegree = degrees.get(j);
                if (!visited[j] && currentDegree < minDegree) {
                    minDegree = currentDegree;
                    minVertex = j;
                }
            }
            
            
            // Add the vertex to the independent set
            if (minVertex >= 0 && !visited[minVertex] ) {

                independentSet.add(minVertex);
                
                // Remove the vertex and its neighbors from further consideration
                visited[minVertex] = true;
                for (int neighbor : getNeighbors(minVertex)) {
                    visited[neighbor] = true;
                }
            }
            
            // update the degrees of the remaining vertices
            for (int j = 0; j < K; j++) {
                degrees.remove(j);
                degrees.add(getNeighborsUpdated(j, visited).size());
            }
            
        }
        
        // Print the maximum independent set to the console
        System.out.print("Maximum Independent Set: { ");
        for (int i = 0; i < independentSet.size(); i++) {
            System.out.print(independentSet.get(i));
            if (i != independentSet.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");

        return independentSet;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.print();
        graph.getMaximumIndependentSet();
    }
}