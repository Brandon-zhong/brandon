package com.leetcode.doublepointer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author brandon
 * create on 2019-06-13
 * desc: 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 */
public class _349_Solution {


    /**
     * 算法一：遍历数组一将所有元素的，值作key存map，value都为1，然后遍历数组二，map中存在的值即为交集元素
     */
    public static int[] intersection_1(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums1).forEach(i -> map.put(i, 1));

        Arrays.stream(nums2).forEach(i -> {
            if (map.containsKey(i) && map.get(i) == 1) {
                set.add(i);
            }
        });

        int[] res = new int[set.size()];
        int index = 0;
        for (Integer i : set) {
            res[index++] = i;
        }
        return res;
    }

    /**
     * 算法二：将两个集合从小到大排序，同时从头部开始遍历数组
     * 比较两个数字的大小，小的向后移动一位，相等即为交集元素
     */
    public static int[] intersection_2(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> set = new HashSet<>();
        int index1 = 0, index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                set.add(nums1[index1]);
                index1++;
                index2++;
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (Integer i : set) {
            res[index++] = i;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{4, 9, 5}, nums2 = new int[]{9, 4, 9, 8, 4};

        System.out.println(Arrays.toString(intersection_2(nums1, nums2)));

    }

}






