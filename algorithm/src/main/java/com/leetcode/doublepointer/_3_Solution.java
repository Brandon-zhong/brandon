package com.leetcode.doublepointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /**
     * 算法一：滑动窗口法，通过两个指针来扫描元素段，并有一个set来存储扫描过的元素，
     * 每次j指针扫过的元素都比较set里的元素，如果存在，则i--并继续比较，直到set里没有和j元素相同的元素，
     * 此时i到j之间的元素都是不重复的，每次添加set元素是比较i到j的距离和len，去大的那个
     */
    public static int lengthOfLongestSubstring_1(String s) {
        int len = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                len = Math.max(len, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return len;
    }

    /**
     * 算法二：滑动窗口优化，算法一中排除重复元素时是从左往有一个一个移动，
     * 其实如果可以建立元素和位置的关系，可以直接定位到重复元素的位置就可以直接跳过重复元素的位置
     */
    public static int lengthOfLongestSubstring_2(String s) {

        int len = 0, i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < s.length()) {
            if (map.containsKey(s.charAt(j)) && map.get(s.charAt(j)) >= i) {
                i = Math.max(map.get(s.charAt(j)), i) + 1;
            }
            len = Math.max(j - i + 1, len);
            map.put(s.charAt(j), j++);
        }
        return len;
    }

    public static void main(String[] args) {
        String str = "abcbdabceab";
        int len = lengthOfLongestSubstring_2(str);
        System.out.println(len);
    }

}
