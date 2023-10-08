package com.lfp.py_lite_compiler.model.tokens;

import com.lfp.py_lite_compiler.model.Stackable;
import com.lfp.py_lite_compiler.model.StackableType;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;
import lombok.*;

@Getter @Setter @AllArgsConstructor @ToString
public class Token implements Stackable {
    private TokenType tokenType;
    private int startPosition;
    private int line;
    private int column;
    private int index;
    private String lexeme;

    @Override
    public StackableType getType() {
        return StackableType.TOKEN;
    }
}
