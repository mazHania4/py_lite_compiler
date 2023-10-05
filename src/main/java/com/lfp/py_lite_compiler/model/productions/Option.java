package com.lfp.py_lite_compiler.model.productions;

import com.lfp.py_lite_compiler.model.Stackable;
import lombok.*;

import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @ToString
public class Option {
    private List<String> elements;
    private List<Stackable> foundElements;
}
