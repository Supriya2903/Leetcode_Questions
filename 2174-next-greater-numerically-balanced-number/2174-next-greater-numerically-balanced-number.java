class Solution {
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; ; i++) {
            if (isNumericallyBalanced(i)) return i;
        }
    }

    private boolean isNumericallyBalanced(int x) {
        int[] count = new int[10];
        int t = x;
        while (t > 0) {
            count[t % 10]++;
            t /= 10;
        }
        // digit 0 must not appear; any other digit d if appears must appear exactly d times
        if (count[0] > 0) return false;
        for (int d = 1; d <= 9; d++) {
            if (count[d] > 0 && count[d] != d) return false;
        }
        return true;
    }
}
