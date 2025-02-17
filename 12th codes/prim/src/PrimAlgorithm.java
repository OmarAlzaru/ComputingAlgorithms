import java.util.*;

public class PrimAlgorithm {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int V;
        List<List<Edge>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int src, int dest, int weight) {
            Edge edge1 = new Edge(src, dest, weight);
            Edge edge2 = new Edge(dest, src, weight);
            adjList.get(src).add(edge1);
            adjList.get(dest).add(edge2);
        }

        void primMST() {
            boolean[] inMST = new boolean[V];
            int[] parent = new int[V];
            int[] key = new int[V];
            Arrays.fill(key, Integer.MAX_VALUE);

            PriorityQueue<Edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(e -> e.weight));
            key[0] = 0;
            pq.add(new Edge(-1, 0, 0));

            while (!pq.isEmpty()) {
                int u = pq.poll().dest;
                inMST[u] = true;

                for (Edge e : adjList.get(u)) {
                    int v = e.dest;
                    int weight = e.weight;
                    if (!inMST[v] && weight < key[v]) {
                        key[v] = weight;
                        parent[v] = u;
                        pq.add(new Edge(u, v, weight));
                    }
                }
            }

            // Print the MST
            System.out.println("Edge   Weight");
            for (int i = 1; i < V; i++) {
                System.out.println(parent[i] + " - " + i + "    " + key[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}
