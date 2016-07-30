import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addRouteController {
	@FXML
	Button add = new Button(); 	
	@FXML	
	Button ticket = new Button();
	@FXML
	Button admin = new Button();
	
	@FXML
	TextField to = new TextField();

	@FXML
	TextField from = new TextField();

	@FXML
	TextField fare = new TextField();

	@FXML
	TextField seats = new TextField();

	@FXML 
	Label message = new Label(); 

	@FXML 
	Label success = new Label();

	@FXML	
	public void Routing (ActionEvent e) throws Exception {
		Parent root;
		Stage stage;
		
		if(e.getSource() == add){
		        stage = (Stage) add.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("route.fxml"));
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

	@FXML
	public void formSubmission(ActionEvent e){
		if(to.getText().equals("") == false){
			if(from.getText().equals("") == false){
				System.out.println(to.getText());
				System.out.println(from.getText());
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
}
