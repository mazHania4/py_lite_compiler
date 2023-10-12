package com.lfp.py_lite_compiler.controller.parser;

import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.special_symbols.SpecialSymbol;
import com.lfp.py_lite_compiler.model.tokens.Token;
import lombok.Builder;

import java.util.ArrayList;
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
            String identifier = getFunctionIdentifier(function);
            function.setIndex(i);
            function.setIdentifier( identifier );
            function.setReferencesCounter( countCalls(identifier) );
        }
        addNotDefinedButCalledFunctions();
        return functions;
    }

    public List<Production> getParams(Production function) {
        var opt_parameters =  (Production) function.getMatchedOption().getFoundElements().get(3);
        if (opt_parameters.getMatchedOption().getFoundElements().get(0) == SpecialSymbol.s_epsilon) {
            return null;
        }
        List<Production> parameterList = new ArrayList<>();
        var parameters = (Production) opt_parameters.getMatchedOption().getFoundElements().get(0);
        var param = (Production) parameters.getMatchedOption().getFoundElements().get(0);
        param(param, parameterList);
        var _params1 = (Production) parameters.getMatchedOption().getFoundElements().get(1);
        _params(_params1, parameterList);
        if (param.getName().equals("param_no_default")) {
            var _params2 = (Production) parameters.getMatchedOption().getFoundElements().get(2);
            _params(_params2, parameterList);
        }
        return parameterList;
    }

    private void param(Production paramNoDefault, List<Production> parameterList){
        var param = (Production) paramNoDefault.getMatchedOption().getFoundElements().get(0);
        String paramName = ((Token) param.getMatchedOption().getFoundElements().get(0)).getLexeme();
        parameterList.add(Production.builder()
                .index(parameterList.size())
                .identifier(paramName)
                .build());
    }

    private void _params(Production _param, List<Production> parameterList){
        String elementName = _param.getName();
        if ( !(_param.getMatchedOption().getFoundElements().get(0) == SpecialSymbol.s_epsilon) ) {
            var param = (Production) _param.getMatchedOption().getFoundElements().get(0);
            param(param, parameterList);
            var _params1 = (Production) _param.getMatchedOption().getFoundElements().get(1);
            _params(_params1, parameterList);
        }
    }

    public String getFunctionIdentifier(Production function){
        var identifierToken = (Token) function.getMatchedOption().getFoundElements().get(1);
        return identifierToken.getLexeme();
    }

    private int countCalls(String identifier){
        int counter = 0;
        for (Production functionCall: functionCalls) {
            var token = (Token) functionCall.getMatchedOption().getFoundElements().get(0);
            String calledIdentifier = token.getLexeme();
            if (calledIdentifier.equals(identifier)) {
                counter++;
            }
        }
        return counter;
    }

    private void addNotDefinedButCalledFunctions(){
        HashSet<String> definedFunctionsNames = new HashSet<>();
        HashMap<String, Production> calledFunctions = new HashMap<>();
        for (Production function : functions) {
            definedFunctionsNames.add(getFunctionIdentifier(function));
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
