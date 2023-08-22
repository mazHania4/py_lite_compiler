package com.lfp.py_lite_compiler.view.coloring;

import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ColoringController {

    public StyleSpans<Collection<String>> getStyleSpans(List<Token> tokens, String srcCode) {
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastTokenEnd = 0;
        for (Token token : tokens) {
            int tokenStart = token.getStartPosition();
            int tokenEnd = tokenStart + token.getLexeme().length();
            if (tokenEnd > lastTokenEnd) {
                String styleClass = determineStyleClass(token.getTokenType());
                spansBuilder.add(Collections.emptyList(), tokenStart - lastTokenEnd);
                spansBuilder.add(Collections.singleton(styleClass), tokenEnd - tokenStart);
                lastTokenEnd = tokenEnd;
            }
        }
        spansBuilder.add(Collections.emptyList(), srcCode.length() - lastTokenEnd);
        return spansBuilder.create();
    }

    private static String determineStyleClass(TokenType tokenType) {
        String typeId = tokenType.getName();
        return switch (typeId.substring(0, 2)) {
            case "KW" -> "keyword";
            case "OP" -> "delimiter_operator";
            case "LI" -> "literal";
            case "ID" -> "identifier";
            case "DE" -> switch (typeId) {
                case "DEL_OPEN_PARENTHESIS", "DEL_CLOSE_PARENTHESIS",
                        "DEL_OPEN_BRACKET", "DEL_CLOSE_BRACKET",
                        "DEL_OPEN_BRACE", "DEL_CLOSE_BRACE",
                        "DEL_COMMA", "DEL_SEMICOLON", "DEL_COLON" -> "other";
                default -> "delimiter_operator";
            };
            default -> "comment";
        };
    }
}