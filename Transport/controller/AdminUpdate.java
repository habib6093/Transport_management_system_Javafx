package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Teleportation;
import services.DataAccess;

/*
	Update admins

	password can be also changed from here
*/


	public class AdminUpdate {

		Teleportation tele = new Teleportation();


		@FXML 
		private Button home = new Button();

		@FXML 
		private Button update = new Button();
		@FXML 
		private Button delete = new Button();
		@FXML 
		private Button discard = new Button();


		@FXML
		private TextField getname = new TextField();
		@FXML
		private TextField getaddress = new TextField();
		@FXML
		private TextField getPhone = new TextField();


	/*
	sets TextField to the previous data
	*/
	@FXML
	private void initialize(){
		AdminController a = new AdminController();
		this.getname.setText(a.admin_name);
		getname.setDisable(true);
		this.getaddress.setText(a.admin_address);
		this.getPhone.setText(a.admin_phone);
	}


	/*
	routes home button to admin section
	*/
	@FXML
	public void Routing (ActionEvent e) throws Exception {
		if (e.getSource() == home) {
			tele.changeTo(home, "Admin.fxml");	
		}
	}


	/*
	different form submission for update, delete and discard
	point to be noted here that if password field is left blank than previous password will stay.
	but for other textfields the text in the box when pressing update will be updated.
	*/
	public void formSubmission(ActionEvent e){
		Parent root;
		Stage stage;
		AdminController a = new AdminController();
		DataAccess da=new DataAccess();
		
		if(e.getSource() == update) {
			/*
			checks if all the fields are filled or not
			*/
			if(getname.getText().equals("") == false && getPhone.getText().equals("") == false && getaddress.getText().equals("") == false){
				
				String q = "Update admins set phone = '"+getPhone.getText()+"', address ='"+getaddress.getText()+"' where admin_id = "+a.admin_id	;				

				try {
					da.updateDB(q);
					da.close();						
				} catch(SQLException exp){
					tele.pop("Error: Please contact developers");
				}

				tele.changeTo(home, "Admin.fxml");
				tele.pop("Updated!!");

			} else {
				tele.pop("Please fill up all fields");
			}

		/*
		deletes an admin
		changes active state to 0.
		*/	

	} else if(e.getSource() == delete){
		String q = "update admins set active = 0 where admin_id = "+a.admin_id	;				
		System.out.println(q);
		try {
			da.updateDB(q);
			da.close();						
		} catch(SQLException exp){
			tele.pop("Error: Please contact developers");
		}

		tele.changeTo(home, "Admin.fxml");	
		tele.pop("Deleted!!");
		
		/*
		discards any changes
		*/
	} else if(e.getSource() == discard){


		try {
			da.close();						
		} catch(SQLException exp){
			tele.pop("Error: Please contact developers");
		}	
		
		tele.changeTo(home, "Admin.fxml");
	}
}

}