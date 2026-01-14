//rohit01-pro
import java.util.*;

class Solution {
    public int rectangleArea(int[][] rectangles) {
        int MOD = 1_000_000_007;
        int n = rectangles.length;
        
        int[][] events = new int[n * 2][4];
        for (int i = 0; i < n; i++) {
            events[2 * i] = new int[]{rectangles[i][1], 1, rectangles[i][0], rectangles[i][2]};
            events[2 * i + 1] = new int[]{rectangles[i][3], -1, rectangles[i][0], rectangles[i][2]};
        }
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        long totalArea = 0;
        List<int[]> activeIntervals = new ArrayList<>();
        int prevY = events[0][0];

        for (int[] event : events) {
            int currY = event[0];
            int type = event[1];
            int x1 = event[2];
            int x2 = event[3];
            long queryWidth = 0;
            if (!activeIntervals.isEmpty()) {
                Collections.sort(activeIntervals, (a, b) -> a[0] - b[0]);
                int curL = -1, curR = -1;
                for (int[] interval : activeIntervals) {
                    if (interval[0] > curR) {
                        queryWidth += curR - curL;
                        curL = interval[0];
                        curR = interval[1];
                    } else {
                        curR = Math.max(curR, interval[1]);
                    }
                }
                queryWidth += curR - curL;
            }
            totalArea = (totalArea + queryWidth * (currY - prevY)) % MOD;

            if (type == 1) {
                activeIntervals.add(new int[]{x1, x2});
            } else {
                for (int i = 0; i < activeIntervals.size(); i++) {
                    if (activeIntervals.get(i)[0] == x1 && activeIntervals.get(i)[1] == x2) {
                        activeIntervals.remove(i);
                        break;
                    }
                }
            }
            prevY = currY;
        }
        return (int) totalArea;
    }
}