package com.lfp.py_lite_compiler;

import com.lfp.py_lite_compiler.controller.parser.FunctionController;
import com.lfp.py_lite_compiler.controller.parser.Parser;
import com.lfp.py_lite_compiler.controller.scanner.Scanner;
import com.lfp.py_lite_compiler.model.errors.Error;
import com.lfp.py_lite_compiler.model.productions.Production;
import com.lfp.py_lite_compiler.model.tokens.Token;
import com.lfp.py_lite_compiler.utils.FileManager;
import com.lfp.py_lite_compiler.view.coloring.ColoringController;
import com.lfp.py_lite_compiler.view.graphviz.GraphGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private TextArea output;
    private ColoringController colorCtrl;
    private FunctionController functionCtrl;

    // Token table elements:
    private ObservableList<Token> tokenList = FXCollections.observableArrayList();
    private Token selectedToken;
    @FXML
    private TableView<Token> tokenTable;
    @FXML
    private TableColumn<Token, String> nameColumn, patternColumn, colColumn, lineColumn, lexemeColumn, indexColumn;

    // Function table elements:
    private ObservableList<Production> functionList = FXCollections.observableArrayList();
    private Production selectedFunction;
    @FXML
    private TableView<Production> functionTable;
    @FXML
    private TableColumn<Production, String> defIndex, defIdentifier, defStartLine, defEndLine, defReferences;

    // Token table elements:
    private ObservableList<Production> blockList = FXCollections.observableArrayList();
    private Production selectedBlock;
    @FXML
    private TableView<Production> blockTable;
    @FXML
    private TableColumn<Production, String> blockIndex, blockStartLine, blockEndLine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorCtrl = new ColoringController();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.appendText(sampleCode);
        initializeTokenTable();
        initializeFunctionTable();
        initializeBlockTable();
    }

    @FXML
    protected void onRunButtonClick() {
        //scanner:
        Scanner scanner = new Scanner(("\n" + codeArea.getText() + "\n").toCharArray());
        List<Token> tokens = scanner.analyze();
        codeArea.setStyleSpans(0, colorCtrl.getStyleSpans(tokens, codeArea.getText()));
        tokenList = FXCollections.observableArrayList(tokens);
        tokenTable.setItems(tokenList);
        String lexicalErrors = getErrorMessages(scanner.getErrors());
        //parser:
        var parser = new Parser(tokens);
        String syntaxError = "";
        if (parser.analyze()){
            showFunctionTable(parser, tokens);
        } else {
            syntaxError = parser.getError().getMessage();
        }
        printOutput(lexicalErrors, syntaxError);
    }

    private void showFunctionTable(Parser parser, List<Token> tokens){
        var functions = parser.getFunctions();
        var functionCalls = parser.getFunctionCalls();
        functionCtrl = FunctionController.builder().functions(functions).functionCalls(functionCalls).tokens(tokens).build();
        functionList = FXCollections.observableArrayList( functionCtrl.getFunctions() );
        functionTable.setItems(functionList);
    }

    private void printOutput(String lexicalErrors, String syntaxError){
        output.setText(
                "Resultados análisis léxico: "
                + (lexicalErrors.isEmpty() ? "\n > Completado sin errores" : "\n" + lexicalErrors)
                + "\n\nResultados análisis sintáctico: "
                + (syntaxError.isEmpty() ? "\n > Completado sin errores" : "\n" + syntaxError)
        );
    }

    @FXML
    protected void onFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("python files", "*.py"),
                new FileChooser.ExtensionFilter("text files", "*.txt"));
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

    @FXML
    public void onParamsButtonClick() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Function parameters");
        popupStage.setWidth(300);
        popupStage.setHeight(400);
        Scene popupScene = new Scene(new Group());
        Label label = new Label("La función seleccionada \nno está definida \nen el archivo");
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().add(label);
        label.setFont(new Font("monospace", 16));
        if (selectedFunction.getStartLine() != 0) {
            List<Production> parameters = functionCtrl.getParams(selectedFunction);
            if (parameters == null) {
                label.setText("La función seleccionada \nno tiene parametros");
            } else {
                label.setText("Parametros de \n     '" + functionCtrl.getFunctionIdentifier(selectedFunction) + "' :");
                TableView<Production> paramsTable = new TableView<>();
                TableColumn<Production, String> index = new TableColumn<>("#");
                TableColumn<Production, String> paramName = new TableColumn<>("Nombre");
                paramsTable.setItems(FXCollections.observableList(parameters));
                index.setCellValueFactory(new PropertyValueFactory<>("index"));
                index.setPrefWidth(50);
                paramName.setCellValueFactory(new PropertyValueFactory<>("identifier"));
                paramName.setPrefWidth(230);
                paramsTable.getColumns().addAll(index, paramName);
                paramsTable.setPrefHeight(300);
                vbox.getChildren().add(paramsTable);
            }
        }
        ((Group) popupScene.getRoot()).getChildren().addAll(vbox);
        popupStage.setScene(popupScene);
        popupStage.show();
    }

    @FXML
    public void onSymbolTableButtonClick() {
        System.out.println("Show symbol table");
    }

    @FXML
    public void onStatementsButtonClick() {
        System.out.println("Show Instructions");
    }

    private String getErrorMessages(List<Error> errors) {
        StringBuilder buffer = new StringBuilder();
        for (Error error : errors) {
            buffer.append(error.getMessage());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private void initializeFunctionTable(){
        functionTable.setItems(functionList);
        SelectionModel<Production> selectionModel = functionTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedFunction = newSelection;
        });
        defIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        defIdentifier.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        defStartLine.setCellValueFactory(new PropertyValueFactory<>("startLine"));
        defEndLine.setCellValueFactory(new PropertyValueFactory<>("endLine"));
        defReferences.setCellValueFactory(new PropertyValueFactory<>("referencesCounter"));
    }

    private void initializeBlockTable(){
        blockTable.setItems(blockList);
        SelectionModel<Production> selectionModel = blockTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedBlock = newSelection;
        });
        blockIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        blockStartLine.setCellValueFactory(new PropertyValueFactory<>("startLine"));
        blockEndLine.setCellValueFactory(new PropertyValueFactory<>("endLine"));
    }

    private void initializeTokenTable(){
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
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
    }

    @FXML
    protected void onHelpButtonClick() {
        System.out.println("presiono ayuda");
    }

    private String sampleCode =
            """
            # Python program to display the Fibonacci sequence
                                
            def recur_fibonacci(n):
               if n <= 1:
                   return n
               else:
                   return(recur_fibonacci(n-1) + recur_fibonacci(n-2))
            """;
}