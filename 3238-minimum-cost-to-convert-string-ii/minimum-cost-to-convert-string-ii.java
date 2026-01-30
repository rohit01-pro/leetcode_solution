import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        Map<String, Integer> strToId = new HashMap<>();
        int idCounter = 0;
        for (String s : original) if (!strToId.containsKey(s)) strToId.put(s, idCounter++);
        for (String s : changed) if (!strToId.containsKey(s)) strToId.put(s, idCounter++);

        long[][] dist = new long[idCounter][idCounter];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < idCounter; i++) dist[i][i] = 0;

        for (int i = 0; i < original.length; i++) {
            int u = strToId.get(original[i]);
            int v = strToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }
        for (int k = 0; k < idCounter; k++) {
            for (int i = 0; i < idCounter; i++) {
                for (int j = 0; j < idCounter; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        Set<Integer> lengths = new HashSet<>();
        for (String s : original) lengths.add(s.length());
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (source.charAt(i - 1) == target.charAt(i - 1)) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }
            for (int len : lengths) {
                if (i - len >= 0) {
                    String sSub = source.substring(i - len, i);
                    String tSub = target.substring(i - len, i);
                    if (strToId.containsKey(sSub) && strToId.containsKey(tSub)) {
                        int u = strToId.get(sSub);
                        int v = strToId.get(tSub);
                        if (dist[u][v] < Long.MAX_VALUE / 2) {
                            dp[i] = Math.min(dp[i], dp[i - len] + dist[u][v]);
                        }
                    }
                }
            }
        }
        return dp[n] >= Long.MAX_VALUE / 2 ? -1 : dp[n];
    }
}