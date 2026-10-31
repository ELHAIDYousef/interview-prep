package prep.linkedlist;

/**
 *
 * @author Yousef ELHAID
 */
public final class ListAlgorithms {

    private ListAlgorithms() {
    }

    public static boolean hasCycle(ListNode head) { // T O(n) M O(1)
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode middle(ListNode head) { // T O(n) M O(1)
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) { // T O(n) M O(n)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}