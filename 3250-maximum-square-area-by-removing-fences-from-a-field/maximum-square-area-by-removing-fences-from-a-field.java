//rohit01-pro
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long maxSide = -1;
        Set<Integer> hDiffs = new HashSet<>();
        
        int[] h = new int[hFences.length + 2];
        h[0] = 1;
        h[1] = m;
        for (int i = 0; i < hFences.length; i++) {
            h[i + 2] = hFences[i];
        }
        Arrays.sort(h);
        
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hDiffs.add(h[j] - h[i]);
            }
        }
        int[] v = new int[vFences.length + 2];
        v[0] = 1;
        v[1] = n;
        for (int i = 0; i < vFences.length; i++) {
            v[i + 2] = vFences[i];
        }
        Arrays.sort(v);
        
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int diff = v[j] - v[i];
                if (hDiffs.contains(diff)) {
                    maxSide = Math.max(maxSide, diff);
                }
            }
        }
        if (maxSide == -1) {
            return -1;
        }
        return (int) ((maxSide * maxSide) % 1_000_000_007);
    }
}