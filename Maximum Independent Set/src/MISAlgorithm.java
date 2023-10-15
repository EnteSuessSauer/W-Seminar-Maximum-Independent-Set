import java.util.List;

public class MISAlgorithm {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.print();

        System.out.println("Greedy: ");
        Greedy GreedyAlg = new Greedy(graph);
        graph.printMIS(GreedyAlg.runAlg());

        System.out.println("Bron-Kerbosch: ");
        BronKerbosch BKAlg = new BronKerbosch(graph);
        List<List<Integer>>IndependentSets = BKAlg.runAlg();
        for (List<Integer> list : IndependentSets) {
            graph.printMIS(list);
        }
    }
}
