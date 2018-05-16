package Server;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerController implements Initializable {
    private static final int DEFAULT_PORT = 5555;

	
	 @FXML
	    private Button conbut;

	    @FXML
	    private PasswordField passtxt;

	    @FXML
	    private TextField usertxt;
	
	    
	    public void start(Stage primaryStage) throws Exception {

			String title = "Server";
			String srcFXML = "/Server/ServerApp.fxml";
			String srcCSS = "/Server/application.css";
			
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(srcFXML));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource(srcCSS).toExternalForm());
				primaryStage.setTitle(title);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				e.printStackTrace();
			}
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					// TODO Auto-generated method stub
					Platform.exit();
				}
			});

		}
		

		public void onClicked(ActionEvent event)
		{
			
			int port = DEFAULT_PORT; // Port to listen on
			SystemServer.user = usertxt.getText();
			SystemServer.password = passtxt.getText();	
			SystemServer sc = new SystemServer(port);
			try {
				// try to connect to db
				sc.connectToDB();
				
				
				
				sc.listen(); // Start listening for connections
				System.out.println(String.format("Server has started listening on port: %d" , port));
				
			} 
			catch (SQLException ex)
			{
				displayAlert(AlertType.ERROR, "Database Connection Error", "Error!", "Could not connect to sql!");
			}
			catch (IOException ex)
			{
				displayAlert(AlertType.ERROR, "Connection Error", "Error!", "Could not listen for clients!");
			}
			
			
		}
		
		private void displayAlert(AlertType type , String title , String header , String content)
		{
			Alert alert = new Alert(type);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
		}
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
