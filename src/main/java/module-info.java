module com.lfp.py_lite_compiler {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports com.lfp.py_lite_compiler;
    opens com.lfp.py_lite_compiler to javafx.fxml;
}