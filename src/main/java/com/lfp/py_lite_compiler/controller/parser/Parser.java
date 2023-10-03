package com.lfp.py_lite_compiler.controller.parser;

import com.lfp.py_lite_compiler.model.StackableType;
import com.lfp.py_lite_compiler.model.productions.Option;
import com.lfp.py_lite_compiler.model.productions.ProdFCTY;
import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.special_symbols.SpecialSymbol;
import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    private final List<Token> tokens;
    private int currentPosition;
    private int positionBeforeError;

    public String analyze(){
        var prod = tryMatchProduction(ProdFCTY.statements.get());
        if (prod == null) {
            return "Error in position: " + positionBeforeError;
        }
        return "Analysis completed, no errors \n Found:" + prod.getMatchedOption();
    }

    private Production tryMatchProduction(Production prod){
        boolean optionSucceeded = false;
        boolean elementWasMatched;
        Option matchedOption = Option.builder().elements(new ArrayList<>()).build();
        int positionSave = currentPosition;
        int counter = 0;
        for(Option option : prod.getOptions()) {
            var stack = initializeStack(option);
            System.out.println("\n" + counter + ". try option: " + option);
            do{
                elementWasMatched = false;
                System.out.print("\n---element evaluated:" + stack.peek() + " current position: " + currentPosition );
                switch ((getType(stack.peek()))){
                    case TOKEN -> {
                        var tokenType = getToken(stack.peek());
                        if(readEntry() == tokenType){
                            matchedOption.getElements().add(stack.pop());
                            elementWasMatched = true;
                            currentPosition++;
                        }
                    } case PRODUCTION -> {
                        var returnedProd = tryMatchProduction(getProduction(stack.pop()));
                        if (returnedProd != null){
                            matchedOption.getElements().add("   " + returnedProd.getMatchedOption());
                            elementWasMatched = true;
                        }
                    } case SPECIAL_SYMBOL -> {
                        var s_symbol = getSpecialSymbol(stack.pop());
                        switch (s_symbol){
                            case s_epsilon -> {
                                matchedOption.getElements().add(s_symbol.name());
                                elementWasMatched = true;
                            }
                            case s_next_is_t_lookahead, s_next_is_close_parenthesis -> elementWasMatched = nextProductionIs(s_symbol.getProduction());
                            case s_next_is_not_t_lookahead -> elementWasMatched = !nextProductionIs(s_symbol.getProduction());
                        }
                    }
                }
                System.out.print(" | succeeded? " + elementWasMatched);
            } while (!stack.isEmpty() && elementWasMatched);
            System.out.println(" | Out of WHILE: "+ elementWasMatched + " option: " + option);
            if(elementWasMatched) { //the option was completely matched so the production succeeded
                optionSucceeded = true;
                break;
            } else {
                positionBeforeError = currentPosition; // review
                currentPosition = positionSave;
            }
        }
        return optionSucceeded ? Production.builder().matchedOption(matchedOption).build() : null;
    }

    private boolean nextProductionIs(Production prod){
        boolean elementWasMatched;
        for(Option option : prod.getOptions()) {
            var stack = initializeStack(option);
            do{
                elementWasMatched = (readEntry() == getToken(stack.pop()));
            } while (!stack.isEmpty() && elementWasMatched);
            if(stack.isEmpty() && elementWasMatched) { //the option was completely matched so the production succeeded
                return true;
            }
        }
        return false;
    }

    private Stack<String> initializeStack(Option option){
        Stack<String> stack = new Stack<>();
        var elements = option.getElements().toArray(stack.toArray(new String[0]));
        for (int i = elements.length; i > 0; i--) {
            stack.push(elements[i-1]);
        }
        return stack;
    }

    private Production getProduction(String stackedValue){
        return ProdFCTY.valueOf(stackedValue).get();
    }

    private TokenType getToken(String stackedValue){
        return TokenTypesFCTY.valueOf(stackedValue).get();
    }

    private SpecialSymbol getSpecialSymbol(String stackedValue){
        return SpecialSymbol.valueOf(stackedValue);
    }

    private StackableType getType(String stackedValue){
        if(stackedValue.equals(stackedValue.toUpperCase())) {
            return StackableType.TOKEN;
        } else if (stackedValue.startsWith("s_")){
            return StackableType.SPECIAL_SYMBOL;
        } else {
            return StackableType.PRODUCTION;
        }
    }

    private TokenType readEntry() {
        return (tokens.size() > currentPosition) ? tokens.get(currentPosition).getTokenType() : TokenTypesFCTY.END_OF_FILE.get();
    }

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        currentPosition = 0;
    }
}
