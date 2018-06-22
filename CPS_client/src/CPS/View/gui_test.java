package CPS.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;    
import javax.xml.soap.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.shape.*;


    public class gui_test extends Application {    

        @Override
        public void start(Stage primaryStage) {

            int SIZE = 10;
            int length = SIZE;
            int width = SIZE;

            GridPane root = new GridPane();    

            ArrayList<Integer> list = new ArrayList<Integer>();
           for( int i = 0 ; i< 10; i++)
            list.add(i);
           
           int[][] b = {{1, 2}, {3, 4, 5}};


            
            //list.add(myInt);

            //List li = new List();
          //  li.add(1);
            
            
            for(int y = 0; y < length; y++){
                for(int x = 0; x < list.get(y); x++){

                    Random rand = new Random();
                    int rand1 = rand.nextInt(2);


                    Rectangle r = new Rectangle();
                   // r.setX(5);
                  //  r.setY(5);
                    r.setWidth(50);
                    r.setHeight(50);
                    r.setArcWidth(70);
                    r.setArcHeight(70);

                    
                    
                    r.setStroke(Color.BLACK);

                    r.setFill(Color.RED);
                    
                    // Create a new TextField in each Iteration
                    TextField tf = new TextField();
                    
                    tf.setPrefHeight(15);
                    tf.setPrefWidth(15);
                   // tf.setAlignment(Pos.CENTER);
                    tf.setEditable(false);
                    tf.setText("(" + rand1 + ")");

                    // Iterate the Index using the loops
                  //  root.setRowIndex(tf,y);
                    //root.setColumnIndex(tf,x); 
                    //root.getChildren().add(tf);
                    
                    root.setRowIndex(r,y);
                    root.setColumnIndex(r,x); 
                    root.getChildren().add(r);
                    
                    
                }
            }

            Scene scene = new Scene(root, 550, 550);    
            primaryStage.setTitle("Random Binary Matrix (JavaFX)");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {    
            launch(args);
        }    
    }