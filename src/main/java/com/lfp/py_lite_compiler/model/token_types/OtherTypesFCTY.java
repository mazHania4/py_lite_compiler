package com.lfp.py_lite_compiler.model.token_types;

public enum OtherTypesFCTY {

    LIT_STRING(new TokenType("LIT_STRING", "'[[0-39)U(39-255]]*' | \"[[0-34)U(34-255]]*\"")),
    LIT_INTEGER(new TokenType("LIT_INTEGER", "(0-9)+")),
    LIT_FLOAT(new TokenType("LIT_FLOAT", "(0-9)*.(0-9)*")),
    LIT_FLOAT_EXP(new TokenType("LIT_FLOAT_EXP", "(0-9)*.(0-9)*(E|e)(+|-)(0-9)*")),
    LIT_IMAGINARY(new TokenType("LIT_IMAGINARY", "(0-9)*.(0-9)*[(E|e)(+|-)(0-9)*(J|j)]|(J|j)")),
    IDENTIFIER(new TokenType("IDENTIFIER", "((a-Z)|_)((a-Z)|_|(0-9))*")),
    KW_OR_ID(new TokenType("KW_OR_ID", "")),
    NEWLINE(new TokenType("NEWLINE", "LF | CR")),
    INDENT(new TokenType("INDENT", " ")),
    DEDENT(new TokenType("DEDENT", " "));

    private final TokenType tokenType;

    OtherTypesFCTY(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }


}
