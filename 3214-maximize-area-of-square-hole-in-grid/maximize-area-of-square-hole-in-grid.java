//rohit01-pro
import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = getMaxConsecutive(hBars);
        int maxV = getMaxConsecutive(vBars);
        
        int side = Math.min(maxH + 1, maxV + 1);
        return side * side;
    }
    private int getMaxConsecutive(int[] bars) {
        Arrays.sort(bars);
        int maxCount = 1;
        int currentCount = 1;
        
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currentCount++;
            } else {
                currentCount = 1;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
}