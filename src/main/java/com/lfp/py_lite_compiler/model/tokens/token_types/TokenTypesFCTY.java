package com.lfp.py_lite_compiler.model.tokens.token_types;

public enum TokenTypesFCTY {

    LIT_STRING(new TokenType("LIT_STRING", "'[[0-39)U(39-255]]*' | \"[[0-34)U(34-255]]*\"")),
    LIT_INTEGER(new TokenType("LIT_INTEGER", "(0-9)+")),
    LIT_FLOAT(new TokenType("LIT_FLOAT", "(0-9)*.(0-9)*")),
    LIT_FLOAT_EXP(new TokenType("LIT_FLOAT_EXP", "(0-9)*.(0-9)*(E|e)(+|-)(0-9)*")),
    LIT_IMAGINARY(new TokenType("LIT_IMAGINARY", "(0-9)*.(0-9)*[(E|e)(+|-)(0-9)*(J|j)]|(J|j)")),
    IDENTIFIER(new TokenType("IDENTIFIER", "((a-Z)|_)((a-Z)|_|(0-9))*")),
    KW_OR_ID(new TokenType("KW_OR_ID", "")),
    NEWLINE(new TokenType("NEWLINE", "LF | CR")),
    INDENT(new TokenType("INDENT", " ")),
    DEDENT(new TokenType("DEDENT", " ")),



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
    KW_YIELD(new TokenType("KW_YIELD", "yield")),


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
    DEL_BACKSLASH(new TokenType("DEL_BACKSLASH", "\\")),


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

    TokenTypesFCTY(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType get() {
        return tokenType;
    }


}
