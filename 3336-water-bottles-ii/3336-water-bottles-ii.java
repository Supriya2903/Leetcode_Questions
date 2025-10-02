class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int drank = 0;
        int full = numBottles;
        int empty = 0;

        while (full > 0) {
            // Drink all current full bottles
            drank += full;
            empty += full;
            full = 0;

            // Try to exchange once if possible
            if (empty >= numExchange) {
                empty -= numExchange;
                full = 1;  // gained 1 full bottle
                numExchange++; // cost increases
            }
        }

        return drank;
    }
}
