package com.lfp.py_lite_compiler.model.errors;

import com.lfp.py_lite_compiler.model.errors.error_types.ErrorType;
import com.lfp.py_lite_compiler.model.tokens.Token;
import lombok.*;

@Getter @Setter @AllArgsConstructor @ToString @Builder
public class Error {
    private ErrorType errorType;
    private int startLine;
    private int startColumn;
    private Token token;
    private String productionName;

    public String getMessage(){
        return ("> " + errorType.getName() + ": " + errorType.getDescription()
            + (((token == null) || (productionName.isEmpty()) )
                ? "\n   on line: " + startLine + ", column: " + startColumn
                : ("\n   around token #" + token.getIndex() + " : {" + token.getTokenType().getName() + ": '" + token.getLexeme() + "'} "
                    + "\n   on line: " + token.getLine() + ", column: " + token.getColumn())
                    + " while trying to produce: " + productionName )
        );
    }
}
