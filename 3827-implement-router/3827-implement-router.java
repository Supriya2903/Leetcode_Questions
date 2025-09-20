import java.util.*;

class Router {
    private int memoryLimit;
    private Queue<int[]> queue;
    private Set<String> seen;
    private Map<Integer, ArrayList<Integer>> destMap;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new LinkedList<>();
        this.seen = new HashSet<>();
        this.destMap = new HashMap<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;
        if (seen.contains(key)) return false; // duplicate
        
        // evict oldest if memory full
        if (queue.size() == memoryLimit) {
            int[] removed = queue.poll();
            String remKey = removed[0] + "#" + removed[1] + "#" + removed[2];
            seen.remove(remKey);
            // remove timestamp from destMap
            ArrayList<Integer> list = destMap.get(removed[1]);
            list.remove(Collections.binarySearch(list, removed[2])); 
        }
        
        // add new packet
        queue.offer(new int[]{source, destination, timestamp});
        seen.add(key);
        destMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        return true;
    }
    
    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[]{}; 
        
        int[] packet = queue.poll();
        String key = packet[0] + "#" + packet[1] + "#" + packet[2];
        seen.remove(key);
        
        ArrayList<Integer> list = destMap.get(packet[1]);
        list.remove(Collections.binarySearch(list, packet[2]));
        
        return packet;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) return 0;
        ArrayList<Integer> list = destMap.get(destination);
        
        // binary search lower bound
        int left = lowerBound(list, startTime);
        int right = upperBound(list, endTime);
        return Math.max(0, right - left);
    }
    
    private int lowerBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    private int upperBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */