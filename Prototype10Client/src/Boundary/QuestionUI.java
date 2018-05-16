package Boundary;

import Control.QuestionsController;
import javafx.application.Application;
import javafx.stage.Stage;


// QuestionUI responsible on question GUI run (main + function of start)
public class QuestionUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	// 'start' function responsible on the running GUI of Question update
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

		QuestionsController questionController = new QuestionsController(); //create new entity of question Controller type
		questionController.start(arg0); //run the entity of question Controller type
	}
}
