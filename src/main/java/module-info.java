module com.lfp.py_lite_compiler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.fxmisc.richtext;

    exports com.lfp.py_lite_compiler;
    exports com.lfp.py_lite_compiler.model.tokens;
    exports com.lfp.py_lite_compiler.model.errors;
    opens com.lfp.py_lite_compiler to javafx.fxml;
    opens com.lfp.py_lite_compiler.model.tokens to javafx.base;
}