class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //先建一个保护结点，即哨兵
        ListNode protect = new ListNode();
        ListNode tail = protect;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        if (l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }
        return protect.next;
        
        // ListNode p = l1,q = l2;
        // ListNode head = (l1.val <= l2.val) ? l1 : l2;
        // while (p != null && q != null) {
        //     if (p.val <= q.val) {
        //         ListNode temp = p;
        //         while (p != null && p.val <= q.val) {
        //             temp = p;
        //             p = p.next;
        //         }
        //         temp.next = q;
        //     } else {
        //         ListNode temp = q;
        //         while (q != null && q.val < p.val) {
        //             temp = q;
        //             q = q.next;
        //         }
        //         temp.next = p;
        //     }
        // }
        // return head;
    }
}