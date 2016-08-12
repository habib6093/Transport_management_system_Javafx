import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController  {
	@FXML
	Button home = new Button();

	@FXML
	private TableView<Admins> table = new TableView<>();

	@FXML
	private TableColumn<Admins,String> name = new TableColumn<>();
	@FXML
	private TableColumn<Admins,String> phone = new TableColumn<>();
	@FXML
	private TableColumn<Admins,String> address = new TableColumn<>();



	@FXML
	private void initialize() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));

		table.setItems(getAdmin());
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

	public ObservableList<Admins> getAdmin(){
		ObservableList<Admins> admin = FXCollections.observableArrayList();
		

		admin.add(new Admins("Siam", "01555669342", "dbsan njajdnajda dajdhasj djbaida"));	
		admin.add(new Admins("Siam", "01555669342", "dbsan njajdnajda dajdhasj djbaida"));	
		admin.add(new Admins("Siam", "01555669342", "dbsan njajdnajda dajdhasj djbaida"));	

		return admin;
	}


}