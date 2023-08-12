package com.lfp.py_lite_compiler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainWindowController {

    @FXML TextArea srcCode;
    @FXML TextArea output;
    @FXML
    protected void onRunButtonClick() {
        System.out.println("presiono correr");;
    }

    @FXML
    protected void onHelpButtonClick() {
        System.out.println("presiono ayuda");;
    }

    @FXML
    protected void onGraphButtonClick() {
        System.out.println("presiono grafico");;
    }

    @FXML
    protected void onFileButtonClick() {
        System.out.println("presiono archivo");;
    }
}