class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) 
            prefix[i + 1] = prefix[i] + stations[i];

        long[] initialPower = new long[n];
        for (int i = 0; i < n; i++) {
            int L = Math.max(0, i - r);
            int R = Math.min(n - 1, i + r);
            initialPower[i] = prefix[R + 1] - prefix[L];
        }

        long low = 0, high = prefix[n] + k;
        while (low < high) {
            long mid = (low + high + 1) >>> 1;
            if (canAchieve(mid, initialPower, r, k))
                low = mid;
            else
                high = mid - 1;
        }
        return low;
    }

    private boolean canAchieve(long target, long[] power, int r, long k) {
        int n = power.length;
        long used = 0;

        long[] diff = new long[n + 1];
        long add = 0;

        for (int i = 0; i < n; i++) {
            add += diff[i];
            long current = power[i] + add;

            if (current < target) {
                long need = target - current;
                used += need;
                if (used > k) return false;

                int pos = Math.min(n - 1, i + r);       // where to add station
                int end = Math.min(n, pos + r + 1);     // influence end index

                add += need;            // start adding now
                diff[end] -= need;      // stop adding at end
            }
        }

        return true;
    }
}
