package application.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import application.model.Variables;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddEmployeeController {
	
	private Stage currentStage;
	@FXML
	private TextField tf_name;
	@FXML
	private TextField tf_ssn;
	@FXML
	private TextField tf_address;
	@FXML
	private TextField tf_dep;
	@FXML
	private Button btn_sumbit;
	
	public AddEmployeeController(){
		
	}
	@FXML
	public void setCurrentStage(Stage dialogStage) {
		this.currentStage = dialogStage;
	}
	@FXML
	private void initialize() {
		
	}
	@FXML
	private void handleSubmit() {

		if (!this.tf_name.getText().equals("") &&!this.tf_ssn.getText().equals("") 
				&& !this.tf_address.getText().equals("") && !this.tf_dep.getText().equals("")) {
			try{
				   String driver = "com.mysql.cj.jdbc.Driver";
				   String url = "jdbc:mysql://localhost:3306/db";
				   String username = "root";
				   String password = "12345678";
				   Class.forName(driver);
	
				   Connection conn = DriverManager.getConnection(url,username,password);
				   System.out.println("Connected");
				  
	            //do real work
	             Statement stmt = conn.createStatement();
	             Variables.max_id++;

	             String query = "INSERT INTO EMPLOYEE VALUES (" + Variables.max_id+", '" + tf_name.getText() + "'," + tf_ssn.getText() + ", '" 
	             + tf_address.getText() + "', '" + tf_dep.getText() + "');" ;
	             System.out.println(query);
		         int set = stmt.executeUpdate(query);
		         this.currentStage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING);
        	alert.initOwner(this.currentStage);
        	alert.setTitle("Error!");
        	alert.setContentText("Please fill info in all fields!");
        	alert.showAndWait();
		}
	}
	
}
