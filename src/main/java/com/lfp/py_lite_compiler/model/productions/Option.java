package com.lfp.py_lite_compiler.model.productions;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @ToString
public class Option {
    private List<String> elements;
}
