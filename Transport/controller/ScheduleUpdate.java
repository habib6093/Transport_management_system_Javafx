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
// database
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Teleportation;
import services.KeyValuePair;
import services.DataAccess;

/*
this class inherits schedulecontroller class.
*/

public class ScheduleUpdate extends scheduleController {

	Teleportation tele = new Teleportation();

	@FXML 
	Button home = new Button();

	@FXML 
	Button update = new Button();
	@FXML 
	Button delete = new Button();
	@FXML 
	Button discard = new Button();

	@FXML 
	private TextField gettime = new TextField();
	@FXML 
	private TextField getfare = new TextField();

	/*
	Dropdown box
	*/
	@FXML
	private ComboBox<KeyValuePair> getbus = new ComboBox<KeyValuePair>();
	@FXML
	private ComboBox<KeyValuePair> getroute = new ComboBox<KeyValuePair>();


	/*
	sets TextField to the previous data
	*/
	@FXML
	private void initialize(){
		/*
		here uses the setRoute() and setBus() function from schedulecontroller which gets data from database
		also static vairable schedule_time and schedule_fare is inherited.
		*/
		this.gettime.setText(schedule_time);
		this.getfare.setText(schedule_fare);

		getroute.setItems(setRoute());
		getbus.setItems(setBus());

		/*
		setting combobox to predefiend value
		custom for loop is a feature for observable list
		got from oracle javafx official website
		*/
		ObservableList<KeyValuePair> b = FXCollections.observableArrayList();
		b = setBus();
		for (KeyValuePair a : b) {
			if ( bus_id == a.getKey() ){
				getbus.setValue(a);
				break;	
			} 	
		}


		ObservableList<KeyValuePair> route = FXCollections.observableArrayList();
		route = setRoute();
		for (KeyValuePair x : route) {
			if ( route_id == x.getKey() ){
				getroute.setValue(x);
				break;	
			} 	
		}
	}


	/*
	routes home button to admin section
	*/
	@FXML
	public void Routing (ActionEvent e) throws Exception {
		if (e.getSource() == home) {
			tele.changeTo(home, "schedule.fxml");
		}
	}


	public void formSubmission(ActionEvent e){
		DataAccess da = new DataAccess();

		if (e.getSource() == update) {
			if (gettime.getText().equals("") == false && getfare.getText().equals("") == false) {
				String q = "update schedule set route_id = "+getroute.getValue().getKey()+", bus_id =" + getbus.getValue().getKey()+ ", time = '"+gettime.getText()+"', fare ="+getfare.getText()+" where schedule_id = "+schedule_id;
				da.updateDB(q);
				tele.pop("Updated!");
			} else {
				tele.pop("Please fill up all TextFields");
			} 
		} else if (e.getSource() == delete) {
			String q = "update schedule set active = 0 where schedule_id = "+schedule_id;
			da.updateDB(q);			
		} else if( e.getSource() == discard){}

		try {
			da.close();
		} catch(Exception exp){}

		tele.changeTo(home, "schedule.fxml");
	}

}	