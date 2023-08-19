package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.Token;
import com.lfp.py_lite_compiler.model.token_types.OtherTypesFCTY;
import com.lfp.py_lite_compiler.model.token_types.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class IndentationController {

    private Stack<Integer> indentationStack;

    public Optional<Token[]> getIndentationTokens(String spaces) {
        int numSpaces = spaces.length();
        List<Token> tokens = new ArrayList<>();
        if (numSpaces == indentationStack.peek())
            return Optional.empty();
        if (indentationStack.peek() < numSpaces) {
            indentationStack.push(numSpaces);
            tokens.add(new Token(OtherTypesFCTY.INDENT.getTokenType(), 0, 0, spaces));
        }
        if (indentationStack.contains(numSpaces)) {
            while (indentationStack.peek() > numSpaces) {
                indentationStack.pop();
                tokens.add(new Token(OtherTypesFCTY.DEDENT.getTokenType(), 0, 0, spaces));
            }
        }
        Token[] tokensArray = new Token[tokens.size()];
        return Optional.of(tokens.toArray(tokensArray));
    }

    public boolean isInconsistentDedent(String spaces){
        return (indentationStack.peek() > spaces.length()) && !(indentationStack.contains(spaces.length()));
    }

    public IndentationController() {
        indentationStack = new Stack<>();
        indentationStack.push(0);
    }

}
