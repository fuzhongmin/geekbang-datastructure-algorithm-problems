class Twitter {
    //用于记录时间
    private int time = 0;
    //用户与推文的映射,推文List自动升序排列
    private Map<Integer, List<Tweet>> userTweetMap;
    //用户与关注者的映射
    private Map<Integer, Set<Integer>> followMap;

    /** Initialize your data structure here. */
    public Twitter() {
        userTweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweetIds = userTweetMap.getOrDefault(userId, new ArrayList<>());
        tweetIds.add(new Tweet(tweetId, time++));
        userTweetMap.put(userId, tweetIds);
    }


    public List<Integer> getNewsFeed(int userId) {
        List<List<Tweet>> allTweet = new ArrayList<>();
        //自己发送的推文列表
        List<Tweet> selfTweets = userTweetMap.getOrDefault(userId, null);
        if (selfTweets != null) {
        	allTweet.add(selfTweets);
        }
        //关注的人发送的推广列表
        Set<Integer> followeeSet = followMap.getOrDefault(userId, new HashSet<>());
        if (!followeeSet.isEmpty()) {
        	for (Integer followee : followeeSet) {
        		List<Tweet> tweetIds = userTweetMap.getOrDefault(followee, null);
        		if (tweetIds != null) {
        			allTweet.add(tweetIds);
        		}
        	}
        }
        List<Integer> ans = new ArrayList<>();
        if (allTweet.size() == 0) {
        	return ans;
        }
        //建立大顶堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (List<Tweet> list : allTweet) {
        	int index = list.size() - 1;
        	Tweet maxTweet = list.get(index);
        	maxHeap.add(new Node(list, index));
        }
        while (!maxHeap.isEmpty()) {
        	Node maxNode = maxHeap.poll();
        	if (ans.size() == 10) {
        		break;
        	}
        	ans.add(maxNode.getTweetId());
        	List<Tweet> maxList = maxNode.list;
        	if (maxNode.index - 1 >= 0) {
        		maxHeap.add(new Node(maxList, maxNode.index - 1));
        	}
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeSet = followMap.getOrDefault(followerId, new HashSet<>());
        followeeSet.add(followeeId);
        followMap.put(followerId, followeeSet);
        }

        public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeeSet = followMap.get(followerId);
        if (followeeSet != null && followeeSet.contains(followeeId)) {
        	followeeSet.remove(followeeId);
        }
    }


    class Tweet {
        private int tweetId;
        private int time;

        public Tweet(int tweetId, int time) {
        	this.tweetId = tweetId;
        	this.time = time;
        }
    }

    class Node implements Comparable<Node> {
        private List<Tweet> list;
        private int index;

        public Node(List<Tweet> list, int index) {
        	this.list = list;
        	this.index = index;
        }

        public int compareTo(Node obj) {
        	return this.getTime() - obj.getTime();
        }

        public int getTweetId() {
        	return list.get(index).tweetId;
        }

        public int getTime() {
        	return list.get(index).time;
        }
    }
}