/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package CPS.View;


import java.io.IOException;

import client.Client;
import javafx.scene.control.ProgressIndicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import server.Customer;
import server.Worker;
import server.Worker.WorkerType;

public class LoginViewController {

	
	@FXML
    private RadioButton manager_radio;

    @FXML
    private RadioButton worker_radio;

    @FXML
    private RadioButton customer_radio;

    @FXML
    private ProgressIndicator login_view_progress_bar;

    @FXML
    private TextField id_bar;

    @FXML
    private Button log_as_occasional_customer_btn;

    @FXML
    private RadioButton ceo_radio;

    @FXML
    private Button login_btn;

    @FXML
    private ToggleGroup type;

    @FXML
    private PasswordField password_bar;

    @FXML
    private Button log_as_in_advance_customer_btn;

    @FXML
    private Button login_view_customer_sign_in_btn;




	    /*@FXML
	void login_view_finished_parking(ActionEvent event) throws IOException {
		String url = "FinishedParking.fxml";
		switchWindow(url);
		switchScene(event, "Finish Parking");
	}*/


	@FXML
	void log_as_occasional_customer(ActionEvent event) throws IOException {

		changeViewWithDates(event, "OccasionalCustomer.fxml", "occasional customer");

		//String url = "OccasionalCustomer.fxml";
		// switchWindow(url);
		PersonViewController controller = loader.getController();
		controller.Occasional_Customer_loadDates();
		controller.Occasional_Customer_load_car_lots();
		// switchScene(event,"occasional customer");
	}

	@FXML
	void log_as_in_advance_customer(ActionEvent event) throws IOException {

		changeViewWithDates(event, "InAdvanceCustomer.fxml", "order in advance");

		//String url = "InAdvanceCustomer.fxml";
		//switchWindow(url);
		PersonViewController controller = loader.getController();
		controller.In_Advance_Customer_loadDates();
		controller.In_Advance_Customer_load_car_lots(); 
		//switchScene(event, "order in advance");


	}


	@FXML
	void login(ActionEvent event) throws IOException {
		login_view_progress_bar.setVisible(true);

		String url = "";
		String title = "";
		String err_msg = "";
		//TODO need to consider what kind of login

		Client client = new Client();

		//worker login
		if (worker_radio.isSelected()) {
			if (isEmptyBars(id_bar, password_bar)) {
				err_msg = err_msg + "missing password or user id\n";
			} else {
				Worker w = client.workerLogin(id_bar.getText(), password_bar.getText(), WorkerType.ParkingWorker);
				if (w == null) {
					w = client.workerLogin(id_bar.getText(), password_bar.getText(), WorkerType.CustomerService);
				}
				if (w == null) {
					err_msg = err_msg + "You are not a worker\n";
				} else {
					url = "workerView.fxml";
					switchWindow(url);
					PersonViewController controller = loader.getController();
					controller.setWorkerName(w.getFirstName());
					controller.setPID(w.getPid());

					controller.setWorkerInterfaceController(wIC);
					title = "Worker Interface";
				}
			}
		}

		//manger login
		else if (manager_radio.isSelected()) {
			if (isEmptyBars(id_bar, password_bar)) {
				err_msg = err_msg + "missing password or user id\n";
			} else {

				Worker w = client.workerLogin(id_bar.getText(), password_bar.getText(), WorkerType.ParkingManger);

				if (w == null) {
					err_msg = err_msg + "You are not a manager\n";
				} else {
					url = "ManagerView.fxml";
					switchWindow(url);
					PersonViewController controller = loader.getController();
					controller.setManagerName(w.getFirstName());
					controller.setPID(w.getPid());

					controller.ManagerLoad();

					title = "Manager Interface";
				}
			}
		}

		//customer login
		else if (customer_radio.isSelected()) {
			if (isEmptyBars(id_bar, password_bar)) {
				err_msg = err_msg + "missing password or user id\n";
			} else {
				Customer c = client.customerLogin(id_bar.getText(), password_bar.getText());
				if (c == null) {
					err_msg = err_msg + "You are not a customer\n";
				} else {
					url = "CustomerView.fxml";
					switchWindow(url);
					PersonViewController controller = loader.getController();
					controller.setCustomerName(c.getFirstName());
					controller.setPID(c.getPid());
					title = "Customer Interface";
				}

				//  controller.setWorkerInterfaceController(wIC);
			}
		}
		else if (ceo_radio.isSelected())
		{
			if (isEmptyBars(id_bar, password_bar)) {
				err_msg = err_msg + "missing password or user id\n";
			} 
			else {
				Worker w = client.workerLogin(id_bar.getText(), password_bar.getText(), WorkerType.CEO);
				if (w == null) {
					err_msg = err_msg + "You are not a ceo\n";
				} else {
					url = "CeoView.fxml";
					switchWindow(url);
					PersonViewController controller = loader.getController();
					controller.CeoLoad();
					controller.setPID(w.getPid());

					//controller.setCeo_name(w.getFirstName());
					title = "Ceo Interface";
				}

				//  controller.setWorkerInterfaceController(wIC);
			}
			
			
			
			
		}
		
		
		
		else {
			err_msg = err_msg + "you need to select how to login\n";

		}
		if (!err_msg.isEmpty()) {
			createErrMsg(event, err_msg);
			login_view_progress_bar.setVisible(false);

			return;
		}
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setResizable(false);
		window.setScene(tableViewScene);
		window.setTitle(title);
		login_view_progress_bar.setVisible(false);

		window.show();
	}
	

