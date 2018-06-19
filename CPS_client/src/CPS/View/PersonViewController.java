/**
 * Sample Skeleton for 'WorkerView.fxml' Controller Class
 */

package CPS.View;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import client.Client;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import server.Customer;

public class PersonViewController {

	Client client = new Client();



	//----------------Customer View-------------------------------------


	@FXML // fx:id="customer_view_label"
	private Label customer_view_label; // Value injected by FXMLLoader

	@FXML // fx:id="customer_view_leave_parking_btn"
	private Button customer_view_leave_parking_btn; // Value injected by FXMLLoader

	@FXML // fx:id="customer_view_log_out"
	private Button customer_view_log_out; // Value injected by FXMLLoader

	@FXML // fx:id="customer_view_enter_parking_btn"
	private Button customer_view_enter_parking_btn; // Value injected by FXMLLoader

	@FXML
	void customer_view_log_out(ActionEvent event) throws IOException {
		log_out(event);

	}

	@FXML
	void customer_view_enter_parking(ActionEvent event) {

	}

	@FXML
	void customer_view_leave_parking(ActionEvent event) {

	}

	void setCustomerName(String name)
	{
		customer_view_label.setText("Hello " + name);
	}







	//-------------------^^^-Customer View^^^-----------------------------------






	//-----------------Display Bill------------------

	@FXML
	private Button display_bill_ok_btn;

	@FXML
	private Text display_bill_bill_txt;

	@FXML
	void display_bill_ok(ActionEvent event) throws IOException {
		Stage stage = (Stage) display_bill_ok_btn.getScene().getWindow();
		stage.close();

	}
	void createBillMsg(ActionEvent event,String billMsg) throws IOException {
		switchWindow("DisplayBill.fxml");
		Stage bill_win_instance;
		PersonViewController controller = loader.getController();
		controller.setBillLabel(billMsg);
		bill_win_instance = new Stage();
		bill_win_instance.setScene(tableViewScene);
		bill_win_instance.setTitle("bill window");
		bill_win_instance.show();
	}

	void setBillLabel(String str)
	{
		display_bill_bill_txt.setText(str);
	}


	//------------------^^^^^^Display Bill^^^^^----------------















	//----------------------Finished Parking--------------------------

	@FXML
	private TextField finished_parking_id_bar;

	@FXML
	private Button finish_parking_back_btn;

	@FXML
	private TextField finished_parking_car_number_bar;

	@FXML
	void finish_parking_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event,"login page");

	}

	@FXML
	void finish_parking_pay(ActionEvent event) throws IOException {
		// TODO calculate the price
		createBillMsg(event, "0");
	}






	//---------------------^^^Finished Parking^^----------------------------








	//-----------------------Customer Sign up---------------------------------


    @FXML // fx:id="customer_sign_up_id"
    private TextField customer_sign_up_id; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_email"
    private TextField customer_sign_up_email; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_sign_up_btn"
    private Button customer_sign_up_sign_up_btn; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_last_name"
    private TextField customer_sign_up_last_name; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_car_number"
    private TextField customer_sign_up_car_number; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_back_btn"
    private Button customer_sign_up_back_btn; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_date_picker"
    private DatePicker customer_sign_up_date_picker; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_first_name"
    private TextField customer_sign_up_first_name; // Value injected by FXMLLoader

    @FXML // fx:id="customer_sign_up_phone_number"
    private TextField customer_sign_up_phone_number; // Value injected by FXMLLoader

    @FXML
    void Customer_Sign_up_Sign_up(ActionEvent event) throws IOException {



    	//TODO ceck extra parameters
    	String id=customer_sign_up_id.getText();
        String car_number = customer_sign_up_car_number.getText();
        String email = customer_sign_up_email.getText();
        LocalDate start_date = customer_sign_up_date_picker.getValue();
        String err_msg = Customer_Sign_Up_InputIsValid(id,car_number,email,start_date );

        if(!err_msg.isEmpty())
        {
            //TODO error msg
            createErrMsg(event, err_msg);

            return;
        }
    }

    private String Customer_Sign_Up_InputIsValid(String id, String car_number, String email,LocalDate end_date )
    {
        String msg = "";
        if(id.isEmpty())
            msg = msg + "id is empty\n";
        if(car_number.isEmpty())
            msg = msg + "car_number is empty\n";
        if(email.isEmpty())
            msg = msg + "email is empty\n";
        if( end_date != null)
        {
            LocalDate today = LocalDate.now();
            if(today.isAfter(end_date))
                msg = msg + "choose only future dates\n";
        }
        else
            msg = msg + "date is empty\n";

        return msg;
    }




    @FXML
    void Customer_Sign_up_Sign_up_back(ActionEvent event) throws IOException {
    	String url = "LoginView.fxml";
        switchWindow(url);
        switchScene(event,"login page");

    }

    public void Customer_Sign_Up_Sign_In_loadDates() {
    	customer_sign_up_date_picker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }






