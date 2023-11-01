import java.util.List;
import java.util.Scanner;

public class MISAlgorithm {
    /**
     * Main-Methode des Programms
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int maxPrintOrder = 50;

        // In einer Schleife wird per Inputs ein neuer Graph erstellt und die Algorithmen auf ihm angewandt
        while(!exit) {

            boolean validInput = false;
            System.out.println("Type 'exit' to exit");
            System.out.println("Do you want to use a graph example from the paper? (yes/no):");
            String answer = scanner.nextLine();
            Graph graph = new Graph();
            graph.reset();

            // Validiere den Input
            if (answer.equalsIgnoreCase("exit")) {
                // Brich die Schleife ab
                exit = true;
                break;
            } else if (answer.equalsIgnoreCase("yes")) {
                // Initialisiere einen Beispielgrah
                try {
                    // Parameter für die Beispielgraphen
                    System.out.println("Which graph example do you want to use? (1/2/3):");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    switch (num) {
                        // erster Beispielgraph der Arbeit
                        case 1: graph.initialiseGraph(5);;
                                graph.addEdge(0, 2);
                                graph.addEdge(0, 3);
                                graph.addEdge(1, 3);
                                graph.addEdge(2, 3);
                                graph.addEdge(2, 4);

                                validInput = true;
                                break;
                        // zweiter Beispielgraph der Arbeit
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

                                validInput = true;
                                break;
                        // dritter Beispielgraph der Arbeit
                        case 3: graph.initialiseGraph(6);
                                graph.addEdge(0, 1);
                                graph.addEdge(0, 2);
                                graph.addEdge(1, 2);
                                graph.addEdge(3, 4);
                                graph.addEdge(3, 5);
                                graph.addEdge(4, 5);

                                validInput = true;
                                break;
                        // ungültiger Input
                        default:
                                System.out.println("Invalid input. Please enter an integer from 1 to 3.");
                                break;
                    }
                } catch (Exception e) {
                    //ungültiger Input
                    scanner.nextLine();
                    System.out.println("Invalid input. Please enter an integer from 1 to 3.");
                }

            } else if (answer.equalsIgnoreCase("no")) {
                // Generiere zufällig einen Graph 
                try {
                    // Parameter für die Graph-generierung
                    System.out.println("Enter the size of the graph:");
                    int size = scanner.nextInt();
                    scanner.nextLine();
                    graph.initialiseGraph(size);
                    graph.generate();
                    validInput = true;
                } catch (Exception e) {
                    // ungültiger Input
                    scanner.nextLine();
                    System.out.println("Invalid input. Please enter a valid size.");
                }
            }

            // führt die Algorithmen nur dann aus, wenn durch gültige Inputs ein Graph entstanden ist
            if (validInput) {
                // aus Zeit- und Übersichtsgründen wird die Adjazenzmantrix nur ausgegeben, wenn sie innerhalb einer Grenze liegt
                if (graph.getOrder() <= maxPrintOrder) {
                    System.out.println("");
                    System.out.println("Adjacency matrix:");
                    graph.print();
                }
                System.out.println("");

                // Der Greedy-Algorithmu wird auf dem Graph angewendet
                System.out.println("Greedy: ");
                Greedy greedyAlg = new Greedy(graph);
                List<Integer> MIS = greedyAlg.runAlg();
                // Die Lösung des Greedy-Algorithmus wird ausgegeben
                System.out.println("Size of the Maximum Independent Set: " + MIS.size());
                graph.printMIS(MIS);
                MIS.sort(null);
                System.out.println("sorted: ");
                graph.printMIS(MIS);

                System.out.println("");

                // Der Bron-Kerbosch-Algorithmus wird auf dem Graph angewendet
                System.out.println("Bron-Kerbosch: ");
                BronKerbosch bkAlg = new BronKerbosch(graph);
                // Die Lösung des Bron-Kerbosch-Algorithmus wird ausgegeben
                List<List<Integer>>IndependentSets = bkAlg.runAlg();
                for (List<Integer> list : IndependentSets) {
                    graph.printMIS(list);
                }
            }
        }
        scanner.close();
    }
}