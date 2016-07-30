import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;


import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class Login extends Application
{
	public static void main(String arg[])
	{
           launch(arg);
	}



    public void start(Stage stage_ob)
      {
        StackPane root = new StackPane();
      	Scene one = new Scene(root,1280, 720);
      	root.setAlignment(Pos.CENTER);
      	stage_ob.setResizable(false);



      	//..connect css file//
      	File f = new File("app.css");
        root.getStylesheets().clear();
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        

        Text t=new Text("Wellcome to JourneyBy");
        PasswordField password = new PasswordField();
        TextField text=new TextField();
        Button login=new Button("Login");
        Button cancel=new Button("Cancel");

        text.setMaxSize(200,30);
        password.setMaxSize(200,30);

        root.getChildren().add(t);
        root.getChildren().add(text);
        root.getChildren().add(password);
        root.getChildren().add(login);
        root.getChildren().add(cancel);

        login.setTranslateX(-30);
        login.setTranslateY(130);

        cancel.setTranslateX(60);
        cancel.setTranslateY(130);
        
        text.setTranslateX(10);
        text.setTranslateY(30);

      	password.setTranslateX(10);
        password.setTranslateY(75);

        t.setTranslateX(10);
        t.setTranslateY(-50);

        text.setPromptText("Enter ID");
        password.setPromptText("Enter password");
   
        t.setId("logintext");
        root.setId("loginroot");
        
      	
      	stage_ob.setTitle("Transport management v0.1");
      	stage_ob.setScene(one);
      	stage_ob.show();
      	
   }

}
