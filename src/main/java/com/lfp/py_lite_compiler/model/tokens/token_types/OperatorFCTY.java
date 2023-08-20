package com.lfp.py_lite_compiler.model.tokens.token_types;

public enum OperatorFCTY {

    OP_ADD(new TokenType("OP_ADD", "+")),
    OP_SUB(new TokenType("OP_SUB", "-")),
    OP_MULT(new TokenType("OP_MULT", "*")),
    OP_EXP(new TokenType("OP_EXP", "**")),
    OP_DIV(new TokenType("OP_DIV", "/")),
    OP_FLOOR_DIV(new TokenType("OP_FLOOR_DIV", "//")),
    OP_OR(new TokenType("OP_OR", "|")),
    OP_AND(new TokenType("OP_AND", "&")),
    OP_GREATER_THAN(new TokenType("OP_GREATER_THAN", ">")),
    OP_GREATER_EQUAL_THAN(new TokenType("OP_GREATER_EQUAL_THAN", "<=")),
    OP_RIGHT_SHIFT(new TokenType("OP_RIGHT_SHIFT", ">>")),
    OP_LESS_THAN(new TokenType("OP_LESS_THAN", "<")),
    OP_LESS_EQUAL_THAN(new TokenType("OP_LESS_EQUAL_THAN", "<=")),
    OP_LEFT_SHIFT(new TokenType("OP_LEFT_SHIFT", "<<")),
    OP_MOD(new TokenType("OP_MOD", "%")),
    OP_XOR(new TokenType("OP_XOR", "^")),
    OP_EQUALS(new TokenType("OP_EQUALS", "==")),
    OP_EXPRESSION_ASSIGNMENT(new TokenType("OP_EXPRESSION_ASSIGNMENT", ":=")),
    OP_NOT(new TokenType("OP_NOT", "~")),
    OP_NOT_EQUALS(new TokenType("OP_NOT_EQUALS", "!="));


    private final TokenType tokenType;

    OperatorFCTY(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
    }
