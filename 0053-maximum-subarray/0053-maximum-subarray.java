class Solution {
    public int maxSubArray(int[] nums) {
        int curSum = 0;  // Current sum of the array
        int maxSum = nums[0]; // maximum is initialized with the first element in the array

        for(int i =0; i<nums.length; i++){ //scan array
            if(curSum <0){ //dropping the negative value
                curSum = 0;
            }
            curSum = curSum + nums[i]; //adding the new number to the current value
            maxSum = Math.max(curSum, maxSum); // update the max if needed
        }
        return maxSum;
    }
}