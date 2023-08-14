package com.lfp.py_lite_compiler;

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

    @FXML private TextArea srcCode;
    @FXML private TextArea errorOutput;
    private final ObservableList<Token> tokenList = FXCollections.observableArrayList();
    @FXML private TableView<Token> tokenTable;
    @FXML private TableColumn<Token, String> nameColumn, patternColumn, colColumn, lineColumn, lexemeColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tokenTable.setItems(tokenList);
        nameColumn.setCellValueFactory(data -> data.getValue().getTokenType().nameProperty());
        patternColumn.setCellValueFactory(data -> data.getValue().getTokenType().patternProperty());
        lexemeColumn.setCellValueFactory(new PropertyValueFactory<>("lexeme"));
        lineColumn.setCellValueFactory(new PropertyValueFactory<>("line"));
        colColumn.setCellValueFactory(new PropertyValueFactory<>("column"));
    }

    public void addToTokenList(Token token){
        tokenList.add(token);
    }

    @FXML
    protected void onRunButtonClick() {
        System.out.println("presiono correr");
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
}