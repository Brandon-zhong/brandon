package com.compiling.lexical;

import com.compiling.lexical.LexicalEnum.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brandon
 * Created on 2020-01-04.
 * desc: 简单词法分析器， 有限状态机的应用
 * 分析 age >= 45 这样的简单应用
 **/
public class SimpleLexicalAnalyzer {

    private List<Node> nodeList = new ArrayList<>();
    private static final Node first = new Node();

    public static void main(String[] args) {

        SimpleLexicalAnalyzer lexicalAnalyzer = new SimpleLexicalAnalyzer();
        String script = "   age = 234";
        lexicalAnalyzer.lexicalAnalysis(script);


        script = "int i = 123";
        lexicalAnalyzer.lexicalAnalysis(script);

        script = "inta i = 234";

        lexicalAnalyzer.lexicalAnalysis(script);

        script = "double i = 123";
        lexicalAnalyzer.lexicalAnalysis(script);

        script = "char ch = 12 int ";
        lexicalAnalyzer.lexicalAnalysis(script);

    }

    public void lexicalAnalysis(String text) {
        this.nodeList.clear();
        Node currentNode = initNode(text.charAt(0));
        //根据上一个状态来处理当前字符
        for (int i = 1; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (isBlank(ch)) {
                //关键字
                if (currentNode.getState().equals(NodeState.Id) &&
                        Keyword.isKeyword(currentNode.getText().toString())) {
                    currentNode.setState(NodeState.Keyword);
                }
                currentNode = initNode(ch);
                continue;
            }
            switch (currentNode.getState()) {
                case Init:
                    currentNode = initNode(ch);
                    break;
                case Id:
                    //当前ID状态，只要是字符串或者数据保持状态，其他的都退回到初始状态
                    if (isAlpha(ch) || isDigit(ch)) {
                        currentNode.appendText(ch);
                    } else {
                        currentNode = initNode(ch);
                    }
                    break;
                case Operator:
                    //上一个是操作符，只有当前也是操作符才保持状态，否则退回初始化状态
                    if (Operators.isOperator(ch)) {
                        currentNode.appendText(ch);
                    } else {
                        currentNode = initNode(ch);
                    }
                    break;
                case Number:
                    //上一个是数字，当前为数组才保持
                    if (isDigit(ch)) {
                        currentNode.appendText(ch);
                    } else {
                        currentNode = initNode(ch);
                    }
                    break;
            }
        }
        System.out.println("------------------\nscript --> " + text);
        nodeList.forEach(node ->
                System.out.println(node.getState().name() + "   " + node.getText().toString())
        );
    }

    public Node initNode(char ch) {
        if (isBlank(ch)) {
            return first;
        }
        Node newNode = new Node();
        newNode.setState(NodeState.Init);
        //先确定开始状态
        if (isAlpha(ch)) { //字符串处理
            //更改状态
            newNode.setState(NodeState.Id);
        } else if (Operators.isOperator(ch)) { //操作符处理
            newNode.setState(NodeState.Operator);
        } else if (isDigit(ch)) { //数字处理
            newNode.setState(NodeState.Number);
        } else {
            throw new IllegalArgumentException("not support the symbol : " + ch);
        }
        newNode.appendText(ch);
        nodeList.add(newNode);
        return newNode;
    }

    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isAlpha(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static boolean isBlank(int ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

}


