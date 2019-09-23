package com.leetcode.array;

/**
 * @author brandon
 * Created on 2019-07-13.
 * desc: 不修改数组找出重复的数字。
 * 在一个长度为 n+1 的数组里的所有数字都在1~n的范围内。所以数组中至少有一个数字是重复的。
 * 请找出数组中任意一个重复的数字，但不能修改输入的数组。例如，如果输入长度为8的数组{2,3,5,4,3,2,6,7}，
 * 那么对应的输出是重复的数组2或者3
 */
public class InterviewQuestions_3_2 {

    /**
     *
     */
    public static int solution_1(int[] nums) {

        //数组范围为 1 ~ nums.length - 1
        int start = 1, end = nums.length - 1;
        while (start < end) {
            int prive = (end + start) >> 1;
            //统计 start~prive的数字的个数
            int count = countNum(nums, start, prive);
            if (count != ((prive - start) + 1)) {
                end = prive;
                continue;
            }
            start = prive + 1;
        }
        return start;
    }

    private static int countNum(int[] nums, int start, int end) {
        int count = 0;
        for (int num : nums) {
            if (num >= start && num <= end) {
                ++count;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 5, 4, 3, 7, 6, 7};
        int i = solution_1(nums);
        System.out.println(i);

    }


}
