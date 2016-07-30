import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicketController {
	/*@FXML
	Button add = new Button(); */	
	@FXML
	Button routes = new Button(); 	
	@FXML	
	Button online = new Button();
	@FXML
	Button admin = new Button();
	
	@FXML
	TextField name = new TextField();
	@FXML
	TextField phone = new TextField();
	@FXML
	TextField to = new TextField();
	@FXML
	TextField from = new TextField();
	@FXML
	TextField fare = new TextField();

	@FXML
	Label message = new Label();

	@FXML
	Label success = new Label();

	@FXML	
	public void Routing (ActionEvent e) throws Exception {
		Parent root;
		Stage stage;
		/*
		if(e.getSource() == add){
		        stage = (Stage) add.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("AddRoute.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);		
		} else*/ if(e.getSource() == routes){
			stage = (Stage) routes.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("route.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);	
		} else if(e.getSource() == online){
			System.out.println("online ticket will be added latar");
		} else if(e.getSource() == admin){
			System.out.println("More to be added");
		}
		
	}

	public void formSubmission(ActionEvent event){
		if(name.getText().equals("") == false){
			if(phone.getText().equals("") == false){
				System.out.println(name.getText());
				System.out.println(phone.getText());
				message.setText("");
				success.setText("Success!!");
			} else {
				success.setText("");
				message.setText("*phone number required");
			}
		} else {
			success.setText("");
			message.setText("*Name required");
		}	 	

	}
}
