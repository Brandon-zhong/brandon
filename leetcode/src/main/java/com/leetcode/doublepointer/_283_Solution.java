package com.leetcode.doublepointer;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-14
 * desc: 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 */
public class _283_Solution {


    /**
     * 算法一：将0移动到后面其实就是将非0的数字移动到前面，定义一个index指针指向第一个元素，
     * 遍历数组将非0元素移动到index并将index+1，最后将index及后面的元素都置为0
     */
    public static void moveZeroes_1(int[] nums) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        //将index和后面的元素置为空
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes_1(nums);
        System.out.println(Arrays.toString(nums));
    }
}
