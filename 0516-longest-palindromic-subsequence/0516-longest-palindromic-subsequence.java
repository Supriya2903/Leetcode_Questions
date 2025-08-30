class Solution {
    public int longestPalindromeSubseq(String s) {
// Reverse the string because LPS(s) = LCS(s, reverse(s))
        String rev = new StringBuilder(s).reverse().toString();
        return lcs(s, rev);  // Compute LCS of s and its reverse
    }

    // Function to compute Longest Common Subsequence (LCS)
    private int lcs(String a , String b){
        int m = a.length();
        int n = b.length();

        // dp[i][j] will store LCS length of a[i..end] and b[j..end]
        int[][] dp = new int[m+1][n+1]; // Extra row & col (base cases = 0)

        // Fill the dp table from bottom-right corner (since using i+1, j+1)
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                
                // If characters match, extend the subsequence
                if(a.charAt(i) == b.charAt(j)){
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    // Otherwise, take the best by skipping one char
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        // The answer for the full strings is stored at dp[0][0]
        return dp[0][0];
    }
}