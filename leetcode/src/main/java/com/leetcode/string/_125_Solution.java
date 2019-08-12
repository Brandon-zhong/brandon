package com.leetcode.string;

/**
 * @author brandon
 * Created on 2019-08-12.
 * desc: 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 **/
public class _125_Solution {

    /**
     * 算法一：将字符串转成小写，然后前后两个指针移动，如果指针的字符不是字母和数字，则相应的++或--然后continue
     * 然后比较两字符是否相等，不相等直接返回false，最后都相等返回true
     * 注意: 如果不想将字符串转成小写或大写的话，要注意0P问题，0和大写P之间也是相差32
     */
    public boolean isPalindrome(String s) {

        if (s == null) {
            return false;
        }
        if (s.trim().length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int start = 0, end = s.length() - 1;
        for (; start < end; ) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar < '0' || (startChar > '9' && startChar < 'a') || startChar > 'z') {
                ++start;
                continue;
            }
            if (endChar < '0' || (endChar > '9' && endChar < 'a') || endChar > 'z') {
                --end;
                continue;
            }
            if (startChar != endChar) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean palindrome = new _125_Solution().isPalindrome("0P");
        System.out.println("_125_Solution.main --> " + palindrome);
    }
}
