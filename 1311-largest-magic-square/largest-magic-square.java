//rohit01-pro
class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rowPrefix = new int[m][n + 1];
        int[][] colPrefix = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[i + 1][j] = colPrefix[i][j] + grid[i][j];
            }
        }
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, rowPrefix, colPrefix, i, j, k)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
    private boolean isMagic(int[][] grid, int[][] rowPrefix, int[][] colPrefix, int r, int c, int k) {
        int targetSum = rowPrefix[r][c + k] - rowPrefix[r][c];

        for (int i = r + 1; i < r + k; i++) {
            if (rowPrefix[i][c + k] - rowPrefix[i][c] != targetSum) return false;
        }
        for (int j = c; j < c + k; j++) {
            if (colPrefix[r + k][j] - colPrefix[r][j] != targetSum) return false;
        }
        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + k - 1 - i];
        }
        return diag1 == targetSum && diag2 == targetSum;
    }
}