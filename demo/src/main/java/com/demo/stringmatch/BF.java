package com.demo.stringmatch;

/**
 * @author brandon
 * Created on 2020-02-25.
 * desc: 字符串匹配  BF算法
 **/
public class BF {

    public static boolean match(String s, String p) {
        if (s.length() < p.length()) {
            return false;
        }
        char f = p.charAt(0);
        for (int i = 0; i <= s.length() - p.length(); ++i) {
            if (s.charAt(i) != f) {
                continue;
            }
            //如果等于，则开始匹配剩下的元素
            boolean match = false;
            for (int j = 0; j < p.length(); ++j) {
                if (s.charAt(j + i) != p.charAt(j)) {
                    match = false;
                    break;
                }
                match = true;
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(BF.match("aab", "ab"));
    }

}
