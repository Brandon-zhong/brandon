package com.leetcode.array;

/**
 * @author brandon
 * create on 2019-11-11
 * desc: 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 */
public class _35_Solution {

    //TODO 未完成
    public static int maxSubArray(int[] nums) {
        int sum = nums[0], first = 0, index = 0;
        for (int i = 0; i < nums.length; ++i) {
            int tmp = sum;
            if (first != i) {
                tmp += nums[i];
            }
            if (tmp > sum) {
                sum = tmp;
                index = i;
            }
            if (tmp < 0) {
                first = i;
            }
        }
        System.out.println(first + "  " + index + "   sum --> " + sum);
        return sum;
    }

    public static void main(String[] args) {
        maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

}
