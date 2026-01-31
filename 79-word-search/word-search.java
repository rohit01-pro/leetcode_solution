class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int index, int r, int c) {
        if (index == word.length()) {
            return true;
        }

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }
        char temp = board[r][c];
        board[r][c] = '#';

        boolean found = dfs(board, word, index + 1, r + 1, c) ||
                        dfs(board, word, index + 1, r - 1, c) ||
                        dfs(board, word, index + 1, r, c + 1) ||
                        dfs(board, word, index + 1, r, c - 1);

        board[r][c] = temp;
        return found;
    }
}