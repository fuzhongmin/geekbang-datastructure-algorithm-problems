class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        //last初始时代表head的前继节点，由于头节点没有前继，因此last初始是null
        ListNode last = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = last;
            last = head;
            head = temp;
        }
        return last;
    }
}