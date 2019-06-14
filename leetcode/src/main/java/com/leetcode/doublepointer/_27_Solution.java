package com.leetcode.doublepointer;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-14
 * desc: 移除元素
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class _27_Solution {

    /**
     * 算法一：定义两个指针，一个从前往后走，一个从后往前走
     * 如果前指针和后指针的值都等于val，则后指针向前移动一位，前指针保持不动
     * 如果只有前指针的值等于val，则将将后指针的值覆盖到前指针，后指针以及以前的元素都是有效的，长度为后指针的位置值+1
     */
    public static int removeElement_1(int[] nums, int val) {

//        Arrays.sort(nums);

        int start = 0, end = nums.length - 1;
        int i = 0;
        while (start <= end) {
            i++;
            if (nums[start] == val && nums[end] == val) {
                end--;
                continue;
            }
            if (nums[start] == val) {
                nums[start] = nums[end];
                end--;
            }
            start++;
        }
        System.out.println(i);
        return end + 1;
    }


    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 3, 2, 3, 3, 2, 3, 3, 3, 2, 3, 3, 3};
        int len = removeElement_1(nums, 3);
        System.out.println(Arrays.toString(Arrays.copyOf(nums, len)));

    }
}
