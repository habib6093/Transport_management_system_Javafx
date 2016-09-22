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
import services.DataAccess;
import services.KeyValuePair;
import services.schedules;


public class scheduleController {

	Teleportation tele = new Teleportation();

	@FXML
	Button home = new Button(); 			// Home Button. takes the user to home


	public static String schedule_time;
	public static String schedule_fare;
	public static int route_id;
	public static int bus_id;
	public static int schedule_id;


	/*
	Add new schedule form 
	*/	
	@FXML
	private TextField getfare = new TextField();	
	@FXML
	private TextField gettime = new TextField();
	@FXML
	private TextField search = new TextField();

	/*
	table column and view
	takes schedule type data which in turn is a string data
	*/
	@FXML
	private TableView<schedules> table = new TableView<>();

	@FXML
	private TableColumn<schedules,String> to = new TableColumn<>();
	@FXML
	private TableColumn<schedules,String> from = new TableColumn<>();
	@FXML
	private TableColumn<schedules,String> fare = new TableColumn<>();
	@FXML
	private TableColumn<schedules,String> name = new TableColumn<>();
	@FXML
	private TableColumn<schedules,String> time = new TableColumn<>();


	/*
	Dropdown box
	*/
	@FXML
	private ComboBox<KeyValuePair> getbus = new ComboBox<KeyValuePair>();
	@FXML
	private ComboBox<KeyValuePair> getroute = new ComboBox<KeyValuePair>();


	int check = 0;
	int refresh = 0;

	
	@FXML
	private void initialize() {

		/*
		initializing table 
		*/
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));

		table.setItems(getSchedule());

		/*
		initializing combobox from database
		*/
		getroute.setItems(setRoute());
		getbus.setItems(setBus());


		/*
		search thread
		*/
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				ObservableList<schedules> sch = FXCollections.observableArrayList();
				sch = getSchedule();

				while (check != 1) {					

					if (sch.isEmpty() == false) {
						if (search.getText().equals("") == false) {
							updateTable();
						} else {
							table.setItems(sch);
						}						
					}

					if(refresh == 1){
						sch = getSchedule();
						refresh = 0;
					}

					try{Thread.sleep(500);}catch(Exception e){}				

				}
			}
		});

		t.start();

		/*
		detects double click
		*/ 
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.getClickCount() == 2) {
					if (table.getSelectionModel().getSelectedItem() != null) {

						schedule_id = table.getSelectionModel().getSelectedItem().getScheduleId();
						route_id = table.getSelectionModel().getSelectedItem().getRouteId();
						bus_id = table.getSelectionModel().getSelectedItem().getBusId();
						schedule_fare = table.getSelectionModel().getSelectedItem().getFare();
						schedule_time = table.getSelectionModel().getSelectedItem().getTime();

						check = 1;
						tele.changeTo(home, "ScheduleUpdate.fxml");
					}
				}
			}
		});


		/*
		Code used and modified from StackOverflow
		credit : http://stackoverflow.com/questions/13362607/combobox-jump-to-typed-char	

		setting key handler to getbus and getroute combobox
		*/
		getbus.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyValuePair s = jumpTo(event.getText(), getbus.getValue(), getbus.getItems());
				if (s != null) {
					getbus.setValue(s);
				}
			}
		});



		getroute.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyValuePair s = jumpTo(event.getText(), getroute.getValue(), getroute.getItems());
				if (s != null) {
					getroute.setValue(s);
				}
			}
		});
	}

	/*
	takes the key pressed as input and compares them to the values of the combobox
	matched value is set. 
	*/
	static KeyValuePair jumpTo(String keyPressed, KeyValuePair currentlySelected, ObservableList<KeyValuePair> items) {
		String key = keyPressed.toUpperCase();

		if (key.matches("^[A-Z]$")) {
			boolean letterFound = false;
			boolean foundCurrent = currentlySelected == null;
			for (KeyValuePair s : items) {
				if (s.toString().toUpperCase().startsWith(key)) {
					return s;
				}
			}
		}
		return null;
	}




	/*
	routing for the home button 
	*/
	@FXML
	public void Routing(ActionEvent e) throws Exception {
		if (e.getSource() == home) {
			check = 1;
			tele.changeTo(home, "route.fxml");
		}
	}



	/*
	setting up the routes combobox. key holds the id of route which after selecting will go to the database
	*/
	public ObservableList<KeyValuePair> setRoute(){

		ObservableList<KeyValuePair> r = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "select * from routes where active = 1";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				r.add(new KeyValuePair(rs.getInt("route_id"),rs.getString("departs")+" "+rs.getString("destination")));
			}

		}catch(SQLException e){
			tele.pop("Error: Please contact developers");
		}
		return r; 

	}



	/*
	setting up the choose bus combobox from database 
	*/
	public ObservableList<KeyValuePair> setBus(){
		ObservableList<KeyValuePair> r = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "select * from buses where active = 1";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				r.add(new KeyValuePair(rs.getInt("bus_id"), rs.getString("bus_name")));
			}

		}catch(SQLException e){

		}
		return r; 

	}


	/*
	checks for proper form fillup and  inserting to database. 
	*/
	public void formSubmission(){
		if(getroute.getValue().getKey() != 0 && getbus.getValue().getKey() != 0){
			DataAccess da=new DataAccess();
			String q="insert into schedule(route_id,bus_id,time,fare) values";
			int x = getbus.getValue().getKey();
			int y = getroute.getValue().getKey();

			try {
				da.updateDB(q+"('"+y+"','"+x+"','"+gettime.getText()+"','"+getfare.getText()+"')");			
				gettime.setText("");
				getfare.setText("");

				refresh = 1;
				
				tele.pop("Success!!");

				da.close();
			} catch(SQLException a){
				tele.pop("Error: Please contact developers");
			}
		}

	}


	/*
	gets schedules from database and converting them to ObservableList.
	returns observable schedule data to table.
	*/
	public ObservableList<schedules> getSchedule(){
		ObservableList<schedules> schedule = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "SELECT routes.route_id,schedule.schedule_id,buses.bus_id,schedule.time,schedule.fare,routes.departs,routes.destination,buses.bus_name FROM schedule join routes on schedule.route_id = routes.route_id join buses on schedule.bus_id = buses.bus_id where schedule.active = 1";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				int route_id = rs.getInt("routes.route_id");				
				int schedule_id = rs.getInt("schedule.schedule_id");				
				int bus_id = rs.getInt("buses.bus_id");	

				String time = rs.getString("schedule.time");
				String fare = rs.getString("schedule.fare");
				String departs = rs.getString("routes.departs");
				String destination = rs.getString("routes.destination");
				String bus = rs.getString("buses.bus_name");

				schedule.add(new schedules(route_id,bus_id,schedule_id,departs,destination,bus,fare,time));
			}
		} catch(SQLException q){

		}

		return schedule;
	}



	/*
	matches search box entry with the schedule table 
	*/
	public void updateTable(){
		ObservableList<schedules> sch = FXCollections.observableArrayList();

		for (schedules b : table.getItems()) {
			if (like(b.getTo(), search.getText())  || like(b.getFrom(), search.getText()) || like(b.getName(), search.getText())){
				sch.add(new schedules(b.getRouteId(),b.getBusId(),b.getScheduleId(),b.getTo(),b.getFrom(),b.getName(), b.getFare(), b.getTime()));
			}

		}

		table.setItems(sch);
	}


	/*
	following function works similer to sql like  
	*/
	public static boolean like(String str, String expr) {
		expr = expr.toLowerCase(); 
		str = str.toLowerCase();
		return str.matches("(.*)"+expr+"(.*)");
	}



}