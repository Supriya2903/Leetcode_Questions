class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;

        while(start <= end){
            int mid = start + (end-start)/2;

            //target found, it is present at mid
            if(nums[mid] == target){
                return mid;
            }
//left half is sorted
            if(nums[start] <= nums[mid]){
                //checking on the left side
                if(nums[start] <= target && target<nums[mid]){
                    end = mid-1; // target lies in left half
                }else{
                    start = mid +1; // target lies in right half
                }
            
            //otherwise right half is sorted
            }else{
                if(nums[end] >= target && target > nums[mid]){
                    start = mid+1; // target lies in rightt half
                }else{
                    end = mid-1; // target lies in left half
                }

            }
        }
        return -1;
    }
}