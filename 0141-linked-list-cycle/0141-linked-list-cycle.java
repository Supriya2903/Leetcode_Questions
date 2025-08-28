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
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false; //If head is null or only one node with no next, no cycle.

        ListNode slow = head; //Initialize Pointers
        ListNode fast = head; //Initialize Pointers

        while(fast != null && fast.next!= null){
            slow = slow.next; //Move slow by 1, fast by 2.
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}