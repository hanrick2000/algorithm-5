/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        int count = k;
        while (fast.next != null) {
            fast = fast.next;
            count--;
            if (count == 1) {
                break;
            }
        }
        if (count != 1) {
            return head;
        } 
        ListNode subHead = fast.next;
        fast.next = null;
        ListNode newHead = reverse(head);
        ListNode newNode = reverseKGroup(subHead, k);
        head.next = newNode;
        return newHead;
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverse(next);
        next.next = head;
        head.next = null;
        return newHead;
    }
}
