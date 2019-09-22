package com.leetcode.array;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2019-08-04.
 * desc: 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2:
 * <p>
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 说明:
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 */
public class _832_Solution {


    /**
     * 题目很简单，思路直接看代码吧，去反用的异或 ^  0^0=0；   0^1=1；   1^0=1；   1^1=0
     */
    public static int[][] flipAndInvertImage(int[][] A) {

        int[][] nums = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; ++i) {
            int start = 0, end = A[i].length - 1;
            while (start <= end) {
                int temp = A[i][end];
                nums[i][end] = A[i][start] ^ 1;
                nums[i][start] = temp ^ 1;
                ++start;
                --end;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        for (int[] a : A) {
            System.out.println(Arrays.toString(a));
        }
        int[][] ints = flipAndInvertImage(A);
        System.out.println("--------------");
        for (int[] a : ints) {
            System.out.println(Arrays.toString(a));
        }


    }

}
