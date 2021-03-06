/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package makecleancoder;

import Parser.CleanCoderParser;
import Parser.ParseException;
import GetTextAreaNumber.CleanCoderTextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import AddCommentParser.AddCommentParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author Milk
 */
public class Controller implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    CleanCoderTextArea editArea;

    @FXML
    CleanCoderTextArea consoleArea;
    @FXML
    CleanCoderTextArea lineNumberArea;

    @FXML
    private void fileOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        try {
            String inputAllString;
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String str;
                inputAllString = "";
                while ((str = br.readLine()) != null) {
                    inputAllString += str + "\n";
                }
            }
            editArea.setText(inputAllString);
            int lineNumber = editArea.getLineNumber(editArea.getText());
            String line = "";

            for (int i = 1; i <= lineNumber; i++) {
                line += String.valueOf(i) + "\n";
            }
            lineNumberArea.setText(line);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

    }

    @FXML
    private void fileSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File selectedFile = fileChooser.showSaveDialog(root.getScene().getWindow());
      
        try (FileWriter filewriter = new FileWriter(selectedFile)) {
                filewriter.write(editArea.getText());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @FXML
    private void executeComment(ActionEvent event) {

        String str = extracted();
        String  outPutString = "";
        String result = "";
            try {
                AddCommentParser parser = new AddCommentParser(new StringReader(str));
                result = parser.loopComment();
            } catch (Exception e) {
				e.printStackTrace();
			}
            outPutString = result;
        consoleArea.setText(outPutString);

    }

    @FXML
    private void executeParser(ActionEvent event) {

        String str = extracted();
        String[] strs = str.split("\n");
        String outPutString = "";
        List<String> result = new ArrayList<String>();
        String result2 ="";
        int count = 1;
        for (int i = 0; i < strs.length; i++) {
            try {
                CleanCoderParser parser = new CleanCoderParser(new StringReader(strs[i]));
                result = parser.VariableDeclaration();
                for (int j = 0; j < result.size(); j++) {
                    if (result.get(j).isEmpty()) {
                    } else {
                        outPutString += String.valueOf(count) + ":" + result.get(j) + "\n";
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            count++;
        }

        consoleArea.setText(outPutString);

    }

	private String extracted() {
		String str = editArea.getText();
		return str;
	}

    @FXML
    private void createLineNumber(KeyEvent event) {
        System.out.println(event.getCode().toString());
        
        if ("ENTER".equals(event.getCode().toString())) {
            
            double scrollTop = editArea.getScrollTop(); 
            String string = extracted();
            int lineNumber = editArea.getLineNumber(string);
            String line = "";
            for (int i = 1; i <= lineNumber + 1; i++) {
                line += String.valueOf(i) + "\n";
            }
            lineNumberArea.setText(line);
            editArea.setScrollTop(scrollTop);
        }
    }

    @FXML
    private void clearConsoleArea(ActionEvent event) {
        consoleArea.setText("");
    }

    @FXML
    private void clearEditArea(ActionEvent event) {
        editArea.setText("");
        lineNumberArea.setText("1\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {;

        editArea.scrollTopProperty().bindBidirectional(lineNumberArea.scrollTopProperty());
    }

}
