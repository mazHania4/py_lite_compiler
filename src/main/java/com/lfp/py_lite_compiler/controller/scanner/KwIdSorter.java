package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.token_types.KeywordFCTY;
import com.lfp.py_lite_compiler.model.token_types.OtherTypesFCTY;
import com.lfp.py_lite_compiler.model.token_types.TokenType;
import java.util.HashMap;

public class KwIdSorter {

    private HashMap<String, TokenType> keywords;

    public KwIdSorter() {
        keywords = new HashMap<>();
        for (KeywordFCTY kw : KeywordFCTY.values()) {
            keywords.put(kw.getTokenType().getPattern(), kw.getTokenType());
        }
    }

    public TokenType sortGetTokenType(String lexeme) {
        TokenType tokenType = keywords.get(lexeme);
        if (tokenType == null) tokenType = OtherTypesFCTY.IDENTIFIER.getTokenType();
        return tokenType;
    }


}
