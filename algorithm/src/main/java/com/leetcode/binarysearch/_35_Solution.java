package com.leetcode.binarysearch;

/**
 * @author brandon
 * create on 2019-06-27
 * desc: 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class _35_Solution {

    public static int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int index = -1;
        for (; i <= j; ) {
            index = (i + j) >> 1;
            if (nums[index] > target) {
                j = index - 1;
            } else if (nums[index] < target) {
                i = index + 1;
            } else {
                return index;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 4));
    }

}
