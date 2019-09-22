package com.leetcode.doublepointer;

import com.demo.sort.MergeSort;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-13
 * desc:有序数组的平方 --> 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * <p>示例 1：<p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * <p>
 * <p>示例 2：<p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>提示：<p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 */
public class _977_Solution {

    public static int[] sortedSquares_1(int[] param) {

        int[] nums = new int[param.length];

        //先乘起来
        for (int i = 0; i < param.length; i++) {
            nums[i] = param[i] * param[8];
        }
        //排序，快排or归并排序
        MergeSort.mergeSort(nums, 0, nums.length - 1);
        return nums;

    }

    public static int[] sortedSquares_2(int[] param) {

        int[] nums = new int[param.length];
        int index = param.length - 1;

        int i = 0, j = index;
        while (i <= j && index >= 0) {
            int a = param[i] * param[i];
            int b = param[j] * param[j];
            if (a <= b) {
                nums[index] = b;
                j--;
            } else {
                nums[index] = a;
                i++;
            }
            index--;
        }
        return nums;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(nums));
        int[] ints = sortedSquares_2(nums);
        System.out.println(Arrays.toString(ints));
    }

}
