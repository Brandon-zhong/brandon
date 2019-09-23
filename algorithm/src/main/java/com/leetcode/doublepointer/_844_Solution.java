package com.leetcode.doublepointer;

import java.util.Queue;
import java.util.Stack;

/**
 * @author brandon
 * Created on 2019-06-15.
 * desc: 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 */
public class _844_Solution {

    class Node {
        char ch;
        Node pre;

        protected Node(char ch, Node node) {
            this.ch = ch;
            this.pre = node;
        }
    }

    /**
     * 算法一：用向前单链表作为栈来使用
     * 本题目就是考差栈的使用，其他解大同小异，所以不做其他算法解
     */
    public boolean backspaceCompare_1(String S, String T) {

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        Node sNode = toNode(s);
        Node tNode = toNode(t);
        if (sNode == null && tNode == null) {
            return true;
        } else if (sNode != null && tNode != null) {
            while (sNode != null && tNode != null) {
                if (sNode.ch != tNode.ch) {
                    return false;
                }
                sNode = sNode.pre;
                tNode = tNode.pre;
            }
            return sNode == null && tNode == null;
        }
        return false;
    }

    public Node toNode(char[] s) {
        char sp = '#';
        int i = 0;
        Node index = null;
        while (i < s.length) {
            if (s[i] == sp) {
                if (index == null) {
                    i++;
                    continue;
                }
                Node temp = index.pre;
                index.pre = null;
                index = temp;
            } else {
                index = new Node(s[i], index);
            }
            i++;
        }
        return index;
    }

    public static void main(String[] args) {
        String s = "bxj##tw", t = "bxj###tw";
        System.out.println(new _844_Solution().backspaceCompare_1(s, t));


    }
}
