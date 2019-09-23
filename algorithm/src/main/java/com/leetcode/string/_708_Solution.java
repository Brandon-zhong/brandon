package com.leetcode.string;

/**
 * @author brandon
 * Created on 2019-08-12.
 * desc: 转换成小写字母
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
 * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 * 示例 1：
 * <p>
 * 输入: "Hello"
 * 输出: "hello"
 * 示例 2：
 * <p>
 * 输入: "here"
 * 输出: "here"
 * 示例 3：
 * <p>
 * 输入: "LOVELY"
 * 输出: "lovely"
 * <p>
 **/
public class _708_Solution {

    public static String toLowerCase(String str) {
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c += 32;
            }
            ch[i] = c;
        }
        return new String(ch);
    }

    public static void main(String[] args) {

        String str = "HelkajfKDJFDl";
        System.out.println("toLowerCase --> " + toLowerCase(str));

    }
}
