package CPS.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;    
import javax.xml.soap.Text;

import client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.shape.*;


    public class gui_test extends Application {    

    	public int[] fillArray(int length) {
    	    int[] arr = new int[length];
    	    for (int i = 0; i < length; ++i) {
    	        arr[i] = i;
    	    }
    	    return arr;
    	}
    	
    	
    	
    	
    	@Override
        public void start(Stage primaryStage) {

         
           GridPane root = new GridPane();       
           Client cli = new Client();
           int[][][] c = cli.getParkSoltStatus(0);
            
           int maxRow=0;
           	for(int i = 0; i< c[0].length; i++)
           			if(c[0][i].length > maxRow)
           				maxRow = c[0].length;
           		
           	
           	int[]arr_col = fillArray(maxRow);
           	int []arr_row = fillArray(c[0].length);
           	
           for(int x= 0; x < maxRow; x++)
           {
        	   TextField tf = new TextField();
               
               tf.setPrefHeight(25);
               tf.setPrefWidth(25);
               tf.setEditable(false);
               tf.setText("" + arr_col[x] + "");
               tf.setMinSize(25, 25);
               root.setRowIndex(tf,0);
               root.setColumnIndex(tf,x+1); 
               root.getChildren().add(tf);
           }
        
            for(int y = 0; y < c[0].length; y++)
            {
                for(int x = 0; x < c[0][y].length; x++)
                {
                	if(x==0)
                	{
                		TextField tf = new TextField();
                        tf.setPrefHeight(25);
                        tf.setPrefWidth(25);
                        tf.setMinSize(25, 25);
                        tf.setEditable(false);
                        tf.setText("" + arr_row[y] + "");
                        root.setRowIndex(tf,y+1);
                        root.setColumnIndex(tf,0); 
                        root.getChildren().add(tf);
                	}
                	
                    Rectangle r = new Rectangle();
                    r.setWidth(50);
                    r.setHeight(50);
                    r.setArcWidth(70);
                    r.setArcHeight(70);
                    r.setStroke(Color.BLACK);
                    
                    if(c[0][y][x] == 0)
                    	r.setFill(Color.BLUE);
                    if(c[0][y][x] == 1)
                        r.setFill(Color.RED);
                    if(c[0][y][x] == 2)
                        r.setFill(Color.GREEN);
                    if(c[0][y][x] == 3)
                    	r.setFill(Color.BLACK);  
                    root.setRowIndex(r,y+1);
                    root.setColumnIndex(r,x+1); 
                    root.getChildren().add(r);    
                }
            }

            ScrollPane sp = new ScrollPane(root);
            sp.setFitToWidth(true);

            Scene scene = new Scene(sp, 550, 550);    
            primaryStage.setTitle("Report");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {    
            launch(args);
        }    
    }