package application.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;
import application.model.Employee;
import application.model.Variables;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class TableController {
	private Stage currentStage;
	@FXML
	private TableView table;
	@FXML
	private TableColumn c1;
	@FXML
	private TableColumn c2;
	@FXML
	private TableColumn c3;
	@FXML
	private TableColumn c4;
	@FXML
	private TableColumn c5;
	@FXML
	private Text text;
	
	private ArrayList<Employee> employees = new ArrayList<>();

	public TableController(){
		
	}
	@FXML
	public void setCurrentStage(Stage dialogStage) {

		this.currentStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
//			System.out.println("12222222");
		setup();
		
	}
	
	public void setup() {
		try{
			   String driver = "com.mysql.cj.jdbc.Driver";
			   String url = "jdbc:mysql://localhost:3306/db";
			   String username = "root";
			   String password = "12345678";
			   Class.forName(driver);

			   Connection conn = DriverManager.getConnection(url,username,password);
			   System.out.println("Connected");
			  
         //do real work
		Statement otmt = conn.createStatement();
          Statement stmt = conn.createStatement();
          ResultSet set = stmt.executeQuery("SELECT * FROM EMPLOYEE;");
          while (set.next()) {
         	 //get ID
         	 String id = "" + set.getInt("ID");
         	 if (Integer.parseInt(id) > Variables.max_id) {
         		Variables.max_id = Integer.parseInt(id);
         	 }
         	 String name = set.getString("NAME");
         	 String ssn = ""+set.getInt("SSN");
         	 String address = set.getString("ADDRESS");
         	 String department = set.getString("DEPARTMENT");
              System.out.println("id"+id);
              System.out.println("name"+name);
              System.out.println("SSN"+ssn);

              Employee e = new Employee(id, name, ssn, address, department);
//      		System.out.println(e.getID());

              employees.add(e);
          }
        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//time to show
		ObservableList showing = FXCollections.observableArrayList(employees);
		
		//set up table and it's columns
//		System.out.println(showing);

		table.setItems(showing);
			
		c1.setCellValueFactory(new PropertyValueFactory("id"));
		c2.setCellValueFactory(new PropertyValueFactory("name"));
		c3.setCellValueFactory(new PropertyValueFactory("ssn"));
		c4.setCellValueFactory(new PropertyValueFactory("address"));
		c5.setCellValueFactory(new PropertyValueFactory("department"));
	}
	
	@FXML
	public void handleADD() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AddEmployeeView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add new employee");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddEmployeeController controller = loader.getController();
            controller.setCurrentStage(dialogStage);
            dialogStage.showAndWait();
//            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
	}
	
	public void handleDelete() {
		
	}
	
	public void handleUpdate() {
		table.setItems(FXCollections.observableArrayList());
		employees = new ArrayList<>();

		setup();
	}
	
}
