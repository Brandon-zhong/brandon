package com.other;

/**
 * @author brandon
 * Created on 2019-09-22.
 * desc: 编辑距离问题
 * 给定两个字符串s1和s2，计算出将s1转换成s2所使用的最少操作数。
 * 可以对一个字符串如下三种操作：
 * 1、插入一个字符串
 * 2、删除一个字符串
 * 3、替换一个字符串
 * 示例1：
 * 输入：s1 = "horse", s2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse（将'h'替换为'r'）
 * rorse -> rose（删除'r'）
 * rose -> ros（删除'e'）
 **/
public class EditorDistance {

    //TODO 有问题，并没有求出最短编辑路径，待完成
    public static int editor_distince(String str1, String str2) {

        StringBuilder s1 = new StringBuilder(str1);
        StringBuilder s2 = new StringBuilder(str2);

        int s1_index = s1.length() - 1;
        int s2_index = s2.length() - 1;
        int count = 0;

        while (s1_index >= 0 && s2_index >= 0) {
            char ch1 = s1.charAt(s1_index), ch2 = s2.charAt(s2_index);
            //如果相同的话则同时跳过
            if (ch1 == ch2) {
                --s1_index;
                --s2_index;
                continue;
            }

            //如果当前指向的前一个相同(如果有的话)，后面没有字符，则删除当前
            if (s1_index > 0 && ch2 == s1.charAt(s1_index - 1)) {
                --s1_index;
                s1.deleteCharAt(s1_index + 1);
                System.out.println(s1 + "  del -->" + ch1);
                //如果当前指向的前一个相同，后一个相同，则替换当前
            } else if (s1_index > 0 && s1_index < (s1.length() - 1)
                    && s2_index > 0 && s2_index != (s2.length() - 1)
                    && s1.charAt(s1_index - 1) == s2.charAt(s2_index - 1)
                    && s1.charAt(s1_index + 1) == s2.charAt(s2_index + 1)) {
                s1.setCharAt(s1_index, ch2);
                System.out.println(s1 + "  replace -->  " + ch1 + "-" + ch2);
                //如果当前指向的前一个不相同(如果有的话)，则插入
            } else if (s1_index > 0 && ch2 != s1.charAt(s1_index - 1)) {
                --s2_index;
                s1.insert(s1_index + 1, ch2);
                System.out.println(s1 + "  insert --> " + ch2);
            } else {
                s1.setCharAt(s1_index--, ch2);
                System.out.println(s1 + "  replace --> " + ch2);
            }
            ++count;
        }
        if (s2_index == -1) {
            System.out.println(s1 + " del --> " + s1.substring(0, s1_index + 1));
            s1.delete(0, s1_index + 1);
            ++count;
        }
        System.out.println(String.format("s1 --> %s, s2 --> %s", s1.toString(), s2.toString()));
        return count;
    }


    public static void main(String[] args) {
        String s1 = "intention", s2 = "execution";
        int count = editor_distince(s1, s2);
        System.out.println("EditorDistance.main --> " + count);
    }

}