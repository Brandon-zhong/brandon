package com.other;

import java.util.List;

/**
 * @author brandon
 * Created on 2019-07-13.
 * desc: 找出数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中的某些数字是重复的，
 * 但不知道有一个数字重复了，也不知道每个数组重复了几次。请找出数组中的任意一个重复的数组。
 * 例如，如果输入长度为7的数组 {2,3,1,0,2,5,3}，
 * 那么对应的输出是重复的数字2或者3。
 */
public class InterviewQuestions_3_1 {

    /**
     * 根据题目可知，长度为n的数组里所有元素都是在0~n-1的范围内，
     * 所以如果数组内没有重复元素，则数组内的下标和元素值可以一一对应，
     * 基于这个思路，遍历数组，先判断下标i和值j是否一致，一致则继续遍历，不一致，则找到值j为下标的元素k
     * 判断该值j和k是否一直，如果一致则表示j和k为重复值，直接返回，如果不一直，将将值j和和元素k交换位置
     * 使得值j在正确的位置上，然后继续判断下标i所在的值
     */
    public static int solution_1(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            //取出i位置的值和i比较，相等则表示此位置的值在正确的位置上
            int j = nums[i];
            if (i == j) {
                i++;
                continue;
            }
            //如果不相等，则用i位置的值当作下标去找
            int k = nums[j];
            if (k == j) {
                return k;
            }
            //不是重复值，则交换这两个位置的值，将i位置的值放到正确的位置上
            nums[j] = j;
            nums[i] = k;
        }
        return -1;
    }

    /**
     * 查找所有的重复元素
     */
    public static List<Integer> solution_2(int[] nums) {
        int i = 0;
        while (i < nums.length) {

        }
        return null;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int i = solution_1(nums);
        System.out.println(i);

    }


}
