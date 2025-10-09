class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        // prefPrev[i] = sum_{k=0..i} skill[k]*mana[prevCol]
        long[] prefPrev = new long[n];
        long acc = 0;
        for (int i = 0; i < n; i++) {
            acc += 1L * skill[i] * mana[0];
            prefPrev[i] = acc;
        }

        long Sprev = 0L; // S_0 = 0
        for (int j = 1; j < m; j++) {
            long prefixCur = 0L;          // sum_{k=0..i-1} p[k][j] as we iterate i
            long maxA = Long.MIN_VALUE;   // max_i (prefPrev[i] - prefixCur)
            for (int i = 0; i < n; i++) {
                long valPrev = prefPrev[i];
                long A = valPrev - prefixCur;
                if (A > maxA) maxA = A;
                prefixCur += 1L * skill[i] * mana[j];
            }
            Sprev += maxA;

            // build prefPrev for current column (to be used in next iteration)
            acc = 0L;
            for (int i = 0; i < n; i++) {
                acc += 1L * skill[i] * mana[j];
                prefPrev[i] = acc;
            }
        }

        long lastLen = prefPrev[n - 1]; // total processing time of last potion across all wizards
        return Sprev + lastLen;
    }
}
