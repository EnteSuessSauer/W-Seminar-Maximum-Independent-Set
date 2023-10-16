import java.util.ArrayList;
import java.util.List;

public class BronKerbosch {
    private Graph graph;
    private List<Integer> P;
    private List<List<Integer>> MISets;  
    public BronKerbosch(Graph graph) {
        this.graph = graph;
        this.P = new ArrayList<Integer>();
        for (int i = 0; i < P.size();) {
            P.add(i);
        }

        MISets = new ArrayList<List<Integer>>();
    }

    public List<List<Integer>> runAlg() {
        return bronKerbosch(new ArrayList<Integer>(), P, new ArrayList<Integer>());
    }

    public List<List<Integer>> bronKerbosch(List<Integer> R, List<Integer> P, List<Integer> X) {
        if (P.isEmpty() && X.isEmpty()) {
            MISets.add(new ArrayList<>(R));
        } else {
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

                pCopy.remove((Integer) v);
                X.add(v);
            }
        }

        return MISets;
    }
}
