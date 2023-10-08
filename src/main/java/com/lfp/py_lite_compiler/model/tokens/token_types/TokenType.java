package com.lfp.py_lite_compiler.model.tokens.token_types;
import com.lfp.py_lite_compiler.model.Stackable;
import com.lfp.py_lite_compiler.model.StackableType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;
@Getter @Setter @AllArgsConstructor @ToString
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
