package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.scene.input.*;

import services.Teleportation;
import services.Admins;
import services.DataAccess;



// database
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminController  {
	
	Teleportation tele = new Teleportation();


	@FXML
	Button home = new Button();



	/*
	Acts as a session variable so that other classes can access them 
	*/
	public static int admin_id; 
	public static String admin_name; 
	public static String admin_address; 
	public static String admin_phone;

	/*
	table structure
	*/
	@FXML
	private TableView<Admins> table = new TableView<>();

	@FXML
	private TableColumn<Admins,String> name = new TableColumn<>();
	@FXML
	private TableColumn<Admins,String> phone = new TableColumn<>();
	@FXML
	private TableColumn<Admins,String> address = new TableColumn<>();

	/*
	TextFields for add admin
	*/
	@FXML
	private TextField getname = new TextField();
	@FXML
	private TextField getaddress = new TextField();
	@FXML
	private TextField getPhone = new TextField();
	@FXML
	private PasswordField getPass = new PasswordField();
	@FXML
	private TextField search = new TextField();

	int check = 0;
	int refresh = 0;

	@FXML
	private void initialize() {
		/*
		initializes table
		*/
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));

		table.setItems(getAdmin());


		/*
		search thread
		*/
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				ObservableList<Admins> admin = FXCollections.observableArrayList();
				admin = getAdmin();

				while (check != 1) {
					if (admin.isEmpty() == false) {
						if (search.getText().equals("") == false) {
							updateTable();
						} else {
							table.setItems(admin);
						}						
					}

					/*
					when a new entry in entered the table reloads
					*/
					if(refresh == 1){
						admin = getAdmin();
						refresh = 0;
					}

					try{Thread.sleep(500);}catch(Exception e){}				

				}
			}
		});

		t.start();



		/*
		checks double click on table
		*/
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.getClickCount() == 2) {
					if (table.getSelectionModel().getSelectedItem() != null) {

						admin_id = table.getSelectionModel().getSelectedItem().getId();
						admin_name = table.getSelectionModel().getSelectedItem().getName();
						admin_phone = table.getSelectionModel().getSelectedItem().getPhone();
						admin_address = table.getSelectionModel().getSelectedItem().getAddress();
						check = 1;	
						tele.changeTo(home, "AdminUpdate.fxml");
					}
				}
			}
		});
	}



	/*
	routing for home button
	*/
	@FXML
	public void Routing(ActionEvent e) throws Exception {
		if (e.getSource() == home) {
			check = 1;
			tele.changeTo(home, "route.fxml");
		}
	}


	/*
	gets admin data from database converts them to observable 
	returns to the table 
	*/
	public ObservableList<Admins> getAdmin(){
		ObservableList<Admins> admin = FXCollections.observableArrayList();

		DataAccess da=new DataAccess();
		ResultSet rs=null;

		String query = "SELECT * from admins where active = 1";

		rs = da.getData(query);

		try {
			while (rs.next()) {
				int id = rs.getInt("admin_id");				
				String name = rs.getString("admin_name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");

				admin.add(new Admins(id,name,phone,address));
			}
		} catch(SQLException q){
			tele.pop("Error: Please contact developers");
		}

		return admin;

	}



	/*
	checks if required text fields are filled or not 
	then  calls insertData() function  
	*/	
	public void formSubmission(){
		if (getname.getText().equals("") == false && getaddress.getText().equals("") == false && getPhone.getText().equals("") == false && getPass.getText().equals("") == false) {
			InsertData(getname.getText(),getPass.getText(),getaddress.getText(),getPhone.getText());
			getname.setText("");
			getPass.setText("");
			getaddress.setText("");
			getPhone.setText("");

			table.setItems(getAdmin());   		// updates the table after a successful insertion 
		} else {
			tele.pop("Please fill up all fields");
		}
	}


	/*
	inserts data to database
	*/
	public void InsertData(String n,String m,String o,String p){
		DataAccess da=new DataAccess();
		String q="insert into admins(admin_name,password,address,phone) values";
		try {
			da.updateDB(q+"('"+n+"','"+m+"','"+o+"','"+p+"')");			
			refresh = 1;
			da.close();
		} catch(SQLException a){
			tele.pop("Error: Please contact developers");
		}
	}



	public void updateTable(){
		ObservableList<Admins> admin = FXCollections.observableArrayList();

		for (Admins b : table.getItems()) {
			if (like(b.getName(), search.getText())  || like(b.getAddress(), search.getText())){
				admin.add(new Admins(b.getId(),b.getName(),b.getPhone(),b.getAddress()));
			}

		}

		table.setItems(admin);
	}



	public static boolean like(String str, String expr) {
		expr = expr.toLowerCase(); 
		str = str.toLowerCase();
		return str.matches("(.*)"+expr+"(.*)");
	}
}	