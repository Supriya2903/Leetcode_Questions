class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length()) return false;
        
        // Array to store frequency of 26 lowercase letters
        int[] count = new int[26];
        
        // Traverse both strings simultaneously
        for (int i = 0; i < s.length(); i++) {
            // Increment count for character in string s
            count[s.charAt(i) - 'a']++;
            // Decrement count for character in string t
            count[t.charAt(i) - 'a']--;
        }
        
        // After processing both strings,
        // if all counts are zero → strings are anagrams
        for (int c : count) {
            if (c != 0) return false; // mismatch found
        }
        
        return true; // all counts matched → valid anagram
    }
}
