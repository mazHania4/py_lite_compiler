package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;
import java.util.HashMap;

public class KwIdSorter {

    private final HashMap<String, TokenType> keywords;

    public KwIdSorter() {
        keywords = new HashMap<>();
        var kwTypes = TokenTypesFCTY.values();
        for (int i = 10; i < 45; i++) {
            keywords.put(kwTypes[i].get().getPattern(), kwTypes[i].get());
        }
    }

    public TokenType sortGetTokenType(String lexeme) {
        TokenType tokenType = keywords.get(lexeme);
        if (tokenType == null) tokenType = TokenTypesFCTY.IDENTIFIER.get();
        return tokenType;
    }


}
