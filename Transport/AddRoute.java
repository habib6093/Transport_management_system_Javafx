import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AddRoute extends Application {
    public static void main(String args[]){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("route.fxml"));
        Scene scene = new Scene(root,1280,720);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

	primaryStage.setMaxWidth(1280);        
    	primaryStage.setMaxHeight(720);
	primaryStage.setResizable(false);

        primaryStage.setScene(scene);
   	//primaryStage.setFullScreen(true);  
	primaryStage.show();	
    }
	/*Button btnscene1,btnscene2;
	Label lblscene1, lblscene2; 
	FlowPane pane1,pane2;
	Scene scene1,scene2;
	Stage theStage;

	@Override 
	public void start(Stage primaryStage){
		theStage = primaryStage;
		btnscene1 = new Button("Click to go to the nxt page");
		btnscene2 = new Button("Click to go back");
		btnscene1.setOnAction(e->ButtonClicked(e));
		btnscene1.setOnAction(e->ButtonClicked(e));
		lblscene1 = new Label("Scene 1");
		lblscene2 = new Label("Scene 2");
		
		pane1 = new FlowPane();
		pane2 = new FlowPane();
		
		pane1.setVgap(10);
		pane2.setVgap(10);
	
		pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
		pane2.setStyle("-fx-background-color: red;-fx-padding: 10px;");

		pane1.getChildren().addAll(lblscene1,btnscene1);
		pane2.getChildren().addAll(lblscene2,btnscene2);	
		scene1 = new Scene(pane1, 200, 100);
		scene2 = new Scene(pane2, 200, 100);
		
		primaryStage.setTitle("Hello World");
		primaryStage.setTitle(scene1);
		primaryStage.show();	
	}

	public void ButtonClicked(ActionEvent e){
		if(e.getSource() == btnscene1){
			thestage.setScene(scene2);
		} else {
			thestage.setScene(scene1);
		}
	} */

	
}	
