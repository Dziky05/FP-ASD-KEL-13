import java.util.*;

public class Graph {
    private double[][] adjMatrix;
    private int n;

    public Graph(double[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
        this.n = adjMatrix.length;
    }

    public DijkstraResult dijkstra(int src) {
        double[] dist = new double[n];
        int[] prev = new int[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Double.compare(a.dist, b.dist));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (visited[u.v]) continue;
            visited[u.v] = true;
            for (int v = 0; v < n; v++) {
                double w = adjMatrix[u.v][v];
                if (w <= 0 || Double.isInfinite(w)) continue;
                if (dist[v] > dist[u.v] + w) {
                    dist[v] = dist[u.v] + w;
                    prev[v] = u.v;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return new DijkstraResult(dist, prev);
    }

    private static class Node {
        int v; double dist;
        Node(int v,double d){this.v=v;this.dist=d;}
    }

    public static class DijkstraResult {
        public final double[] dist;
        public final int[] prev;
        public DijkstraResult(double[] dist, int[] prev) {
            this.dist = dist; this.prev = prev;
        }
        public String reconstructPath(int target) {
            StringBuilder sb = new StringBuilder();
            int cur = target;
            while (cur != -1) {
                sb.insert(0, cur + (sb.length()==0? "":" -> "));
                cur = prev[cur];
            }
            return sb.toString();
        }
    }
}
