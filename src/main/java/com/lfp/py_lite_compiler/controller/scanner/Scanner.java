package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.controller.scanner.states.*;
import com.lfp.py_lite_compiler.model.errors.Error;
import com.lfp.py_lite_compiler.model.errors.error_types.ErrorType;
import com.lfp.py_lite_compiler.model.errors.error_types.LexicalErrorFCTY;
import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final char[] charStream;
    private int currentLine, currentColumn, currentPosition, startPosition;
    private char currentChar;
    private State currentState;
    private final StringBuilder lexeme;
    private final List<Token> tokens;
    private final List<Error> errors;
    private final TransitionTable transitionTable;
    private final KwIdSorter kwIdSorter;
    private final IndentationController indentationCtrl;

    public List<Token> analyze() {
        while (isNotLastCharacter()) {
            findToken();
            addTokens();
            currentState = State.S0;
            startPosition = currentPosition;
            lexeme.delete(0, lexeme.length());
        }
        emptyIndentionPile();
        return tokens;
    }

    private void emptyIndentionPile() {
        var dedentTokens = indentationCtrl.getIndentationTokens("");
        if (dedentTokens.isPresent()) {
            for (Token dedent : dedentTokens.get()) {
                dedent.setLine(currentLine);
                dedent.setColumn(dedent.getColumn() + currentColumn);
                dedent.setIndex(tokens.size());
                tokens.add(dedent);
            }
        }
    }

    private void findToken() {
        if (nextIsErrorState()) {
            moveOnToNextCharAndState();
            return;
        }
        while (!nextIsErrorState()) {
            moveOnToNextCharAndState();
            if ((currentState == State.S0) || (currentState == State.S55)) {
                startPosition = currentPosition;
                lexeme.delete(0, lexeme.length());
            }
        }

    }

    private void addTokens() {
        if (currentIsErrorState()) {
            addError(LexicalErrorFCTY.INVALID_CHARACTER.getErrorType());
            return;
        }
        if (isNewline()) {
            addIndentation();
            return;
        }
        Token token = new Token(getTokenType(), startPosition, currentLine, currentColumn, tokens.size(), lexeme.toString());
        tokens.add(token);
    }


    private void addIndentation() {
        if (isNotLastCharacter()) {
            String spaces = lexeme.substring(1);
            if ((!tokens.isEmpty()) && (tokens.get(tokens.size() - 1).getTokenType() != TokenTypesFCTY.NEWLINE.get())) {
                Token token = new Token(getTokenType(), startPosition, currentLine, currentColumn, tokens.size(), lexeme.substring(0, 1));
                tokens.add(token);
            }
            if (!nextIsEndOfPhysicalLineOrComment()) {
                if (indentationCtrl.isInconsistentDedent(spaces)) {
                    addError(LexicalErrorFCTY.INCONSISTENT_DEDENT.getErrorType());
                    return;
                }
                var indTokens = indentationCtrl.getIndentationTokens(spaces);
                if (indTokens.isPresent()) {
                    for (Token indToken : indTokens.get()) {
                        indToken.setLine(currentLine);
                        indToken.setColumn(indToken.getColumn() + currentColumn);
                        indToken.setIndex(tokens.size());
                        tokens.add(indToken);
                    }
                }
            }
        }
    }

    private void addError(ErrorType errorType) {
        errors.add(Error.builder().errorType(errorType).startLine(currentLine).startColumn(currentColumn).build());
    }

    public List<Error> getErrors() {
        return errors;
    }

    private boolean isNewline() {
        return getTokenType() == TokenTypesFCTY.NEWLINE.get();
    }

    private TokenType getTokenType() {
        if (currentState == State.S57) return kwIdSorter.sortGetTokenType(lexeme.toString());
        // if currentState doesn't have a tokenType, then it means it's not an acceptance state, so it generates an error
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
        return isNotLastCharacter() ? ((currentState == State.S63) || currentState.getTokenType().isEmpty())
                                    : currentState != State.S63 && currentState.getTokenType().isEmpty();
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
        if (currentPosition + 1 == charStream.length) {
            return false;
        }
        return (charStream[currentPosition] == 10) || (charStream[currentPosition] == 13);
    }

    private boolean nextIsEndOfPhysicalLineOrComment() {
        if (currentPosition + 1 == charStream.length) {
            return false;
        }
        return (charStream[currentPosition + 1] == 10) || (charStream[currentPosition + 1] == 13) || (charStream[currentPosition + 1] == 35);
    }

    private boolean isNotLastCharacter(){
        return currentPosition + 1 < charStream.length;
    }

    public Scanner(char[] srcContent) {
        this.charStream = srcContent;
        currentLine = 0;
        currentColumn = 0;
        startPosition = 0;
        currentPosition = -1;
        currentChar = 0;
        currentState = State.S0;
        lexeme = new StringBuilder();
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        transitionTable = new TransitionTable();
        kwIdSorter = new KwIdSorter();
        indentationCtrl = new IndentationController();
    }
}
