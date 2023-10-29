import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private int K;   // Anzahl an Knoten
    private boolean[][] adj;   // Adjazenzmatrix


    // Konstruktor
    public Graph() {
        K = 0;
        adj = new boolean[K][K];
    }
    
    public void initialiseGraph(int K) {
        this.K = K;
        adj = new boolean[K][K];
    }

    public void reset() {
        K = 0;
        adj = new boolean[K][K];
    }

    public void generate(int size) {
        this.K = size;
        this.adj = new boolean[K][K];
        Random random = new Random();

        // Generating random edges
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int randomEdge = random.nextInt(2); // 0 or 1
                if (randomEdge == 1) {
                    addEdge(i, j);
                } else {
                    removeEdge(i, j);
                }
            }
        }
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

    public int getOrder() {
        return K;
    }

    public void inverseGraph() {
        boolean[][] inversed = new boolean[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                if (hasEdge(i, j) && !inversed[i][j]) {
                    removeEdge(i, j);
                    inversed[i][j] = true;
                    inversed[j][i] = true;
                } else if (!hasEdge(i, j) && !inversed[i][j]) {
                    addEdge(i, j);
                    inversed[i][j] = true;
                    inversed[j][i] = true;
                }
            }
        }
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
        System.out.println("");
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                System.out.print(adj[i][j] ? "1 " : "0 ");  // 1 -> Kante
            }
            System.out.println();
        }
    }


    public void printMIS(List<Integer> independentSet) {
        System.out.print("Maximum Independent Set: { ");
        for (int i = 0; i < independentSet.size(); i++) {
            System.out.print(independentSet.get(i));
            if (i != independentSet.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }

}