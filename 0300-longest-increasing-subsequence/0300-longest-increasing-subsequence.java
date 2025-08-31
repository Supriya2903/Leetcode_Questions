class Solution {
    public int lengthOfLIS(int[] nums) {

        // Edge case: if the input array is null or empty, return 0
        if(nums == null || nums.length == 0) return 0;

        int n = nums.length;
        
        // dp[i] will store the length of the Longest Increasing Subsequence (LIS) ending at index i
        int[] dp = new int[n];
        
        // Initially, every element is a subsequence of length 1 by itself
        Arrays.fill(dp, 1);

        // Variable to keep track of the overall maximum LIS length
        int maxLen = 1;

        // Iterate over each element in nums starting from index 1
        for(int i = 1; i < n; i++) {
            
            // Check all previous elements to see if we can extend the subsequence
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    // If nums[i] can extend the subsequence ending at nums[j], update dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // Update the overall maximum LIS length
            maxLen = Math.max(maxLen, dp[i]);
        }

        // Return the length of the longest increasing subsequence found
        return maxLen;
    }
}
