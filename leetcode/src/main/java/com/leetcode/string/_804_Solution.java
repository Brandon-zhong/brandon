package com.leetcode.string;


import java.util.HashSet;
import java.util.Set;

/**
 * @author brandon
 * Created on 2019-08-12.
 * desc: 唯一摩尔斯密码词
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
 * <p>
 * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
 * <p>
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
 * <p>
 * 返回我们可以获得所有词不同单词翻译的数量。
 * <p>
 * 例如:
 * 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * <p>
 * 共有 2 种不同翻译, "--...-." 和 "--...--.".
 *  
 * <p>
 * 注意:
 * <p>
 * 单词列表words 的长度不会超过 100。
 * 每个单词 words[i]的长度范围为 [1, 12]。
 * 每个单词 words[i]只包含小写字母。
 **/
public class _804_Solution {

    private static final String[] str = new String[]{
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-",
            "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> uniqueMorseCode = new HashSet<>();
        for (String word : words) {
            uniqueMorseCode.add(toMorseCode(word));
        }
        return uniqueMorseCode.size();
    }

    public String toMorseCode(String word) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            int index = word.charAt(i) - 'a';
            sb.append(str[index]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int result = new _804_Solution().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});
        System.out.println("result --> " + result);
    }

}
