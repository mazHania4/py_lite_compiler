package com.lfp.py_lite_compiler.model.productions;

import com.lfp.py_lite_compiler.model.Stackable;
import com.lfp.py_lite_compiler.model.StackableType;
import lombok.*;

import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @ToString
public class Production  implements Stackable {
    private List<Option> options;
    private Option matchedOption;
    private String name;
    private int startLine;
    private int endLine;
    private int referencesCounter;
    private int index;
    private String identifier;


    @Override
    public StackableType getType() {
        return StackableType.PRODUCTION;
    }
}
