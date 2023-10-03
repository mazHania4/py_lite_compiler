module com.lfp.py_lite_compiler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.fxmisc.richtext;
    requires org.apache.logging.log4j;
    requires guru.nidi.graphviz;
    requires reactfx;
    requires org.fxmisc.flowless;

    exports com.lfp.py_lite_compiler;
    exports com.lfp.py_lite_compiler.model;
    exports com.lfp.py_lite_compiler.model.tokens;
    exports com.lfp.py_lite_compiler.model.errors;
    exports com.lfp.py_lite_compiler.model.productions;
    opens com.lfp.py_lite_compiler to javafx.fxml;
    opens com.lfp.py_lite_compiler.model.tokens to javafx.base;
    exports com.lfp.py_lite_compiler.model.special_symbols;
}