package services;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Teleportation {

	/*
	function to change scene when button clicked  	
	*/

	public void changeTo(Button button, String to){
		Parent root;
		Stage stage;
		String fxml = "/fxml/"+to;

		try {
			stage = (Stage) button.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(fxml));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
			stage.setScene(scene);		
		} catch(Exception exp){
			System.out.println(exp);
		}
		
	}	

	/*
	creates popup window with parameterized message.
	*/

	public void pop(String message){
		
		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/popup.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			popup p = (popup) fxmlLoader.getController();
			Scene scene = new Scene(root,300,150);

			p.setMessage(message);

			scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Oops!!!");	
			stage.showAndWait();

		} catch(Exception e) {
			System.out.println(e);
		}	
	}


}