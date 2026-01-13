class Solution {
    public int countPyramids(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] revGrid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                revGrid[m - 1 - i][j] = grid[i][j];
            }
        }
        return count(grid) + count(revGrid);
    }

    private int count(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int total = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (i == m - 1 || j == 0 || j == n - 1) {
                        dp[i][j] = 1;
                    } else {
                        int minPrev = dp[i + 1][j - 1];
                        if (dp[i + 1][j] < minPrev) minPrev = dp[i + 1][j];
                        if (dp[i + 1][j + 1] < minPrev) minPrev = dp[i + 1][j + 1];
                        dp[i][j] = minPrev + 1;
                    }
                    total += (dp[i][j] - 1);
                }
            }
        }
        return total;
    }
}