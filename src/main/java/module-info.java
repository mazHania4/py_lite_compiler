module com.lfp.py_lite_compiler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    exports com.lfp.py_lite_compiler;
    exports com.lfp.py_lite_compiler.model;
    opens com.lfp.py_lite_compiler to javafx.fxml;
    opens com.lfp.py_lite_compiler.model to javafx.base;
}