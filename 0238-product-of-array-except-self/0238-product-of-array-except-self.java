class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int pre =1, post =1;

        for(int i =0; i< nums.length; i++){
            result[i] = pre;
            pre *= nums[i];
        }
        for(int i = n-1; i>=0; i--){
            result[i] *= post;
            post *= nums[i];
        }
        return result;
    }
}