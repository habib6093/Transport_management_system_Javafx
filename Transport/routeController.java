import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class routeController {
	@FXML
	Button add = new Button(); 	
	@FXML	
	Button ticket = new Button();
	@FXML
	Button admin = new Button();
	
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


	@FXML
    private void initialize() {
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
    
        table.setItems(getRoute());
    }


	@FXML	
	public void Routing (ActionEvent e) throws Exception {
		Parent root;
		Stage stage;
		
		if(e.getSource() == add){
		        stage = (Stage) add.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("AddRoute.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);		
		} else if(e.getSource() == ticket){
			stage = (Stage) ticket.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);	
		} else if(e.getSource() == admin){
			System.out.println("More to be added");
		}
		
	}



	public ObservableList<routeModel> getRoute(){
		ObservableList<routeModel> routes = FXCollections.observableArrayList();
		
		routes.add(new routeModel("Dhaka","Chittagong","700","40","11:00Am"));
		routes.add(new routeModel("Dhaka","Rajshahi","550","20","12:00Am"));
		routes.add(new routeModel("Dhaka","Cox-Bazar","800","40","1:00Pm"));
		routes.add(new routeModel("Chittagong","Dhaka","700","01","4:00Am"));
		routes.add(new routeModel("Chittagong","Cox-Bazar","700","01","4:00Am"));
		routes.add(new routeModel("Dhaka","Chittagong","700","40","11:00Am"));
		routes.add(new routeModel("Dhaka","Rajshahi","550","20","12:00Am"));
		routes.add(new routeModel("Dhaka","Cox-Bazar","800","40","1:00Pm"));
		routes.add(new routeModel("Dhaka","Chittagong","700","40","11:00Am"));
		routes.add(new routeModel("Dhaka","Rajshahi","550","20","12:00Am"));
		routes.add(new routeModel("Dhaka","Cox-Bazar","800","40","1:00Pm"));
		return routes;
	}
}
