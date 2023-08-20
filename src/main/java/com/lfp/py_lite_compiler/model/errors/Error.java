package com.lfp.py_lite_compiler.model.errors;

import com.lfp.py_lite_compiler.model.errors.error_types.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class Error {
    private ErrorType errorType;
    private int startLine;
    private int startColumn;
}