	/* @FXML
	    void login_view_end_parking(ActionEvent event) throws IOException {
		 String url = "FinishedParking.fxml";
			switchWindow(url);
			switchScene(event,"Finish Parking");
	    }

	    @FXML
	    void login_view_start_parking(ActionEvent event) throws IOException 
	    {
	    	
	    	String url = "StartParking.fxml";
			switchWindow(url);
			switchScene(event,"Start Parking");
	    }
	    

	
	
	    @FXML
	    void login_view_complaint(ActionEvent event) throws IOException {
	    	String url = "ComplaintView.fxml";
			switchWindow(url);
			switchScene(event,"complaint section");
	    }
	
	*/
	
	//----------------------------------------------------------------------------

	private FXMLLoader loader;
	private Parent tableViewParent;
	private Scene tableViewScene;
	WorkerInterfaceController wIC;


	public LoginViewController() {
		this.wIC = new WorkerInterfaceController();
	}


	public LoginViewController(WorkerInterfaceController wIC) {
		this.wIC = wIC;
	}


	void switchWindow(String url) throws IOException {

		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url));
		tableViewParent = loader.load();
		tableViewScene = new Scene(tableViewParent);

	}

	void switchScene(ActionEvent event, String pageTitle) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setResizable(false);
		window.setScene(tableViewScene);
		window.setTitle(pageTitle);
		window.show();
	}


	void createErrMsg(ActionEvent event, String errMsg) throws IOException {
		Stage err_win = null;
		
		switchWindow("PopUpMsg.fxml");

		PersonViewController controller = loader.getController();
		controller.setErrLabel(errMsg);

		err_win = new Stage();
		err_win.setResizable(false);
		err_win.setScene(tableViewScene);
		err_win.setTitle("Error");
		err_win.centerOnScreen();
		err_win.show();


	}


	
	
	
	@FXML
	void login_view_customer_sign_in(ActionEvent event) throws IOException {
		// String url = "CustomerSignIn.fxml";
		//switchWindow(url);
		changeViewWithDates(event, "CustomerSignUp.fxml", "sign up");
		PersonViewController controller = loader.getController();
		controller.Customer_Sign_Up_loadDates();
		// switchScene(event,"sign in");
	}

	void changeViewWithDates(ActionEvent event, String url, String title) throws IOException {
		switchWindow(url);
		switchScene(event, title);

	}


	boolean isEmptyBars(TextField tf1, TextField tf2) {
		if (tf1.getText().equals("") || tf2.getText().equals(""))
			return true;
		return false;
	}
	
	
	
	
	
}
