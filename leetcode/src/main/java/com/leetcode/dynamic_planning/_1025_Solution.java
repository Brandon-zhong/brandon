package com.leetcode.dynamic_planning;

/**
 * @author brandon
 * create on 2019-09-02
 * desc: 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * <p>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * <p>
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * <p>
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 1000
 * <p>
 */
public class _1025_Solution {


    /**
     * 简单思路：因为爱丽丝先手，如果N为偶数，那爱丽丝如果一直出1的话，那爱丽丝稳赢，反之则是鲍勃赢
     * 例子：2，爱丽丝1，鲍勃无法操作，爱丽丝赢
     * 3，爱丽丝出1，鲍勃出1，爱丽丝无法操作，鲍勃赢
     * 所以只要判断N的奇偶数就行
     */
    public boolean divisorGame_1(int N) {
        return N % 2 == 0;
    }


    /**
     * 经典动态规划
     */
    public boolean devisorGame_2(int N) {


        return false;
    }


    public static void main(String[] args) {

    }

}
