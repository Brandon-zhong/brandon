package com.demo.sort;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-06-13
 * desc: 归并排序实现,递归主要改变的是数组的大小，也就是改变开始（start）和结束（end）的下标
 */
public class MergeSort {

    public static void mergeSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        //取中间值
        int q = (start + end) / 2;
        mergeSort(a, start, q);
        mergeSort(a, q + 1, end);
        merge(a, start, q, q + 1, end);
    }

    public static void merge(int[] a, int pStart, int pEnd, int qStart, int qEnd) {

        int pIndex = pStart, qIndex = qStart;
        int index = 0;
        int[] temp = new int[qEnd - pStart + 1];
        while (pIndex <= pEnd && qIndex <= qEnd) {
            if (a[pIndex] <= a[qIndex]) {
                temp[index] = a[pIndex];
                pIndex++;
            } else {
                temp[index] = a[qIndex];
                qIndex++;
            }
            index++;
        }

        //判断哪个数组还有数据剩余
        int remainIndex = pIndex;
        int remainEnd = pEnd;
        if (pIndex > pEnd) {
            remainIndex = qIndex;
            remainEnd = qEnd;
        }
        //将剩下的数据拷贝到r数组中
        while (remainIndex <= remainEnd) {
            temp[index++] = a[remainIndex++];
        }
        //将temp的数据拷贝会 a 数组中
        for (int i = 0; i < temp.length; i++) {
            a[pStart + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 38, 1, 5, 2, 4, 6, 8, 6, 3};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }

}
