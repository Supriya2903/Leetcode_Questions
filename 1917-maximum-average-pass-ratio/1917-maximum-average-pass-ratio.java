class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max heap based on gain
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        
        // Initialize heap: {gain, pass, total}
        for (int[] c : classes) {
            int pass = c[0], total = c[1];
            pq.offer(new double[]{gain(pass, total), pass, total});
        }
        
        // Assign extra students
        while (extraStudents-- > 0) {
            double[] cur = pq.poll();
            int pass = (int) cur[1], total = (int) cur[2];
            pass++;
            total++;
            pq.offer(new double[]{gain(pass, total), pass, total});
        }
        
        // Calculate final average
        double sum = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            sum += cur[1] / cur[2];
        }
        
        return sum / classes.length;
    }
    
    // Helper: calculate gain if we add one student
    private double gain(int pass, int total) {
        return (double)(pass + 1) / (total + 1) - (double)pass / total;
    }
}