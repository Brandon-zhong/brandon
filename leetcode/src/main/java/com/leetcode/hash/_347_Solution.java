package com.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brandon
 * Created on 2019-07-09.
 * desc: 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class _347_Solution {

    /**
     * 通排序
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        //先用hash表统计元素的频次
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.merge(i, 1, (a, b) -> a + b);
        }
        List[] ints = new List[nums.length + 1];
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            List list = ints[value];
            if (list == null) {
                list = new ArrayList();
                ints[value] = list;
            }
            list.add(key);
        }
        List<Integer> list = new ArrayList<>(k);
        for (int i = ints.length - 1; i >= 0; i--) {
            if (list.size() >= k) {
                break;
            }
            List li = ints[i];
            if (li != null) {
                list.addAll(li);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        List<Integer> list = new _347_Solution().topKFrequent(new int[]{-1, -1}, 2);
        System.out.println(list.toString());

    }

}
