package com.leetcode.array;

/**
 * @author brandon
 * Created on 2019-08-19.
 * desc: 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 * 示例 3：
 * <p>
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 *  
 * <p>
 * 提示：
 * <p>
 * 0 ≤ N ≤ 30
 * <p>
 **/
public class _509_Solution {

    /**
     * 递归方法，最简单，但是时间复杂度很高
     */
    public static int fib_1(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib_1(N - 1) + fib_1(N - 2);
    }

    public static int fib_2(int N) {
        int one = 1, two = 1;
        int three = one;
        if (N == 0) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        for (int i = 2; i < N; ++i) {
            one = two;
            two = three;
            three = one + two;
        }
        return three;
    }

    public static void main(String[] args) {
        int fib = _509_Solution.fib_2(3);
        System.out.println(fib);
    }

}
