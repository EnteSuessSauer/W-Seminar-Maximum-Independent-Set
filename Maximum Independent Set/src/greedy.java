import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private Graph graph;
    
    public Greedy(Graph graph) {
        this.graph = graph;
    }

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
            int minVertex = -1;
            for (int j = 0; j < order; j++) {
                int currentDegree = degrees[j];
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
                for (int neighbor : graph.getNeighbors(minVertex)) {
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