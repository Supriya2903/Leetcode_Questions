import java.util.*;

class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Long> map = new HashMap<>();
        for (int p : power) {
            map.put(p, map.getOrDefault(p, 0L) + p);
        }

        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        int n = keys.size();
        long[] dp = new long[n];
        dp[0] = map.get(keys.get(0));

        for (int i = 1; i < n; i++) {
            long take = map.get(keys.get(i));
            
            // Binary search to find last non-conflicting index
            int j = binarySearch(keys, i - 1, keys.get(i) - 3);
            if (j != -1) take += dp[j];
            
            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }

    private int binarySearch(List<Integer> keys, int right, int target) {
        int low = 0, high = right, ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (keys.get(mid) <= target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
