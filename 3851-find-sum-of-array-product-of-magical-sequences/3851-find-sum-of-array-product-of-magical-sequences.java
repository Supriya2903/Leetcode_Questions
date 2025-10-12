import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        // Precompute factorials and inverse factorials up to m
        long[] fact = new long[m+1], invFact = new long[m+1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i-1] * i % MOD;
        invFact[m] = modInv(fact[m]);
        for (int i = m-1; i >= 0; i--) invFact[i] = invFact[i+1] * (i+1) % MOD;

        // Precompute powNum[i][c] = nums[i]^c % MOD for c=0..m
        long[][] powNum = new long[n][m+1];
        for (int i = 0; i < n; i++) {
            powNum[i][0] = 1;
            long base = nums[i] % MOD;
            for (int c = 1; c <= m; c++) powNum[i][c] = (powNum[i][c-1] * base) % MOD;
        }

        // dp[t][carry][s] = sum of weights (Π nums[i]^ci / Π ci!) for processed positions
        int M = m;
        int sizeT = M + 1;
        int sizeC = M + 1;
        int sizeS = M + 1;

        long[][][] dp = new long[sizeT][sizeC][sizeS];
        dp[0][0][0] = 1; // no positions chosen yet

        for (int idx = 0; idx < n; idx++) {
            long[][][] next = new long[sizeT][sizeC][sizeS];

            for (int t = 0; t <= M; t++) {
                for (int carry = 0; carry <= M; carry++) {
                    for (int s = 0; s <= M; s++) {
                        long cur = dp[t][carry][s];
                        if (cur == 0) continue;
                        // choose ci occurrences of index idx
                        int maxCi = M - t;
                        for (int ci = 0; ci <= maxCi; ci++) {
                            int newT = t + ci;
                            int sum = ci + carry;
                            int bit = sum & 1;
                            int newS = s + bit;
                            if (newS > M) break; // prune
                            int newCarry = sum >> 1;
                            if (newCarry > M) continue; // should not usually happen
                            // weight multiply by nums[idx]^ci / ci!
                            long add = cur;
                            add = add * powNum[idx][ci] % MOD;
                            add = add * invFact[ci] % MOD;
                            next[newT][newCarry][newS] += add;
                            if (next[newT][newCarry][newS] >= MOD) next[newT][newCarry][newS] -= MOD;
                        }
                    }
                }
            }

            dp = next;
        }

        // Collect results: we must have total chosen t == m
        long res = 0;
        for (int carry = 0; carry <= M; carry++) {
            int carryBits = Integer.bitCount(carry);
            for (int s = 0; s <= M; s++) {
                if (s + carryBits == k) {
                    res += dp[M][carry][s];
                    if (res >= MOD) res -= MOD;
                }
            }
        }

        // multiply by m!
        res = res * fact[M] % MOD;
        return (int) res;
    }

    // modular inverse via pow
    private long modInv(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long a, long e) {
        long res = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return res;
    }
}