/*
    @FXML
    private DatePicker Customer_Sign_In_Sign_In_date_picker_btn;

	@FXML
	private Button Customer_Sign_In_Sign_In_back_btn;

	@FXML
	private TextField Customer_Sign_In_Sign_In_car_number_btn;

	@FXML
	private TextField Customer_Sign_In_Sign_In_email_btn;

	@FXML
	private TextField Customer_Sign_In_Sign_In_id_btn;

	@FXML
	private Button Customer_Sign_In_Sign_In_btn;

	@FXML
	void Customer_Sign_In_Sign_In(ActionEvent event) throws IOException {
		String id=Customer_Sign_In_Sign_In_id_btn.getText();
		String car_number = Customer_Sign_In_Sign_In_car_number_btn.getText();
		String email = Customer_Sign_In_Sign_In_email_btn.getText();
		LocalDate start_date = Customer_Sign_In_Sign_In_date_picker_btn.getValue();
		String err_msg = Customer_Sign_In_InputIsValid(id,car_number,email,start_date );

		if(!err_msg.isEmpty())
		{
			//TODO error msg
			createErrMsg(event, err_msg);

			return;
		}




	}
	public void Customer_Sign_In_Sign_In_loadDates() {
		Customer_Sign_In_Sign_In_date_picker_btn.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0);
			}
		});
	}


	private String Customer_Sign_In_InputIsValid(String id, String car_number, String email,LocalDate end_date )
	{
		String msg = "";
		if(id.isEmpty())
			msg = msg + "id is empty\n";
		if(car_number.isEmpty())
			msg = msg + "car_number is empty\n";
		if(email.isEmpty())
			msg = msg + "email is empty\n";
		if( end_date != null)
		{
			LocalDate today = LocalDate.now();
			if(today.isAfter(end_date))
				msg = msg + "choose only future dates\n";
		}
		else
			msg = msg + "date is empty\n";

		return msg;
	}




	@FXML
	void Customer_Sign_In_Sign_In_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event,"login page");
	}


*/



	//------------------------^^^^Customer Sign In^^^----------------------------------


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

		//String url = "LoginView.fxml";
		//switchWindow(url);
		//switchScene(event, "login page");

		log_out(event);
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
		//String url = "LoginView.fxml";
		//switchWindow(url);
		//switchScene(event, "login page");
		log_out( event);
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
		err_win_instance.setTitle("error window");
		err_win_instance.show();
	}


    @FXML
    void Occasional_Customer_Order(ActionEvent event) throws IOException, ParseException {

		String id=Occasional_Customer_id.getText();
		String car_number = Occasional_Customer_car_number.getText();
		String car_park = Occasional_Customer_car_park.getText();
		String email = Occasional_Customer_email.getText();
		LocalDate end_date = Occasional_Customer_end_date.getValue();
		String end_time = Occasional_Customer_end_time.getText();
		String err_msg = Occasional_Customer_inputIsValid(id,car_number,car_park,email,end_date,end_time );
		String first_name="Occasional";
		String last_name="Occasional";
		String phone="Occasional";

		if(!err_msg.isEmpty())
		{
			//TODO error msg
			createErrMsg(event, err_msg);

			return;
		}

		//      System.out.println("all good");
		client.addNewCustomer(id, first_name, last_name, "Occasional", Customer.type.OCCASIONAL, email, phone);
		client.addNewCar(id, car_number);
		
	}

    private String Occasional_Customer_inputIsValid(String id, String car_number, String car_park, String email,LocalDate end_date,String end_time ) throws ParseException
    {
       String msg = "";
       int dateFlag=1;
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //parse start and end time

        if(id.isEmpty())
            msg = msg + "id is empty\n";
        if(car_number.isEmpty())
            msg = msg + "car_number is empty\n";
        if(car_park.isEmpty())
            msg = msg + "car_park is empty\n";
        if(email.isEmpty())
            msg = msg + "email is empty\n";
       /* if( end_date != null)
        {
            LocalDate today = LocalDate.now();
            if(today.isAfter(end_date))
                msg = msg + "choose only future dates\n";
        }
        else
        {
            msg = msg + "date is empty\n";
        }*/
        //TODO checck also time and hour
        if(end_time.isEmpty())
            msg = msg + "end time is empty\n";



        if(end_time.isEmpty())
            msg = msg + "start time is empty\n";
        else
        	if(!check_hour_minute(end_time))
        	{
        	msg=msg + "fix starting hour and minute \n";
        	dateFlag = 0;
        	}


        if(end_date != null && dateFlag == 1)
        {
    		String end = end_date.toString()+" "+end_time;

    			Date e = df.parse(end);
    			Date now = new Date();

    			if(e.before(now))
    				msg = msg + "ending date must be in the future\n";


            /*LocalDate today = LocalDate.now();
            if(today.isAfter(end_date) || today.isAfter(start_date))
                msg = msg + "choose only future dates\n";
            if(start_date.isAfter(end_date))
                msg = msg + "starting date must be prior to ending date\n";

                */
        }

        else if( end_date == null)
        {
            msg = msg + "fill the date please\n";
        }


    return msg;
    }

	@FXML
	void Occasional_Customer_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event,"login page");
	}

	public void Occasional_Customer_loadDates() {
		Occasional_Customer_end_date.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0);
			}
		});
	}


	//---------------------------^^Occasional Customer^^----------------------------------




