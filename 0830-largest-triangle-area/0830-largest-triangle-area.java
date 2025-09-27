class Solution {
    public double largestTriangleArea(int[][] points) {
         double maxArea = 0.0;
        int n = points.length;

        // Try every combination of 3 points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Get coordinates for the three points
                    int[] p1 = points[i];
                    int[] p2 = points[j];
                    int[] p3 = points[k];

                    // Calculate area using the shoelace formula
                    double area = 0.5 * Math.abs(
                        p1[0] * (p2[1] - p3[1]) +
                        p2[0] * (p3[1] - p1[1]) +
                        p3[0] * (p1[1] - p2[1])
                    );
                     // Update maxArea if this area is larger
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }
}