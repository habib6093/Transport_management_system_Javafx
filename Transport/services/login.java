package services;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import java.io.File;                //for css //
import javafx.geometry.Pos;         //for positioning//
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.*;

// database
import java.sql.ResultSet;
import java.sql.SQLException;

import services.DataAccess;
import services.Teleportation;


public class login extends Application
{
  public static String name;
  public static String pass;
  public static int admin_id;


  Teleportation tele = new Teleportation();

  public static void main(String args[])
  {
    launch(args);
  }



  public void start(Stage stage_ob)
  {

    StackPane stackPane_ob=new StackPane();
    stackPane_ob.setAlignment(Pos.CENTER);

    Scene scene_ob=new Scene(stackPane_ob,1280,720);


    //..connect css file//

    stackPane_ob.getStylesheets().add("css/app.css");

    Label t = new Label("Transportation management");
    PasswordField password = new PasswordField();
    TextField id=new TextField();
    Button login=new Button("Login");
    Button cancel=new Button("Cancel");

    id.setMaxSize(200,30);
    id.setPromptText("Enter ID");

    password.setMaxSize(200,30);
    password.setPromptText("Enter password");

    stackPane_ob.getChildren().add(t);
    stackPane_ob.getChildren().add(id);
    stackPane_ob.getChildren().add(password);
    stackPane_ob.getChildren().add(login);
    stackPane_ob.getChildren().add(cancel);

    login.setTranslateX(-30);
    login.setTranslateY(130);

    cancel.setTranslateX(60);
    cancel.setTranslateY(130);

    id.setTranslateX(10);
    id.setTranslateY(30);

    password.setTranslateX(10);
    password.setTranslateY(75);

    t.setTranslateX(10);
    t.setTranslateY(-50);


        //set css ID....//

    t.setId("log_label");
    stackPane_ob.setId("login");



      //database connection....


    login.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {


       if(!id.getText().equals("") && !password.getText().equals("")){
         int x = databass(id.getText(),password.getText());
         //System.out.println(x);

         if(x == 1){
          try {
            Parent root = null;
            Stage stage = null;

            stage = (Stage) id.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/route.fxml"));
            Scene scene = new Scene(root,1280,720);
            scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
            stage.setScene(scene);  

          } catch(Exception e) {
            System.out.println(e);
          }

        } else if(x==2) {

          try {
            Parent root = null;
            Stage stage = null;

            stage = (Stage) id.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/owner.fxml"));
            Scene scene = new Scene(root,1280,720);
            scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
            stage.setScene(scene);  

          } catch(Exception e) {
            System.out.println(e);
          }

        } else {
          tele.pop("wrong password or id....");
        }
      } else {
        //System.out.println("password or user_name is empty...");
        tele.pop("password or user_name is empty...");

      }


    }
  });


    stage_ob.setResizable(false);
    stage_ob.setScene(scene_ob);
    stage_ob.show();
  }



  public int databass(String id,String password){   

    int x=0;
    DataAccess da=new DataAccess();
    ResultSet rs;
    try{
      String q="select * from admins";
      rs = da.getData(q);

      while(rs.next())
      {
        String ids=rs.getString("admin_name");
        String passwords=rs.getString("password");


        if(id.equals(ids) && password.equals(passwords)){
          x=rs.getInt("type");

          admin_id=rs.getInt("admin_id");
          pass=rs.getString("password");
          name=rs.getString("admin_name");

          break;
        }

      }

    }catch(Exception ex){
      System.out.println(ex);
    }

    finally {
      try {
        da.close();
      } catch(SQLException exp){

      }
    }  
    return x;
  }

    //end of login class//// 

  
}
