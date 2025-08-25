class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sorting of array
        List<List<Integer>> result =  new ArrayList<>(); // storing result

        for(int i = 0; i<nums.length && nums[i]<=0 ; i++){ // positive number discarded
            if(i ==0 || nums[i] != nums[i-1]){ // dulicate number not allowed
                twoSum2(nums, i , result); //helper function
            }
        }
           return result;
    }
    //define helper function
    void twoSum2(int[] nums, int i, List<List<Integer>> result){
        int left = i+1; // increase the pointer towards right
        int right = nums.length -1; //end of the array

        while(left<right){
            int sum = nums[i] + nums[left]+ nums[right]; // calculating the triplet
            if(sum<0){  //too small-> move left forward to increase sum
                left++;
            }else if(sum>0){ //too big, move right inward to decrease sum
                right--;
            }else{
                result.add(Arrays.asList(nums[i] , nums[left++], nums[right--]));//found triplet, add to result, move both pointers 
                            //skip duplicate for the left
            while(left<right && nums[left] == nums[left-1]){
                ++left;
            } 
            }   
        }
    }
}