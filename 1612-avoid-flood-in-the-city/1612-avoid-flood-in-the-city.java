import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> full = new HashMap<>(); // lake -> last rainy day
        TreeSet<Integer> dryDays = new TreeSet<>();   // available dry days

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                ans[i] = 1; // default value (we may update later)
            } else {
                ans[i] = -1;
                int lake = rains[i];
                if (full.containsKey(lake)) {
                    Integer dryDay = dryDays.higher(full.get(lake)); // find next dry day after last rain
                    if (dryDay == null) {
                        return new int[0]; // flood inevitable
                    }
                    ans[dryDay] = lake; // dry this lake on that dry day
                    dryDays.remove(dryDay);
                }
                full.put(lake, i); // mark this lake as filled
            }
        }

        return ans;
    }
}
