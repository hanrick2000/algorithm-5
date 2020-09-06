#### 2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
  
  * Move the l1 and l2 pointer only when they are not null, if carry == 1, go into the loop and create a new node to store the carry
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode newNode = new ListNode(sum % 10);
            cur.next = newNode;
            carry = sum / 10;
            cur = cur.next;
        }
        return dummy.next;
    }
}
```
--------
#### 19. Remove Nth Node From End of List

Given a linked list, remove the n-th node from the end of list and return its head.

  * In case the head is the one to be removed, create a dummy node which dummy.next = head.
  * use two pointers, the first pointer move n + 1 steps (or put the slow at dummy's position), then the slow pointer begin to move,
  * when fast moves to the end (null), slow is at the n+1 th from the end.
  
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        int count = 0;
        while (count < n) {
            fast = fast.next;
            count++;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```
--------

#### 21 Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```
------
#### 23. Merge k Sorted Lists
  * two two merge use recursion
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
        
    }
    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid+ 1, right);
        return mergeTwo(l1, l2);
    }
    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```
  * use a priority queue to store the nodes, pop the smallest to add to the res.
  * similar to the BFS, the already node popped out at this step will generate it's next and add to pq.
  
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b)-> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }
        return dummy.next;
    }
}
```
------
#### 24. Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode subHead = swapPairs(head.next.next);
        head.next.next = head;
        head.next = subHead;
        return newHead;
    }
}
```
#### 25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

  * find the kth node, kth node's next is the sub problem, so it is the head of the next sub problem. Breank the list here and reverse from head to kth node.
  * Then head.next is the subproblem's new solved head.
  * return the current new head to the higher leverl.
-------
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        int n = k;
        while (n > 1) {
            if (fast.next == null) {
                return head;
            }
            fast = fast.next;
            n--;
        }
        ListNode next = fast.next;
        fast.next = null;
        ListNode newHead = reverse(head);
        ListNode subHead = reverseKGroup(next, k);
        head.next = subHead;
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
```
------
#### 148. Sort List

Sort a linked list in O(n log n) time using constant space complexity.

  * Find the mid node, the nodes before mid (include mid) will be the first half, mid.next is the second half, then set mid.next = null
  * merge the two sides.
  
```java
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(midNext);
        ListNode newHead = merge(l1, l2);
        return newHead;
    }
    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head; 
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

-------

#### 206. Reverse Linked List

Reverse a singly linked list.

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```
  * recursion
  
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```
