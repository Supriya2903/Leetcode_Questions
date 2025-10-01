class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drank = 0;
        int empty = 0;
        
        int full = numBottles;
        while (full > 0) {
            drank += full;
            empty += full;
            full = empty / numExchange;
            empty = empty % numExchange;
        }
        
        return drank;
    }
}
