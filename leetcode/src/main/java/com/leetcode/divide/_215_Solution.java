package com.leetcode.divide;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author brandon
 * create on 2019-07-09
 * desc: 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class _215_Solution {

    /**
     * 借鉴快排的思路，快排的分区函数将数组分成两部分，前一部分小于基准值，后一部分大于基准值，
     * 假设前一部分的数量为K，后一部分的数量为L，那么基准值为第k+1大的元素，也是第L-1个最大的元素
     */
    public int findKthLargest_1(int[] nums, int k) {

        int l = 0, r = nums.length - 1;
        k = nums.length - k;
        while (l < r) {
            int partition = partition(nums, l, r);
            if (partition > k) {
                r = partition - 1;
            } else if (partition < k) {
                l = partition + 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int start, int end) {
        int first = start, secend = start;
        int pivot = nums[end];
        while (secend < end) {
            if (nums[secend] < pivot) {
                //将secend的值换到first，并将first+1
                swap(nums, first++, secend);
            }
            secend++;
        }
        //分区结束后，将最后的基准值放到first的位置
        swap(nums, first, end);
        return first;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 利用堆排序的思路，创建一个K大小的大顶堆，将数据遍历放入堆中，每次遍历的时候判断堆是否大于K，是则弹出一个元素
     */
    public int findKthLargest_2(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparing(Integer::intValue));

        // keep k largest elements in the heap
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }

    public static void main(String[] args) {
        int[] nums = {3, 9, 2, 7, 1, 5, 6, 4};
        int partition = new _215_Solution().findKthLargest_2(nums, 2);
        System.out.println(partition);
    }

}
