package com.lfp.py_lite_compiler.model.special_symbols;

import com.lfp.py_lite_compiler.model.Stackable;
import com.lfp.py_lite_compiler.model.StackableType;
import com.lfp.py_lite_compiler.model.productions.Option;
import com.lfp.py_lite_compiler.model.productions.Production;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter @NoArgsConstructor
public enum SpecialSymbol implements Stackable{


    s_epsilon( null, SpecialSymbolType.EPSILON ),
    s_next_is_close_parenthesis( get_close_parenthesis(), SpecialSymbolType.NEXT_IS ),
    s_next_is_not_t_lookahead( get_t_lookahead(), SpecialSymbolType.NEXT_IS_NOT ),
    s_next_is_t_lookahead( get_t_lookahead(), SpecialSymbolType.NEXT_IS )
;
    private Production production;
    private SpecialSymbolType type;

    private static Production get_t_lookahead(){
        return Production.builder().options(
                List.of(
                        Option.builder().elements(List.of("DEL_OPEN_PARENTHESIS")).build(),
                        Option.builder().elements(List.of("DEL_OPEN_BRACKET")).build(),
                        Option.builder().elements(List.of("DEL_DOT")).build()
                )).build();
    }

    private static Production get_close_parenthesis(){
        return Production.builder().options(
                List.of(
                        Option.builder().elements(List.of("DEL_CLOSE_PARENTHESIS")).build()
                )).build();
    }
    @Override
    public StackableType getType(){
        return StackableType.SPECIAL_SYMBOL;
    }

    SpecialSymbol(Production production, SpecialSymbolType type) {
        this.production = production;
        this.type = type;
    }
}


