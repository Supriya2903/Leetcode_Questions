class Solution {
    public long flowerGame(int n, int m) {
        long oddX = (n + 1L) / 2L;
        long evenX = n / 2L;
        // Count of odd and even y in [1..m]
        long oddY = (m + 1L) / 2L;
        long evenY = m / 2L;
        // Alice wins when x+y is odd:
        // (odd x & even y) or (even x & odd y)
        return oddX * evenY + evenX * oddY;
    }
}