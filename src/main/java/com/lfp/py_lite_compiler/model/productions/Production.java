package com.lfp.py_lite_compiler.model.productions;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @ToString
public class Production {
    private List<Option> options;
    private Option matchedOption;

}
