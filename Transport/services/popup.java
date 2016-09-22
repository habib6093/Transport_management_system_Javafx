package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.event.EventHandler;

import javafx.application.Application;


public class popup {
	@FXML
	Label titlePop = new Label();

	public void setMessage(String s){
		titlePop.setText(s);
		System.out.println(titlePop.getText());
	}

	public void exit(){
		Stage stage=(Stage) titlePop.getScene().getWindow();
		stage.close();
	}

}
