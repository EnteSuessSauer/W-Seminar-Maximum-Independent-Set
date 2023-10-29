import java.util.List;
import java.util.Scanner;

public class MISAlgorithm {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            System.out.println("Type 'exit' to exit");
            System.out.println("Do you want to use a graph example from the paper? (yes/no):");
            String answer = scanner.nextLine();
            Graph graph = new Graph();
            graph.reset();

            // Validate the input and handle it accordingly
            if (answer.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            } else if (answer.equalsIgnoreCase("yes")) {  
                try {
                    System.out.println("Which graph example do you want to use? (1/2/3):");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    switch (num) {
                        case 1: graph.initialiseGraph(4);;
                                graph.addEdge(0, 1);
                                graph.addEdge(0, 2);
                                graph.addEdge(1, 2);
                                graph.addEdge(2, 3);
                                break;
                        case 2: graph.initialiseGraph(7);
                                graph.addEdge(0, 1);
                                graph.addEdge(0, 2);
                                graph.addEdge(0, 3);
                                graph.addEdge(1, 4);
                                graph.addEdge(1, 5);
                                graph.addEdge(1, 6);
                                graph.addEdge(2, 4);
                                graph.addEdge(2, 5);
                                graph.addEdge(2, 6);
                                graph.addEdge(3, 4);
                                graph.addEdge(3, 5);
                                graph.addEdge(3, 6);
                                graph.addEdge(4, 5);
                                graph.addEdge(4, 6);
                                graph.addEdge(5, 6);
                                break;
                        case 3: graph.initialiseGraph(7);
                                graph.addEdge(0, 1);
                                graph.addEdge(0, 2);
                                graph.addEdge(1, 2);
                                graph.addEdge(3, 4);
                                graph.addEdge(3, 5);
                                graph.addEdge(4, 5);
                                break;
                        default:
                                System.out.println("Invalid input. Please enter an integer from 1 to 3.");
                                break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer from 1 to 3.");
                }
            } else if (answer.equalsIgnoreCase("no")) {  
                try {
                    System.out.println("Enter the size of the graph:");
                    int size = scanner.nextInt();
                    scanner.nextLine();
                    graph.generate(size);
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid size.");
                }
            }

            System.out.println("");
            System.out.println("Adjacency matrix:");
            graph.print();
            System.out.println("");

            System.out.println("Greedy: ");
            Greedy greedyAlg = new Greedy(graph);
            List<Integer> MIS = greedyAlg.runAlg();
            System.out.println("Size of the Maximum Independent Set: " + MIS.size());
            graph.printMIS(MIS);
            MIS.sort(null);
            System.out.println("sorted: ");
            graph.printMIS(MIS);

            System.out.println("");

            System.out.println("Bron-Kerbosch: ");
            BronKerbosch bkAlg = new BronKerbosch(graph);
            List<List<Integer>>IndependentSets = bkAlg.runAlg();
            for (List<Integer> list : IndependentSets) {
                graph.printMIS(list);
            }
        }
        scanner.close();
    }
}