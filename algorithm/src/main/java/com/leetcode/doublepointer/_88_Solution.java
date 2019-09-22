package com.leetcode.doublepointer;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2019-06-15.
 * desc: 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class _88_Solution {

    /**
     * 算法一：参照归并排序的思路，定义一个 m + n 大小的临时数组用于盛放合并好的数组，最后将临时数组写会num1数组
     */
    public static void merge_1(int[] nums1, int m, int[] nums2, int n) {

        int index1 = 0, index2 = 0, index = 0;
        int[] temp = new int[m + n];

        while (index1 < m && index2 < n) {
            temp[index++] = (nums1[index1] <= nums2[index2]) ? nums1[index1++] : nums2[index2++];
        }

        //检查那个数组还剩元素
        int t = index1;
        int[] c = nums1;
        int len = m;
        if (index1 == m) {
            t = index2;
            c = nums2;
            len = n;
        }

        //将剩下的元素写入temp中
        while (t < len) {
            temp[index++] = c[t++];
        }

        index = 0;
        //将temp中的元素写入nums1中
        while (index < temp.length) {
            nums1[index] = temp[index++];
        }
    }

    /**
     * 算法二：算法一为了盛放临时数据需要另外声明内存，如果从num1数组的后面开始写入的话就可以避免声明声明内存
     * 从后向前遍历，依次比较，将大的元素放入num1数组的尾部，最后将num2剩下的数据直接写入num1中
     */
    public static void meger_2(int[] nums1, int m, int[] nums2, int n) {

        int index1 = m - 1, index2 = n - 1;
        int index = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            nums1[index--] = (nums1[index1] >= nums2[index2]) ? nums1[index1--] : nums2[index2--];
        }

        //将剩下的元素写入num1中
        while (index2 >= 0) {
            nums1[index--] = nums2[index2--];
        }
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 4, 6, 0, 0};
        int[] nums2 = new int[]{2, 3};

        meger_2(nums1, 3, nums2, 2);
        System.out.println(Arrays.toString(nums1));


    }

}
