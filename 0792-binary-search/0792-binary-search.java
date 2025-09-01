class Solution {
    public int search(int[] nums, int target) {
    //always start with 0 to n-1
        int start =0;
        int end = nums.length-1;
//first calculate mid, then if target is less(left side), more(right side )
        while(start<=end){
            int mid = start +(end-start)/2; //calculating mid
            if(target>nums[mid]){ // right side
                start = mid+1;
            }else if(target<nums[mid]){//left side
                end = mid-1;
            }else{
                return mid; //mid == target
            }
            

        }
        return -1; //not valid target 

    }
}