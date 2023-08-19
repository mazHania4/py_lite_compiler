package com.lfp.py_lite_compiler.model.token_types;

public enum KeywordFCTY {

    KW_AND(new TokenType("KW_AND", "and")),
    KW_AS(new TokenType("KW_AS", "as")),
    KW_ASSERT(new TokenType("KW_ASSERT", "assert")),
    KW_ASYNC(new TokenType("KW_ASYNC", "async")),
    KW_AWAIT(new TokenType("KW_AWAIT", "await")),
    KW_BREAK(new TokenType("KW_BREAK", "break")),
    KW_CLASS(new TokenType("KW_CLASS", "class")),
    KW_CONTINUE(new TokenType("KW_CONTINUE", "continue")),
    KW_DEF(new TokenType("KW_DEF", "def")),
    KW_DEL(new TokenType("KW_DEL", "del")),
    KW_ELIF(new TokenType("KW_ELIF", "elif")),
    KW_ELSE(new TokenType("KW_ELSE", "else")),
    KW_EXCEPT(new TokenType("KW_EXCEPT", "except")),
    KW_FALSE(new TokenType("KW_FALSE", "False")),
    KW_FINALLY(new TokenType("KW_FINALLY", "finally")),
    KW_FOR(new TokenType("KW_FOR", "for")),
    KW_FROM(new TokenType("KW_FROM", "from")),
    KW_GLOBAL(new TokenType("KW_GLOBAL", "global")),
    KW_IF(new TokenType("KW_IF", "if")),
    KW_IMPORT(new TokenType("KW_IMPORT", "import")),
    KW_IN(new TokenType("KW_IN", "in")),
    KW_IS(new TokenType("KW_IS", "is")),
    KW_LAMBDA(new TokenType("KW_LAMBDA", "lambda")),
    KW_NONE(new TokenType("KW_NONE", "None")),
    KW_NONLOCAL(new TokenType("KW_NONLOCAL", "nonlocal")),
    KW_NOT(new TokenType("KW_NOT", "not")),
    KW_OR(new TokenType("KW_OR", "or")),
    KW_PASS(new TokenType("KW_PASS", "pass")),
    KW_RAISE(new TokenType("KW_RAISE", "raise")),
    KW_RETURN(new TokenType("KW_RETURN", "return")),
    KW_TRUE(new TokenType("KW_TRUE", "True")),
    KW_TRY(new TokenType("KW_TRY", "try")),
    KW_WHILE(new TokenType("KW_WHILE", "while")),
    KW_WITH(new TokenType("KW_WITH", "with")),
    KW_YIELD(new TokenType("KW_YIELD", "yield"));

    private final TokenType tokenType;

    KeywordFCTY(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }



}
