import java.util.*;

class Solution {
    // DSU implementation
    static class DSU {
        int[] parent, rank;
        
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }
        
        int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }
        
        void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return;
            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pa] > rank[pb]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);

        // Step 1: Build grids using DSU
        for (int[] edge : connections) {
            dsu.union(edge[0], edge[1]);
        }

        // Step 2: Prepare heaps for each component
        Map<Integer, PriorityQueue<Integer>> heapMap = new HashMap<>();

        for (int i = 1; i <= c; i++) {
            int p = dsu.find(i);
            heapMap.putIfAbsent(p, new PriorityQueue<>());
            heapMap.get(p).add(i);
        }

        boolean[] offline = new boolean[c + 1];
        List<Integer> result = new ArrayList<>();

        // Step 3: Process queries
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];

            if (type == 2) {
                // Station goes offline
                offline[x] = true;
            } else {
                // Query: Who handles maintenance for station x?
                if (!offline[x]) {
                    result.add(x); // it handles itself
                } else {
                    int p = dsu.find(x);
                    PriorityQueue<Integer> pq = heapMap.get(p);

                    // Remove offline stations from heap top (lazy deletion)
                    while (!pq.isEmpty() && offline[pq.peek()]) {
                        pq.poll();
                    }

                    if (pq.isEmpty()) {
                        result.add(-1);
                    } else {
                        result.add(pq.peek());
                    }
                }
            }
        }

        // Convert to array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
