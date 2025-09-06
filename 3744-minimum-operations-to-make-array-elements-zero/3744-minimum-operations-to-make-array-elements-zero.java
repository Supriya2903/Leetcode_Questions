class Solution {
    // Helper: compute prefix sum of costs from 1..n
    private long prefix(long n) {
        if (n <= 0) return 0;
        long sum = 0;
        long base = 1;   // start of interval (4^k)
        int cost = 1;    // numbers in [4^k, 4^(k+1)-1] have cost = k+1
        while (base <= n) {
            long end = Math.min(n, base * 4 - 1); // end of this interval
            long count = end - base + 1;          // how many numbers fall here
            sum += count * cost;
            base *= 4;
            cost++;
        }
        return sum;
    }

    public long minOperations(int[][] queries) {
        long total = 0;
        for (int[] q : queries) {
            long l = q[0], r = q[1];
            long totalCost = prefix(r) - prefix(l - 1);
            long ops = (totalCost + 1) / 2; // ceil(totalCost/2)
            total += ops;
        }
        return total;
    }
}
