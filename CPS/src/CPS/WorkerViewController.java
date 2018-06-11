/**
 * Sample Skeleton for 'WorkerView.fxml' Controller Class
 */

package CPS;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WorkerViewController {

    @FXML // fx:id="worker_log_out_btn"
    private Button worker_log_out_btn; // Value injected by FXMLLoader

    @FXML // fx:id="worker_name_label"
    private Label worker_name_label; // Value injected by FXMLLoader

    @FXML // fx:id="worker_availability_btn"
    private Button worker_availability_btn; // Value injected by FXMLLoader

    @FXML // fx:id="worker_report_btn"
    private Button worker_report_btn; // Value injected by FXMLLoader

    @FXML // fx:id="worker_reserve_parking_btn"
    private Button worker_reserve_parking_btn; // Value injected by FXMLLoader

    @FXML
    void worker_report(ActionEvent event) {
return;
    }

    @FXML
    void worker_resreve_parking(ActionEvent event) {
return;
    }

    @FXML
    void worker_check_availability(ActionEvent event) {
return;
    }

    
    @FXML
    void worker_log_out(ActionEvent event) throws IOException 
    {   
    	
    	String url = "LoginView.fxml";
    	switchWindow(url);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }
    //--------------------------------------
    
    private FXMLLoader loader;
    private Parent tableViewParent;
    private Scene tableViewScene;
	WorkerInterfaceController wIC;
	
	
	void setWorkerInterfaceController(WorkerInterfaceController wIC)
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
    
    
   //---------------------------------------------------------- 
    
void setWorkerName(String name)
{
	worker_name_label.setText("Hello " + name );
}

}
