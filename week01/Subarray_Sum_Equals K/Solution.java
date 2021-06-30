class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int[] sum = new int[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        sum[0] = 0;
        map.put(sum[0], 1);
        //建立前缀和数组与计数映射，每算出一个前缀和sum[i],就寻找在其前面值等于(sum[i] - k)的元素个数
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
            int target = sum[i + 1] - k;
            result += map.getOrDefault(target, 0);
            Integer count = map.putIfAbsent(sum[i + 1], 1);
            if (count != null) {
                map.put(sum[i + 1], ++count);
            }
        }
        //暴力O(n2)
        // for (int i = sum.length - 1; i >= 1; i--) {
        //    int target = sum[i] - k;
        //    for (int j = i - 1; j >= 0; j--) {
        //        if (sum[j] == target) {
        //            result +=  1;
        //        }
        //    }
        // }
        return result;  
    }
}