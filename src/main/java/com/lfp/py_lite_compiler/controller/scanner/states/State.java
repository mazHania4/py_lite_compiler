package com.lfp.py_lite_compiler.controller.scanner.states;

import com.lfp.py_lite_compiler.model.tokens.token_types.TokenTypesFCTY;
import com.lfp.py_lite_compiler.model.tokens.token_types.TokenType;

import java.util.Optional;

public enum State {
    S0(null),
    S1(TokenTypesFCTY.NEWLINE.get()),
    S2(null),
    S3(null),
    S4(TokenTypesFCTY.OP_ADD.get()),
    S5(TokenTypesFCTY.DEL_ADD_AND.get()),
    S6(TokenTypesFCTY.OP_SUB.get()),
    S7(TokenTypesFCTY.DEL_SUB_AND.get()),
    S8(TokenTypesFCTY.DEL_RETURN_ANNOTATION.get()),
    S9(TokenTypesFCTY.OP_MULT.get()),
    S10(TokenTypesFCTY.DEL_MULT_AND.get()),
    S11(TokenTypesFCTY.OP_EXP.get()),
    S12(TokenTypesFCTY.DEL_EXP_AND.get()),
    S13(TokenTypesFCTY.OP_DIV.get()),
    S14(TokenTypesFCTY.DEL_DIV_AND.get()),
    S15(TokenTypesFCTY.OP_FLOOR_DIV.get()),
    S16(TokenTypesFCTY.DEL_FLOOR_DIV_AND.get()),
    S17(TokenTypesFCTY.OP_OR.get()),
    S18(TokenTypesFCTY.DEL_OR_AND.get()),
    S19(TokenTypesFCTY.OP_AND.get()),
    S20(TokenTypesFCTY.DEL_AND_AND.get()),
    S21(TokenTypesFCTY.OP_GREATER_THAN.get()),
    S22(TokenTypesFCTY.OP_GREATER_EQUAL_THAN.get()),
    S23(TokenTypesFCTY.OP_RIGHT_SHIFT.get()),
    S24(TokenTypesFCTY.DEL_RIGHT_SHIFT_AND.get()),
    S25(TokenTypesFCTY.OP_LESS_THAN.get()),
    S26(TokenTypesFCTY.OP_LESS_EQUAL_THAN.get()),
    S27(TokenTypesFCTY.OP_LEFT_SHIFT.get()),
    S28(TokenTypesFCTY.DEL_LEFT_SHIFT_AND.get()),
    S29(TokenTypesFCTY.OP_MOD.get()),
    S30(TokenTypesFCTY.DEL_MOD_AND.get()),
    S31(TokenTypesFCTY.OP_XOR.get()),
    S32(TokenTypesFCTY.DEL_XOR_AND.get()),
    S33(TokenTypesFCTY.DEL_AT.get()),
    S34(TokenTypesFCTY.DEL_AT_AND.get()),
    S35(TokenTypesFCTY.DEL_ASSIGN.get()),
    S36(TokenTypesFCTY.OP_EQUALS.get()),
    S37(TokenTypesFCTY.DEL_COLON.get()),
    S38(TokenTypesFCTY.OP_EXPRESSION_ASSIGNMENT.get()),
    S39(TokenTypesFCTY.OP_NOT.get()),
    S40(null),
    S41(TokenTypesFCTY.OP_NOT_EQUALS.get()),
    S42(TokenTypesFCTY.DEL_OPEN_PARENTHESIS.get()),
    S43(TokenTypesFCTY.DEL_CLOSE_PARENTHESIS.get()),
    S44(TokenTypesFCTY.DEL_OPEN_BRACKET.get()),
    S45(TokenTypesFCTY.DEL_CLOSE_BRACKET.get()),
    S46(TokenTypesFCTY.DEL_OPEN_BRACE.get()),
    S47(TokenTypesFCTY.DEL_CLOSE_BRACE.get()),
    S48(TokenTypesFCTY.DEL_COMMA.get()),
    S49(TokenTypesFCTY.DEL_SEMICOLON.get()),
    S50(TokenTypesFCTY.DEL_DOT.get()),
    S51(null),
    S52(TokenTypesFCTY.LIT_STRING.get()),
    S53(null),
    S54(TokenTypesFCTY.LIT_STRING.get()),
    S55(null),
    S56(TokenTypesFCTY.DEL_BACKSLASH.get()),
    S57(TokenTypesFCTY.KW_OR_ID.get()),
    S58(TokenTypesFCTY.LIT_INTEGER.get()),
    S59(TokenTypesFCTY.LIT_FLOAT.get()),
    S60(null),
    S61(TokenTypesFCTY.LIT_FLOAT_EXP.get()),
    S62(TokenTypesFCTY.LIT_IMAGINARY.get()),
    S63(null);

    private final TokenType tokenType;

    State(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Optional<TokenType> getTokenType() {
        return Optional.ofNullable(tokenType);
    }
}
