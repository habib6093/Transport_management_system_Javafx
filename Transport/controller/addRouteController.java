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
import services.routes;
import services.DataAccess;
import services.CountRoute;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class addRouteController {

	Teleportation tele = new Teleportation();

	public static int route_id;
	public static String route_to;
	public static String route_from;

	@FXML
	TextField search = new TextField();

	@FXML
	Button home = new Button(); 	
	@FXML
	TextField getto = new TextField();
	@FXML
	TextField getfrom = new TextField();
	@FXML 
	Label message = new Label(); 
	@FXML 
	Label success = new Label();


	@FXML
	public TableView<routes> table = new TableView<>();

	@FXML
	private TableColumn<routes,String> to = new TableColumn<>();
	@FXML
	private TableColumn<routes,String> from = new TableColumn<>();
	@FXML
	private TableColumn<routes,String> trips = new TableColumn<>();
	@FXML
	private TableColumn<routes,String> earnings = new TableColumn<>();

	int check = 0;
	int refresh = 0;

	@FXML
	private void initialize() {
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		trips.setCellValueFactory(new PropertyValueFactory<>("trips"));
		earnings.setCellValueFactory(new PropertyValueFactory<>("earnings"));

		table.setItems(getRoute());


		/*
		search thread
		*/
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				ObservableList<routes> route = FXCollections.observableArrayList();
				route = getRoute();

				while (check != 1) {
					if (route.isEmpty() == false) {
						if (search.getText().equals("") == false) {
							updateTable();
						} else {
							table.setItems(route);
						}
						
					}

					if(refresh == 1){
						route = getRoute();
						refresh = 0;
					}

					try{Thread.sleep(1000);}catch(Exception e){}				
					
				}
			}
		});

		t.start();


		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.getClickCount() == 2) {
					if (table.getSelectionModel().getSelectedItem() != null) {

						route_id = table.getSelectionModel().getSelectedItem().getId();
						route_to = table.getSelectionModel().getSelectedItem().getTo();
						route_from = table.getSelectionModel().getSelectedItem().getFrom();
						check = 1;
						
						tele.changeTo(home, "RouteUpdate.fxml");
					}
				}
			}
		});
	}

	@FXML	
	public void Routing (ActionEvent e) throws Exception {
		check = 1;
		tele.changeTo(home, "route.fxml");
	}


	@FXML
	public void formSubmission(ActionEvent e){
		if(getto.getText().equals("") == false){
			if(getfrom.getText().equals("") == false){
				InsertData(getto.getText(), getfrom.getText());
				getto.setText("");
				getfrom.setText("");
				message.setText("");
				success.setText("Sucess!!");	
			} else {
				success.setText("");
				message.setText("*From must be filled");	
			}
		} else {
			success.setText("");
			message.setText("*To must be filled");
		}
	}


	public void InsertData(String to,String from){
		DataAccess da=new DataAccess();
		String q="insert into routes(departs,destination) values";
		try {
			da.updateDB(q+"('"+to+"','"+from+"')");			
			refresh = 1;
			da.close();
		} catch(SQLException a){
			tele.pop("Error: Please contact developers");
		}
	}



	public ObservableList<routes> getRoute(){
		ObservableList<routes> r = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;
		CountRoute totalRoute = new CountRoute();

		String query = "select * From routes where active = 1";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				int i = rs.getInt("route_id");
				String f = rs.getString("departs");
				String t = rs.getString("destination");
				String total = "" + totalRoute.getTotalRoute(i);
				String fare = "" + totalRoute.getTotalEarning(i);


				r.add(new routes(i,f,t, total,fare));	
			}
		} catch(SQLException q){
			tele.pop("Error: Please contact developers");
		}
		return r;
	}


	public void updateTable(){
		ObservableList<routes> route = FXCollections.observableArrayList();

		for (routes r : table.getItems()) {
			if (like(r.getTo(), search.getText())  || like(r.getFrom(), search.getText())){
				route.add(new routes(r.getId(),r.getTo(),r.getFrom(),r.getTrips(),r.getEarnings()));
			}

		}

		table.setItems(route);
	}



	public static boolean like(String str, String expr) {
		expr = expr.toLowerCase(); 
		str = str.toLowerCase();
		return str.matches("(.*)"+expr+"(.*)");
	}



}


