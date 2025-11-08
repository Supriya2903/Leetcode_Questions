class Solution {
    public int minimumOneBitOperations(int n) {
        return helper(n);
    }

    private int helper(int n) {
        if (n == 0) return 0;

        // find highest set bit
        int k = 31 - Integer.numberOfLeadingZeros(n);

        // remove highest bit to recurse
        int mask = 1 << k;

        return (1 << (k + 1)) - 1 - helper(n ^ mask);
    }
}
