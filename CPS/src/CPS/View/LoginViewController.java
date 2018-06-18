/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package CPS.View;


import java.io.IOException;

import javafx.scene.control.ProgressIndicator;
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
    private Button login_view_customer_sign_in_btn;

    @FXML
    private ProgressIndicator login_view_progress_bar;

    @FXML
    private Button login_view_finished_parking_btn;


    @FXML
    void login_view_finished_parking(ActionEvent event) throws IOException {
        String url = "FinishedParking.fxml";
        switchWindow(url);
        switchScene(event,"Finish Parking");
    }



    @FXML
    void log_as_occasional_customer(ActionEvent event) throws IOException {

        changeViewWithDates(event,"OccasionalCustomer.fxml","occasional customer");

        //String url = "OccasionalCustomer.fxml";
       // switchWindow(url);
        PersonViewController controller = loader.getController();
        controller.Occasional_Customer_loadDates();
       // switchScene(event,"occasional customer");
    }

    @FXML
    void log_as_in_advance_customer(ActionEvent event) throws IOException {

        changeViewWithDates(event,"InAdvanceCustomer.fxml","order in advance");

        //String url = "InAdvanceCustomer.fxml";
        //switchWindow(url);
        PersonViewController controller = loader.getController();
        controller.In_Advance_Customer_loadDates();
        //switchScene(event, "order in advance");



    }

    
    @FXML
    void login(ActionEvent event) throws IOException 
    {
        login_view_progress_bar.setVisible(true);

        String url="";
    	String title="";
    	String err_msg="";
    	//TODO need to consider what kind of login

       //     Client c = new Client();
    	/*if( !WorkerInterfaceController.login(email_bar.getText(),password_bar.getText()))

        {
    		email_bar.clear();
    		password_bar.clear();
    		//error_msg_info.setVisible(true);
    		//error_msg_info.setText("Wrong Parameters ! ");
            err_msg = err_msg + "Wrong Parameters\n";
           // createErrMsg(event, "Wrong Parameters ! ");
    		
    	}*/


    	if(worker_radio.isSelected())
    	{
       /*   if( !c.loginAsWorker(email_bar.getText(),password_bar.getText()))
            {
                err_msg = err_msg + "You are not a worker\n";
                return;
            }*/

        //WorkerInterfaceController.login(email_bar.getText(),password_bar.getText());
    	url = "workerView.fxml";
    	switchWindow(url);
        PersonViewController controller = loader.getController();
        controller.setWorkerName(email_bar.getText());
        controller.setWorkerInterfaceController(wIC);
        title = "Worker Interface";
    	}
    	
    	else
    		if(manager_radio.isSelected())
        	{
             /*   if( !c.loginAsManager(email_bar.getText(),password_bar.getText()))
                {
                    err_msg = err_msg + "You are not a worker\n";
                    return;
                }*/

            //WorkerInterfaceController.login(email_bar.getText(),password_bar.getText());
        	url = "ManagerView.fxml";
        	switchWindow(url);
            PersonViewController controller = loader.getController();
            controller.setManagerName(email_bar.getText());
            title = "Manager Interface";
          //  controller.setWorkerInterfaceController(wIC);
        	}

        if(customer_radio.isSelected())
        {
             /*   if( !c.loginAsManager(email_bar.getText(),password_bar.getText()))
                {
                    err_msg = err_msg + "You are not a worker\n";
                    return;
                }*/

            //WorkerInterfaceController.login(email_bar.getText(),password_bar.getText());
            url = "CustomerView.fxml";
            switchWindow(url);
            PersonViewController controller = loader.getController();
            controller.setCustomerName(email_bar.getText());
            title = "Customer Interface";
            //  controller.setWorkerInterfaceController(wIC);
        }



        
    	else
    	{
            err_msg = err_msg + "no button clicked\n";

    	}
if (!err_msg.isEmpty()) {
    createErrMsg(event, "no button clicked");
    login_view_progress_bar.setVisible(false);

    return;
}
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
	    window.setTitle( title );
        login_view_progress_bar.setVisible(false);

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

    void switchScene(ActionEvent event, String pageTitle)
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle(pageTitle);
        window.show();
    }
    

    void createErrMsg(ActionEvent event,String errMsg) throws IOException {
        Stage err_win;
        switchWindow("ErrorMsg.fxml");

        PersonViewController controller = loader.getController();
        controller.setErrLabel(errMsg);

        err_win =  new Stage();
        err_win.setScene(tableViewScene);
        err_win.setTitle("error window");
        err_win.show();


    }


    @FXML
    void login_view_customer_sign_in(ActionEvent event) throws IOException {
       // String url = "CustomerSignIn.fxml";
        //switchWindow(url);
        changeViewWithDates(event,"CustomerSignIn.fxml","sign in");
        PersonViewController controller = loader.getController();
        controller.Customer_Sign_In_Sign_In_loadDates();
       // switchScene(event,"sign in");

    }



void changeViewWithDates(ActionEvent event,String url, String title) throws IOException {
    switchWindow(url);
    switchScene(event,title);

}











//
}
