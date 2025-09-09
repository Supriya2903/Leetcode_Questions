class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 2]; // dp[i]: people who learn secret on day i
        dp[1] = 1;
        long share = 0; // number of people who can share on day i

        for (int i = 2; i <= n; i++) {
            // People start sharing after 'delay' days
            if (i - delay >= 1) {
                share = (share + dp[i - delay]) % MOD;
            }
            // People forget after 'forget' days
            if (i - forget >= 1) {
                share = (share - dp[i - forget] + MOD) % MOD;
            }
            dp[i] = share; // people who learn the secret on day i
        }
         // Sum people who still remember the secret at the end
        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        return (int) ans;
    }
}