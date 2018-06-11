package CPS;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.ChatClient;
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

public class WorkerInterface extends Application {

	WorkerInterfaceController wIC;
	public static List<Scene> sceneList= new ArrayList<Scene>(); 
	public static Stage window;
	public Scene loginScene,workStationScene;
	public  URL url;
	public AnchorPane pane;
	public String loginStr = "WorkerSceneLogin.fxml";
	public String workStationStr = "WorkerSceneWorkStation.fxml";
	
	

	/*
	 * 
	 * this is for the login interface
	 */
	
    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="emailTf"
    private TextField emailTf; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTf"
    private TextField passwordTf; // Value injected by FXMLLoader

   
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    
    /*
	 * 
	 * this is for work station interface
	 */
	
    
    
    @FXML // fx:id="logoutBtn"
    private Button logoutBtn; // Value injected by FXMLLoader

    @FXML // fx:id="reserveBtn"
    private Button reserveBtn; // Value injected by FXMLLoader

    @FXML // fx:id="reportBtn"
    private Button reportBtn; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader
//*************************************************************************
    
    @FXML
    void logout(ActionEvent event) 
    {
    	wIC.Logout();
    	setInterface(0,"Worker login page");
    	window.show();
    	nameLabel.setText("Snir Vitrack") ;
    	
    }
    
    @FXML
    void login(ActionEvent event) 
    {
    	
    	//System.out.println("recevied msg "+response);
    	//TODO create error window
    	//if( !wIC.Login(emailTf.getText(),emailTf.getText()))
    	//{
    	//	emailTf.clear();
    	//	emailTf.clear();
    	//}
    	//else
    		WorkerInterfaceController.login(emailTf.getText(),emailTf.getText());
    		setInterface(1,"Work Station");
    		window.show();
    	
    }
    
    private void setInterface(int index, String status)
	{
		window.setScene(WorkerInterface.sceneList.get(index));
    	window.setTitle(status);
	}
	
    
    public WorkerInterface()
    {
		this.wIC = new WorkerInterfaceController();
	}
	
	
    public WorkerInterface(WorkerInterfaceController wIC) 
    {
		this.wIC = wIC;
	}
    
	public void setup(){
		
	}
	
	public boolean checkParkingAvliblity(){
		return false;
		
	}
	
	public void reserveParkingSlot(){
		
	}

	public void getReport(){
		
	}
	
    
	
  @Override
  public void start(Stage primaryStage) throws IOException 
  {
    
	window = primaryStage;
	  
     //constructing our scene
    url = getClass().getResource(loginStr);
    pane = FXMLLoader.load( url );
    loginScene = new Scene( pane );
    
    url = getClass().getResource(workStationStr);
    pane = FXMLLoader.load( url );
    workStationScene = new Scene(pane);
    
    sceneList.add(loginScene);
    sceneList.add(workStationScene);
    
    // setting the stage
    window.setScene( loginScene );
    window.setTitle( "Worker login page" );
    window.show();
    
  }
	
	public static void main(String[] args) {
		launch(args);
	}
}

