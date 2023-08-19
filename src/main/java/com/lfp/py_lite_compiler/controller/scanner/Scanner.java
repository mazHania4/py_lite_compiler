package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.Token;
import com.lfp.py_lite_compiler.model.token_types.OtherTypesFCTY;
import com.lfp.py_lite_compiler.model.token_types.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private char[] charStream;
    private int currentLine, currentColumn, currentPosition;
    private char currentChar;
    private State currentState;
    private final StringBuilder lexeme;
    private final List<Token> tokens;
    private final TransitionTable transitionTable;
    private final KwIdSorter kwIdSorter;
    private IndentationController indentationCtrl;

    public List<Token> analyze() {
        while (currentPosition + 1 < charStream.length) {
            findToken();
            addTokens();
            currentState = State.S0;
            lexeme.delete(0, lexeme.length());
        }
        cleanNewlines();
        return tokens;
    }

    private void findToken() {
        while (!nextIsErrorState()) {
            moveOnToNextCharAndState();
            if ((currentState == State.S0) || (currentState == State.S55))
                lexeme.delete(0, lexeme.length());
        }

    }

    private void addTokens() {
        if (currentIsErrorState()) {
            manageError();
            return;
        }
        if (isNewline()) {
            manageNewline();
            return;
        }
        Token token = new Token(getTokenType(), currentLine, currentColumn, lexeme.toString());
        tokens.add(token);
    }


    private void manageNewline() {
        Token token = new Token(getTokenType(), currentLine, currentColumn, lexeme.substring(0, 1));
        tokens.add(token);
        String spaces = lexeme.substring(1);
        if (indentationCtrl.isInconsistentDedent(spaces)) {
            manageError();
            return;
        }
        var indTokens = indentationCtrl.getIndentationTokens(spaces);
        if (indTokens.isPresent()) {
            for (Token indToken : indTokens.get()) {
                indToken.setLine(currentLine);
                indToken.setColumn(indToken.getColumn() + currentColumn);
                tokens.add(indToken);
            }
        }
    }

    private void manageError() {
        if (currentState == State.S1) {
            System.out.println("Inconsistent Dedent Error");
        } else {
            System.out.println("Invalid character error");
        }
    }

    private void cleanNewlines() {



    }

    private boolean isNewline() {
        return getTokenType() == OtherTypesFCTY.NEWLINE.getTokenType();
    }

    private TokenType getTokenType() {
        if (currentState == State.S57) return kwIdSorter.sortGetTokenType(lexeme.toString());

        // if currentState doesn't have a tokenType, then it means it's not an acceptance state, so it generates an UNIDENTIFIED token
        return currentState.getTokenType().get();
    }

    private boolean nextIsErrorState() {
        if (currentPosition + 1 < charStream.length) {
            State nextState = transitionTable.getState(currentState, charStream[currentPosition + 1]);
            return nextState == State.S63;
        }
        return true;
    }

    private boolean currentIsErrorState() {
        return (currentState == State.S63) || currentState.getTokenType().isEmpty();
    }

    private void moveOnToNextCharAndState() {
        currentPosition++;
        if (isEndOfPhysicalLine()) {
            currentLine++;
            currentColumn = 0;
        } else {
            currentColumn++;
        }
        currentChar = charStream[currentPosition];
        lexeme.append(currentChar);
        currentState = transitionTable.getState(currentState, currentChar);
    }

    private boolean isEndOfPhysicalLine() {
        if (currentPosition + 1 < charStream.length) {
            return (charStream[currentPosition] == 10) || (charStream[currentPosition] == 13);
        }
        return true;
    }

    public Scanner(char[] srcContent) {
        this.charStream = srcContent;
        currentLine = 0;
        currentColumn = 0;
        currentPosition = -1;
        currentChar = 0;
        currentState = State.S0;
        lexeme = new StringBuilder();
        tokens = new ArrayList<>();
        transitionTable = new TransitionTable();
        kwIdSorter = new KwIdSorter();
        indentationCtrl = new IndentationController();
    }
}
