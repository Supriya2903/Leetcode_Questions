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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // If list1 is empty, return list2 (no merging needed)
        if (list1 == null) return list2;

        // If list2 is empty, return list1 (no merging needed)
        if (list2 == null) return list1;

        // Recursive case:
        // Compare the current nodes of both lists
        if (list1.val < list2.val) {
            // list1 node is smaller → it should be part of the merged list
            // Recursively merge the rest of list1 and list2
            list1.next = mergeTwoLists(list1.next, list2);
            return list1; // return current list1 node as the head
        } else {
            // list2 node is smaller or equal → it should be part of the merged list
            // Recursively merge list1 and the rest of list2
            list2.next = mergeTwoLists(list1, list2.next);
            return list2; // return current list2 node as the head
        }
    }
}