//------------------------------------In Advance Customer ----------------------------



    @FXML
    private TextField In_Advance_Customer_id1;

    @FXML
    private TextField In_Advance_Customer_id2;

    @FXML
    private TextField In_Advance_Customer_id3;

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
	void In_Advance_Customer_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event, "login page");
	}

    @FXML
    void In_Advance_Customer__Order(ActionEvent event) throws IOException, ParseException {
		Client client = new Client();
        String id=In_Advance_Customer_id.getText();
        String car_number = In_Advance_Customer_car_number.getText();
        String car_park = In_Advance_Customer_car_park.getText();
        String email = In_Advance_Customer_email.getText();
        LocalDate start_date = In_Advance_Customer_start_date.getValue();
        String start_time = In_Advance_Customer_start_time.getText();
        LocalDate end_date = In_Advance_Customer_end_date.getValue();
        String end_time = In_Advance_Customer_end_time.getText();
        String err_msg = In_Advance_Customer_inputIsValid(id,car_number,car_park,email,start_date,start_time, end_date,end_time );
        int status = client.AdvanceOneTimeOrder(id,car_number,car_park,email,start_date,start_time,end_date,end_time);


        if(!err_msg.isEmpty())
        {
            //TODO error msg
            //System.out.println(err_msg);
            createErrMsg(event, err_msg);


            return;
        }

	}


    private String In_Advance_Customer_inputIsValid(String id, String car_number, String car_park, String email,LocalDate start_date,String start_time,LocalDate end_date,String end_time ) throws ParseException
    {





    	String msg = "";
    	int dateFlag = 1;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //parse start and end time


        if(id.isEmpty())
            msg = msg + "id is empty\n";
        if(car_number.isEmpty())
            msg = msg + "car_number is empty\n";
        if(car_park.isEmpty())
            msg = msg + "car_park is empty\n";
        if(email.isEmpty())
            msg = msg + "email is empty\n";

        if(start_time.isEmpty())
            msg = msg + "start time is empty\n";
        else
        	if(!check_hour_minute(start_time))
        	{
        	msg=msg + "fix starting hour and minute \n";
        	dateFlag = 0;
        	}


        if(end_time.isEmpty())
            msg = msg + "end time is empty\n";
        else
        	if(!check_hour_minute(end_time))
        	{
        	msg=msg + "fix ending hour and minute\n";
        	dateFlag = 0;
        	}



        if( start_date != null && end_date != null && dateFlag == 1)
        {
        	String start = start_date.toString()+" "+start_time;
    		String end = end_date.toString()+" "+end_time;

    			Date s = df.parse(start);
    			Date e = df.parse(end);

    			if(e.before(s))
    				msg = msg + "starting date must be prior to ending date\n";


            /*LocalDate today = LocalDate.now();
            if(today.isAfter(end_date) || today.isAfter(start_date))
                msg = msg + "choose only future dates\n";
            if(start_date.isAfter(end_date))
                msg = msg + "starting date must be prior to ending date\n";

                */
        }

        else if(start_date == null && end_date == null)
        {
            msg = msg + "fill all the dates please\n";
        }


        return msg;
    }


    boolean check_hour_minute(String hour_minute)
    {

    	 Pattern pattern;
         Matcher matcher;
         String TIME12HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    	 pattern = Pattern.compile(TIME12HOURS_PATTERN);
         matcher = pattern.matcher(hour_minute);
         return (matcher.matches());
    }







//TODO copy for all buttons
     public void In_Advance_Customer_loadDates() {
         In_Advance_Customer_start_date.setDayCellFactory(picker -> new DateCell() {
             public void updateItem(LocalDate date, boolean empty) {
                 super.updateItem(date, empty);
                 LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0);
			}
		});

		In_Advance_Customer_end_date.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0 );
			}
		});
	}






	/* @FXML
    void DatePicked(ActionEvent event) {

    }*/
	//--------------------------------^^^^^In Advance Customer^^^^--------------------------








void log_out(ActionEvent event ) throws IOException {
    String url = "LoginView.fxml";
    switchWindow(url);
    switchScene(event, "login page");

	}









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


	void switchScene(ActionEvent event,String pageTitle)
	{
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle(pageTitle);
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
