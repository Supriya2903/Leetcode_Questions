class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Map: user -> set of languages they know (1-indexed)
        Map<Integer, Set<Integer>> userLang = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            userLang.put(i + 1, new HashSet<>());
            for (int lang : languages[i]) {
                userLang.get(i + 1).add(lang);
            }
        }
         // Set of users who can't communicate with their friends
        Set<Integer> needTeach = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0], v = f[1];
            Set<Integer> setU = userLang.get(u), setV = userLang.get(v);
            // Check if they share a language
            boolean canCommunicate = false;
            for (int lang : setU) {
                if (setV.contains(lang)) {
                    canCommunicate = true;
                    break;
                }
            }
             // If not, add both to needTeach set
            if (!canCommunicate) {
                needTeach.add(u);
                needTeach.add(v);
            }
        }
         // For each language, count how many in needTeach don't know it
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : needTeach) {
                if (!userLang.get(user).contains(lang)) {
                    count++;
                }
            }
            minTeach = Math.min(minTeach, count);
        }
        // If all friends can already communicate, minTeach will be 0
        return minTeach == Integer.MAX_VALUE ? 0 : minTeach;
    }
}