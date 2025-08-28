class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];

        //DP uses bottom-up approach 
        for(int i = m-1; i>=0; i--){
            for(int j =n-1; j>=0; j--){
                if(text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = 1 + dp[i+1][j+1]; //If characters match, we extend the subsequence by 1.
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]); //Otherwise, skip one character either from text1 or from text2 and take the best.
                }
            }
        }
        return dp[0][0]; // The answer for the whole strings starts at (0,0)
    }
}