/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head; //initialization
        ListNode fast = head;

        while(fast != null && fast.next != null){ //fast != null(for the odd) and the other one is for the even linked list
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}