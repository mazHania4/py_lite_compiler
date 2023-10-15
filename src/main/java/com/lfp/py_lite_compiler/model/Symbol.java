package com.lfp.py_lite_compiler.model;

import lombok.*;

@Getter
@Setter
@Builder
public class Symbol {
    private String identifier;
    private String type;
    private int line;
    private int column;
    private int index;
}
