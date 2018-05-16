package Server;

import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUII extends Application {

	
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * create GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		ServerController ser = new ServerController();
		ser.start(primaryStage);
	}


}