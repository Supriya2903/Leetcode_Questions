class Solution {
    public boolean canPartition(int[] nums) {
       int sum = 0;

        // Step 1: Calculate the total sum of the array
        for (int num : nums) {
            sum += num;
        }
        
        // Step 2: If sum is odd, it's impossible to split equally
        if (sum % 2 != 0) return false;
        
        // Step 3: Our target is half of the total sum
        int target = sum / 2;

        // Step 4: dp[j] = true means we can form sum 'j' using some subset of numbers
        boolean[] dp = new boolean[target + 1];

        // Base case: sum = 0 is always possible (by taking no elements)
        dp[0] = true;  
        
        // Step 5: Process each number in the array
        for (int num : nums) {
            // Traverse backwards so that each number is only used once
            for (int j = target; j >= num; j--) {
                // Either we already could form 'j' (dp[j] was true),
                // OR we can form 'j' by including this number (dp[j - num])
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        // Step 6: Final answer is whether we can form 'target'
        return dp[target]; 
    }
}