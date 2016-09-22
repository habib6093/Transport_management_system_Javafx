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
import javafx.event.EventHandler;
import javafx.scene.input.*;

import services.Teleportation;
import services.DataAccess;


// database
import java.sql.ResultSet;
import java.sql.SQLException;


public class BusUpdate {

	Teleportation tele = new Teleportation();

	@FXML 
	private Button home = new Button();
	
	@FXML
	private TextField name = new TextField();
	@FXML
	private TextField reg = new TextField();


	@FXML 
	private Button update = new Button();
	@FXML 
	private Button delete = new Button();
	@FXML 
	private Button discard = new Button();


	@FXML
	private void initialize(){
		busController bus = new busController();
		this.name.setText(bus.bus_name);
		this.reg.setText(bus.bus_reg);
	}		


	/*
	routes home button to admin section
	*/
	@FXML
	public void Routing (ActionEvent e) throws Exception {
		tele.changeTo(home, "bus.fxml");
	}



	public void formSubmission(ActionEvent e){
		DataAccess da = new DataAccess();
		busController bus = new busController();

		if (e.getSource() == update) {
			if (name.getText().equals("") == false && reg.getText().equals("") == false ) {
				String q = "update buses set bus_name = '"+name.getText()+"', model= '"+reg.getText()+"' where bus_id = "+bus.bus_id;				
				//System.out.println(q);
				da.updateDB(q);
			} else {
				tele.pop("Please fill up all TextFields");
			}
		} else if (e.getSource() == delete) {
			if (checkLegal(bus.bus_id) == true) {
				String q = "update buses set active = 0 where bus_id ="+bus.bus_id;	
				da.updateDB(q);
			} else {
				tele.changeTo(home, "schedule.fxml");
				tele.pop("Cannot be deleted used in a schedule");
			}
		} else if(e.getSource() == discard) {}

		try {
			da.close();						
		} catch(SQLException exp){
			tele.pop("Error: Please contact developers");
		}

		tele.changeTo(home, "bus.fxml");

	}
	

	/*
	checks if any bus is used in any schedule
	*/
	public boolean checkLegal(int bus_id){
		DataAccess da = new DataAccess();
		ResultSet rs = null;

		String q = "select bus_id from schedule where active = 1";
		rs = da.getData(q);

		try {
			while (rs.next()){
				if(bus_id == rs.getInt("bus_id")){
					try {
						da.close();
					} catch(Exception ex){}

					return false;
				} 
			}
		} catch(SQLException exp){
			tele.pop("Error: Please contact developers");
		}

		return true;
	}


}