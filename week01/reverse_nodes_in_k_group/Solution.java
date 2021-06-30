class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        int size = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            size++;
        }
        //等同于普通的反转链表
        if (k == size) {
            ListNode last = null;
            while (head != null) {
                ListNode temp = head.next;
                head.next = last;
                last = head;
                head = temp;
            }
            return last;
        }
        //循环次数
        int loop = size / k;
        ListNode newHead = null, start = head, prev = null;
        for (int i = 0; i < loop; i++) {
            if (i == 0) {
                newHead = reverseBetween(start, k, prev);
                printList(newHead);
                prev = start;
                start = start.next;
                continue;
            }
            reverseBetween(start, k, prev);
            prev = start;
            start = start.next;
            printList(newHead);
        }
        return newHead;       
    }
    
    //从startNode开始，反转k个结点，prev为startNode的前置,返回反转后的新起始结点
    public ListNode reverseBetween(ListNode startNode, int k, ListNode prevOfStartNode) {
        ListNode last = null, p = startNode;
        for (int i = 0; i < k; i++) {
            ListNode temp = p.next;
            p.next = last;
            last = p;
            p = temp;
        }
        if (prevOfStartNode != null) {
            prevOfStartNode.next = last;
        }
        startNode.next = p;
        return last;
    }
    
    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println("");
    }
}