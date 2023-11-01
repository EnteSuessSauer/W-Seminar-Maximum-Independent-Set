import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    // Anzahl an Knoten
    private int order;
    // Adjazenzmatrix
    private boolean[][] adj;


    /**
     * Erstellt ein Graph-Objekt, setzt dabei die Größe des Graphs auf 0
     */
    public Graph() {
        order = 0;
        adj = new boolean[order][order];
    }
    
    /**
     * Initialisiert den Graphen auf eine bestimmte Größe
     * @param order = Größe des Graphs
     */
    public void initialiseGraph(int order) {
        this.order = order;
        adj = new boolean[order][order];
    }
    
    /**
     * Setzt die Größe und die Adjazenzmatrix des Graphen zurück
     */
    public void reset() {
        order = 0;
        adj = new boolean[order][order];
    }

    /**
     * Generiert zufällig die Kanten des ungerichteten Graphen
     */
    public void generate() {
        Random random = new Random();

        // Generiere zufällige Kanten
        for (int i = 0; i < order; i++) {
            for (int j = i + 1; j < order; j++) {
                boolean randomEdge = random.nextBoolean(); // 0 oder 1
                if (randomEdge) {
                    addEdge(i, j);
                } else {
                    removeEdge(i, j);
                }
            }
        }
    }

    /**
     * Fügt dem Graphen eine ungerichtete Kante zur Adjazenzmatrix hinzu
     * @param k Anfangsknoten
     * @param l Endknoten
     */
    public void addEdge(int k, int l) {
        adj[k][l] = true;
        adj[l][k] = true;
    }

    /**
     * Entfernt eine Kante des Graphen
     * @param k Anfangsknoten
     * @param l Endknoten
     */
    public void removeEdge(int k, int l) {
        adj[k][l] = false;
        adj[l][k] = false;
    }

    /**
     * Prüft nach einer Kante zwischen zwei Knoten
     * @param k Anfangsknoten
     * @param l Endknoten
     * @return true, wenn es eine Kante zwischen den Knoten gibts
     */
    public boolean hasEdge(int k, int l) {
        return adj[k][l];
    }

    /**
     * Gibt die Größe des Graphs aus
     * @return Größe des Graphs
     */
    public int getOrder() {
        return order;
    }

    /**
     * Wandelt den Graphen zu seinem Komplementgraphen um
     */
    public void inverseGraph() {
        boolean[][] inversed = new boolean[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                if (!inversed[i][j]) {
                    if (hasEdge(i, j)) {
                        removeEdge(i, j);
                    } else {
                        addEdge(i, j);
                    }
                    inversed[i][j] = true;
                    inversed[j][i] = true;
                }
            }
        }
    }

    
    
    /**
     * Bestimmt alle Nachbarn eines Knotens
     * @param k Knoten
     * @return Liste alle Nachbarn des Knotens k
     */
    public List<Integer> getNeighbors(int k) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < order; i++) {
            if (adj[k][i] && k != i) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
    
    /**
     * Bestimmt alle Nachbarn eines Knotens
     * @param k Knoten
     * @param visited Array der bereits besuchten Knoten 
     * @return Liste alle unbesuchten Nachbarn des Knotens k
     */
    public List<Integer> getNeighborsUpdated(int k, boolean[] visited) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < order; i++) {
            if (adj[k][i] && k != i && !visited[i]) {
                neighbors.add((Integer) i);
            }
        }
        return neighbors;
    }

    /**
     * Gibt die Adjazenzmatrix in der Konsole aus
     */
    public void print() {
        System.out.println("");
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                System.out.print(adj[i][j] ? "1 " : "0 ");  // 1 -> Kante
            }
            System.out.println();
        }
    }

    /**
     * Gibt ein Maximaum Independent Set in der Konsole aus
     * @param independentSet Maximum Independent Set
     */
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