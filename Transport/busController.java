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

public class busController {
	@FXML
	Button home = new Button();
	@FXML
	TextField getName = new TextField();
	@FXML
	TextField getModel = new TextField();

	@FXML
	private TableView<buses> table = new TableView<>();

	@FXML
	private TableColumn<buses,String> name = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> id = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> trips = new TableColumn<>();
	@FXML
	private TableColumn<buses,String> earning = new TableColumn<>();

	@FXML
	private void initialize() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		trips.setCellValueFactory(new PropertyValueFactory<>("trips"));
		earning.setCellValueFactory(new PropertyValueFactory<>("earning"));

		table.setItems(getBus());
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


	@FXML 
	public void formSubmission(ActionEvent e){
		if(getName.getText().equals("") == false){
			if(getModel.getText().equals("") == false){
				InsertData(getName.getText(), getModel.getText());
				getName.setText("");
				getModel.setText("");
				//message.setText("");
				//success.setText("Sucess!!");	
				System.out.println("success");
			} else {
				System.out.println("fail");
				//success.setText("");
				//message.setText("*From must be filled");	
			}
		} else {
			System.out.println("fail");
			//success.setText("");
			//message.setText("*To must be filled");
		}
	}


	public void InsertData(String n,String m){
		DataAccess da=new DataAccess();
		String q="insert into buses(bus_name,model) values";
		try {
			da.updateDB(q+"('"+n+"','"+m+"')");			
			table.setItems(getBus());
			da.close();
		} catch(SQLException a){

		}
	}

	public ObservableList<buses> getBus(){
		ObservableList<buses> bus = FXCollections.observableArrayList();
		
		DataAccess da=new DataAccess();
		ResultSet rs=null;
		
		String query = "Select * from buses";
		rs = da.getData(query);

		try{
			while (rs.next()) {
				String name = rs.getString("bus_name");				
				String model = rs.getString("model");
				bus.add(new buses(name, model, "20", "15000"));				
			}
		} catch(SQLException e){

		}

		/*bus.add(new buses("BMW", "123-ddd", "20", "15000"));
		bus.add(new buses("Lamborgini", "123-ddd", "20", "15000"));
		bus.add(new buses("Mercedes", "123-ddd", "20", "15000"));
		bus.add(new buses("Benz", "123-ddd", "20", "15000"));
		bus.add(new buses("SLR Maclaren", "123-ddd", "20", "15000"));*/

		return bus;		
		
	}

	
}