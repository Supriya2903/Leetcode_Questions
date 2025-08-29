class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        //iterate through the string
        for(String word : strs){
            //convert each string into array
            char[] chars = word.toCharArray();
            
            //Sorting of arrays
            Arrays.sort(chars);

            //Key will be formed 
            String key = new String(chars);

            //Check if it exists in the map or not
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            //take the key and add the word in the hashmap
            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }
}