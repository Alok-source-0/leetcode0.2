class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        // Padded prefix sum array to handle boundary conditions easily
        int[][] prefixSum = new int[m + 1][n + 1];
        
        // 1. Build the 2D Prefix Sum table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }
        
        int maxSide = 0;
        
        // 2. Iterate through every cell as a potential bottom-right corner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Check if a square of side (maxSide + 1) exists at this corner
                while (i > maxSide && j > maxSide) {
                    int currentSum = getSquareSum(prefixSum, i, j, maxSide + 1);
                    
                    if (currentSum <= threshold) {
                        maxSide++; // We found a larger valid square
                    } else {
                        break; // Cannot expand further at this cell
                    }
                }
            }
        }
        
        return maxSide;
    }
    
    // Helper to calculate sum of square with bottom-right (r, c) and side k
    private int getSquareSum(int[][] P, int r, int c, int k) {
        return P[r][c] - P[r - k][c] - P[r][c - k] + P[r - k][c - k];
    }
}