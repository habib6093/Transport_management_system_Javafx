import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class scheduleController {
	@FXML
	Button home = new Button();

	@FXML
	private TextField getfare = new TextField();
	@FXML
	private TextField gettime = new TextField();

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



	@FXML
	private ComboBox<KeyValuePair> getbus = new ComboBox<KeyValuePair>();
	@FXML
	private ComboBox<KeyValuePair> getroute = new ComboBox<KeyValuePair>();

	@FXML
	private void initialize() {
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));

		table.setItems(getSchedule());
		getroute.setItems(setRoute());
		getbus.setItems(setBus());

	}


	@FXML
	public void Routing(ActionEvent e) throws Exception {
		Parent root;
		Stage stage;
		
		if(e.getSource() == home){
			stage = (Stage) home.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("route.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);		
		} 

	}


	public ObservableList<KeyValuePair> setRoute(){
		ObservableList<KeyValuePair> r = FXCollections.observableArrayList();
		
		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "select * from routes";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				r.add(new KeyValuePair(rs.getInt("route_id"),rs.getString("departs")+" "+rs.getString("destination")));
			}

		}catch(SQLException e){

		}
		return r; 

	}


	public ObservableList<KeyValuePair> setBus(){
		ObservableList<KeyValuePair> r = FXCollections.observableArrayList();
		
		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "select * from buses";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				r.add(new KeyValuePair(rs.getInt("bus_id"), rs.getString("bus_name")));
			}

		}catch(SQLException e){

		}
		return r; 

	}



	public void formSubmission(){
		/*System.out.println(getbus.getValue().getKey());
		System.out.println(getroute.getValue().getKey());*/
		if(getroute.getValue().getKey() != 0 && getbus.getValue().getKey() != 0){
			DataAccess da=new DataAccess();
			String q="insert into schedule(route_id,bus_id,time,fare) values";
			int x = getbus.getValue().getKey();
			int y = getroute.getValue().getKey();

			try {
				da.updateDB(q+"('"+y+"','"+x+"','"+gettime.getText()+"','"+getfare.getText()+"')");			
				table.setItems(getSchedule());
				gettime.setText("");
				getfare.setText("");
				da.close();
			} catch(SQLException a){

			}
		}

	}



	public ObservableList<schedules> getSchedule(){
		ObservableList<schedules> schedule = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "SELECT schedule.time, schedule.fare, routes.destination, routes.departs, buses.bus_name FROM schedule join routes on schedule.route_id = routes.route_id join buses on schedule.bus_id = buses.bus_id";

		rs = da.getData(query);

		try {
			while (rs.next()) {				
				String time = rs.getString("schedule.time");
				String fare = rs.getString("schedule.fare");
				String departs = rs.getString("routes.departs");
				String destination = rs.getString("routes.destination");
				String bus = rs.getString("buses.bus_name");

				schedule.add(new schedules(departs,destination,bus,fare,time));
			}
		} catch(SQLException q){

		}

		/*
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
		schedule.add(new schedules("dhaka", "chitta", "800", "anid", "11.0000"));
*/
		return schedule;
	}
	
}