package com.lfp.py_lite_compiler.model;

import com.lfp.py_lite_compiler.model.token_types.TokenType;
import lombok.*;

@Getter @Setter @AllArgsConstructor @ToString
public class Token{
    private TokenType tokenType;
    private int line;
    private int column;
    private String lexeme;

}
