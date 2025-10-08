import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            long minPotion = (success + spell - 1) / spell; // ceiling division
            int idx = binarySearch(potions, minPotion);
            ans[i] = m - idx; // count of potions that work
        }

        return ans;
    }

    private int binarySearch(int[] potions, long target) {
        int left = 0, right = potions.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (potions[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // first potion >= target
    }
}
