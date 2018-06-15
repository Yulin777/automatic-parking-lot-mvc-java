/**
 * Sample Skeleton for 'WorkerView.fxml' Controller Class
 */

package CPS.View;

import java.io.IOException;
import java.time.LocalDate;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PersonViewController {



    //--------------------error msg-----------------------------------
    @FXML
    private Label err_msg_label;

    @FXML
    private Button err_msg_ok_btn;

    @FXML
    void err_msg_ok(ActionEvent event) {

        Stage stage = (Stage) err_msg_ok_btn.getScene().getWindow();
        stage.close();
    }

    void setErrLabel(String str)
    {
        err_msg_label.setText(str);
    }

    //--------------------^^error msg ^^-----------------------------------






    //------------------------------WORKER------------------------------------------
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
        switchScene(event);
       /* Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();*/

    }
    //----------------------------^WORKER^--------------------------------
    
  //------------------------------------------------------------------------------------------------  
    
    
    
    //--------------------MANAGER----------------------------------------
    
    @FXML // fx:id="manager_current_state_btn"
    private Button manager_current_state_btn; // Value injected by FXMLLoader

    @FXML // fx:id="manager_name_label"
    private Label manager_name_label; // Value injected by FXMLLoader

    @FXML // fx:id="manager_log_out_btn"
    private Button manager_log_out_btn; // Value injected by FXMLLoader

    @FXML // fx:id="manager_performance_report_btn"
    private Button manager_performance_report_btn; // Value injected by FXMLLoader

    @FXML
    void managerPrintCurrentState(ActionEvent event) {

    }

    @FXML
    void managerPerformanceReport(ActionEvent event) {

    }

    @FXML
    void manager_log_out(ActionEvent event) throws IOException 
    {
    	String url = "LoginView.fxml";
    	switchWindow(url);
        switchScene(event);
    	/*Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();*/
    }

    
    
    
    //--------------------^MANAGER^----------------------------------------
    
    
    
    
    //---------------------------Occasional Customer----------------------------------


        @FXML // fx:id="Occasional_Customer_car_park"
        private TextField Occasional_Customer_car_park; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_email"
        private TextField Occasional_Customer_email; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_order"
        private Button Occasional_Customer_order; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_back_btn"
        private Button Occasional_Customer_back_btn; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_end_date"
        private DatePicker Occasional_Customer_end_date; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_id"
        private TextField Occasional_Customer_id; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_car_number"
        private TextField Occasional_Customer_car_number; // Value injected by FXMLLoader

        @FXML // fx:id="Occasional_Customer_end_time"
        private TextField Occasional_Customer_end_time; // Value injected by FXMLLoader

    void createErrMsg(ActionEvent event,String errMsg) throws IOException {
        switchWindow("ErrorMsg.fxml");
        Stage err_win_instance;
        PersonViewController controller = loader.getController();
        controller.setErrLabel(errMsg);
        err_win_instance = new Stage();
        err_win_instance.setScene(tableViewScene);
        err_win_instance.show();
    }


    @FXML
    void Occasional_Customer_Order(ActionEvent event) throws IOException {

        String id=Occasional_Customer_id.getText();
        String car_number = Occasional_Customer_car_number.getText();
        String car_park = Occasional_Customer_car_park.getText();
        String email = Occasional_Customer_email.getText();
        LocalDate end_date = Occasional_Customer_end_date.getValue();
        String end_time = Occasional_Customer_end_time.getText();
        String err_msg = Occasional_Customer_inputIsValid(id,car_number,car_park,email,end_date,end_time );

        if(!err_msg.isEmpty())
        {
            //TODO error msg
            //System.out.println(err_msg);
            createErrMsg(event, err_msg);


            return;
        }

        System.out.println("all good");


    }

    private String Occasional_Customer_inputIsValid(String id, String car_number, String car_park, String email,LocalDate end_date,String end_time )
    {
       String msg = "";
        if(id.isEmpty())
            msg = msg + "id is empty\n";
        if(car_number.isEmpty())
            msg = msg + "car_number is empty\n";
        if(car_park.isEmpty())
            msg = msg + "car_park is empty\n";
        if(email.isEmpty())
            msg = msg + "email is empty\n";
        if( end_date != null)
        {
            LocalDate today = LocalDate.now();
            if(today.isAfter(end_date))
                msg = msg + "choose only future dates\n";
        }
        else
        {
            msg = msg + "date is empty\n";
        }
        //TODO checck also time and hour
        if(end_time.isEmpty())
            msg = msg + "email is empty\n";

    return msg;
    }

    @FXML
    void Occasional_Customer_back(ActionEvent event) throws IOException {
        String url = "LoginView.fxml";
        switchWindow(url);
        switchScene(event);
    }




        //---------------------------^^Occasional Customer^^----------------------------------




