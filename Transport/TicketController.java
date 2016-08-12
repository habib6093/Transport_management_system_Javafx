import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketController {
	@FXML
	AnchorPane anchor; 	

	public Scene scene;


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

	ArrayList<String> seatId = new ArrayList<String>();
	ArrayList<String> seatQuery = new ArrayList<String>();
	String s = new String("-fx-padding: 2 5 2 5;-fx-border-color: white;-fx-border-width: 2;-fx-background-radius: 0;-fx-background-color: #32CD32;-fx-font-family: Helvetica, Arial, sans-serif;-fx-font-size: 8pt;-fx-font-weight: bold;-fx-text-fill: white;-fx-background-insets: 0 0 0 0, 0, 1, 2;");
	int checker;
	Another a;

	private int daily_schedule_id; 
	routeController r;
	
	


	@FXML
	private void initialize() {
		this.daily_schedule_id = r.daily_schedule_id;

		to.setText(r.schedule_to);
		to.setDisable(true);

		from.setText(r.schedule_from);
		from.setDisable(true);

		fare.setText(r.schedule_fare);
		fare.setDisable(true);

	}

	public void setScene(Scene scene){
		this.scene = scene;
		checkTicket(scene);

		Thread t = new Thread(new Runnable() {
			public void run(){
				while (checker != 1) {
					checkTicket(scene);	
				}
			}
		});

		t.start();
	}


	public void checkTicket(Scene scene){

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String q=new String("select * from daily_ticket where daily_schedule_id = "+daily_schedule_id);


		try {
			rs=da.getData(q);

			while(rs.next()){
				int id = rs.getInt("daily_schedule_id");
				String tick = rs.getString("ticket_id");

				Button tb = (Button) scene.lookup("#"+tick);
				tb.setDisable(true);
				//tb.
				//System.out.println(id);
				//System.out.println("#"+tick);
			}
			da.close();  
		} catch(SQLException e){

		}
	}



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
			/*a.stop();*/
			checker = 1;
			stage = (Stage) routes.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("route.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			stage.setScene(scene);	
		} else if(e.getSource() == online){
			/*a.stop();*/
			checker = 1;
			System.out.println("online ticket will be added latar");
		} else if(e.getSource() == admin){
			/*a.stop();*/
			checker = 1;
			System.out.println("More to be added");
		}
		
	}

	public void formSubmission(ActionEvent event){
		if(name.getText().equals("") == false && isSelected()){
			if(phone.getText().equals("") == false){
				System.out.println(name.getText());
				System.out.println(phone.getText());
				/*a.stop();*/
				checker = 1;
				insertDB();
				try {
					Parent root = null;
					Stage stage = null;
					Button btn = (Button) event.getSource();
					stage = (Stage) btn.getScene().getWindow();

					root = FXMLLoader.load(getClass().getResource("route.fxml"));
					Scene scene = new Scene(root,1280,720);
					scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
					stage.setScene(scene);		
				} catch(Exception e){

				}
				
				/*message.setText("");
				success.setText("Success!!");*/
			} else {
				success.setText("");
				message.setText("*phone number required");
			}
		} else {
			success.setText("");
			message.setText("*Name required");
		}	 	

	}


	public void insertDB(){
		for(int i=0;i<seatQuery.size();i++){
			s=(String)seatQuery.get(i);
			DataAccess da=new DataAccess();

			try {
				da.updateDB(s);
				da.close();

			} catch(SQLException a){

			}
		}	

	}


	@FXML 
	public void seatSelect(ActionEvent e){

		Button btn = (Button) e.getSource();
		String id = btn.getId();

		if(seatId.contains(id)){
			seatQuery.remove(seatId.indexOf(id));
			seatId.remove(id);
			btn.setStyle(s);

			//print(seatId);
			//print(seatQuery);

		} else {
			seatId.add(id);
			String q= new String("insert into daily_ticket(daily_schedule_id,ticket_id) values"+"("+daily_schedule_id+","+id+")");
			seatQuery.add(q);
			btn.setStyle("-fx-background-color: red; -fx-text-fill: white;");

			//print(seatId);
			//print(seatQuery);
		}
		//int i = Integer.parseInt(id);
		//DataAccess da=new DataAccess();


		/*try {
			da.updateDB(q+"("+daily_schedule_id+","+i+")");

			da.close();
		} catch(SQLException a){

		}*/

	}

	public void print(ArrayList ar){
		for(int i=0;i<ar.size();i++){
			s=(String)ar.get(i);
			System.out.println(s);
		}
	}


	public boolean isSelected(){
		if(seatId.size() > 0){
			return  true;
		} else {
			return false;
		}
	}

}