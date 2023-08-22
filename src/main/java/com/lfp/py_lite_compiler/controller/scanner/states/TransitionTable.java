package com.lfp.py_lite_compiler.controller.scanner.states;

public class TransitionTable {
    private final State[][] table;

    public State getState(State initState, char inputSymbol) {
        var state = table[initState.ordinal()][inputSymbol];
        return state == null ? State.S63 : state;
    }
    private void setStateOnInterval(int min, int max, State initState, State nextState) {
        for (int i = min; i <= max; i++) {
            table[initState.ordinal()][i] = nextState;
        }
    }
    public TransitionTable() {
        table = new State[State.values().length][256];
        table[State.S0.ordinal()][10] = State.S1;
        table[State.S0.ordinal()][13] = State.S1;
        table[State.S0.ordinal()][32] = State.S0;
        table[State.S0.ordinal()][33] = State.S40;
        table[State.S0.ordinal()][34] = State.S53;
        table[State.S0.ordinal()][35] = State.S55;
        table[State.S0.ordinal()][37] = State.S29;
        table[State.S0.ordinal()][38] = State.S19;
        table[State.S0.ordinal()][39] = State.S51;
        table[State.S0.ordinal()][40] = State.S42;
        table[State.S0.ordinal()][41] = State.S43;
        table[State.S0.ordinal()][42] = State.S9;
        table[State.S0.ordinal()][43] = State.S4;
        table[State.S0.ordinal()][44] = State.S48;
        table[State.S0.ordinal()][45] = State.S6;
        table[State.S0.ordinal()][46] = State.S50;
        table[State.S0.ordinal()][47] = State.S13;
        setStateOnInterval(48, 57, State.S0, State.S58);
        table[State.S0.ordinal()][58] = State.S37;
        table[State.S0.ordinal()][59] = State.S49;
        table[State.S0.ordinal()][60] = State.S25;
        table[State.S0.ordinal()][61] = State.S35;
        table[State.S0.ordinal()][62] = State.S21;
        table[State.S0.ordinal()][64] = State.S33;
        setStateOnInterval(65, 90, State.S0, State.S57);
        table[State.S0.ordinal()][91] = State.S44;
        table[State.S0.ordinal()][92] = State.S56;
        table[State.S0.ordinal()][93] = State.S45;
        table[State.S0.ordinal()][94] = State.S31;
        table[State.S0.ordinal()][95] = State.S57;
        setStateOnInterval(97, 122, State.S0, State.S57);
        table[State.S0.ordinal()][123] = State.S46;
        table[State.S0.ordinal()][124] = State.S17;
        table[State.S0.ordinal()][125] = State.S47;
        table[State.S0.ordinal()][126] = State.S39;
        table[State.S1.ordinal()][32] = State.S1;
        table[State.S4.ordinal()][61] = State.S5;
        table[State.S6.ordinal()][61] = State.S7;
        table[State.S6.ordinal()][62] = State.S8;
        table[State.S9.ordinal()][42] = State.S11;
        table[State.S9.ordinal()][61] = State.S10;
        table[State.S11.ordinal()][61] = State.S12;
        table[State.S13.ordinal()][47] = State.S15;
        table[State.S13.ordinal()][61] = State.S14;
        table[State.S15.ordinal()][61] = State.S16;
        table[State.S17.ordinal()][61] = State.S18;
        table[State.S19.ordinal()][61] = State.S20;
        table[State.S21.ordinal()][61] = State.S22;
        table[State.S21.ordinal()][62] = State.S23;
        table[State.S23.ordinal()][61] = State.S24;
        table[State.S25.ordinal()][60] = State.S27;
        table[State.S25.ordinal()][61] = State.S26;
        table[State.S27.ordinal()][61] = State.S28;
        table[State.S29.ordinal()][61] = State.S30;
        table[State.S31.ordinal()][61] = State.S32;
        table[State.S33.ordinal()][61] = State.S34;
        table[State.S35.ordinal()][61] = State.S36;
        table[State.S37.ordinal()][61] = State.S38;
        table[State.S40.ordinal()][61] = State.S41;
        setStateOnInterval(48, 57, State.S50, State.S59);
        setStateOnInterval(0, 255, State.S51, State.S51);
        table[State.S51.ordinal()][39] = State.S52;
        setStateOnInterval(0, 255, State.S53, State.S53);
        table[State.S53.ordinal()][34] = State.S54;
        setStateOnInterval(0, 255, State.S55, State.S55);
        table[State.S55.ordinal()][10] = State.S1;
        table[State.S55.ordinal()][13] = State.S1;
        setStateOnInterval(48, 57, State.S57, State.S57);
        setStateOnInterval(65, 90, State.S57, State.S57);
        table[State.S57.ordinal()][95] = State.S57;
        setStateOnInterval(97, 122, State.S57, State.S57);
        table[State.S58.ordinal()][46] = State.S59;
        setStateOnInterval(48, 57, State.S58, State.S58);
        table[State.S58.ordinal()][74] = State.S62;
        table[State.S58.ordinal()][106] = State.S62;
        setStateOnInterval(48, 57, State.S59, State.S59);
        table[State.S59.ordinal()][69] = State.S60;
        table[State.S59.ordinal()][74] = State.S62;
        table[State.S59.ordinal()][101] = State.S60;
        table[State.S59.ordinal()][106] = State.S62;
        setStateOnInterval(48, 57, State.S60, State.S61);
        setStateOnInterval(48, 57, State.S61, State.S61);
        table[State.S61.ordinal()][74] = State.S62;
        table[State.S61.ordinal()][106] = State.S62;
    }
}