//------------------------------------In Advance Customer ----------------------------
    @FXML // fx:id="In_Advance_Customer_back_btn"
    private Button In_Advance_Customer_back_btn; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_end_time"
    private TextField In_Advance_Customer_end_time; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_email"
    private TextField In_Advance_Customer_email; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_order"
    private Button In_Advance_Customer_order; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_car_park"
    private TextField In_Advance_Customer_car_park; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_start_date"
    private DatePicker In_Advance_Customer_start_date; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_id"
    private TextField In_Advance_Customer_id; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_end_date"
    private DatePicker In_Advance_Customer_end_date; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_start_time"
    private TextField In_Advance_Customer_start_time; // Value injected by FXMLLoader

    @FXML // fx:id="In_Advance_Customer_car_number"
    private TextField In_Advance_Customer_car_number; // Value injected by FXMLLoader

    @FXML
    void DatePicked(ActionEvent event) {

    }

    @FXML
    void In_Advance_Customer_back(ActionEvent event) {

    }

    @FXML
    void In_Advance_Customer__Order(ActionEvent event) throws IOException {
        String id=In_Advance_Customer_id.getText();
        String car_number = In_Advance_Customer_car_number.getText();
        String car_park = In_Advance_Customer_car_park.getText();
        String email = In_Advance_Customer_email.getText();
        LocalDate start_date = In_Advance_Customer_start_date.getValue();
        String start_time = In_Advance_Customer_start_time.getText();
        LocalDate end_date = In_Advance_Customer_end_date.getValue();
        String end_time = In_Advance_Customer_end_time.getText();
        String err_msg = In_Advance_Customer_inputIsValid(id,car_number,car_park,email,start_date,start_time, end_date,end_time );

        if(!err_msg.isEmpty())
        {
            //TODO error msg
            //System.out.println(err_msg);
            createErrMsg(event, err_msg);


            return;
        }

    }


    private String In_Advance_Customer_inputIsValid(String id, String car_number, String car_park, String email,LocalDate start_date,String start_time,LocalDate end_date,String end_time )
    {
        String msg = "";
/*
        if(id.isEmpty())
            msg = msg + "id is empty\n";
        if(car_number.isEmpty())
            msg = msg + "car_number is empty\n";
        if(car_park.isEmpty())
            msg = msg + "car_park is empty\n";
        if(email.isEmpty())
            msg = msg + "email is empty\n";

        if( start_date != null && end_date != null)
        {
            LocalDate today = LocalDate.now();
            if(today.isAfter(end_date))
                msg = msg + "choose only future dates\n";
        }
        else
        {
            msg = msg + "date is empty\n";
        }
        //TODO checck also time and hour
        if(end_time.isEmpty())
            msg = msg + "email is empty\n";
*/
        return msg;
    }






   /* @FXML
    void DatePicked(ActionEvent event) {

    }*/
    //--------------------------------^^^^^In Advance Customer^^^^--------------------------





















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
    

    void switchScene(ActionEvent event)
    {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
   //---------------------------------------------------------- 
    
void setWorkerName(String name)
{
	worker_name_label.setText("Hello " + name );
}



void setManagerName(String name)
{
	manager_name_label.setText("Hello " + name );
	
}

}
