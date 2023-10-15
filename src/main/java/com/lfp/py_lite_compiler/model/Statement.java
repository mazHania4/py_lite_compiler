package com.lfp.py_lite_compiler.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Statement {
    private String statement;
    private int index;
}
