package com.demo.greedy;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2020-02-27.
 * desc: 贪心算法分糖果
 * 只有当天国的大小大于等于孩子对糖果的期望大小是，孩子才能得到满足
 * 孩子数量大于糖果数量，在糖果数目一定的情况下，如果尽可能多的满足更多的孩子
 **/
public class Candy {

    /**
     * @param candys 糖果数组，下标表示糖果编号，值表示糖果大小
     * @param childs 孩子数组，下标表示孩子编号，值表示孩子对糖果的期望大小
     */
    public void distributeCandy(int[] candys, int[] childs){
        // 最孩子数组正序排序
        Arrays.sort(childs);
        Arrays.sort(candys);
        for (;;) {

        }


    }

    public static void main(String[] args) {

    }

}
