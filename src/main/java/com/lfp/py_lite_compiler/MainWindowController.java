package com.lfp.py_lite_compiler;

import com.lfp.py_lite_compiler.controller.scanner.ScannerInitializer;
import com.lfp.py_lite_compiler.model.Token;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private TextArea srcCode;
    @FXML
    private TextArea errorOutput;
    private ObservableList<Token> tokenList = FXCollections.observableArrayList();
    @FXML
    private TableView<Token> tokenTable;
    @FXML
    private TableColumn<Token, String> nameColumn, patternColumn, colColumn, lineColumn, lexemeColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tokenTable.setItems(tokenList);
        nameColumn.setCellValueFactory(data -> data.getValue().getTokenType().nameProperty());
        patternColumn.setCellValueFactory(data -> data.getValue().getTokenType().patternProperty());
        lexemeColumn.setCellValueFactory(new PropertyValueFactory<>("lexeme"));
        lineColumn.setCellValueFactory(new PropertyValueFactory<>("line"));
        colColumn.setCellValueFactory(new PropertyValueFactory<>("column"));
        srcCode.setText(sampleCode);
    }

    @FXML
    protected void onRunButtonClick() {
        var tokens = new ScannerInitializer().getScannerFromContent(srcCode.getText()).analyze();
        tokenList = FXCollections.observableArrayList(tokens);
        tokenTable.setItems(tokenList);
    }

    @FXML
    protected void onHelpButtonClick() {
        System.out.println("presiono ayuda");
    }

    @FXML
    protected void onGraphButtonClick() {
        System.out.println("presiono grafico");
    }

    @FXML
    protected void onFileButtonClick() {
        System.out.println("presiono archivo");
    }

    private String sampleCode =
            """
                    # Esto es un comentario

                    # Palabras reservadas y operadores
                    def main():
                        a = 10
                        b = 20.5
                        suma = a + b
                        resta = a - b
                        multiplicacion = a * b
                        division = a / b

                        if suma > 15:
                            print("La suma es mayor que 15")
                        else:
                            print("La suma no es mayor que 15")

                        for i in range(5):
                            print(i)

                        while a > 0:
                            print(a)
                            a -= 1

                        cadena = "Hola, mundo!'
                        print(cadena)

                        # Error de cadena sin cerrar
                        otra_cadena = '¡Esta cadena no se cierra correctamente

                        # Símbolo desconocido
                        @ = 5

                    # Identificadores
                    mi_variable = 42
                    nombre = "Juan"
                    _123_identificador = "Ejemplo"

                    # Saltos de línea
                    print("\\nFin del programa")

                                            """;
}