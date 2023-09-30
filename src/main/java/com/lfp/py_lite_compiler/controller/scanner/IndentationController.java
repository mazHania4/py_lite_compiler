package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;

import java.util.*;

public class IndentationController {

    private final Stack<Integer> indentationStack;

    public Optional<Token[]> getIndentationTokens(String spaces) {
        int numSpaces = spaces.length();
        List<Token> tokens = new ArrayList<>();
        if (numSpaces == indentationStack.peek())
            return Optional.empty();
        if (indentationStack.peek() < numSpaces) {
            indentationStack.push(numSpaces);
            tokens.add(new Token(TokenTypesFCTY.INDENT.get(), 0, 0, 0, spaces));
        }
        if (indentationStack.contains(numSpaces)) {
            while (indentationStack.peek() > numSpaces) {
                indentationStack.pop();
                tokens.add(new Token(TokenTypesFCTY.DEDENT.get(), 0, 0, 0, spaces));
            }
        }
        Token[] tokensArray = new Token[tokens.size()];
        return Optional.of(tokens.toArray(tokensArray));
    }

    public boolean isInconsistentDedent(String spaces) {
        return (indentationStack.peek() > spaces.length()) && !(indentationStack.contains(spaces.length()));
    }

    public IndentationController() {
        indentationStack = new Stack<>();
        indentationStack.push(0);
    }

}
