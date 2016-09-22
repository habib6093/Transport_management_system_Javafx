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

	Updates and delete routes  

*/

	public class RouteUpdate {

		/*
		used to change other scene.
		consists of two parameters. 
		a button reference and the String name fxml file 
		*/
		private Teleportation t = new Teleportation();


		@FXML 
		private Button home = new Button();

		@FXML 
		private Button update = new Button();
		@FXML 
		private Button delete = new Button();
		@FXML 
		private Button discard = new Button();


		@FXML
		private TextField to = new TextField();
		@FXML
		private TextField from = new TextField();

		
		@FXML
		private void initialize(){
			addRouteController a = new addRouteController();
			this.to.setText(a.route_to);
			this.from.setText(a.route_from);
		}


		@FXML
		public void Routing (ActionEvent e) throws Exception {
			if(e.getSource() == home){
				t.changeTo(home, "AddRoute.fxml");				
			}

		}


		public void formSubmission(ActionEvent e){
			DataAccess da = new DataAccess();
			addRouteController a = new addRouteController();

			if (e.getSource() == update) {
				if (to.getText().equals("") == false && from.getText().equals("") == false) {
					/*
					updates data in database
					*/
					String q = "update routes set destination = '"+from.getText()+"', departs ='"+to.getText()+"' where route_id="+a.route_id;	
					da.updateDB(q);

				} else {
					t.pop("Please fill up all TextFields");
				}	
			} else if (e.getSource() == delete) {
				if (checkLegal(a.route_id) == true) {
					String q = "update routes set active = 0 where route_id = "+ a.route_id;	
					da.updateDB(q);
				} else {
					t.pop("This route is used in a schedule");
					t.changeTo(home, "schedule.fxml");					

				}
			} else if (e.getSource() == discard){}

			try{ 
				da.close();
			} catch(SQLException ex){}	

			t.changeTo(home, "AddRoute.fxml");		
		}



		/*
		if any route which must be deleted is being used in any schedule it cannot be deleted.
		*/
		public boolean checkLegal(int route_id){
			DataAccess da = new DataAccess();
			ResultSet rs = null;

			String q = "select route_id from schedule where active = 1";
			rs = da.getData(q);

			try {
				while (rs.next()){
					if(route_id == rs.getInt("route_id")){
						try {
							da.close();
						} catch(Exception ex){}

						return false;
					} 
				}
			} catch(SQLException exp){}

			return true;
		} 




	}