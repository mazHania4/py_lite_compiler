package com.lfp.py_lite_compiler.model.errors.error_types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class ErrorType {
    private String name;
    private String description;

}
