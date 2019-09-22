package com.leetcode.binarysearch;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-27
 * desc: 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 */
public class _167_Solution {

    /**
     * 简单的双指针法，前后两指针遍历
     */
    public static int[] twoSum_1(int[] numbers, int target) {

        int i = 0, j = numbers.length - 1;
        for (; i < j; ) {
            if (numbers[j] > target) {
                j--;
                continue;
            }
            int k = numbers[i] + numbers[j];
            if (k > target) {
                j--;
            } else if (k < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }

    public static int[] twoSum_2(int[] numbers, int target) {

        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum_2(new int[]{2, 7, 11, 15}, 9)));
    }
}
