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


// date
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class routeController {

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

	int checker = 0;

	@FXML
	private void initialize() {
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
		seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));

		table.setItems(getRoute());

		Thread t = new Thread(new Runnable() {
			public void run(){
				while (checker != 1) {
					//System.out.println("dsasda");
					table.setItems(getRoute());

					try{Thread.sleep(5000);}catch(Exception e){}				
				}
			}
		});

		t.start();


		// handles double click 
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
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ticket.fxml"));
							root = (Parent) fxmlLoader.load();
							TicketController myController = (TicketController) fxmlLoader.getController();
							Scene scene = new Scene(root,1280,720);
							
							//System.out.println(root);
							//System.out.println(scene);

							myController.setScene(scene);
							
							scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
							stage.setScene(scene);	


							//root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));

						} catch(Exception e) {

						}
					}
				}
			}
		});



}




@FXML	
public void Routing (ActionEvent e) throws Exception {
	Parent root;
	Stage stage;

	if(e.getSource() == add){
		checker = 1;
		stage = (Stage) add.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("AddRoute.fxml"));
		Scene scene = new Scene(root,1280,720);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		stage.setScene(scene);		
		} /*else if(e.getSource() == ticket){
			stage = (Stage) ticket.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);	
		}*/ else if(e.getSource() == admin){
			checker = 1;
			stage = (Stage) admin.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);

		} else if(e.getSource() == bus){
			checker = 1;
			stage = (Stage) bus.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("bus.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);

		} else if(e.getSource() == schedule){
			checker = 1;
			stage = (Stage) schedule.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("schedule.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);

		} else if(e.getSource() == settings){
			checker = 1;
			stage = (Stage) settings.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);

		}

	}



	public ObservableList<routeModel> getRoute(){
		ObservableList<routeModel> routes = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;
		ResultSet seats=null;

		String q=new String("SELECT * FROM daily_schedule join schedule on schedule.schedule_id = daily_schedule.schedule_id join routes on schedule.route_id = routes.route_id join buses on schedule.bus_id = buses.bus_id where daily_schedule.active = 1 and daily_schedule.date = ");

		String nm="SELECT daily_schedule_id, COUNT( * ) AS tickets FROM  `daily_ticket` WHERE active =1 GROUP BY  `daily_schedule_id`";


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//System.out.println(q+dateFormat.format(date));
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

					code

				*/

					while(seats.next()){
						if(seats.getString("daily_schedule_id").equals(id)){
							seat = 40 - seats.getInt("tickets");
							break;
					} /*else if(seat == 0){
						seat = 40;
					}*/
				}

				int i = rs.getInt("daily_schedule_id");

				String str = Integer.toString(seat);

				routes.add(new routeModel(i,destination,departs,fare,str,time));
			}
			da.close(); 
		} catch(SQLException a){

		}

		return routes;
	}

}