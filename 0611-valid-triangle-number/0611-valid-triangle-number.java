class Solution {
    public int triangleNumber(int[] nums) {
          // Sort the array to use two pointers efficiently
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        // Fix the largest side at index k, iterate from end
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1;
            // Use two pointers to find valid pairs
            while (i < j) {
                // If the sum of the two smaller sides is greater than the largest
                if (nums[i] + nums[j] > nums[k]) {
                    // All pairs between i and j are valid
                    count += (j - i);
                    j--; // Try next smaller j
                } else {
                    i++; // Try next bigger i
                }
            }
        }
        return count;

    }
}