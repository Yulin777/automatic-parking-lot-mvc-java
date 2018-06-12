package CPS;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mainView extends Application
{

	
		public static List<Scene> sceneList= new ArrayList<Scene>(); 
		public static Stage window;
		public Scene loginScene,workStationScene;
		public  URL url;
		public AnchorPane pane;
		public String loginStr = "LoginView.fxml";
		public String workStationStr = "WorkerView.fxml";
		
		
		
	  @Override
	  public void start(Stage primaryStage) throws IOException 
	  {
		window = primaryStage;
		
		url = getClass().getResource(loginStr);
	    pane = FXMLLoader.load( url );
	    loginScene = new Scene( pane );
	    url = getClass().getResource(workStationStr);
	    pane = FXMLLoader.load( url );
	    workStationScene = new Scene(pane);
	    
	    sceneList.add(loginScene);
	    sceneList.add(workStationScene);
	   
	    window.setScene( loginScene );
	    window.setTitle( "login page" );
	    
	    window.show();
		
	  }
		
		public static void main(String[] args) 
		{
			launch(args);
		}

		
	}

	
	
	
	
	
	
	
	

