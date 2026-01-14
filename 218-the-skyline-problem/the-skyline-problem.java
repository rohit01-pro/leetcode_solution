//rohit01-pro 
import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> edges = new ArrayList<>();
        
        for (int[] b : buildings) {
            edges.add(new int[]{b[0], -b[2]});
            edges.add(new int[]{b[1], b[2]});
        }
        Collections.sort(edges, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(0);
        int prevHeight = 0;
        for (int[] edge : edges) {
            int x = edge[0];
            int height = edge[1];
            if (height < 0) {
                maxHeap.offer(-height);
            } else {
                maxHeap.remove(height);
            }
            int currentHeight = maxHeap.peek();
            if (currentHeight != prevHeight) {
                result.add(Arrays.asList(x, currentHeight));
                prevHeight = currentHeight;
            }
        }
        return result;
    }
}