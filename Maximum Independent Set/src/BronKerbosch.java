import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    private List<Integer> V;
    private List<List<Integer>> MISets;
    private Clock clock; 

    /**
     * Erstellt ein Bron-Kerbosch-Objekt, setzt den Graph auf den mitgegebenen Graph
     * @param graph Graph
     */
    public BronKerbosch(Graph graph) {
        this.graph = graph;
        this.V = new ArrayList<Integer>();
        for (int i = 0; i < graph.getOrder(); i++) {
            V.add(i);
        }
        MISets = new ArrayList<List<Integer>>();

        clock = Clock.systemDefaultZone();
    }

    /**
     * Lässt den Bron-Kerbosch-Algorithmus auf dem Graphen laufen
     * @return alle gefundenen Maxium-Independent-Sets
     */
    public List<List<Integer>> runAlg() {
        // Nutzt den Komplementärgraphen
        graph.inverseGraph();
        // Start der Zeitmessung
        long startingTime = clock.millis();
        // Ruft den Rekursiven Kern des Algorithmus auf
        List<List<Integer>> MISCandidates = bronKerbosch(new ArrayList<Integer>(), V, new ArrayList<Integer>());
        // Ende der Zeitmessung
        long elapsedTime = clock.millis() - startingTime;
        System.out.println("Finished running in " + elapsedTime + " milliseconds");

        // Setzt den Graph auf seine ursprüngliche Form zurück
        graph.inverseGraph();

        // Filtert alle größten unabhängigen Mengen aus der Liste der maximalen unabhängigen Mengen
        int maxSize = 0;
        for (List<Integer> list : MISCandidates) {
            if (list.size() > maxSize) {
                maxSize = list.size();
            }
        }

        List<List<Integer>> MIS = new ArrayList<List<Integer>>();
        for (List<Integer> list : MISCandidates) {
            if (list.size() == maxSize) {
                MIS.add(list);
            }
        }

        // Gibt die Anzahl und Größe der gefundenen größten unabhängigen Mengen aus
        System.out.println(MIS.size() + " Maximum Independent Sets found!");
        System.out.println("Size of the Maximum Independent Sets: " + maxSize);

        return MIS;
    }

    /**
     * Findet alle maximalen Cliquen in em Graphen
     * @param R Menge der der maximalen Clique angehörigende Knoten
     * @param P Menge der Kandidaten 
     * @param X Menge der aus dem weiteren Vorgehen ausgeschlossenen Knoten
     * @return alle maximalen Cliquen
     */
    private List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        // wenn P und X leer sind, ist R eine maximale Clique
        if (P.isEmpty() && X.isEmpty()) {
            List<Integer> MIS = new ArrayList<>(R);
            MISets.add(MIS);
            return MISets;
        } else {
            // zur Iteration kopierte Kandidatenmenge
            List<Integer> pCopy = new ArrayList<>(P);
            for (int v : pCopy) {
                List<Integer> vNeighbors = graph.getNeighbors(v);
                List<Integer> Rnew = new ArrayList<>(R);
                Rnew.add(v);
                List<Integer> Pnew = new ArrayList<>(P);
                Pnew.retainAll(vNeighbors);
                List<Integer> Xnew = new ArrayList<>(X);
                Xnew.retainAll(vNeighbors);
                bronKerbosch(Rnew, Pnew, Xnew);  

                P.remove((Integer) v);
                X.add((Integer) v);
            }
        }

        return MISets;
    }
}
