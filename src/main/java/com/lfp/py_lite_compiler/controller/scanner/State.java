package com.lfp.py_lite_compiler.controller.scanner;

import com.lfp.py_lite_compiler.model.token_types.DelimiterFCTY;
import com.lfp.py_lite_compiler.model.token_types.OperatorFCTY;
import com.lfp.py_lite_compiler.model.token_types.OtherTypesFCTY;
import com.lfp.py_lite_compiler.model.token_types.TokenType;

import java.util.Optional;

public enum State {
    S0(null),
    S1(OtherTypesFCTY.NEWLINE.getTokenType()),
    S2(null),
    S3(null),
    S4(OperatorFCTY.OP_ADD.getTokenType()),
    S5(DelimiterFCTY.DEL_ADD_AND.getTokenType()),
    S6(OperatorFCTY.OP_SUB.getTokenType()),
    S7(DelimiterFCTY.DEL_SUB_AND.getTokenType()),
    S8(DelimiterFCTY.DEL_RETURN_ANNOTATION.getTokenType()),
    S9(OperatorFCTY.OP_MULT.getTokenType()),
    S10(DelimiterFCTY.DEL_MULT_AND.getTokenType()),
    S11(OperatorFCTY.OP_EXP.getTokenType()),
    S12(DelimiterFCTY.DEL_EXP_AND.getTokenType()),
    S13(OperatorFCTY.OP_DIV.getTokenType()),
    S14(DelimiterFCTY.DEL_DIV_AND.getTokenType()),
    S15(OperatorFCTY.OP_FLOOR_DIV.getTokenType()),
    S16(DelimiterFCTY.DEL_FLOOR_DIV_AND.getTokenType()),
    S17(OperatorFCTY.OP_OR.getTokenType()),
    S18(DelimiterFCTY.DEL_OR_AND.getTokenType()),
    S19(OperatorFCTY.OP_AND.getTokenType()),
    S20(DelimiterFCTY.DEL_AND_AND.getTokenType()),
    S21(OperatorFCTY.OP_GREATER_THAN.getTokenType()),
    S22(OperatorFCTY.OP_GREATER_EQUAL_THAN.getTokenType()),
    S23(OperatorFCTY.OP_RIGHT_SHIFT.getTokenType()),
    S24(DelimiterFCTY.DEL_RIGHT_SHIFT_AND.getTokenType()),
    S25(OperatorFCTY.OP_LESS_THAN.getTokenType()),
    S26(OperatorFCTY.OP_LESS_EQUAL_THAN.getTokenType()),
    S27(OperatorFCTY.OP_LEFT_SHIFT.getTokenType()),
    S28(DelimiterFCTY.DEL_LEFT_SHIFT_AND.getTokenType()),
    S29(OperatorFCTY.OP_MOD.getTokenType()),
    S30(DelimiterFCTY.DEL_MOD_AND.getTokenType()),
    S31(OperatorFCTY.OP_XOR.getTokenType()),
    S32(DelimiterFCTY.DEL_XOR_AND.getTokenType()),
    S33(DelimiterFCTY.DEL_AT.getTokenType()),
    S34(DelimiterFCTY.DEL_AT_AND.getTokenType()),
    S35(DelimiterFCTY.DEL_ASSIGN.getTokenType()),
    S36(OperatorFCTY.OP_EQUALS.getTokenType()),
    S37(DelimiterFCTY.DEL_COLON.getTokenType()),
    S38(OperatorFCTY.OP_EXPRESSION_ASSIGNMENT.getTokenType()),
    S39(OperatorFCTY.OP_NOT.getTokenType()),
    S40(null),
    S41(OperatorFCTY.OP_NOT_EQUALS.getTokenType()),
    S42(DelimiterFCTY.DEL_OPEN_PARENTHESIS.getTokenType()),
    S43(DelimiterFCTY.DEL_CLOSE_PARENTHESIS.getTokenType()),
    S44(DelimiterFCTY.DEL_OPEN_BRACKET.getTokenType()),
    S45(DelimiterFCTY.DEL_CLOSE_BRACKET.getTokenType()),
    S46(DelimiterFCTY.DEL_OPEN_BRACE.getTokenType()),
    S47(DelimiterFCTY.DEL_CLOSE_BRACE.getTokenType()),
    S48(DelimiterFCTY.DEL_COMMA.getTokenType()),
    S49(DelimiterFCTY.DEL_SEMICOLON.getTokenType()),
    S50(DelimiterFCTY.DEL_DOT.getTokenType()),
    S51(null),
    S52(OtherTypesFCTY.LIT_STRING.getTokenType()),
    S53(null),
    S54(OtherTypesFCTY.LIT_STRING.getTokenType()),
    S55(null),
    S56(DelimiterFCTY.DEL_BACKSLASH.getTokenType()),
    S57(OtherTypesFCTY.KW_OR_ID.getTokenType()),
    S58(OtherTypesFCTY.LIT_INTEGER.getTokenType()),
    S59(OtherTypesFCTY.LIT_FLOAT.getTokenType()),
    S60(null),
    S61(OtherTypesFCTY.LIT_FLOAT_EXP.getTokenType()),
    S62(OtherTypesFCTY.LIT_IMAGINARY.getTokenType()),
    S63(null);

    private final Optional<TokenType> tokenType;

    State(TokenType tokenType) {
        this.tokenType = Optional.ofNullable(tokenType);
    }

    public Optional<TokenType> getTokenType() {
        return tokenType;
    }
}
