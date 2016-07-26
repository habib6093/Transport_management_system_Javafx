import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class routeController {
	@FXML	
	Button btn = new Button();
	
	@FXML
	public void print(ActionEvent event){
		System.out.println("Transport");
	}
    
}
