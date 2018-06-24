/**
 * Sample Skeleton for 'WorkerView.fxml' Controller Class
 */

package CPS.View;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.ComplaintController;
import server.Customer;
import server.Customer.type;
import server.CustomerController;
import server.OrderController;

public class PersonViewController {

	Client client = new Client();
	static String pid;


	
	
	
	
	
	//------------Reserve Parking--------------
	
	 @FXML
	    private Button reserve_parking_back_btn;

	    @FXML
	    private RadioButton reserve_parking_saved_btn;

	    @FXML
	    private ToggleGroup status_parking;

	    @FXML
	    private SplitMenuButton reserve_parking_level_menu;

	    @FXML
	    private Button reserve_parking_commit_change_btn;

	    @FXML
	    private RadioButton reserve_parking_broken_btn;

	    @FXML
	    private SplitMenuButton reserve_parking_row_menu;

	    @FXML
	    private TextField reserve_parking_column_bar;

    @FXML
    void reserve_parking_commit_change_btn(ActionEvent event) throws IOException 
    {
    	String level = reserve_parking_level_menu.getText();
    	String row = reserve_parking_row_menu.getText();
    	String column = reserve_parking_column_bar.getText();

    	String err_msg = Reserve_Parking_InputIsValid( level,  row,  column) ;
    	
    	if(!err_msg.isEmpty())
    	{
    		createMsg(event, err_msg, "error msg");
    		return;
    	}
    	String location =  client.checkWorkerStaion(pid);
		int location_id = OrderController.getOrderParkingId(location);
    	int[][][] c = client.getParkSoltStatus(location_id);
    	
    	
    	int level_int = Integer.parseInt(level);
    	int row_int = Integer.parseInt(row);
    	int column_int = Integer.parseInt(column);
    	int flag = 0;
    	if(c[level_int][row_int].length <= column_int)
    	{
    		err_msg +="there is no such parking\n";
    	}
    	
    	else
    	{
    	if(reserve_parking_saved_btn.isSelected())
    	{
    		client.setResevedSlot( location_id, level_int,row_int, column_int);
    		flag=1;
    	}
    	
    	if(reserve_parking_broken_btn.isSelected())
    	{
    		client.setOutOfOrderSlot(location_id, level_int,row_int, column_int);
    	}
    	
    	
    	if(flag==0)
    	{
    		err_msg +="no button was selected\n";
    	}
    	}
    	
    	if(!err_msg.isEmpty())
    	{
    		createMsg(event, err_msg, "error msg");
    		return;
    	}
    	String succ_msg = "change commited succsesfully\n";
    	createMsg(event, succ_msg, "succ msg");
    	
    }
    
    private String Reserve_Parking_InputIsValid(String level, String row, String column) 
    	{
		String msg = "";
		if (level.equals("Select Level"))
			msg = msg + "level is not selected\n";
		if (row.equals("Select Row"))
			msg = msg + "row is not selected\n";
		
		//TODO check column is an integer
		if (column.isEmpty())
			msg = msg + "column is not selected\n";
		return msg;
	}

