package com.lfp.py_lite_compiler.controller.parser;

import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.tokens.Token;
import lombok.Builder;

import java.util.HashMap;
import java.util.HashSet;
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
        addNotDefinedButCalledFunctions();
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

    private void addNotDefinedButCalledFunctions(){
        HashSet<String> definedFunctionsNames = new HashSet<>();
        HashMap<String, Production> calledFunctions = new HashMap<>();
        for (Production function : functions) {
            var IdentifierToken = (Token) function.getMatchedOption().getFoundElements().get(1);
            definedFunctionsNames.add(IdentifierToken.getLexeme());
        }
        for (Production calledFunction : functionCalls) {
            var IdentifierToken = (Token) calledFunction.getMatchedOption().getFoundElements().get(0);
            calledFunctions.put(IdentifierToken.getLexeme(), calledFunction);
        }
        for (String calledFunctionName: calledFunctions.keySet()) {
            if (  !definedFunctionsNames.contains( calledFunctionName )  ) {
                var functionCall = calledFunctions.get(calledFunctionName);
                functionCall.setIndex(functions.size());
                functionCall.setIdentifier( calledFunctionName );
                functionCall.setReferencesCounter( countCalls(calledFunctionName) );
                functionCall.setStartLine(0);
                functionCall.setEndLine(0);
                functions.add(functionCall);
            }
        }
    }
}
