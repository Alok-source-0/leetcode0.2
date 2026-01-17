class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Find the boundaries of the intersection area
                long interX1 = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                long interY1 = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                long interX2 = Math.min(topRight[i][0], topRight[j][0]);
                long interY2 = Math.min(topRight[i][1], topRight[j][1]);

                // Calculate width and height of the intersection rectangle
                long width = interX2 - interX1;
                long height = interY2 - interY1;

                // If an intersection exists, the largest square is constrained 
                // by the smaller of the two dimensions.
                if (width > 0 && height > 0) {
                    long side = Math.min(width, height);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        // Return the area (side squared)
        return maxSide * maxSide;
    }
}