    @FXML
    void reserve_parking_back(ActionEvent event) throws IOException {
    	String url = "WorkerView.fxml";
		switchWindow(url);
		switchScene(event, "Worker page");


    }
	public void Reserve_Parking_load_car_lots() {

		reserve_parking_level_menu.getItems().clear();
		reserve_parking_row_menu.getItems().clear();
		
		String[] level_arr = {"0", "1", "2"};	
		String[] row_arr = {"0", "1", "2"};	
		for (String i : level_arr) {
			MenuItem mi = new MenuItem(i);
			mi.setText(i);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					reserve_parking_level_menu.setText(mi.getText());
				}
			});
			reserve_parking_level_menu.getItems().add(mi);
		}
		
		for (String i : row_arr) {
			MenuItem mi = new MenuItem(i);
			mi.setText(i);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					reserve_parking_row_menu.setText(mi.getText());
				}
			});
			reserve_parking_row_menu.getItems().add(mi);
		}
		
		
		

	}

	//-------------^^^^Reserve Parking^^^^^------------
	
    
    
	//-------------Order detail view -----------------
	
	@FXML
    private TextField order_detail_view_id_bar;

    @FXML
    private TextArea order_detail_view_text_area;

    @FXML
    private Button order_detail_view_read_order_details_btn;

    @FXML
    private Button order_detail_view_back_btn;

    @FXML
    void order_detail_view_read_order_details(ActionEvent event) throws IOException 
    {
    	String order_id = order_detail_view_id_bar.getText();
    	String err_msg ="";
    	
    	if(order_id.isEmpty())
    		err_msg += "please provide order id\n"; 
    	
    	
    	
    	if(!err_msg.isEmpty())
    	{
			createMsg(event, err_msg, "error msg");
			return;
    	}

    	String details = "";
    	details=client.getOrderStatus(Integer.parseInt(order_id));
    	
    	if(details == null)
    	{
    		err_msg+= "wrong order id\n";
			createMsg(event, err_msg, "error msg");
			return;
    	}
    
    	order_detail_view_text_area.setText(details);
    	order_detail_view_text_area.setEditable(false);	
    }

    @FXML
    void order_detail_view_back(ActionEvent event) throws IOException {
    	String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "customer page");


    }
	
	
	//-------------------^^^^^Order detail view^^^^^^^-------------------

	//--------------------cancel order --------------------------

	@FXML
	private TextField cancel_order_view_id_bar;

	@FXML
	private Button cancel_order_view_back_btn;

	@FXML
	private Button cancel_order_view_cancel_order_btn;

	@FXML
	void cancel_order_view_back(ActionEvent event) throws IOException {
		String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "customer page");

	}

	@FXML
	void cancel_order_view_cancel_order(ActionEvent event) throws IOException {
		String err_msg = "";

		String order_id = cancel_order_view_id_bar.getText();

		if (order_id.isEmpty()) {
			err_msg += "please provide order id\n";
		}

		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}


		double bill = client.cancelOrder(Integer.parseInt(order_id));		

		//TODO need to delete order from database. DONE!
		
		String succ_msg="order was canceled succsecfully\n " + "your bill is: " + new DecimalFormat("##.##").format(bill);
		createMsg(event, succ_msg, "succ msg");
	}


	
	

	//------------------^^^^cancel order-^^^^----------------------











	//-----------------------add car view -------------------------------

	@FXML
	private Button add_car_view_add_btn;

	@FXML
	private TextField add_car_view_car_number_bar;

	@FXML
	private Button add_car_view_back_btn;

    @FXML
    void add_car_view_add(ActionEvent event) throws IOException {
    			
    	String car_num = add_car_view_car_number_bar.getText();
    	String err_msg="";
    	if(car_num.isEmpty())
    		err_msg +="please provide car number\n";
    	else if(!client.addNewCar(add_car_view_car_number_bar.getText(), pid))
    		err_msg +="car already exists\n";
    	
    	if(!err_msg.isEmpty())
    	{
    		createMsg(event, err_msg, "error msg");
			return;
    	}
    	else {
    		String succ_msg = "car was added succsecfully\n";
    		createMsg(event, succ_msg, "succ msg");
    	}
    }
    

	@FXML
	void add_car_view_back(ActionEvent event) throws IOException {


		String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "customer page");


	}


	//---------------------^^add car view ^^^--------------------


	//------------------Complaint View----------------------------------


	@FXML
	private Button complaint_view_back_btn;

	@FXML
	private TextField complaint_view_id_bar;

	@FXML
	private TextArea complaint_view_text_area;

	@FXML
	private Button complaint_view_send_complaint_btn;

	@FXML
	void complaint_view_back(ActionEvent event) throws IOException {
		//String url = "LoginView.fxml";
		String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "login page");


	}

	@FXML
	void complaint_view_send_complaint(ActionEvent event) throws IOException {
		String err_msg = "";
		String text_area = complaint_view_text_area.getText();
		String id = complaint_view_id_bar.getText();

		if (text_area.isEmpty())
			err_msg = err_msg + "please fill complaint\n";

		if (id.isEmpty())
			err_msg = err_msg + "please enter your id\n";

		else {
			Customer c;
			c = CustomerController.getClientById(id);
			if (c == null)
				err_msg = err_msg + "you are not a customer\n";

		}

		if (!err_msg.isEmpty() && !ComplaintController.addNewComplaint(id, text_area)) {
			err_msg += "New complaint failed to be added\n";
		}

		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}


		//TODO write complaints to the server
		String succ = "New complaint was added successfully\n";
		createMsg(event, succ, "succ msg");
	}


	//-----------------^^Complaint View^^^-----------------------------

	//---------------------Start Parking---------------------------


	@FXML
	private Button start_parking_start_parking_btn;

	@FXML
	private TextField start_parking_car_number_bar;

	@FXML
	private Button start_parking_back_btn;

	@FXML
	void start_parking_back(ActionEvent event) throws IOException {
		//String url = "LoginView.fxml";
		String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "login page");


	}

	@FXML
	void start_parking_start_parking(ActionEvent event) throws IOException {
		String car_num = start_parking_car_number_bar.getText();
		String err_msg="";
		if (car_num.isEmpty()) {
			err_msg += "please provide car number\n";
//			createMsg(event, err_msg, "error msg");
			return;
		}
		else if(client.startParking(car_num, pid)) {
			//TODO check if parking available + if this car number is signed + if customer is late (will pay fine)
			
			String succ_msg = "parking started successfully\n";
			createMsg(event, succ_msg, "succes msg");							
		}
		else {
			err_msg += "cannot start parking\n";
		}
		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}

	}


	//------------------^^Start Parking ^^---------------------------


	//----------------Customer View-------------------------------------

	@FXML
    private Label customer_view_label12;

    @FXML
    private Button customer_view_see_my_orders_btn;

    @FXML
    private Label customer_view_label;

    @FXML
    private Button customer_view_end_parking_btn;

    @FXML
    private Button customer_view_add_car_btn;

    @FXML
    private Label customer_view_num_msg_label;

    @FXML
    private Button customer_view_cancel_btn;

    @FXML
    private Button customer_view_log_out_btn;

    @FXML
    private Label customer_view_label1;

    @FXML
    private Button customer_view_read_massages_btn;

    @FXML
    private Button customer_view_complaint_btn;

    @FXML
    private Button customer_view_start_parking_btn;


	@FXML
	void customer_view_read_massages(ActionEvent event) throws IOException {
		String msg = "no massages yet\n";
		createMsg(event, msg, "error msg");
	}

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

	void setCustomerName(String name) {
		customer_view_label.setText("Hello " + name);
		
	}
	
	void setPID(String id) {
		pid = id;
	}


	@FXML
	void customer_view_add_car(ActionEvent event) throws IOException {

		String url = "AddCarView.fxml";
		switchWindow(url);
		switchScene(event, "add car interface");

	}

	
	 @FXML
	    void customer_view_see_my_orders(ActionEvent event) throws IOException {
		 	String url = "OrderDetailView.fxml";
			switchWindow(url);
			switchScene(event, "customer page");
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

	void createBillMsg(ActionEvent event, String billMsg) throws IOException {
		switchWindow("DisplayBill.fxml");
		Stage bill_win_instance;
		PersonViewController controller = loader.getController();
		controller.setBillLabel(billMsg);
		bill_win_instance = new Stage();
		bill_win_instance.setScene(tableViewScene);
		bill_win_instance.setTitle("bill window");
		bill_win_instance.show();
	}

	void setBillLabel(String str) {
		display_bill_bill_txt.setText(str);
	}


	//------------------^^^^^^Display Bill^^^^^----------------


	//----------------------Finished Parking--------------------------


	@FXML
	private Button finish_parking_back_btn;

	@FXML
	private TextField finished_parking_car_number_bar;

	@FXML
	void finish_parking_back(ActionEvent event) throws IOException {
		//String url = "LoginView.fxml";
		String url = "CustomerView.fxml";
		switchWindow(url);
		switchScene(event, "login page");

	}

	@FXML
	void finish_parking_pay(ActionEvent event) throws IOException {
		// TODO calculate the price
		String car_id = finished_parking_car_number_bar.getText();
		String err_msg = "";
		if (car_id.isEmpty()) {
			err_msg = err_msg + "please provide car number\n";
		}
		else if(client.endParking(car_id, pid)) {
			double bill = client.getPrice(car_id);
			if (bill != Double.MAX_VALUE) {
//				createBillMsg(event, String.valueOf(bill));
				String succ_msg="order ended succsecfully\n " + "your bill is: " + new DecimalFormat("##.##").format(bill);
				createMsg(event, succ_msg, "succ msg");
				
			}
		}
		else
			err_msg = err_msg + "invalid car number\n";

		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}
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

	//@FXML // fx:id="customer_sign_up_date_picker"
	//private DatePicker customer_sign_up_date_picker; // Value injected by FXMLLoader

	@FXML // fx:id="customer_sign_up_first_name"
	private TextField customer_sign_up_first_name; // Value injected by FXMLLoader

	@FXML // fx:id="customer_sign_up_phone_number"
	private TextField customer_sign_up_phone_number; // Value injected by FXMLLoader

	@FXML
	private TextField customer_sign_up_password;


	@FXML
	void Customer_Sign_up_Sign_up(ActionEvent event) throws IOException {


		String id = customer_sign_up_id.getText();
		String car_number = customer_sign_up_car_number.getText();
		String email = customer_sign_up_email.getText();
		//LocalDate start_date = customer_sign_up_date_picker.getValue();

		String firstName = customer_sign_up_first_name.getText();
		String lastName = customer_sign_up_last_name.getText();
		String password = customer_sign_up_password.getText();
		String phone = customer_sign_up_phone_number.getText();

		String err_msg = Customer_Sign_Up_InputIsValid(firstName, lastName, password, phone, id, car_number, email);


		if (!err_msg.isEmpty()) {
			//TODO error msg
			createMsg(event, err_msg, "error msg");
			return;
		}
		Client c = new Client();
		if (!c.addNewCustomer(id, firstName, lastName, password, type.SUBSCRIBED, email, phone)) {
			err_msg = "Somthing went wrong during sign up customer\n";
			createMsg(event, err_msg, "error msg");
			return;
		}
		c.addNewCar(car_number, id);

		String succ_msg = "Sign up succed\n";
		createMsg(event, succ_msg, "succes msg");
		return;

	}

	private String Customer_Sign_Up_InputIsValid(String firstName, String lastName, String password, String phone, String id, String car_number, String email) {
		String msg = "";
		if (id.isEmpty())
			msg = msg + "id is empty\n";
		if (car_number.isEmpty())
			msg = msg + "car_number is empty\n";
		if (email.isEmpty())
			msg = msg + "email is empty\n";

		if (firstName.isEmpty())
			msg = msg + "first name is empty\n";
		if (lastName.isEmpty())
			msg = msg + "last name is empty\n";
		if (password.isEmpty())
			msg = msg + "password is empty\n";
		if (phone.isEmpty())
			msg = msg + "phone is empty\n";

		/*if (end_date != null) {
			LocalDate today = LocalDate.now();
			if (today.isAfter(end_date))
				msg = msg + "choose only future dates\n";
		} else
			msg = msg + "date is empty\n";*/

		return msg;
	}


	@FXML
	void Customer_Sign_up_Sign_up_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event, "login page");

	}

	public void Customer_Sign_Up_loadDates() {
		/*customer_sign_up_date_picker.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();

				setDisable(empty || date.compareTo(today) < 0);
			}
		});*/
	}


	@FXML
	void customer_view_end_parking(ActionEvent event) throws IOException {
		String url = "FinishedParking.fxml";
		switchWindow(url);
		switchScene(event, "Finish Parking");
	}

	@FXML
	void customer_view_start_parking(ActionEvent event) throws IOException {

		String url = "StartParking.fxml";
		switchWindow(url);
		switchScene(event, "Start Parking");
	}


	@FXML
	void customer_view_complaint(ActionEvent event) throws IOException {
		String url = "ComplaintView.fxml";
		switchWindow(url);
		switchScene(event, "complaint section");
	}


	@FXML
	void customer_view_cancel(ActionEvent event) throws IOException {
		String url = "CancelOrderView.fxml";
		switchWindow(url);
		switchScene(event, "cancel order");

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

	void setErrLabel(String str) {
		err_msg_label.setText(str);
	}

	//--------------------^^error msg ^^-----------------------------------


	//------------------------------WORKER------------------------------------------
	@FXML
    private Button worker_log_out_btn;

    @FXML
    private Label worker_name_label;

    @FXML
    private Button worker_availability_btn;

    @FXML
    private Button worker_reserve_parking_btn;

	@FXML
	void worker_resreve_parking(ActionEvent event) throws IOException {
		
		String url = "ReserveParking.fxml";
		switchWindow(url);
		PersonViewController controller = loader.getController();
		controller.Reserve_Parking_load_car_lots();
		switchScene(event, "reserve parking interface");
	}

	@FXML
	void worker_check_availability(ActionEvent event) {
		return;
	}


	@FXML
	void worker_log_out(ActionEvent event) throws IOException {

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

	@FXML
	private Button manager_current_state_btn;

	@FXML
	private Label manager_name_label;

	@FXML
	private Button manager_View_print_report_btn;

	@FXML
	private Button manager_log_out_btn;

	@FXML
	private Button manager_performance_report_btn;

	@FXML
	private SplitMenuButton manager_View_level_menu;


	@FXML
	void managerPrintCurrentState(ActionEvent event) {

	}

	@FXML
	void managerPerformanceReport(ActionEvent event) {

	}

	@FXML
	void manager_log_out(ActionEvent event) throws IOException {
		//String url = "LoginView.fxml";
		//switchWindow(url);
		//switchScene(event, "login page");
		log_out(event);
		/*Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();*/
	}


	@FXML
	void manager_View_print_report(ActionEvent event) throws IOException {
		String level = manager_View_level_menu.getText();
		String err_msg = "";
		err_msg = manager_Check_Valid_input_report(level);


		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}
		//int location_id = OrderController.getOrderParkingId(location);
		int level_int = Integer.parseInt(level);


		//TODO get the location number from manager person
		ShowReport(level_int, 1);

	}


	String manager_Check_Valid_input_report(String level) {
		String err_msg = "";
		if (level.isEmpty())
			err_msg += "please provide level\n";

		return err_msg;
	}


	void ManagerLoad() {
		List<String> ls = Client.getStations();
		manager_View_level_menu.getItems().clear();

		String[] level_arr = {"0", "1", "2"};
		for (String i : level_arr) {
			MenuItem mi = new MenuItem(i);
			mi.setText(i);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					manager_View_level_menu.setText(mi.getText());
				}
			});
			manager_View_level_menu.getItems().add(mi);
		}


	}


	//--------------------^MANAGER^----------------------------------------


	//---------------------------Occasional Customer----------------------------------


	@FXML
	private ToggleGroup money_toggle2;

	@FXML
	private TextField Occasional_Customer_email;

	@FXML
	private Button Occasional_Customer_order;

	@FXML
	private DatePicker Occasional_Customer_end_date;

	@FXML
	private PasswordField Occasional_Customer_password;

	@FXML
	private Button Occasional_Customer_back_btn;

	@FXML
	private RadioButton Occasional_Customer_cash;

	@FXML
	private RadioButton Occasional_Customer_credit_card;

	@FXML
	private SplitMenuButton Occasional_Customer_parking_lot_btn;

	@FXML
	private TextField Occasional_Customer_id;

	@FXML
	private TextField Occasional_Customer_car_number;

	@FXML
	private TextField Occasional_Customer_end_time;


	void createMsg(ActionEvent event, String Msg, String Title) throws IOException {
		switchWindow("PopUpMsg.fxml");
		Stage win_instance;
		PersonViewController controller = loader.getController();
		controller.setErrLabel(Msg);
		win_instance = new Stage();
		win_instance.setScene(tableViewScene);
		win_instance.setTitle(Title);
		win_instance.show();
	}


	@FXML
	void Occasional_Customer_Order(ActionEvent event) throws IOException, ParseException {

		String id = Occasional_Customer_id.getText();
		String car_number = Occasional_Customer_car_number.getText();
		String car_park = Occasional_Customer_parking_lot_btn.getText();
		//		System.out.println(car_park);
		//= Occasional_Customer_car_park.getText();
		String email = Occasional_Customer_email.getText();
		LocalDate end_date = Occasional_Customer_end_date.getValue();
		String end_time = Occasional_Customer_end_time.getText();
		String password = Occasional_Customer_password.getText();

		String payMethod = "";
		if (Occasional_Customer_credit_card.isSelected())
			payMethod = "CREDIT";
		if (Occasional_Customer_cash.isSelected())
			payMethod = "CASH";

		String err_msg = Occasional_Customer_inputIsValid(id, car_number, car_park, email, end_date, end_time, password);
		String first_name = "Occasional";
		String last_name = "Occasional";
		String phone = "Occasional";


		//		Timestamp end_time_overall = new Timestamp(end_date_year, end_date_month, end_date_day, end_date_hours, end_date_minutes, 0, 0);

		if (!err_msg.isEmpty()) {
			//TODO error msg
			createMsg(event, err_msg, "error msg");

			return;
		}
		String[] end_time_by_parts = end_time.split(":");
		int end_date_hours = Integer.valueOf(end_time_by_parts[0]);
		int end_date_minutes = Integer.valueOf(end_time_by_parts[1]);
		String end_date_string = Occasional_Customer_end_date.getValue().toString();
		Timestamp timestamp = Timestamp.valueOf(end_date_string + " " + end_date_hours + ":" + end_date_minutes + ":00.0");
		timestamp.setHours(end_date_hours);
		timestamp.setMinutes(end_date_minutes);

		client.addNewCustomer(id, first_name, last_name, "Occasional", Customer.type.OCCASIONAL, email, phone);
		client.addNewCar(car_number, id);
		if (client.addOccasionalOrder(car_number, timestamp, car_park, payMethod)) {

			//TODO add success message to gui
		} else {
			//TODO error msg
			createMsg(event, "could not add order.", "error msg");
			return;
		}
		createMsg(event, "order complete.", "succ msg");
	}

	private String Occasional_Customer_inputIsValid(String id, String car_number, String car_park, String
			email, LocalDate end_date, String end_time, String password) throws ParseException {
		String msg = "";
		int dateFlag = 1;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //parse start and end time

		if (id.isEmpty())
			msg = msg + "id is empty\n";
		if (car_number.isEmpty())
			msg = msg + "car_number is empty\n";
		if (car_park.isEmpty())
			msg = msg + "car_park is empty\n";
		if (email.isEmpty())
			msg = msg + "email is empty\n";
		if (password.isEmpty())
			msg = msg + "password is empty\n";



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
		if (end_time.isEmpty())
			msg = msg + "end time is empty\n";


		if (end_time.isEmpty())
			msg = msg + "start time is empty\n";
		else if (!check_hour_minute(end_time)) {
			msg = msg + "fix starting hour and minute \n";
			dateFlag = 0;
		}


		if (end_date != null && dateFlag == 1) {
			String end = end_date.toString() + " " + end_time;

			Date e = df.parse(end);
			Date now = new Date();

			if (e.before(now))
				msg = msg + "ending date must be in the future\n";


			/*LocalDate today = LocalDate.now();
            if(today.isAfter(end_date) || today.isAfter(start_date))
                msg = msg + "choose only future dates\n";
            if(start_date.isAfter(end_date))
                msg = msg + "starting date must be prior to ending date\n";

			 */
		} else if (end_date == null) {
			msg = msg + "fill the date please\n";
		}


		return msg;
	}

	@FXML
	void Occasional_Customer_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event, "login page");
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

	public void Occasional_Customer_load_car_lots() {


		List<String> ls = Client.getStations();
		Occasional_Customer_parking_lot_btn.getItems().clear();

		for (String str : ls) {
			MenuItem mi = new MenuItem(str);
			mi.setText(str);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Occasional_Customer_parking_lot_btn.setText(mi.getText());
				}
			});
			Occasional_Customer_parking_lot_btn.getItems().add(mi);
		}


	}


	//---------------------------^^Occasional Customer^^----------------------------------


	//------------------------------------In Advance Customer ----------------------------


	@FXML
	private Button In_Advance_Customer_back_btn;

	@FXML
	private TextField In_Advance_Customer_end_time;

	@FXML
	private SplitMenuButton In_Advance_Customer_parking_lot_split_menu_btn;

	@FXML
	private TextField In_Advance_Customer_id;

	@FXML
	private RadioButton In_Advance_Customer_cash;

	@FXML
	private ToggleGroup money_toggle;

	@FXML
	private PasswordField In_Advance_Customer_password;

	@FXML
	private TextField In_Advance_Customer_phone_number;

	@FXML
	private DatePicker In_Advance_Customer_end_date;

	@FXML
	private TextField In_Advance_Customer_car_number;

	@FXML
	private TextField In_Advance_Customer_first_name;

	@FXML
	private TextField In_Advance_Customer_email;

	@FXML
	private RadioButton In_Advance_Customer_credit_card;

	@FXML
	private Button In_Advance_Customer_order;

	@FXML
	private DatePicker In_Advance_Customer_start_date;

	@FXML
	private TextField In_Advance_Customer_last_name;

	@FXML
	private TextField In_Advance_Customer_start_time;


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
	void In_Advance_Customer_Order(ActionEvent event) throws IOException, ParseException {
		Client client = new Client();
		String clientID = In_Advance_Customer_id.getText();
		String first_name = In_Advance_Customer_first_name.getText();
		String last_name = In_Advance_Customer_last_name.getText();
		String carID = In_Advance_Customer_car_number.getText();
		String email = In_Advance_Customer_email.getText();
		LocalDate start_date = In_Advance_Customer_start_date.getValue();
		LocalDate end_date = In_Advance_Customer_end_date.getValue();
		String start_time = In_Advance_Customer_start_time.getText();
		String end_time = In_Advance_Customer_end_time.getText();
		String phone = In_Advance_Customer_phone_number.getText();
		String car_park = In_Advance_Customer_parking_lot_split_menu_btn.getText();
		String password = In_Advance_Customer_password.getText();
		//In_Advance_Customer_parking_lot_split_menu_btn.
		String err_msg = In_Advance_Customer_inputIsValid(clientID, carID, email, start_date, start_time, end_date, end_time, password);

		if (!In_Advance_Customer_credit_card.isSelected() && !In_Advance_Customer_cash.isSelected())
			err_msg = err_msg + "please select payment method\n";

		String payMethod = "";
		if (In_Advance_Customer_credit_card.isSelected())
			payMethod = "CREDIT";
		if (In_Advance_Customer_cash.isSelected())
			payMethod = "CASH";

		if (!err_msg.isEmpty()) {
			//TODO error msg
			//System.out.println(err_msg);
			createMsg(event, err_msg, "error msg");

			return;
		}

		int status = client.AdvanceOneTimeOrder(clientID, carID, car_park, email, start_date, start_time, end_date, end_time);

		String start_date_string = "" + start_date.toString();// In_Advance_Customer_start_date.getValue();
		String end_date_string = "" + end_date.toString();// In_Advance_Customer_end_date.getValue();


		String[] end_time_by_parts = end_time.split(":");
		String[] start_time_by_parts = end_time.split(":");
		int start_date_hours = Integer.valueOf(start_time_by_parts[0]);
		int start_date_minutes = Integer.valueOf(start_time_by_parts[1]);
		int end_date_hours = Integer.valueOf(end_time_by_parts[0]);
		int end_date_minutes = Integer.valueOf(end_time_by_parts[1]);

		Timestamp start_timestamp = Timestamp.valueOf(start_date_string + " " + start_date_hours + ":" + start_date_minutes + ":00.0");

		start_timestamp.setHours(start_date_hours);
		start_timestamp.setMinutes(start_date_minutes);

		Timestamp end_timestamp = Timestamp.valueOf(end_date_string + " " + end_date_hours + ":" + end_date_minutes + ":00.0");
		end_timestamp.setHours(end_date_hours);
		end_timestamp.setMinutes(end_date_minutes);


		client.addNewCustomer(clientID, first_name, last_name, password, Customer.type.ADVANCED, email, phone);
		client.addNewCar(carID, clientID);
		if (client.addInAdvanceOrder(carID, start_timestamp, end_timestamp, car_park, payMethod)) {

			//TODO add success message to gui
		} else {
			//TODO error msg
			createMsg(event, "could not add order.", "error msg");
			return;
		}
		String succ = "order was added succesfully\n";
		createMsg(event,succ, "succ msg");

		
	}


	private String In_Advance_Customer_inputIsValid(String id, String car_number, String email, LocalDate
			start_date, String start_time, LocalDate end_date, String end_time, String password) throws ParseException {


		String msg = "";
		int dateFlag = 1;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //parse start and end time


		if (id.isEmpty())
			msg = msg + "id is empty\n";
		if (car_number.isEmpty())
			msg = msg + "car_number is empty\n";

		if (email.isEmpty())
			msg = msg + "email is empty\n";

		if (password.isEmpty()) {
			msg = msg + "password is empty\n";

		}

		if (start_time.isEmpty())
			msg = msg + "start time is empty\n";


		else if (!check_hour_minute(start_time)) {
			msg = msg + "fix starting hour and minute \n";
			dateFlag = 0;
		}


		if (end_time.isEmpty())
			msg = msg + "end time is empty\n";
		else if (!check_hour_minute(end_time)) {
			msg = msg + "fix ending hour and minute\n";
			dateFlag = 0;
		}


		if (start_date != null && end_date != null && dateFlag == 1) {
			String start = start_date.toString() + " " + start_time;
			String end = end_date.toString() + " " + end_time;

			Date s = df.parse(start);
			Date e = df.parse(end);

			if (e.before(s))
				msg = msg + "starting date must be prior to ending date\n";


			/*LocalDate today = LocalDate.now();
            if(today.isAfter(end_date) || today.isAfter(start_date))
                msg = msg + "choose only future dates\n";
            if(start_date.isAfter(end_date))
                msg = msg + "starting date must be prior to ending date\n";

			 */
		} else if (start_date == null && end_date == null) {
			msg = msg + "fill all the dates please\n";
		}


		return msg;
	}


	boolean check_hour_minute(String hour_minute) {

		Pattern pattern;
		Matcher matcher;
		String TIME12HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		pattern = Pattern.compile(TIME12HOURS_PATTERN);
		matcher = pattern.matcher(hour_minute);
		return (matcher.matches());
	}

	public void In_Advance_Customer_load_car_lots() {
		List<String> ls = Client.getStations();
		In_Advance_Customer_parking_lot_split_menu_btn.getItems().clear();

		for (String str : ls) {
			MenuItem mi = new MenuItem(str);
			mi.setText(str);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					In_Advance_Customer_parking_lot_split_menu_btn.setText(mi.getText());
				}
			});
			In_Advance_Customer_parking_lot_split_menu_btn.getItems().add(mi);
		}
	}


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

				setDisable(empty || date.compareTo(today) < 0);
			}
		});
	}






	/* @FXML
    void DatePicked(ActionEvent event) {

    }*/
	//--------------------------------^^^^^In Advance Customer^^^^--------------------------


	//----------------------------Ceo view-----------------------------

	@FXML
	private SplitMenuButton Ceo_View_level_menu;

	@FXML
	private Button Ceo_View_back_btn;

	@FXML
	private SplitMenuButton Ceo_View_location_menu;

	@FXML
	private Text Ceo_View_hello_label;

	@FXML
	private Button Ceo_View_print_report_btn;

	@FXML
	void Ceo_View_back(ActionEvent event) throws IOException {
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event, "login page");

	}

	@FXML
	void Ceo_View_print_report(ActionEvent event) throws IOException {
		String level = Ceo_View_level_menu.getText();
		String location = Ceo_View_location_menu.getText();
		String err_msg = "";
		err_msg = Ceo_Check_Valid_input_report(level, location);


		if (!err_msg.isEmpty()) {
			createMsg(event, err_msg, "error msg");
			return;
		}
		int location_id = OrderController.getOrderParkingId(location);
		int level_int = Integer.parseInt(level);

		ShowReport(level_int, location_id);


	}

	void setCeo_name(String name) {
		Ceo_View_hello_label.setText("hello " + name);
	}

	String Ceo_Check_Valid_input_report(String level, String location) {
		String err_msg = "";

		if (level.equals("Level"))
			err_msg += "please provide level\n";
		if (location.equals("Location"))
			err_msg += "please provide location\n";


		return err_msg;
	}

	void CeoLoad() {
		List<String> ls = Client.getStations();
		Ceo_View_location_menu.getItems().clear();
		Ceo_View_level_menu.getItems().clear();

		String[] level_arr = {"0", "1", "2"};

		for (String str : ls) {
			MenuItem mi = new MenuItem(str);
			mi.setText(str);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Ceo_View_location_menu.setText(mi.getText());
				}
			});
			Ceo_View_location_menu.getItems().add(mi);
		}


		for (String i : level_arr) {
			MenuItem mi = new MenuItem(i);
			mi.setText(i);
			mi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Ceo_View_level_menu.setText(mi.getText());
				}
			});
			Ceo_View_level_menu.getItems().add(mi);
		}


	}


	//---------------------^^^^^-Ceo View-^-^^-^^^^^------------------------------------


	//-----------------------------------Report----------------------------------

	public int[] fillArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; ++i) {
			arr[i] = i;
		}
		return arr;
	}

	public void ShowReport(int Level, int park_lot_id) {
		Stage st = new Stage();
		GridPane root = new GridPane();
		Client cli = new Client();
		int[][][] c = cli.getParkSoltStatus(park_lot_id);

		int maxRow = 0;
		for (int i = 0; i < c[Level].length; i++)
			if (c[Level][i].length > maxRow)
				maxRow = c[Level][i].length;


		int[] arr_col = fillArray(maxRow);
		int[] arr_row = fillArray(c[Level].length);

		for (int x = 0; x < maxRow; x++) {
			TextField tf = new TextField();

			tf.setPrefHeight(25);
			tf.setPrefWidth(25);
			tf.setEditable(false);
			tf.setText("" + arr_col[x] + "");
			tf.setMinSize(25, 25);
			root.setRowIndex(tf, 0);
			root.setColumnIndex(tf, x + 1);
			root.getChildren().add(tf);
		}

		for (int y = 0; y < c[Level].length; y++) {
			for (int x = 0; x < c[Level][y].length; x++) {
				if (x == 0) {
					TextField tf = new TextField();
					tf.setPrefHeight(25);
					tf.setPrefWidth(25);
					tf.setMinSize(25, 25);
					tf.setEditable(false);
					tf.setText("" + arr_row[y] + "");
					root.setRowIndex(tf, y + 1);
					root.setColumnIndex(tf, 0);
					root.getChildren().add(tf);
				}

				Rectangle r = new Rectangle();
				r.setWidth(50);
				r.setHeight(50);
				r.setArcWidth(70);
				r.setArcHeight(70);
				r.setStroke(Color.BLACK);

				if (c[Level][y][x] == 0)
					r.setFill(Color.BLUE);
				if (c[Level][y][x] == 1)
					r.setFill(Color.RED);
				if (c[Level][y][x] == 2)
					r.setFill(Color.GREEN);
				if (c[Level][y][x] == 3)
					r.setFill(Color.BLACK);
				root.setRowIndex(r, y + 1);
				root.setColumnIndex(r, x + 1);
				root.getChildren().add(r);
			}
		}

		ScrollPane sp = new ScrollPane(root);
		sp.setFitToWidth(true);

		Scene scene = new Scene(sp, 550, 550);
		st.setTitle("Report");
		st.setScene(scene);
		st.show();
	}


	//-----------------------------------^^Report^^----------------------------


	void log_out(ActionEvent event) throws IOException {
		client.logoutClient(pid);
		
		String url = "LoginView.fxml";
		switchWindow(url);
		switchScene(event, "login page");

	}


	private FXMLLoader loader;
	private Parent tableViewParent;
	private Scene tableViewScene;
	WorkerInterfaceController wIC;


	void setWorkerInterfaceController(WorkerInterfaceController wIC) {
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
		window.setScene(tableViewScene);
		window.setTitle(pageTitle);
		window.show();
	}
	//----------------------------------------------------------

	void setWorkerName(String name) {
		worker_name_label.setText("Hello " + name);
	}


	void setManagerName(String name) {
		manager_name_label.setText("Hello " + name);

	}

}
