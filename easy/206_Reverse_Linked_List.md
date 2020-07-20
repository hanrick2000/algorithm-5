### Reverse a singly linked list
#### Method 1 Iterative
* Use pre head next three pointers to iterate the linked list
* Note: the stop condition is head != null, that means when finishing the while loop, head == null. Each note should go into the while loop in order to be reversed. 
* The new head is at the pre pointer.
```java
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
```
#### Method 2 Recursion
* Base case is when only one node left.
* Reverse current node
```java
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        head.next = null;
        return newHead;
    }
```
