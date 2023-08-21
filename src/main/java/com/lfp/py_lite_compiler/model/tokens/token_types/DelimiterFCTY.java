package com.lfp.py_lite_compiler.model.tokens.token_types;

public enum DelimiterFCTY {

DEL_ADD_AND(new TokenType("DEL_ADD_AND", "+=")),
DEL_SUB_AND(new TokenType("DEL_SUB_AND", "-=")),
DEL_RETURN_ANNOTATION(new TokenType("DEL_RETURN_ANNOTATION", "->")),
DEL_MULT_AND(new TokenType("DEL_MULT_AND", "*=")),
DEL_EXP_AND(new TokenType("DEL_EXP_AND", "**=")),
DEL_DIV_AND(new TokenType("DEL_DIV_AND", "/=")),
DEL_FLOOR_DIV_AND(new TokenType("DEL_FLOOR_DIV_AND", "//=")),
DEL_OR_AND(new TokenType("DEL_OR_AND", "|=")),
DEL_AND_AND(new TokenType("DEL_AND_AND", "&=")),
DEL_RIGHT_SHIFT_AND(new TokenType("DEL_RIGHT_SHIFT_AND", ">>=")),
DEL_LEFT_SHIFT_AND(new TokenType("DEL_LEFT_SHIFT_AND", "<<=")),
DEL_MOD_AND(new TokenType("DEL_MOD_AND", "%")),
DEL_XOR_AND(new TokenType("DEL_XOR_AND", "^=")),
DEL_AT(new TokenType("DEL_AT", "@")),
DEL_AT_AND(new TokenType("DEL_AT_AND", "@=")),
DEL_ASSIGN(new TokenType("DEL_ASSIGN", "=")),
DEL_COLON(new TokenType("DEL_COLON", ":")),
DEL_OPEN_PARENTHESIS(new TokenType("DEL_OPEN_PARENTHESIS", "(")),
DEL_CLOSE_PARENTHESIS(new TokenType("DEL_CLOSE_PARENTHESIS", ")")),
DEL_OPEN_BRACKET(new TokenType("DEL_OPEN_BRACKET", "[")),
DEL_CLOSE_BRACKET(new TokenType("DEL_CLOSE_BRACKET", "]")),
DEL_OPEN_BRACE(new TokenType("DEL_OPEN_BRACE", "{")),
DEL_CLOSE_BRACE(new TokenType("DEL_CLOSE_BRACE", "}")),
DEL_COMMA(new TokenType("DEL_COMMA", ",")),
DEL_SEMICOLON(new TokenType("DEL_SEMICOLON", ";")),
DEL_DOT(new TokenType("DEL_DOT", ".")),
DEL_BACKSLASH(new TokenType("DEL_BACKSLASH", "\\"));

    private final TokenType tokenType;

    DelimiterFCTY(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
}
