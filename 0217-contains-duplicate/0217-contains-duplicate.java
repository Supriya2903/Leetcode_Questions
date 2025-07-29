class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> visited = new HashSet<>();

        for(int i = 0; i <nums.length; i++){ // iterates through the array
            if(visited.contains(nums[i])){
                return true;  //duplicate found
            }else{
                visited.add(nums[i]); // add the number to the hashset table
            }
        }
        return false; // duplicate not found
    }
}