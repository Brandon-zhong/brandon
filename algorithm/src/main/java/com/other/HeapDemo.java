package com.other;

import java.util.Arrays;

/**
 * @author brandon
 * create on 2019-11-29
 * desc:
 */
public class HeapDemo {

    private int[] a;    //从下标为1开始存储树数据
    private int n;      //堆的容量
    private int count;  //已经存的数据

    public HeapDemo(int capacity) {
        this.a = new int[capacity + 1];
        this.n = capacity;
        this.count = 9;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void insert(int data) {
        if (count >= n) {
            return;
        }
        //将数据放到最后位置
        a[++count] = data;
        //开始堆化
        int i = count;
        int parent;
        while ((parent = i >> 1) > 0 && a[i] > a[parent]) {
            //交换parent和count
            swap(a, parent, i);
            i = parent;
        }
    }

    public void remove() {

    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }

    public static void main(String[] args) {
        HeapDemo heapDemo = new HeapDemo(10);
        for (int i = 0; i < 10; ++i) {
            heapDemo.insert((int) (Math.random() * 99 + 1));
            System.out.println(heapDemo.toString());
        }
        System.out.println(heapDemo.toString());

    }


}
