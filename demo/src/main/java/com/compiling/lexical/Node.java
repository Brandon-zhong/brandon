package com.compiling.lexical;

/**
 * @author brandon
 * Created on 2020-01-04.
 * desc: 状态节点
 **/
public class Node {

    // 节点当前状态
    private LexicalEnum.NodeState state;
    //节点的字符串
    private StringBuilder text;

    public Node() {
        text = new StringBuilder();
        state = LexicalEnum.NodeState.Init;
    }

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public void appendText(char ch) {
        text.append(ch);
    }

    public LexicalEnum.NodeState getState() {
        return state;
    }

    public void setState(LexicalEnum.NodeState state) {
        this.state = state;
    }
}
