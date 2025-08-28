/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // No cycle possible
        }
        
        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow 1 step
            fast = fast.next.next;      // Move fast 2 steps
            
            if (slow == fast) { // Cycle detected
                // Phase 2: Find the cycle start
                slow = head;  // Reset slow to head
                
                while (slow != fast) {
                    slow = slow.next;   // Move both 1 step
                    fast = fast.next;
                }
                
                return slow; // or fast, both are at cycle start
            }
        }
        
        return null; // No cycle found
    }
}
