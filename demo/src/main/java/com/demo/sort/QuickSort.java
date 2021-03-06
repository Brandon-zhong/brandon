package com.demo.sort;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2019-06-16.
 * desc: 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] nums, int start, int end) {

        if (start >= end) {
            return;
        }
        int partition = partition(nums, start, end);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);


    }

    /**
     * 分区函数
     */
    private static int partition(int[] nums, int start, int end) {

        int first = start, secend = start;
        int pivot = nums[end];

        while (secend < end) {
            if (nums[secend] < pivot) {
                int temp = nums[secend];
                nums[secend] = nums[first];
                nums[first] = temp;
                first++;
            }
            secend++;
        }
        int temp = nums[first];
        nums[first] = nums[end];
        nums[end] = temp;
        return first;
    }


    /**
     * 获取数组第n大的元素
     */
    public static void get(int[] nums, int start, int end, int n) {

        if (start >= end) {
            return;
        }
        int partition = partition(nums, start, end) + 1;

        if (partition < n) {
            get(nums, partition, end, n);
        } else {
            get(nums, start, partition - 2, n);
        }
    }

    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int j = 2;
        int k = nums.length - j + 1;
        get(nums, 0, nums.length - 1, k);
        System.out.println(Arrays.toString(nums) + "   " + nums[k - 1]);

    }

}
