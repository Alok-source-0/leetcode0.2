import java.util.*;

class Solution {
    class Node {
        int id;
        long cost;

        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    // Return type changed to 'int' to satisfy the Driver code
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new ArrayList[n];
        List<int[]>[] revAdj = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[]{v, w});
            revAdj[v].add(new int[]{u, w}); 
        }

        // Keep internal tracking as long to handle the 2*w logic safely
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;
            long d = current.cost;

            if (d > dist[u]) continue;
            if (u == n - 1) break; // Optimization: stop once we reach target

            // 1. Normal edges
            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Node(v, dist[v]));
                }
            }

            // 2. Reversed edges
            for (int[] edge : revAdj[u]) {
                int target = edge[0], weight = edge[1];
                long reversedCost = dist[u] + (2L * weight);
                if (reversedCost < dist[target]) {
                    dist[target] = reversedCost;
                    pq.add(new Node(target, dist[target]));
                }
            }
        }

        if (dist[n - 1] == Long.MAX_VALUE) return -1;
        
        // Final cast to int for the driver code
        return (int) dist[n - 1];
    }
}