class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int INF = 1000000000;

        int[][][] dist = new int[k + 1][m][n];
        for (int l = 0; l <= k; l++) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    dist[l][r][c] = INF;
                }
            }
        }
        dist[0][0][0] = 0;
        for (int l = 0; l <= k; l++) {
            MinHeap pq = new MinHeap(m * n * 10); 
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (dist[l][r][c] < INF) {
                        pq.push(dist[l][r][c], r, c);
                    }
                }
            }
            while (!pq.isEmpty()) {
                Node curr = pq.pop();
                if (curr.d > dist[l][curr.r][curr.c]) continue;

                int[][] dirs = {{0, 1}, {1, 0}};
                for (int[] dir : dirs) {
                    int nr = curr.r + dir[0], nc = curr.c + dir[1];
                    if (nr < m && nc < n) {
                        int newDist = dist[l][curr.r][curr.c] + grid[nr][nc];
                        if (newDist < dist[l][nr][nc]) {
                            dist[l][nr][nc] = newDist;
                            pq.push(newDist, nr, nc);
                        }
                    }
                }
            }
            if (l < k) {
                int[] valueToMinCost = new int[10001];
                for (int i = 0; i <= 10000; i++) valueToMinCost[i] = INF;

                boolean foundAny = false;
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        if (dist[l][r][c] < INF) {
                            if (dist[l][r][c] < valueToMinCost[grid[r][c]]) {
                                valueToMinCost[grid[r][c]] = dist[l][r][c];
                                foundAny = true;
                            }
                        }
                    }
                }
                if (!foundAny) continue;
                int currentMin = INF;
                int[] minFromHigherVal = new int[10001];
                for (int v = 10000; v >= 0; v--) {
                    if (valueToMinCost[v] < currentMin) currentMin = valueToMinCost[v];
                    minFromHigherVal[v] = currentMin;
                }
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        if (minFromHigherVal[grid[r][c]] < dist[l + 1][r][c]) {
                            dist[l + 1][r][c] = minFromHigherVal[grid[r][c]];
                        }
                    }
                }
            }
        }
        int result = INF;
        for (int l = 0; l <= k; l++) {
            if (dist[l][m - 1][n - 1] < result) result = dist[l][m - 1][n - 1];
        }
        return result >= INF ? -1 : result;
    }
    class Node {
        int d, r, c;
        Node(int d, int r, int c) { this.d = d; this.r = r; this.c = c; }
    }
    class MinHeap {
        Node[] heap;
        int size = 0;
        MinHeap(int capacity) { heap = new Node[capacity + 1]; }
        boolean isEmpty() { return size == 0; }
        void push(int d, int r, int c) {
            if (size + 1 >= heap.length) return; // Safety check
            heap[++size] = new Node(d, r, c);
            int cur = size;
            while (cur > 1 && heap[cur].d < heap[cur / 2].d) {
                Node tmp = heap[cur]; heap[cur] = heap[cur / 2]; heap[cur / 2] = tmp;
                cur /= 2;
            }
        }
        Node pop() {
            Node res = heap[1];
            heap[1] = heap[size--];
            int cur = 1;
            while (cur * 2 <= size) {
                int next = cur * 2;
                if (next + 1 <= size && heap[next + 1].d < heap[next].d) next++;
                if (heap[next].d < heap[cur].d) {
                    Node tmp = heap[cur]; heap[cur] = heap[next]; heap[next] = tmp;
                    cur = next;
                } else break;
            }
            return res;
        }
    }
}