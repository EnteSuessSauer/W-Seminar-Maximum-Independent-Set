import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        int edges = random.nextInt((int) Math.floor(groesse / 2), groesse * ((groesse - 1) / 2));   //Anzahl der Kanten in generierten Graphen
    }

    public List<Integer> getMaximumIndependentSet() {
        
        List<Integer> independentSet = new ArrayList<Integer>();
        List<Integer> degrees = new ArrayList<Integer>();
        boolean[] visited = new boolean[K];
        
        // Initialisiere degrees und visited
        for (int i = 0; i < K; i++) {
            degrees.add(getNeighbors(i).size());    // der Grad eines Knotens entspricht der Anzahl der Nachbarn
            visited[i] = false;                     // Array für alle besuchten Knoten
        }
        

        // Ich nenne es the Minimal Cardinality Search algorithm
        for (int i = 0; i < K; i++) {

            // Such den Knoten mit dem kleinsten Grad, der noch nicht besucht wurde
            int minDegree = Integer.MAX_VALUE;
            int minVertex = -1;
            for (int j = 0; j < K; j++) {
                int currentDegree = degrees.get(j);
                if (!visited[j] && currentDegree < minDegree) {
                    minDegree = currentDegree;
                    minVertex = j;
                }
            }
            
            
            // Füge diesen Knoten zum Maximum Independet Set hinzu
            if (minVertex >= 0 && !visited[minVertex] ) {

                independentSet.add(minVertex);
                
                // Lösche den Knoten und seine Nachbarn aus dem weiteren Vorgehen
                visited[minVertex] = true;
                for (int neighbor : getNeighbors(minVertex)) {
                    visited[neighbor] = true;
                }
            }
            
            // Aktualisiere die Grade der Knoten
            for (int j = 0; j < K; j++) {
                degrees.remove(j);
                degrees.add(getNeighborsUpdated(j, visited).size());
            }
            
        }
        
        // Gib das Maxmium Indepent Set in der Konsole aus
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