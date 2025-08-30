class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] = minimum number of coins needed to make amount i
        // Start by filling it with "impossible" (a big number = amount+1)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // like infinity, means "not possible yet"

        dp[0] = 0; // To make amount 0, we need 0 coins

        // Try to solve for every amount from 1 up to 'amount'
        for (int a = 1; a <= amount; a++) {
            // Try using each coin to make current amount 'a'
            for (int coin : coins) {
                if (a - coin >= 0) { 
                    // If this coin can fit into the amount (no negative index)
                    // Take 1 coin + best answer for the remaining amount (a - coin)
                    dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
                }
            }
        }

        // If dp[amount] is still "impossible" (greater than amount), return -1
        // Otherwise, return the minimum coins needed
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
