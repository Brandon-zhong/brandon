package com.demo.sort;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2020-01-14.
 * desc: 堆排序, 这里用的所有堆都是从1下标开始的
 **/
public class HeapSort {

    /**
     * 将指定数组堆话 从前往后堆化，插入堆的思路
     * 假设堆中就只有一个元素，然后将数组中的元素依次插入到堆中，最后完成堆化
     */
    public void buildHeap_1(int[] nums, int len) {
        for (int i = 2; i <= len; i++) {
            downToUpheapify(nums, i);
        }
    }

    private void downToUpheapify(int[] nums, int n) {
        int index = n;
        while (true) {
            int parent = index >> 1;
            if (parent <= 0) {
                break;
            }
            //当前节点大于父节点的值，交换，并把坐标设置到父节点
            if (nums[index] > nums[parent]) {
                swap(nums, index, parent);
            }
            index = parent;
        }
    }

    /**
     * 从数组的后面向前遍历建堆，每次成功交换后，需要将交换后的子节点作为根节点继续向后堆化
     */
    public void buildHeap_2(int[] nums, int len) {

        //跳过叶子节点
        for (int i = len >> 1; i > 0; i--) {
            //计算叶子节点
            int index = i;
            while (true) {
                int max = index;
                int left = index << 1;
                int right = left + 1;
                if (left <= len && nums[left] > nums[max]) {
                    max = left;
                }
                if (right <= len && nums[right] > nums[max]) {
                    max = right;
                }
                if (max == index) {
                    break;
                }
                //交换
                swap(nums, index, max);
                index = max;
            }
        }

    }

    public void heapSort(int[] nums) {

        for (int i = nums.length - 1; i > 0; i--) {
            //对0-i的元素堆化
            buildHeap_2(nums, i);
            //将对顶的元素和i进行交换
            swap(nums, 1, i);
        }

    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 9, 3, 11, 78, 7};
        System.out.println(Arrays.toString(nums));
        new HeapSort().heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
