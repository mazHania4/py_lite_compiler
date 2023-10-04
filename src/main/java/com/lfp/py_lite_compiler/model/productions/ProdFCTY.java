package com.lfp.py_lite_compiler.model.productions;

import com.lfp.py_lite_compiler.model.Stackable;
import com.lfp.py_lite_compiler.model.StackableType;

import java.util.List;


public enum ProdFCTY implements Stackable{

    statements( bProd(List.of(
            bOption(List.of( "statement", "_statements" ))
    ))),

    _statements( bProd(List.of(
            bOption(List.of( "statement", "_statements" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    statement( bProd(List.of(
            bOption(List.of( "compound_stmt" )),
            bOption(List.of( "simple_stmts" ))
    ))),

    simple_stmts( bProd(List.of(
            bOption(List.of( "simple_stmt", "_simple_stmts", "opt_semicolon", "NEWLINE" ))
    ))),

    _simple_stmts( bProd(List.of(
            bOption(List.of( "DEL_SEMICOLON", "simple_stmt", "_simple_stmts" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    simple_stmt( bProd(List.of(
            bOption(List.of( "assignment" )),
            bOption(List.of( "expressions" )),
            bOption(List.of( "return_stmt" )),
            bOption(List.of( "KW_PASS" )),
            bOption(List.of( "KW_BREAK" )),
            bOption(List.of( "KW_CONTINUE" ))
    ))),

    compound_stmt( bProd(List.of(
            bOption(List.of( "function_def" )),
            bOption(List.of( "if_stmt" )),
            bOption(List.of( "for_stmt" )),
            bOption(List.of( "while_stmt" ))
    ))),

    assignment( bProd(List.of(
            bOption(List.of( "IDENTIFIER", "DEL_COLON", "expression", "opt_assign_expressions" )),
            bOption(List.of( "DEL_OPEN_PARENTHESIS", "single_target", "DEL_CLOSE_PARENTHESIS", "DEL_COLON", "expression", "opt_assign_expressions" )),
            bOption(List.of( "single_subscript_attribute_target", "DEL_COLON", "expression", "opt_assign_expressions" )),
            bOption(List.of( "targets", "DEL_ASSIGN", "_assignment", "expressions" )),
            bOption(List.of( "single_target", "augassign", "expressions" ))
    ))),

    _assignment( bProd(List.of(
            bOption(List.of( "targets", "DEL_ASSIGN", "_assignment" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    augassign( bProd(List.of(
            bOption(List.of( "DEL_ADD_AND" )),
            bOption(List.of( "DEL_SUB_AND" )),
            bOption(List.of( "DEL_MULT_AND" )),
            bOption(List.of( "DEL_AT_AND" )),
            bOption(List.of( "DEL_DIV_AND" )),
            bOption(List.of( "DEL_MOD_AND" )),
            bOption(List.of( "DEL_AND_AND" )),
            bOption(List.of( "DEL_OR_AND" )),
            bOption(List.of( "DEL_XOR_AND" )),
            bOption(List.of( "DEL_LEFT_SHIFT_AND" )),
            bOption(List.of( "DEL_RIGHT_SHIFT_AND" )),
            bOption(List.of( "DEL_EXP_AND" )),
            bOption(List.of( "DEL_FLOOR_DIV_AND" ))
    ))),

    return_stmt( bProd(List.of(
            bOption(List.of( "KW_RETURN", "opt_expressions"))
    ))),

    block( bProd(List.of(
            bOption(List.of( "NEWLINE", "INDENT", "statements", "DEDENT" )),
            bOption(List.of( "simple_stmts" ))
    ))),

    function_def( bProd(List.of(
            bOption(List.of( "KW_DEF", "IDENTIFIER", "DEL_OPEN_PARENTHESIS", "opt_parameters", "DEL_CLOSE_PARENTHESIS", "DEL_COLON", "block" ))
    ))),

    parameters( bProd(List.of(
            bOption(List.of( "param_no_default", "_params_n", "_params_w" )),
            bOption(List.of( "param_no_default", "_params_w" ))
    ))),

    _params_n( bProd(List.of(
            bOption(List.of( "param_no_default", "_params_n" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    _params_w( bProd(List.of(
            bOption(List.of( "param_with_default", "_params_w" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    param_no_default( bProd(List.of(
            bOption(List.of( "param", "DEL_COMMA" )),
            bOption(List.of( "param", "s_next_is_close_parenthesis" ))
    ))),

    param_with_default( bProd(List.of(
            bOption(List.of( "param", "_default", "DEL_COMMA" )),
            bOption(List.of( "param", "_default", "s_next_is_close_parenthesis" ))
    ))),

    param( bProd(List.of(
            bOption(List.of( "IDENTIFIER" ))
    ))),

    _default( bProd(List.of(
            bOption(List.of( "DEL_ASSIGN", "expression" ))
    ))),

    if_stmt( bProd(List.of(
            bOption(List.of( "KW_IF", "expression", "DEL_COLON", "block", "opt_elif_or_else_block" ))
    ))),

    elif_stmt( bProd(List.of(
            bOption(List.of( "KW_ELIF", "expression", "DEL_COLON", "block", "opt_elif_or_else_block" ))
    ))),

    else_block( bProd(List.of(
            bOption(List.of( "KW_ELSE", "DEL_COLON", "block" ))
    ))),

    while_stmt( bProd(List.of(
            bOption(List.of( "KW_WHILE", "expression", "DEL_COLON", "block", "opt_else_block" ))
    ))),

    for_stmt( bProd(List.of(
            bOption(List.of( "KW_FOR", "targets", "KW_IN", "expressions", "DEL_COLON", "block", "opt_else_block" ))
    ))),

    expressions( bProd(List.of(
            bOption(List.of( "expression", "_expressions", "opt_comma" ))
    ))),

    _expressions( bProd(List.of(
            bOption(List.of( "DEL_COMMA", "expression", "_expressions" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    expression( bProd(List.of(
            bOption(List.of( "disjunction", "opt_ternary_operator" )),
            bOption(List.of( "disjunction" ))
    ))),

    disjunction( bProd(List.of(
            bOption(List.of( "conjunction", "_disjunction" ))
    ))),

    _disjunction( bProd(List.of(
            bOption(List.of( "KW_OR", "conjunction", "_disjunction" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    conjunction( bProd(List.of(
            bOption(List.of( "inversion", "_conjunction" ))
    ))),

    _conjunction( bProd(List.of(
            bOption(List.of( "KW_AND", "inversion", "_conjunction" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    inversion( bProd(List.of(
            bOption(List.of( "KW_NOT", "inversion" )),
            bOption(List.of( "comparison" ))
    ))),

    comparison( bProd(List.of(
            bOption(List.of( "bitwise_or", "_comparison" )),
            bOption(List.of( "bitwise_or" ))
    ))),

    _comparison( bProd(List.of(
            bOption(List.of( "compare_op_bitwise_or_pair", "_comparison" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    compare_op_bitwise_or_pair( bProd(List.of(
            bOption(List.of( "eq_bitwise_or" )),
            bOption(List.of( "noteq_bitwise_or" )),
            bOption(List.of( "lte_bitwise_or" )),
            bOption(List.of( "lt_bitwise_or" )),
            bOption(List.of( "gte_bitwise_or" )),
            bOption(List.of( "gt_bitwise_or" )),
            bOption(List.of( "notin_bitwise_or" )),
            bOption(List.of( "in_bitwise_or" )),
            bOption(List.of( "isnot_bitwise_or" )),
            bOption(List.of( "is_bitwise_or" ))
    ))),

    eq_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_EQUALS", "bitwise_or" ))
    ))),

    noteq_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_NOT_EQUALS", "bitwise_or" ))
    ))),

    lte_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_LESS_EQUAL_THAN", "bitwise_or" ))
    ))),

    lt_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_LESS_THAN", "bitwise_or" ))
    ))),

    gte_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_GREATER_EQUAL_THAN", "bitwise_or" ))
    ))),

    gt_bitwise_or( bProd(List.of(
            bOption(List.of( "OP_GREATER_THAN", "bitwise_or" ))
    ))),

    notin_bitwise_or( bProd(List.of(
            bOption(List.of( "KW_NOT", "KW_IN", "bitwise_or" ))
    ))),

    in_bitwise_or( bProd(List.of(
            bOption(List.of( "KW_IN", "bitwise_or" ))
    ))),

    isnot_bitwise_or( bProd(List.of(
            bOption(List.of( "KW_IS", "KW_NOT", "bitwise_or" ))
    ))),

    is_bitwise_or( bProd(List.of(
            bOption(List.of( "KW_IS", "bitwise_or" ))
    ))),

    bitwise_or( bProd(List.of(
            bOption(List.of( "bitwise_xor", "_bitwise_or" ))
    ))),

    _bitwise_or( bProd(List.of(
            bOption(List.of( "OP_OR", "bitwise_xor", "_bitwise_or" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    bitwise_xor( bProd(List.of(
            bOption(List.of( "bitwise_and", "_bitwise_xor" ))
    ))),

    _bitwise_xor( bProd(List.of(
            bOption(List.of( "OP_XOR", "bitwise_and", "_bitwise_xor" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    bitwise_and( bProd(List.of(
            bOption(List.of( "shift_expr", "_bitwise_and" ))
    ))),

    _bitwise_and( bProd(List.of(
            bOption(List.of( "OP_AND", "shift_expr", "_bitwise_and" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    shift_expr( bProd(List.of(
            bOption(List.of( "sum", "_shift_expr" ))
    ))),

    _shift_expr( bProd(List.of(
            bOption(List.of( "OP_LEFT_SHIFT", "sum", "_shift_expr" )),
            bOption(List.of( "OP_RIGHT_SHIFT", "sum", "_shift_expr" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    sum( bProd(List.of(
            bOption(List.of( "term", "_sum" ))
    ))),

    _sum( bProd(List.of(
            bOption(List.of( "OP_ADD", "term", "_sum" )),
            bOption(List.of( "OP_SUB", "term", "_sum" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    term( bProd(List.of(
            bOption(List.of( "factor", "_term" ))
    ))),

    _term( bProd(List.of(
            bOption(List.of( "OP_MULT", "factor", "_term" )),
            bOption(List.of( "OP_DIV", "factor", "_term" )),
            bOption(List.of( "OP_FLOOR_DIV", "factor", "_term" )),
            bOption(List.of( "OP_MOD", "factor", "_term" )),
            bOption(List.of( "DEL_AT", "factor", "_term" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    factor( bProd(List.of(
            bOption(List.of( "OP_ADD", "factor" )),
            bOption(List.of( "OP_SUB", "factor" )),
            bOption(List.of( "OP_NOT", "factor" )),
            bOption(List.of( "power" ))
    ))),

    power( bProd(List.of(
                bOption(List.of( "primary", "opt_power")),
                bOption(List.of( "primary" ))
    ))),

    primary( bProd(List.of(
                bOption(List.of( "atom", "_primary" ))
    ))),

    _primary( bProd(List.of(
                bOption(List.of( "DEL_DOT", "IDENTIFIER", "_primary" )),
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "opt_arguments", "DEL_CLOSE_PARENTHESIS", "_primary" )),
                bOption(List.of( "s_epsilon" ))
    ))),

    atom( bProd(List.of(
                bOption(List.of( "IDENTIFIER" )),
                bOption(List.of( "KW_TRUE" )),
                bOption(List.of( "KW_FALSE" )),
                bOption(List.of( "KW_NONE" )),
                bOption(List.of( "strings" )),
                bOption(List.of( "LIT_INTEGER" )),
                bOption(List.of( "LIT_FLOAT" )),
                bOption(List.of( "LIT_FLOAT_EXP" )),
                bOption(List.of( "LIT_IMAGINARY" )),
                bOption(List.of( "tuple")),
                bOption(List.of( "group")),
                bOption(List.of( "list")),
                bOption(List.of( "dict")),
                bOption(List.of( "set"))
    ))),

    group( bProd(List.of(
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "expression", "DEL_CLOSE_PARENTHESIS" ))
    ))),

    strings( bProd(List.of(
                bOption(List.of( "LIT_STRING", "_strings" ))
    ))),

    _strings( bProd(List.of(
                bOption(List.of( "LIT_STRING", "_strings" )),
                bOption(List.of( "s_epsilon" ))
    ))),

    list( bProd(List.of(
                bOption(List.of( "DEL_OPEN_BRACKET", "opt_expressions", "DEL_CLOSE_BRACKET" ))
    ))),

    tuple( bProd(List.of(
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "opt_tuple_content", "DEL_CLOSE_PARENTHESIS" ))
        ))),

    set( bProd(List.of(
                bOption(List.of( "DEL_OPEN_BRACE", "expressions", "DEL_CLOSE_BRACE" ))
        ))),

    dict( bProd(List.of(
                bOption(List.of( "DEL_OPEN_BRACE", "opt_kvpairs", "DEL_CLOSE_BRACE" ))
        ))),

    kvpairs( bProd(List.of(
                bOption(List.of( "kvpair", "_kvpairs", "opt_comma" ))
        ))),
    _kvpairs( bProd(List.of(
                bOption(List.of( "DEL_COMMA", "kvpair", "_kvpairs" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    kvpair( bProd(List.of(
                bOption(List.of( "expression", "DEL_COLON", "expression" ))
        ))),

    arguments( bProd(List.of(
                bOption(List.of( "args", "DEL_COMMA", "s_next_is_close_parenthesis" )),
                bOption(List.of( "args", "s_next_is_close_parenthesis" ))
    ))),

    args( bProd(List.of(
                bOption(List.of( "expression", "_args", "opt_comma_kwargs" )),
                bOption(List.of( "kwargs" ))
        ))),

    _args( bProd(List.of(
                bOption(List.of( "DEL_COMMA", "expression", "_args" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    kwargs( bProd(List.of(
                bOption(List.of( "kwarg", "_kwargs" ))
        ))),

    _kwargs( bProd(List.of(
                bOption(List.of( "DEL_COMMA", "kwarg", "_kwargs" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    kwarg( bProd(List.of(
                bOption(List.of( "IDENTIFIER", "DEL_ASSIGN", "expression" ))
        ))),

    targets( bProd(List.of(
                bOption(List.of( "target_with_targ_atom", "_targets", "opt_comma" ))
        ))),

    _targets( bProd(List.of(
                bOption(List.of( "DEL_COMMA", "target_with_targ_atom", "_targets" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    targets_list_seq(  bProd(List.of(
                bOption(List.of( "target_with_targ_atom", "_targets_seq", "opt_comma" ))
        ))),
    targets_tuple_seq(bProd(List.of(
                bOption(List.of( "target_with_targ_atom", "opt_targets_seq_or_comma" ))
        ))),

    _targets_seq( bProd(List.of(
                bOption(List.of( "DEL_COMMA", "target_with_targ_atom", "_targets_seq" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    target_with_targ_atom( bProd(List.of(
                bOption(List.of( "t_primary", "DEL_DOT", "IDENTIFIER", "s_next_is_not_t_lookahead" )),
                bOption(List.of( "targ_atom" ))
        ))),

    targ_atom( bProd( List.of(
                bOption(List.of( "IDENTIFIER" )),
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "opt_twta_or_tts", "DEL_CLOSE_PARENTHESIS" )),
                bOption(List.of( "DEL_OPEN_BRACKET", "opt_targets_list_seq", "DEL_CLOSE_BRACKET" ))
        ))),

    single_target( bProd(List.of(
                bOption(List.of( "single_subscript_attribute_target" ) ),
                bOption(List.of( "IDENTIFIER" ) ),
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "single_target", "DEL_CLOSE_PARENTHESIS" ) )
        ))),

    single_subscript_attribute_target( bProd(List.of(
                bOption(List.of( "t_primary", "DEL_DOT", "IDENTIFIER", "_t_primary", "s_next_is_not_t_lookahead") )
        ))),

    t_primary( bProd(List.of(
                bOption(List.of( "atom", "_t_primary", "s_next_is_t_lookahead" ) )
        ))),

    _t_primary( bProd(List.of(
                bOption(List.of( "DEL_DOT", "IDENTIFIER", "_t_primary", "s_next_is_t_lookahead" )),
                bOption(List.of( "DEL_OPEN_PARENTHESIS", "opt_arguments", "DEL_CLOSE_PARENTHESIS", "_t_primary", "s_next_is_t_lookahead" )),
                bOption(List.of( "s_epsilon" ))
        ))),

    opt_semicolon( bProd(List.of(
            bOption(List.of( "DEL_SEMICOLON" ) ),
            bOption(List.of( "s_epsilon" ) )
            ))),

    opt_comma( bProd(List.of(
            bOption(List.of( "DEL_COMMA" ) ),
            bOption(List.of( "s_epsilon" ) )
            ))),

    opt_assign_expressions( bProd(List.of(
            bOption(List.of( "DEL_ASSIGN", "expressions" ) ),
            bOption(List.of( "s_epsilon" ) )
            ))),

    opt_expressions( bProd(List.of(
            bOption(List.of( "expressions" ) ),
            bOption(List.of( "s_epsilon" ) )
            ))),

    opt_parameters( bProd(List.of(
            bOption(List.of( "parameters" ) ),
            bOption(List.of( "s_epsilon" ) )
            ))),

    opt_else_block( bProd(List.of(
            bOption(List.of( "else_block" )),
            bOption(List.of( "s_epsilon" ))
            ))),

    opt_elif_or_else_block( bProd(List.of(
            bOption(List.of( "elif_stmt" )),
            bOption(List.of( "else_block" )),
            bOption(List.of( "s_epsilon" ))
            ))),

    opt_ternary_operator( bProd(List.of(
            bOption(List.of( "KW_IF", "disjunction", "KW_ELSE", "expression" )),
            bOption(List.of( "s_epsilon" ))
            ))),

    opt_power( bProd(List.of(
            bOption(List.of( "OP_EXP", "factor" )),
            bOption(List.of( "s_epsilon" ))
            ))),

    opt_arguments( bProd(List.of(
            bOption(List.of( "arguments" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_tuple_content( bProd(List.of(
            bOption(List.of( "expressions", "DEL_COMMA", "opt_expressions" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_kvpairs( bProd(List.of(
            bOption(List.of( "kvpairs" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_comma_kwargs( bProd(List.of(
            bOption(List.of( "DEL_COMMA", "kwargs" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_targets_seq_or_comma( bProd(List.of(
            bOption(List.of( "_targets_seq", "opt_comma" )),
            bOption(List.of( "DEL_COMMA" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_twta_or_tts( bProd(List.of(
            bOption(List.of( "target_with_targ_atom" )),
            bOption(List.of( "targets_tuple_seq" )),
            bOption(List.of( "s_epsilon" ))
    ))),

    opt_targets_list_seq( bProd(List.of(
            bOption(List.of( "targets_list_seq" )),
            bOption(List.of( "s_epsilon" ))
    ))),
    ;

    private static Option bOption(List<String> elements){
        return Option.builder().elements(elements).build();
    }
    private static Production bProd(List<Option> options){
        return Production.builder().options(options).build();
    }

    private final Production production;

    public Production get(){
        return production;
    }

    ProdFCTY(Production production) {
        this.production = production;
    }

    @Override
    public StackableType getType() {
        return StackableType.PRODUCTION;
    }
}
