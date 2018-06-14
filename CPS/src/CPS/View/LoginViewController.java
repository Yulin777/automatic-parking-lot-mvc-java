/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package CPS.View;


import java.io.IOException;

import CPS.WorkerInterfaceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML // fx:id="manager_radio"
    private RadioButton manager_radio; // Value injected by FXMLLoader

    @FXML // fx:id="worker_radio"
    private RadioButton worker_radio; // Value injected by FXMLLoader

    @FXML // fx:id="customer_radio"
    private RadioButton customer_radio; // Value injected by FXMLLoader

    @FXML // fx:id="email_bar"
    private TextField email_bar; // Value injected by FXMLLoader

    @FXML // fx:id="login_btn"
    private Button login_btn; // Value injected by FXMLLoader

    @FXML // fx:id="password_bar"
    private TextField password_bar; // Value injected by FXMLLoader
    
    @FXML // fx:id="error_msg_btn"
    private Label error_msg_btn; // Value injected by FXMLLoader
    
    @FXML // fx:id="error_msg_info"
    private Label error_msg_info; // Value injected by FXMLLoader

    @FXML
    private Button log_as_in_advance_customer_btn;

    @FXML
    private Button log_as_occasional_customer_btn;

    @FXML
    void log_as_occasional_customer(ActionEvent event) throws IOException {
        String url = "OccasionalCustomer.fxml";
        switchWindow(url);
        switchScene(event);
    }

    @FXML
    void log_as_in_advance_customer(ActionEvent event) throws IOException {


    }

    
    @FXML
    void login(ActionEvent event) throws IOException 
    {
    	String url="";
    	String title="";
    	//TODO create error window
    	if( !WorkerInterfaceController.login(email_bar.getText(),password_bar.getText()))
    	{
    		email_bar.clear();
    		password_bar.clear();
    		error_msg_info.setVisible(true);
    		error_msg_info.setText("Wrong Parameters ! ");
    		
    	}
    		
    	
    	
    	if(worker_radio.isSelected())
    	{
        WorkerInterfaceController.login(email_bar.getText(),password_bar.getText());
    	url = "workerView.fxml";
    	switchWindow(url);
        PersonViewController controller = loader.getController();
        controller.setWorkerName(email_bar.getText());
        controller.setWorkerInterfaceController(wIC);
        error_msg_info.setVisible(false);
        error_msg_btn.setVisible(false);
        title = "Worker Interface";
    	}
    	
    	else
    		if(manager_radio.isSelected())
        	{
            WorkerInterfaceController.login(email_bar.getText(),password_bar.getText());
        	url = "ManagerView.fxml";
        	switchWindow(url);
            PersonViewController controller = loader.getController();
            controller.setManagerName(email_bar.getText());
            title = "Manager Interface";
          //  controller.setWorkerInterfaceController(wIC);
          //  error_msg_info.setVisible(false);
           // error_msg_btn.setVisible(false);
        	}
        	
        
    	else
    	{
            error_msg_btn.setVisible(true);
    		System.out.println("no button clicked");
    		return;
    	}
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
	    window.setTitle( title );
        window.show();
    }
    
    //----------------------------------------------------------------------------
    
    private FXMLLoader loader;
    private Parent tableViewParent;
    private Scene tableViewScene;
	WorkerInterfaceController wIC;

	public LoginViewController()
    {
		this.wIC = new WorkerInterfaceController();
	}
	
	
    public LoginViewController(WorkerInterfaceController wIC) 
    {
		this.wIC = wIC;
	}
    
	
	
	
    void switchWindow(String url) throws IOException
    {
    	
    	loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(url));
        tableViewParent = loader.load();
        tableViewScene = new Scene(tableViewParent);
    	
    }

    void switchScene(ActionEvent event)
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    

}
