class Solution {
    public int maxFrequencyElements(int[] nums) {
         // HashMap to store frequency of each element
        HashMap<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0; // Track the maximum frequency

        // Count frequency of each element
        for (int num : nums) {
            int count = freq.getOrDefault(num, 0) + 1;
            freq.put(num, count);
            // Update max frequency if needed
            if (count > maxFreq) maxFreq = count;
        }

        int total = 0; // To store sum of frequencies with max frequency

        // Sum up frequencies that match maxFreq
        for (int count : freq.values()) {
            if (count == maxFreq) {
                total += count;
            }
        }
       return total;
    }
}