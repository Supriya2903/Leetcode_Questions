class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0; //checks for empty array and if find nothing return 0

        int max= nums[0];       //max product ending at current index
        int min = nums[0];      //min product ending at current index
        int result = max;      //global maximum result so far

        for(int i =1; i<nums.length; i++){
            int cur = nums[i];   //cur variable is the current element we are evaluating

            int temp = Math.max(cur, Math.max(max*cur,min*cur)); //new product max(highest among the 3 is taken)
            min = Math.min(cur, Math.min(max*cur,min*cur)); // new min calculated
            max= temp;

            result = Math.max(result, max); // update global result if the new max is larger than anything
        }
        return result;
    }
}