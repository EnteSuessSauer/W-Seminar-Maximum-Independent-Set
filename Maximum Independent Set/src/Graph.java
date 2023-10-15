import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private int K;   // Anzahl an Knoten
    private boolean[][] adj;   // Adjazenzmatrix

    private Random random;

    // Konstruktor
    public Graph(int K) {
        this.K = K;
        adj = new boolean[K][K];
        random = new Random();
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

    
    public void generateGraph(int groesse) {
        int edges = random.nextInt(groesse * ((groesse - 1) / 2)) + (int) Math.floor(groesse / 2);   //Anzahl der Kanten in generierten Graphen
        for (int i = 0; i < edges; i++) {
            
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