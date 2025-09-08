class Solution {
    public int[] getNoZeroIntegers(int n) {
        // Try all possible values for a
        for (int a = 1; a < n; a++) {
            int b = n - a;
            // Check if both a and b are no-zero integers
            if (isNoZero(a) && isNoZero(b)) {
                // Return the first valid pair
                return new int[]{a, b};
            }
        }
        // Should never reach here as per problem constraints
        return new int[0];
    }
    // Helper function to check if a number contains '0'
    private boolean isNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) return false; // Found a zero digit
            num /= 10;
        }
        return true;
    }
}