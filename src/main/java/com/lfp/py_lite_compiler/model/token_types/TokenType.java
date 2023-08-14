package com.lfp.py_lite_compiler.model.token_types;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;
@Getter @Setter @AllArgsConstructor
public class TokenType {
    private String name;
    private String pattern;

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty patternProperty() {
        return new SimpleStringProperty(pattern);
    }

}
