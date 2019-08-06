package com.leetcode.array;

import java.util.Arrays;

/**
 * @author brandon
 * Created on 2019-08-04.
 * desc: 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class _48_Solution {

    /**
     * 思路是一个坐标的90度旋转符合一个规律，[x,y]的上一个节点为[len-y-1,x]
     * 同时一个正方形可以分割为4个矩形，矩形的长＋宽为正方形的边长，
     * 只要将一个矩形的的所有坐标全部向上推导3次，可以计算出所有节点的位置，然后替换就行了
     */
    public void rotate(int[][] matrix) {

        //先确定移动矩阵的大小
        int w = matrix.length >> 1;
        int h = matrix.length - w;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                pre(i, j, matrix);
            }
        }
    }

    public void pre(int x, int y, int[][] matrix) {
        int len = matrix.length;
        int temp = matrix[x][y];
        for (int i = 0; i < 3; ++i) {
            //计算a，b的上一个坐标
            int u = len - y - 1;
            int o = x;
            //将上一个坐标的值复制到 a,b中
            matrix[x][y] = matrix[u][o];
            x = u;
            y = o;
        }
        //将temp的值复制到a，b中
        matrix[x][y] = temp;
    }

    public void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {5, 1, 9, 11},
                        {2, 4, 8, 10},
                        {13, 3, 6, 7},
                        {15, 14, 12, 16}
                };

        _48_Solution solution = new _48_Solution();
        solution.print(matrix);
        solution.rotate(matrix);
        System.out.println("===========");
        solution.print(matrix);

    }

}
