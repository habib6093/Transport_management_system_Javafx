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
import javafx.scene.input.*;
import javafx.event.EventHandler;

import services.Teleportation;
import services.DataAccess;
import services.routeModel;

//imports the main login file to 
// get the login info
import services.login; 

// date
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class routeController {

	Teleportation tele = new Teleportation();

	/*
	acts similer to session variable 
	takes these data to next page
	*/
	public static int daily_schedule_id; 
	public static String schedule_to; 
	public static String schedule_from; 
	public static String schedule_fare; 

	public static Scene schedule_scene;

	@FXML
	Button add = new Button(); 	
	/*@FXML	
	Button ticket = new Button();*/
	@FXML
	Button admin = new Button();
	@FXML
	Button schedule = new Button();
	@FXML
	Button bus = new Button();
	@FXML
	Button settings = new Button();
	@FXML
	Button logout = new Button();
	@FXML
	Button create = new Button();
	@FXML
	Button statistics = new Button();
	

	@FXML
	TextField search = new TextField();

	@FXML
	private TableView<routeModel> table = new TableView<>();

	@FXML
	private TableColumn<routeModel,String> to = new TableColumn<>();
	@FXML
	private TableColumn<routeModel,String> from = new TableColumn<>();
	@FXML
	private TableColumn<routeModel,String> fare = new TableColumn<>();
	@FXML
	private TableColumn<routeModel,String> seat = new TableColumn<>();
	@FXML
	private TableColumn<routeModel,String> time = new TableColumn<>();

	/*
	checks when thread will stop
	*/
	int checker = 0;

	@FXML
	private void initialize() {
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
		seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));

		table.setItems(getRoute());


		/*
		new thread to check update in table
		calls getRoute()
		*/
		Thread t = new Thread(new Runnable() {
			@Override
			public void run(){
				while (checker != 1) {
					
					ObservableList<routeModel> routes = FXCollections.observableArrayList();
					routes = getRoute();

					table.setItems(routes);


					/*	
					checks if the table is empty
					if empty then a button will come to refresh the page
					calls get() function
					*/

					try {
						if(routes.isEmpty() && search.getText().equals("") ){
							create.setDisable(false);

						} else {
							create.setDisable(true);
						}
					} catch(Exception exp){

					} 

					/*
					if the searchbox is filled then the table will update accordingly
					*/ 
					if (routes.isEmpty() == false) {
						if (search.getText().equals("") == false) {
							updateTable();
						} else {
							table.setItems(routes);
						}						
					}


					try{Thread.sleep(1000);}catch(Exception e){}				
				}
			}
		});

		t.start();




		/*
		handles double click 
		*/
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.getClickCount() == 2) {
					if (table.getSelectionModel().getSelectedItem() != null) {

						schedule_to = table.getSelectionModel().getSelectedItem().getTo();
						schedule_from = table.getSelectionModel().getSelectedItem().getFrom();
						daily_schedule_id = table.getSelectionModel().getSelectedItem().getId();
						schedule_fare = table.getSelectionModel().getSelectedItem().getFare();

						try {
							Parent root = null;
							Stage stage = null;
							checker = 1;

							stage = (Stage) table.getScene().getWindow();
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Ticket.fxml"));
							root = (Parent) fxmlLoader.load();
							TicketController myController = (TicketController) fxmlLoader.getController();
							Scene scene = new Scene(root,1280,720);

							myController.setScene(scene);

							scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
							stage.setScene(scene);	

						} catch(Exception e) {

						}
					}
				}
			}
		});
	}


	/*
	routes app to different pages
	*/

	@FXML	
	public void Routing (ActionEvent e) {
		if(e.getSource() == add){
			checker = 1;
			tele.changeTo(add, "AddRoute.fxml");	
		} else if(e.getSource() == logout){
			Parent root;
			Stage stage;

			stage = (Stage) admin.getScene().getWindow();
			login l = new login();
			l.name = ""; 
			l.pass = ""; 
			l.start(stage);

		} else if(e.getSource() == admin){
			checker = 1;
			tele.changeTo(admin, "Admin.fxml");

		} else if(e.getSource() == bus){
			checker = 1;
			tele.changeTo(bus, "bus.fxml");

		} else if(e.getSource() == schedule){
			checker = 1;
			tele.changeTo(schedule, "schedule.fxml");

		} else if(e.getSource() == settings){
			checker = 1;
			tele.changeTo(settings, "Settings.fxml");
		} else if(e.getSource() == statistics){
			checker = 1;
			tele.changeTo(statistics, "owner.fxml");
			//owner o = new owner();
			//o.start();
		}	

	}


	/*
	geta all the routes from database and returns observable data for table 
	another thread calls this function periodically to check if any update occured in the table
	*/
	public ObservableList<routeModel> getRoute(){
		ObservableList<routeModel> routes = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;
		ResultSet seats=null;

		String q=new String("SELECT * FROM daily_schedule join schedule on schedule.schedule_id = daily_schedule.schedule_id join routes on schedule.route_id = routes.route_id join buses on schedule.bus_id = buses.bus_id where daily_schedule.active = 1 and daily_schedule.date = ");

		String nm="SELECT daily_schedule_id, COUNT( * ) AS tickets FROM  `daily_ticket` WHERE active =1 GROUP BY  `daily_schedule_id`";


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String star = "'";
		rs=da.getData(q+star+dateFormat.format(date)+star);
		seats = da.getData(nm); 


		try {
			while(rs.next()){
				String id = rs.getString("daily_schedule_id");
				String destination = rs.getString("destination");
				String departs = rs.getString("departs");
				String fare = rs.getString("fare");
				String time = rs.getString("time");
				int seat = 40;

				/*
				matches number of seats to its schedule	
				*/				
				while(seats.next()){
					if(seats.getString("daily_schedule_id").equals(id)){
						seat = 40 - seats.getInt("tickets");
						break;
					} 
				}

				seats.beforeFirst();		
				int i = rs.getInt("daily_schedule_id");

				String str = Integer.toString(seat);

				routes.add(new routeModel(i,destination,departs,fare,str,time));
			}

		} catch(SQLException a){
			System.out.println(a);			
		}
		
		/* 
		confirming that db closes 
		*/
		finally {
			try {
				da.close();
			} catch(SQLException exp){

			}
		}  
		return routes;
	}

	/*
	this function fresh starts today's schedule. 	
	*/
	public void get(){
		String q = "insert into daily_schedule(schedule_id, date) select schedule_id,curdate() from schedule where active = 1";
		DataAccess db = new DataAccess();
		db.updateDB(q);
		table.setItems(getRoute());

		try {
			db.close();
		} catch(Exception exp){

		}
	}


	/*
	updates table according to user input	
	*/
	public void updateTable(){
		ObservableList<routeModel> routes = FXCollections.observableArrayList();

		for (routeModel t : table.getItems()) {
			if (like(t.getTo(),search.getText()) || like(t.getFrom(),search.getText()) || like(t.getTime(),search.getText())){
				routes.add(new routeModel(t.getId(),t.getTo(),t.getFrom(),t.getFare(),t.getSeat(),t.getTime()));
			}

		}

		table.setItems(routes);
	}


	/*
	simulates sql LIKE 
	*/
	public static boolean like(String str, String expr) {
		expr = expr.toLowerCase(); 
		str = str.toLowerCase();
		return str.matches("(.*)"+expr+"(.*)");
	}

}