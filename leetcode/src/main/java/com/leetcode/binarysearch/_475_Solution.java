package com.leetcode.binarysearch;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-27
 * desc: 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 * <p>
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 * <p>
 * 说明:
 * <p>
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 * <p>
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * <p>
 */
public class _475_Solution {

    /**
     * 题目有点坑，大概讲一下思路，遍历所有房子，然后查找里房子最近的一个供暖器，求出房子离供暖器的距离，遍历完房子后找出所有距离的最大值
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int len = 0;
        if (heaters.length == 1) {
            return Math.max(Math.abs(heaters[0] - houses[0]), Math.abs(heaters[0] - houses[houses.length - 1]));
        }
        for (int i = 0; i < houses.length; i++) {
            int lastHeaterIndex = findLastHeaterIndex(heaters, houses[i]);
            len = Math.max(len, Math.abs(heaters[lastHeaterIndex] - houses[i]));
        }
        return len;
    }


    public static int findLastHeaterIndex(int[] hearters, int hourse) {

        int end = hearters.length - 1;
        int i = 0, j = end;
        int index = 0;
        while (i <= j) {
            index = (i + j) >> 1;
            if (hourse == hearters[index]) {
                return index;
            } else if (hourse > hearters[index]) {
                i = index + 1;
            } else {
                j = index - 1;
            }
        }
        i = i > end ? end : i;
        j = j < 0 ? 0 : j;
        return Math.abs(hearters[i] - hourse) > Math.abs(hearters[j] - hourse) ? j : i;
    }

    /**
     * 网友给的解法，没看懂，TODO 先挖个坑，以后填
     */
    public static int findRadius_2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int index = 0;
        int l = 0 - heaters[0];
        int r = heaters[0];
        int min = 0;
        for (int i = 0; i < houses.length; i++) {
            while (houses[i] > r && index < heaters.length) {
                index++;
                if (index < heaters.length) {
                    l = r;
                    r = heaters[index];
                } else {
                    l = heaters[index - 1];
                    r = Integer.MAX_VALUE;
                }
            }
            if (houses[i] > l && houses[i] < r) {
                int min1 = Math.min(houses[i] - l, r - houses[i]);
                min = Math.max(min, min1);
            }

        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(findRadius_2(new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923}, new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612}));

    }

}
