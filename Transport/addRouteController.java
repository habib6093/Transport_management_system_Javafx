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

public class addRouteController {

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


	@FXML
	private void initialize() {
		to.setCellValueFactory(new PropertyValueFactory<>("to"));
		from.setCellValueFactory(new PropertyValueFactory<>("from"));
		trips.setCellValueFactory(new PropertyValueFactory<>("trips"));
		earnings.setCellValueFactory(new PropertyValueFactory<>("earnings"));

		table.setItems(getRoute());
	}

	@FXML	
	public void Routing (ActionEvent e) throws Exception {
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
			table.setItems(getRoute());
			da.close();
		} catch(SQLException a){

		}
	}



	public ObservableList<routes> getRoute(){
		ObservableList<routes> r = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "select * From routes";

		rs = da.getData(query);

		try {
			while (rs.next()) {

				String f = rs.getString("departs");
				String t = rs.getString("destination");

				r.add(new routes(f,t,"700","40"));	

			}
		} catch(SQLException q){

		}
		return r;
	}
}
