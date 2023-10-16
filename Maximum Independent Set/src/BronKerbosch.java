import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    private List<Integer> P;
    private List<List<Integer>> MISets;  
    public BronKerbosch(Graph graph) {
        System.out.println("Hallo Welt");
        this.graph = graph;
        this.P = new ArrayList<Integer>();
        for (int i = 0; i < graph.getOrder(); i++) {
            P.add(i);
            System.out.println(i);
            
        }

        System.out.println(P);

        MISets = new ArrayList<List<Integer>>();
    }

    public List<List<Integer>> runAlg() {
        System.out.println("Sarting Bron-Kerbosch-Algorithm");
        List<List<Integer>> MIS = bronKerbosch(new ArrayList<Integer>(), P, new ArrayList<Integer>());
        System.out.println("Finished running");
        return MIS;
    }

    public List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        System.out.println("BK");
        System.out.println(P.isEmpty());
        if (P.isEmpty() && X.isEmpty()) {
            MISets.add(new ArrayList<>(R));
        } else {
            List<Integer> pCopy = new ArrayList<>(P);
            for (int v : pCopy) {
                List<Integer> vNeighbors = graph.getNeighbors(v);
                R.add(v);
                P.retainAll(vNeighbors);
                X.retainAll(vNeighbors);
                bronKerbosch(R, P, X);  

                P.remove((Integer) v);
                X.add(v);
            }
        }

        return MISets;
    }
}
