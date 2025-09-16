class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        
        for (int num : nums) {
            stack.add(num);
            
            // keep merging while last two are non-coprime
            while (stack.size() > 1) {
                int a = stack.get(stack.size() - 2);
                int b = stack.get(stack.size() - 1);
                int g = gcd(a, b);
                
                if (g == 1) break; // coprime → stop
                
                // merge into LCM
                long lcm = (long) a / g * b;
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add((int) lcm);
            }
        }
        
        return stack;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}