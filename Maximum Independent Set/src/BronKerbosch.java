import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    private List<Integer> V;
    private List<List<Integer>> MISets;
    private Clock clock;  
    public BronKerbosch(Graph graph) {
        this.graph = graph;
        this.V = new ArrayList<Integer>();
        for (int i = 0; i < graph.getOrder(); i++) {
            V.add(i);
        }

        // System.out.println(V);

        MISets = new ArrayList<List<Integer>>();

        clock = Clock.systemDefaultZone();
    }

    public List<List<Integer>> runAlg() {
        graph.inverseGraph();
        System.out.println("Sarting Bron-Kerbosch-Algorithm");
        long startingTime = clock.millis();
        List<List<Integer>> MISCandidates = bronKerbosch(new ArrayList<Integer>(), V, new ArrayList<Integer>());
        long elapsedTime = clock.millis() - startingTime;
        System.out.println("Finished running in " + elapsedTime + " milliseconds");
        graph.inverseGraph();

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

        System.out.println("Size of the Maximum Independent Sets: " + maxSize);

        return MIS;
    }

    public List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        // System.out.println("");
        // System.out.println("Recursive: " + "R: " + R + " P: " + P + " X: " + X);
        if (P.isEmpty() && X.isEmpty()) {
            // System.out.println("P & X are empty");
            List<Integer> MIS = new ArrayList<>(R);
            // System.out.println("Reported MIS: " + MIS);
            // System.out.println("");
            MISets.add(MIS);
            return MISets;
        } else {
            // System.out.println("P & X are not empty");
            List<Integer> pCopy = new ArrayList<>(P);
            for (int v : pCopy) {
                // System.out.println("Iterated Vertex: " + v);
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
                // System.out.println("Backtracking " + P + " " + X);
            }
        }

        return MISets;
    }
}
