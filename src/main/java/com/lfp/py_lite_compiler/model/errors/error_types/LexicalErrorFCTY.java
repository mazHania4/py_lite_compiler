package com.lfp.py_lite_compiler.model.errors.error_types;

public enum LexicalErrorFCTY {

    INVALID_CHARACTER(new ErrorType("INVALID_CHARACTER", "can not identify character")),
    INCONSISTENT_DEDENT(new ErrorType("INCONSISTENT_DEDENT", "un-indent does not match any outer indentation level"));

    private final ErrorType errorType;

    LexicalErrorFCTY(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
