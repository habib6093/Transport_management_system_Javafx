//this is a sample code ..having some importants class,method,functions.....

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


 
public class Hello extends Application {
    
    @Override
    public void start(Stage primaryStage)
     {  
     	StackPane root = new StackPane();
     	Scene scene = new Scene(root, 600, 250);
    // 	scene.setResizable(false);
        Button btn = new Button("say what i'm saying ....");
        File f = new File("style.css");
       scene.getStylesheets().clear();
       scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
       btn.setId("button");
      
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });



           

        
        
        root.getChildren().add(btn);
                                  //width,height
        

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();



       
      }





 public static void main(String[] args) {
        launch(args);
    }

}




class firstpage
{

       
 

}
