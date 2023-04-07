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
    public void addEdge(int v, int w) {
        adj[v][w] = true;
        adj[w][v] = true;
    }

    // löscht eine Kante aus der Adjazenzmatrix
    public void removeEdge(int v, int w) {
        adj[v][w] = false;
        adj[w][v] = false;
    }

    // prüft nach einer Kante zwischen zwei Knoten
    public boolean hasEdge(int v, int w) {
        return adj[v][w];
    }

    // bestimmt die Nachbarn eines Knotens
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < K; i++) {
            if (adj[v][i]) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    
    /**
     *  gibt die Adjazenzmatrix in der Konsole aus
     */
    public void print() {
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                System.out.print(adj[i][j] ? "1 " : "0 ");  // 1 -> Kante
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.print();
    }
}