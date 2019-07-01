package com.leetcode.binarysearch;

/**
 * @author brandon
 * create on 2019-06-27
 * desc: 山脉数组的峰顶索引
 * 我们把符合下列属性的数组 A 称作山脉：
 * <p>
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[0,2,1,0]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 */
public class _852_Solution {

    /**
     * 简单二分查找，不做详细解释
     */
    public static int peakIndexInMountainArray(int[] A) {

        int i = 0, j = A.length - 1;
        int index = -1;
        for (; i < j; ) {
            index = (i + j) >> 1;
            int k = A[index];
            if (k > A[index - 1] && k < A[index + 1]) {
                i = index;
            } else if (k < A[index - 1] && k > A[index + 1]) {
                j = index;
            } else {
                return index;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray(new int[]{3, 5, 3, 2, 0}));
    }

}
