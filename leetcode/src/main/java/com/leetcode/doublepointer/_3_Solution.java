package com.leetcode.doublepointer;

/**
 * @author brandon
 * create on 2019-06-14
 * desc: 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 */
public class _3_Solution {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int len = 0;
        int i = 0, j = 0;
        while (i < chars.length) {
            if (j == chars.length - 1) {
                i++;
            }
            if (chars[i] == chars[j]) {
                int temp = j - i;
                if (temp > len) {
                    len = temp;
                }
                i = j;
            }
            j++;
        }
        return len;
    }

    public static void main(String[] args) {
        String str = "abcaddbcbb";
        int len = lengthOfLongestSubstring(str);
        System.out.println(len);

    }

}
