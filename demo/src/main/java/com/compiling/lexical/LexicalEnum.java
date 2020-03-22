package com.compiling.lexical;


/**
 * @author brandon
 * Created on 2020-01-04.
 * desc:
 **/
public interface LexicalEnum {

    enum NodeState {
        Init, Id, Number, Operator, Keyword
    }

    enum Operators {
        GT('>'), EQ('='), LE('<');
        public final char symbol;

        Operators(char symbol) {
            this.symbol = symbol;
        }

        public static boolean isOperator(char ch) {
            for (LexicalEnum.Operators operator : LexicalEnum.Operators.values()) {
                if (operator.symbol == ch) {
                    return true;
                }
            }
            return false;
        }
    }

    enum Keyword {
        Int("int"), Double("double"), Char("char");
        public final String text;

        Keyword(String text) {
            this.text = text;
        }

        public static boolean isKeyword(String text) {
            for (Keyword keyword : Keyword.values()) {
                if (keyword.text.equals(text)) {
                    return true;
                }
            }
            return false;
        }
    }


}
