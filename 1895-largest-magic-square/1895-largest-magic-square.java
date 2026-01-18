class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Prefix sums for rows and columns
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }
        
        // Try side lengths k from largest to smallest
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagic(grid, i, j, k, rowSum, colSum)) {
                        return k;
                    }
                }
            }
        }
        
        return 1; // 1x1 is always a magic square
    }
    
    private boolean isMagic(int[][] grid, int r, int c, int k, int[][] rowSum, int[][] colSum) {
        // Target sum from the first row of this square
        int target = rowSum[r][c + k] - rowSum[r][c];
        
        // Check rows
        for (int i = r + 1; i < r + k; i++) {
            if (rowSum[i][c + k] - rowSum[i][c] != target) return false;
        }
        
        // Check columns
        for (int j = c; j < c + k; j++) {
            if (colSum[r + k][j] - colSum[r][j] != target) return false;
        }
        
        // Check main diagonal
        int d1 = 0;
        for (int i = 0; i < k; i++) {
            d1 += grid[r + i][c + i];
        }
        if (d1 != target) return false;
        
        // Check anti-diagonal
        int d2 = 0;
        for (int i = 0; i < k; i++) {
            d2 += grid[r + i][c + k - 1 - i];
        }
        return d2 == target;
    }
}