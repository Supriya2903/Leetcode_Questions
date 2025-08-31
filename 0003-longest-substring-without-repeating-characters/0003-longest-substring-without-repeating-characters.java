class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Set to keep track of unique characters in the current window
        Set<Character> set = new HashSet<>();
        
        int left = 0;     // left pointer of the sliding window
        int maxLen = 0;   // result: length of longest substring without repeating characters

        // Expand the window with the right pointer
        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If the character is already in the set (duplicate found),
            // shrink the window from the left until the duplicate is removed
            while(set.contains(c)) {
                set.remove(s.charAt(left)); // remove the leftmost character
                left++; // move the left pointer forward
            }

            // Add the current character into the set (unique now)
            set.add(c);

            // Update the maximum window size
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Return the maximum length found
        return maxLen;
    }
}
