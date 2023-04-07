public class Graph {
    private int V;   // number of vertices
    private boolean[][] adj;   // adjacency matrix

    // constructor
    public Graph(int V) {
        this.V = V;
        adj = new boolean[V][V];
    }

    // add an edge between vertices v and w
    public void addEdge(int v, int w) {
        adj[v][w] = true;
        adj[w][v] = true;
    }

    // remove an edge between vertices v and w
    public void removeEdge(int v, int w) {
        adj[v][w] = false;
        adj[w][v] = false;
    }

    // check if there's an edge between vertices v and w
    public boolean hasEdge(int v, int w) {
        return adj[v][w];
    }

    // get the neighbors of a vertex
    public List<Integer> getNeighbors(int v) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i]) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    // print the adjacency matrix
    public void print() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(adj[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}
