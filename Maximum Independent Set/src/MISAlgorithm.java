import java.util.List;

public class MISAlgorithm {
    public static void main(String[] args) {
        // Graph graph = new Graph(5);
        // graph.addEdge(0, 1);
        // graph.addEdge(0, 2);
        // graph.addEdge(1, 2);
        // graph.addEdge(2, 3);

        //g2
        // Graph graph = new Graph(7);
        // graph.addEdge(0, 1);
        // graph.addEdge(0, 2);
        // graph.addEdge(0, 3);
        // graph.addEdge(1, 4);
        // graph.addEdge(1, 5);
        // graph.addEdge(1, 6);
        // graph.addEdge(2, 4);
        // graph.addEdge(2, 5);
        // graph.addEdge(2, 6);
        // graph.addEdge(3, 4);
        // graph.addEdge(3, 5);
        // graph.addEdge(3, 6);
        // graph.addEdge(4, 5);
        // graph.addEdge(4, 6);
        // graph.addEdge(5, 6);

        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.print();

        System.out.println("");

        System.out.println("Greedy: ");
        Greedy GreedyAlg = new Greedy(graph);
        List<Integer> MIS = GreedyAlg.runAlg();
        System.out.println("Size of the Maximum Independent Set: " + MIS.size());
        graph.printMIS(MIS);
        MIS.sort(null);
        System.out.println("sorted: ");
        graph.printMIS(MIS);

        System.out.println("");

        System.out.println("Bron-Kerbosch: ");
        BronKerbosch BKAlg = new BronKerbosch(graph);
        List<List<Integer>>IndependentSets = BKAlg.runAlg();
        for (List<Integer> list : IndependentSets) {
            graph.printMIS(list);
        }
    }
}
