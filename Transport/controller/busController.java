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
import services.buses;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class busController {

	Teleportation tele = new Teleportation();

	public static int bus_id;
	public static String bus_name;
	public static String bus_reg;


	@FXML
	Button home = new Button();
	@FXML
	TextField getName = new TextField();
	@FXML
	TextField getModel = new TextField();

	@FXML
	TextField search = new TextField();
	

	@FXML
	private TableView<buses> table = new TableView<>();

	@FXML
	private TableColumn<buses,String> name = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> reg = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> trips = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> earning = new TableColumn<>();

	int check = 0;
	int refresh = 0;


	@FXML
	private void initialize() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		reg.setCellValueFactory(new PropertyValueFactory<>("reg"));
		trips.setCellValueFactory(new PropertyValueFactory<>("trips"));
		earning.setCellValueFactory(new PropertyValueFactory<>("earning"));

		table.setItems(getBus());

		/*
		search thread
		*/
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				ObservableList<buses> bus = FXCollections.observableArrayList();
				bus = getBus();

				while (check != 1) {					

					if (bus.isEmpty() == false) {
						if (search.getText().equals("") == false) {
							updateTable();
						} else {
							table.setItems(bus);
						}						
					}

					if(refresh == 1){
						bus = getBus();
						refresh = 0;
					}

					try{Thread.sleep(500);}catch(Exception e){}				

				}
			}
		});

		t.start();

		/*
		checks double click on table
		*/
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.getClickCount() == 2) {
					if (table.getSelectionModel().getSelectedItem() != null) {

						bus_id = table.getSelectionModel().getSelectedItem().getId();
						bus_name = table.getSelectionModel().getSelectedItem().getName();
						bus_reg = table.getSelectionModel().getSelectedItem().getReg();

						check = 1;

						tele.changeTo(home, "BusUpdate.fxml");
					}
				}
			}
		});
	}



	@FXML
	public void Routing(ActionEvent e) throws Exception {
		Teleportation tele = new Teleportation();
		if (e.getSource() == home) {
			check = 1;
			tele.changeTo(home, "route.fxml");
		}
	}


	@FXML 
	public void formSubmission(ActionEvent e){
		if(getName.getText().equals("") == false){
			if(getModel.getText().equals("") == false){
				InsertData(getName.getText(), getModel.getText());
				getName.setText("");
				getModel.setText("");
			} else {
				tele.pop("Model is required!");	
			}
		} else {
			tele.pop("Name is required!");
		}
	}


	public void InsertData(String n,String m){
		DataAccess da=new DataAccess();
		String q="insert into buses(bus_name,model) values";
		try {
			da.updateDB(q+"('"+n+"','"+m+"')");			
			refresh = 1;
			da.close();
		} catch(SQLException a){
			tele.pop("Error: Please contact developers");		
		}

		tele.pop("Inserted!!!");
	}



	public ObservableList<buses> getBus(){
		ObservableList<buses> bus = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "Select * from buses where active = 1";
		rs = da.getData(query); 

		try{
			while (rs.next()) {
				String name = rs.getString("bus_name");				
				String model = rs.getString("model");
				bus.add(new buses(rs.getInt("bus_id"),name, model, "20", "15000"));				
			}
		} catch(SQLException e){
			tele.pop("Error: Please contact developers");
		}

		return bus;		

	}


	public void updateTable(){
		ObservableList<buses> bus = FXCollections.observableArrayList();

		for (buses b : table.getItems()) {
			if (like(b.getName(), search.getText())  || like(b.getReg(), search.getText())){
				bus.add(new buses(b.getId(),b.getName(),b.getReg(),b.getTrips(),b.getEarning()));
			}

		}

		table.setItems(bus);
	}



	public static boolean like(String str, String expr) {
		expr = expr.toLowerCase(); 
		str = str.toLowerCase();
		return str.matches("(.*)"+expr+"(.*)");
	}


}