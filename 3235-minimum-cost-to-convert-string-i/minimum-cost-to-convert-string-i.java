import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] minCost = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(minCost[i], Long.MAX_VALUE);
            minCost[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            minCost[u][v] = Math.min(minCost[u][v], (long) cost[i]);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (minCost[i][k] != Long.MAX_VALUE && minCost[k][j] != Long.MAX_VALUE) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                    }
                }
            }
        }
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int sChar = source.charAt(i) - 'a';
            int tChar = target.charAt(i) - 'a';
            
            if (minCost[sChar][tChar] == Long.MAX_VALUE) {
                return -1;
            }
            totalCost += minCost[sChar][tChar];
        }
        return totalCost;
    }
}