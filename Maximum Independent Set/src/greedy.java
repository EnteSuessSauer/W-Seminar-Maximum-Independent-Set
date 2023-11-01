import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private Graph graph;
    
    /**
     * Erstellt ein Greedy-Objekt, setzt den Graph auf den mitgegebenen Graph 
     * @param graph Graph
     */
    public Greedy(Graph graph) {
        this.graph = graph;
    }

    /** 
     * Lässt den Greedy-Algorithmus auf dem Graphen laufen
     * @return das gefundene potenziale Maximum Independent Set
     */
    public List<Integer> runAlg() {

        List<Integer> independentSet = new ArrayList<Integer>();
        int order = graph.getOrder();
        int[] degrees = new int[order];
        boolean[] visited = new boolean[order];
        
        // Initialisiere degrees und visited
        for (int i = 0; i < order; i++) {
            degrees[i] = graph.getNeighbors(i).size();    // der Grad eines Knotens entspricht der Anzahl der Nachbarn
            visited[i] = false;                           // Array für alle besuchten Knoten
        }
        
        for (int i = 0; i < order + 1; i++) {

            // Such den Knoten mit dem kleinsten Grad, der noch nicht besucht wurde
            int minDegree = Integer.MAX_VALUE;
            int minDegreeVertex = -1;
            for (int j = 0; j < order; j++) {
                int currentDegree = degrees[j];
                if (!visited[j] && currentDegree < minDegree) {
                    minDegree = currentDegree;
                    minDegreeVertex = j;
                }
            }
            
            
            // Füge diesen Knoten zum Maximum Independet Set hinzu
            if (minDegreeVertex >= 0 && !visited[minDegreeVertex] ) {

                independentSet.add(minDegreeVertex);
                
                // Lösche den Knoten und seine Nachbarn aus dem weiteren Vorgehen
                visited[minDegreeVertex] = true;
                for (int neighbor : graph.getNeighbors(minDegreeVertex)) {
                    visited[neighbor] = true;
                }
            }
            
            // Aktualisiere die Grade der Knoten
            for (int j = 0; j < order; j++) {
                degrees[j] = graph.getNeighborsUpdated(j, visited).size();
            }
        }

        return independentSet;
    }
}