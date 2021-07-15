
class MedianFinder {

	private PriorityQueue<Integer> minHeap;
	private PriorityQueue<Integer> maxHeap;
	private int size;

    /** initialize your data structure here. */
    public MedianFinder() {
    	size = 0;
    	minHeap = new PriorityQueue<>();
    	maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    //大顶堆的poll元素，要小于或等于小项堆的poll
    //数量为偶数时，大顶堆size==小顶堆size
    //数量为奇数时，大顶堆size=小顶堆size+1
    public void addNum(int num) {
    	//更新size
    	size++;
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if (size%2 == 0) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
    	if (size%2 == 1) {
    		return minHeap.peek();
    	}
    	return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}