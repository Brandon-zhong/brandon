package com.leetcode.doublepointer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author brandon
 * create on 2019-06-19
 * desc: 盛最多水的容器
 * <p>
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * <p>
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class _11_Solution {

    /**
     * （暴力法做解）算法一：通过两个指针一个从前往后，一个从后往前移动，每次计算两个指针对应元素最下值和指针距离的乘积，
     * 更新最大的乘积，并移动两指针中最小的那个指针
     */

    public static int maxArea(int[] height) {
        int area = 0, i = 0, j = height.length - 1;
        int left = i, right = j;
        while (i <= j) {
            int h = Math.min(height[i], height[j]);
            int a = h * (j - i);
            if (a > area) {
                area = a;
                left = i;
                right = j;
            }
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        System.out.println(left + "  " + right);
        return area;
    }


    public static void main(String[] args) {

        int[] height = new int[]{1, 5, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("max Area --> " + maxArea(height));

    }
}
