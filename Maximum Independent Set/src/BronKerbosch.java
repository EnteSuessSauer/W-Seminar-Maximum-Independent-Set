import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    public BronKerbosch(Graph graph) {
        this.graph = graph;
    }

    public List<List<Integer>> runAlg() {
        return bronKerbosch(null, null, null);
    }

    public List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        
        List<List<Integer>> MISets = new ArrayList<>();
        
        if (P.isEmpty() && X.isEmpty()) {
            return null;
        } else {
            List<Integer> pCopy = new ArrayList<>(P);
            for (int v : pCopy) {
                List<Integer> vNeighbors = graph.getNeighbors(v);
                R.add(v);
                P.retainAll(vNeighbors);
                X.retainAll(vNeighbors);

                bronKerbosch(R, P, X);

                pCopy.remove(v);
                X.add(v);
                // R.remove(v);
            }
            return new ArrayList<>();
        }
    }
}
