package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

import services.Teleportation;
import services.DataAccess;

import services.login; 

// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsController {
	private Teleportation tele = new Teleportation();

	private String pass;

	@FXML
	Button home = new Button();
	@FXML
	Button cancel = new Button();

	@FXML
	Label admin = new Label();


	@FXML
	PasswordField previous = new PasswordField();
	@FXML
	PasswordField password = new PasswordField();
	@FXML
	PasswordField confirm = new PasswordField();
	
	@FXML
	private void initialize(){
		login l = new login();
		admin.setText("Logged in as: "+l.name);
		pass = l.pass;
	}


	@FXML
	public void Routing(ActionEvent e){
		if(e.getSource() == home){
			tele.changeTo(home,"route.fxml");	
		} else if (e.getSource() == cancel) {
			tele.changeTo(cancel,"route.fxml");	
		} 

	}


	/*
	admin can only change address and phone number of other admins
	*/

	@FXML
	public void check(ActionEvent e){
		login l = new login();

		if(previous.getText().equals("") == false && password.getText().equals("") == false && confirm.getText().equals("") == false){
			if(previous.getText().equals(l.pass) && password.getText().equals(confirm.getText())){
				String q = "update admins set password = '"+ password.getText()+"' where admin_id = "+l.admin_id;
				DataAccess da=new DataAccess();
				da.updateDB(q);
				try {
					da.close();
				} catch(Exception ex){}

				tele.changeTo(home, "route.fxml");

			} else {
				tele.pop("Wrong password");
			}
		} else {
			tele.pop("Please fill up everything");
		}


	}	
}