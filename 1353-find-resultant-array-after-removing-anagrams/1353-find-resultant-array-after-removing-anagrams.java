import java.util.*;

class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        String prev = "";

        for (String word : words) {
            String sorted = sortString(word);
            if (!sorted.equals(prev)) {
                result.add(word);
                prev = sorted;
            }
        }
        return result;
    }

    private String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
