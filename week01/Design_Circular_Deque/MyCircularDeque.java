class MyCircularDeque {

    //新建节点类
   class Node {
        int val;
        Node next;
        Node prev;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;
    private int capactity = 0;

    
    public MyCircularDeque(int k) {
        this.capactity = k;
        this.head = null;
        this.tail = null;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (size >= capactity) {
            return false;
        }
        Node node = new Node(value);
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        //如果当前添加的元素是第1个元素，要设置尾指针
        if (size == 0) {
            tail = head;
        }
        ++size;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (size >= capactity) {
            return false;
        }
        Node node = new Node(value);
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        //如果当前添加的元素是第1个元素，要设置头指针
        if (size == 0) {
            head = tail;
        }
        ++size;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (head == null || size == 0) {
            return false;
        }
        Node temp = new Node(0);
        temp.next = head.next;
        head.next = null;
        head = temp.next;
        --size;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (tail == null || size == 0) {
            return false;
        }
        Node temp = tail.prev;
        tail.prev = null;
        tail = temp;
        if (tail != null) {
            tail.next = null;
        }
        --size;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (size == 0 || head == null) {
            return -1;
        }
        return head.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (size == 0 || tail == null) {
            return -1;
        }
        return tail.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == capactity;
    }
}
