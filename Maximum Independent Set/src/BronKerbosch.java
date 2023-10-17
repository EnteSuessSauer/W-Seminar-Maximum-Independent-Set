import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    private List<Integer> V;
    private List<List<Integer>> MISets;  
    public BronKerbosch(Graph graph) {
        this.graph = graph;
        this.V = new ArrayList<Integer>();
        for (int i = 0; i < graph.getOrder(); i++) {
            V.add(i);
        }

        System.out.println(V);

        MISets = new ArrayList<List<Integer>>();
    }

    public List<List<Integer>> runAlg() {
        System.out.println("Sarting Bron-Kerbosch-Algorithm");
        List<List<Integer>> MIS = bronKerbosch(new ArrayList<Integer>(), V, new ArrayList<Integer>());
        System.out.println("Finished running");
        return MIS;
    }

    public List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        System.out.println("");
        System.out.println("Recursive: " + "R: " + R + " P: " + P + " X: " + X);
        if (P.isEmpty() && X.isEmpty()) {
            System.out.println("P & X are empty");
            List<Integer> MIS = new ArrayList<>(R);
            System.out.println("Reprted MIS: " + MIS);
            System.out.println("");
            MISets.add(MIS);
            return MISets;
        } else {
            System.out.println("P & X are not empty");
            List<Integer> pCopy = new ArrayList<>(P);
            for (int v : pCopy) {
                System.out.println("Iterated Vertex: " + v);
                List<Integer> vNeighbors = graph.getNeighbors(v);
                R.add(v);
                P.retainAll(vNeighbors);
                X.retainAll(vNeighbors);
                bronKerbosch(R, P, X);  

                R.remove((Integer) v);
                P.add((Integer) v);
                X.add(v);
                System.out.println("Backtracking " + P + " " + X);
            }
        }

        return MISets;
    }
}
