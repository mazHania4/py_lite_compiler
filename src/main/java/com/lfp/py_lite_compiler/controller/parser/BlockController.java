package com.lfp.py_lite_compiler.controller.parser;

import com.lfp.py_lite_compiler.model.Statement;
import com.lfp.py_lite_compiler.model.Symbol;
import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BlockController {

    private List<Production> blocks;
    private List<Token> tokens;

    public List<Production> getBlocks() {
        for (int i = 0; i < blocks.size(); i++) {
            var function = blocks.get(i);
            function.setIndex(i);
        }
        return blocks;
    }

    public List<Symbol> getSymbols(Production prod){
        List<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            var token = tokens.get(i);
            if (token.getTokenType().equals(TokenTypesFCTY.IDENTIFIER.get())
                    && (token.getLine() >= prod.getStartLine())
                    && (token.getLine() < prod.getEndLine())
            ){
                symbols.add(Symbol.builder()
                        .index(symbols.size())
                        .identifier(token.getLexeme())
                        .type(getType(i))
                        .line(token.getLine())
                        .column(token.getColumn())
                        .build());
            }

        }
        return symbols;
    }

    public List<Statement> getStatements(Production prod){
        List<Statement> statements = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            var token = tokens.get(i);
            if ((token.getLine() >= prod.getStartLine())
                && (token.getLine() < prod.getEndLine())
            ){
                StringBuilder statement = new StringBuilder();
                do {
                    statement.append(token.getLexeme()).append(" ");
                    i++;
                    token = tokens.get(i);
                } while (!token.getTokenType().equals(TokenTypesFCTY.NEWLINE.get()));
                if (tokens.get(i+1).getTokenType().equals(TokenTypesFCTY.INDENT.get())){
                    i = statementBlock(i, statement);
                }
                statements.add(Statement.builder()
                        .index(statements.size())
                        .statement(statement.toString())
                        .build());
            }
        }
        return statements;
    }

    private int statementBlock(int i, StringBuilder statement){
        Token token;
        i++;
        do {
            if (tokens.get(i+1).getTokenType().equals(TokenTypesFCTY.INDENT.get())){
                i = statementBlock(i, statement);
            }
            i++;
            token = tokens.get(i);
            statement.append(token.getLexeme()).append(" ");
        } while (!token.getTokenType().equals(TokenTypesFCTY.DEDENT.get()));
        return i;
    }

    private String getType(int i){
        return (tokens.get(i+1).getTokenType().equals(TokenTypesFCTY.DEL_OPEN_PARENTHESIS.get()))
                ? "función" : "variable";
    }
}
