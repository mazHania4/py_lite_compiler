package com.lfp.py_lite_compiler.controller.parser;

import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.tokens.Token;
import lombok.Builder;

import java.util.List;

@Builder
public class FunctionController {
    private List<Production> functions;
    private List<Production> functionCalls;
    private List<Token> tokens;

    public List<Production> getFunctions() {
        for (int i = 0; i < functions.size(); i++) {
            var function = functions.get(i);
            String identifier = ((Token) function.getMatchedOption().getFoundElements().get(1)).getLexeme();
            System.out.println("------**" + identifier);
            function.setIndex(i);
            function.setIdentifier( identifier );
            function.setReferencesCounter( countCalls(identifier) );
        }
        return functions;
    }

    private int countCalls(String identifier){
        int counter = 0;
        System.out.println("*** calls size: " + functionCalls.size());
        for (Production functionCall: functionCalls) {
            var token = (Token) functionCall.getMatchedOption().getFoundElements().get(0);
            String calledIdentifier = token.getLexeme();
            if (calledIdentifier.equals(identifier)) {
                counter++;
                System.out.println(functionCall.getName());
                System.out.println(">>>> token: line" + token.getLine() + " col" + token.getColumn());
            }
        }
        return counter;
    }
}
