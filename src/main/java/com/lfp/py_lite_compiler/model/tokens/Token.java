package com.lfp.py_lite_compiler.model.tokens;

import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;
import lombok.*;

@Getter @Setter @AllArgsConstructor @ToString
public class Token{
    private TokenType tokenType;
    private int startPosition;
    private int line;
    private int column;
    private String lexeme;

}
