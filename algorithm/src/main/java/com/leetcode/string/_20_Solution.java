package com.leetcode.string;

import java.util.Stack;

/**
 * @author brandon
 * Created on 2019-12-17.
 * desc: 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class _20_Solution {

    /**
     * 比较简单，利用栈的特性，栈里只存左边括号的值，匹配的出栈，不匹配且为有括号则入栈，否则返回false
     * 判断是否匹配是利用ascii码中，左右括号匹配的话差值为1或者2，然后就是if...else
     */
    public boolean isValid(String s) {
        Stack<Byte> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            byte ch = (byte) s.charAt(i);
            if (stack.empty()) {
                if (ch == ')' || ch == ']' || ch == '}') {
                    return false;
                }
                stack.push(ch);
            } else {
                byte b = stack.peek();
                if (ch == '{' || ch == '(' || ch == '[') {
                    stack.push(ch);
                    continue;
                }
                int c = ch - b;
                if (c == 1 || c == 2) {
                    stack.pop();
                    continue;
                }
                return false;
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        boolean valid = new _20_Solution().isValid("(]");
        System.out.println("_20_Solution.main --> " + valid);
    }

}
