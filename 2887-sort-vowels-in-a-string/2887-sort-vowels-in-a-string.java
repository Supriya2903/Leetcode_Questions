class Solution {
    public String sortVowels(String s) {
        String vowels = "aeiouAEIOU";
        StringBuilder result = new StringBuilder(s);
        
        // Step 1: collect vowels
        List<Character> vowelList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                vowelList.add(c);
            }
        }
        
        // Step 2: sort vowels by ASCII
        Collections.sort(vowelList);
        
        // Step 3: replace vowels in order
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                result.setCharAt(i, vowelList.get(idx++));
            }
        }
        
        return result.toString();
    }
}