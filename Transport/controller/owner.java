package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.input.*;

import services.Teleportation;
import services.Admins;
import services.DataAccess;
import services.routes;





// database
import java.sql.ResultSet;
import java.sql.SQLException;

public class owner {
 @FXML
 Button home = new Button(); 
 @FXML
 Label revenue = new Label();
 @FXML
 Label route = new Label();
 @FXML
 Label bus = new Label();
 @FXML
 Label schedule = new Label();
 @FXML
 Label trips = new Label();

 Teleportation tele = new Teleportation();

 @FXML
 private void initialize() {
  revenue.setText(Total_revenue()+" TK");
  route.setText(Total("routes")+"");
  bus.setText(Total("buses")+"");
  schedule.setText(Total("schedule")+"");
  trips.setText(Total("daily_schedule")+"");

}


public void Routing(ActionEvent e){
  if (e.getSource() == home) {
    tele.changeTo(home, "route.fxml");
  }
}

public int Total_revenue(){
  Integer total = 0;
  addRouteController r = new addRouteController();
  ObservableList<routes> m = FXCollections.observableArrayList();
  m = r.getRoute();

  for (routes x: m) {
    String number = x.getEarnings();
    int result = Integer.parseInt(number);
    total += result;
  }

  return total;
}

public int Total(String table){
  Integer total = 0;
  String q = "select count(*) as n from "+table+" where active = 1";
  DataAccess da = new DataAccess();
  ResultSet s;
  try {
    s = da.getData(q);
    s.next();
    total = s.getInt("n");
    da.close();
  } catch(Exception ex){}

  return total;
}


}






