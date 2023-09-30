package com.lfp.py_lite_compiler;

import com.lfp.py_lite_compiler.controller.scanner.Scanner;
import com.lfp.py_lite_compiler.model.errors.Error;
import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.utils.FileManager;
import com.lfp.py_lite_compiler.view.coloring.ColoringController;
import com.lfp.py_lite_compiler.view.graphviz.GraphGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private CodeArea codeArea;
    @FXML
    private TextArea errorOutput;
    private Token selectedToken;
    private ObservableList<Token> tokenList = FXCollections.observableArrayList();
    @FXML
    private TableView<Token> tokenTable;
    @FXML
    private TableColumn<Token, String> nameColumn, patternColumn, colColumn, lineColumn, lexemeColumn;

    private ColoringController colorCtrl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorCtrl = new ColoringController();
        tokenTable.setItems(tokenList);
        SelectionModel<Token> selectionModel = tokenTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedToken = newSelection;
        });
        nameColumn.setCellValueFactory(data -> data.getValue().getTokenType().nameProperty());
        patternColumn.setCellValueFactory(data -> data.getValue().getTokenType().patternProperty());
        lexemeColumn.setCellValueFactory(new PropertyValueFactory<>("lexeme"));
        lineColumn.setCellValueFactory(new PropertyValueFactory<>("line"));
        colColumn.setCellValueFactory(new PropertyValueFactory<>("column"));
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.appendText(sampleCode);
    }

    @FXML
    protected void onRunButtonClick() {
        Scanner scanner = new Scanner(("\n" + codeArea.getText() + "\n").toCharArray());
        List<Token> tokens = scanner.analyze();
        codeArea.setStyleSpans(0, colorCtrl.getStyleSpans(tokens, codeArea.getText()));
        tokenList = FXCollections.observableArrayList(tokens);
        tokenTable.setItems(tokenList);
        var errors = scanner.getErrors();
        errorOutput.setText(getErrorMessages(errors));
    }

    @FXML
    protected void onFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text files", "*.txt"),
                new FileChooser.ExtensionFilter("python files", "*.py"));
        File file = fileChooser.showOpenDialog(new Stage());
        String fileContent = new FileManager().getFileContent(file);
        codeArea.replaceText(fileContent);
    }

    @FXML
    protected void onGraphButtonClick() {
        var graphGenerator = new GraphGenerator();
        graphGenerator.lexemeGraph(selectedToken.getLexeme());
        Stage popupStage = new Stage();
        popupStage.setTitle("Generated graph");
        Image image = new Image("file:src/main/resources/generated/graph.png");
        ImageView imageView = new ImageView(image);
        StackPane layout = new StackPane(imageView);
        Scene popupScene = new Scene(layout);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    private String getErrorMessages(List<Error> errors) {
        StringBuilder buffer = new StringBuilder();
        for (Error error : errors) {
            buffer.append(error.getMessage());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    @FXML
    protected void onHelpButtonClick() {
        System.out.println("presiono ayuda");
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
                            
                    # Python program to display the Fibonacci sequence
                                        
                    def recur_fibo(n):
                       if n <= 1:
                           return n
                       else:
                           return(recur_fibo(n-1) + recur_fibo(n-2))
                                        
                    nterms = 10
                                        
                    # check if the number of terms is valid
                    if nterms <= 0:
                       print("Plese enter a positive integer")
                    else:
                       print("Fibonacci sequence:")
                       for i in range(nterms):
                           print(recur_fibo(i))
                            
                    # Símbolo desconocido
                    ? = 5
                    # Saltos de línea
                    print("\\nFin del programa")
                    # Error de cadena sin cerrar
                    cadena = '¡Esta cadena no se cierra correctamente

                                                                """;
}