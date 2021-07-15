public class Solution {

    /**
     * 描述
     给定一个长度为 n 的序列 A，A 中的数各不相同。对于 A 中的每一个数 A_i，求：
     min(1≤j<i) ⁡|A_i-A_j|
     以及令上式取到最小值的 j（记为 P_i）。若最小值点不唯一，则选择使 A_j 较小的那个。
     输出格式
     n-1行，每行2个用空格隔开的整数。分别表示当i取2~n时，对应的 min(1≤j<i) ⁡|A_i-A_j| 和 P_i 的值。
     其实就是求a中每个数的邻值，以及其邻值的下标
     返回值是多个长度为2的列表组成的列表
     * @param a
     * @param n
     * @return
     */
    List<List<Integer>> findNeighborValue(int[] a, int n) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        treeSet.add(a[0]);
        map.put(a[0], 1);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            List<Integer> res = new ArrayList<>();
            int neighborValue = -1, index = -1;
            Integer lower = treeSet.lower(a[i]);
            Integer higher = treeSet.higher(a[i]);
            if (lower != null && higher != null) {
                int lowerAbs = Math.abs(lower - a[i]);
                int higherAbs = Math.abs(higher - a[i]);
                neighborValue = Math.min(lowerAbs, higherAbs);
                if (lowerAbs == higherAbs) {
                    index = Math.min(map.get(lower), map.get(higher));
                } else {
                    index = (lowerAbs < higherAbs ? map.get(lower) : map.get(higher));
                }

            } else if (lower != null) {
                neighborValue = Math.abs(lower - a[i]);
                index = map.get(lower);
            } else if (higher != null) {
                neighborValue = Math.abs(higher - a[i]);
                index = map.get(higher);
            }
            res.add(neighborValue);
            res.add(index);
            ans.add(res);
            map.put(a[i], i + 1);
            treeSet.add(a[i]);
        }
        return ans;
    }
}