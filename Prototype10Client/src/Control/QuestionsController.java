package Control;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Entity.Question;
import Entity.SystemClient;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
// this is a test
// This class responsible on Reading and triggering each button of GUI
public class QuestionsController implements Initializable {

	final int DEFUALT_PORT = 5555;
	final String msgToServer = "getQuestion";
	// define each part of GUI from FXML (scene builder)
    @FXML
    private TextField qus_textbox;

    @FXML
    private Button upd_button;

    @FXML
    private RadioButton ans2_radio;

    @FXML
    private RadioButton ans1_radio;

    @FXML
    private TextField author_text;

    @FXML
    private ComboBox<Question> ques_box;

    @FXML
    private RadioButton ans3_radio;

    @FXML
    private RadioButton ans4_radio;
// Define entity of client (for using GUI)
    private SystemClient client;
// Create ObservableList (this is list of listeners)
	public static ObservableList<Question> observelist;
// Create arrayList that will be get the question into this arrayList. The arrayList will consist only Question type(ID,Author,4 questions,correct answer)
	private ArrayList<Question> questionbservable;
	
// This function set into the ComboBox in GUI all question (and other parts of Question type) one after one; function gets ArrayList that consist question type
	private void setQuestionList(ArrayList<Question> qList)
	{ 	//Create new arrayList
		questionbservable = new ArrayList<>();
// Run on the arrayList with questions and add to the new arrayList
		for (Question q : qList)
			questionbservable.add(q);
		
		observelist = FXCollections.observableArrayList(questionbservable); // The comboBox knows to get only observeList type
		observelist.addListener(new ListChangeListener<Question>() { //add listener to the comboBox of the question

			
// Case: The user change the answer to the question
			@Override
			public void onChanged(Change<? extends Question> c) {
				// TODO Auto-generated method stub
					clearComponents(); //clean all fields of GUI (by special function) 
					ques_box.setItems(observelist);	//set into comboBox the questions
		
			} 
		});
// If the user didnt change the answer -> set into comboBox the question
		ques_box.setItems(observelist);

		
	}	
	
// Wait to the server until load all  list of questions
	public static void handleListUpdateFromClient(ArrayList<Question> qList)
	{//loading by question after question
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				observelist.clear();
				for (Question q : qList)
					observelist.add(q);
			}
		});
		
	} 

// Loading the list into comboBox
	private void initComboBox() {
		setQuestionList(client.getqList());
	}
	
	
// function presents all question details on the screen.
	private void setQuestionDetails(Question question) {
		// TODO Auto-generated method stub
		qus_textbox.setText(String.format("%s", question.getques()));
		author_text.setText(question.getauthor());
//get the number of correct answer for specific question
		int correct = question.getcurrent_ans();
		
// Marker the correct answer on the GUI, cases:
		if (correct == 1)
		{
			ans1_radio.setSelected(true);
			ans2_radio.setSelected(false);
			ans3_radio.setSelected(false);	
			ans4_radio.setSelected(false);	
		}
		else if (correct == 2)
		{
			ans1_radio.setSelected(false);
			ans2_radio.setSelected(true);
			ans3_radio.setSelected(false);	
			ans4_radio.setSelected(false);
		}
		else if (correct == 3)
		{
			ans1_radio.setSelected(false);
			ans2_radio.setSelected(false);
			ans3_radio.setSelected(true);	
			ans4_radio.setSelected(false);
		}
		else if (correct == 4)
		{
			ans1_radio.setSelected(false);
			ans2_radio.setSelected(false);
			ans3_radio.setSelected(false);	
			ans4_radio.setSelected(true);	
		}
// Set the answer into the GUI (4 answers)
		ans1_radio.setText(question.getpos_ans()[0]);
		ans2_radio.setText(question.getpos_ans()[1]);
		ans3_radio.setText(question.getpos_ans()[2]);
		ans4_radio.setText(question.getpos_ans()[3]);
		
		
	}
	
// open THe GUI window of "Questions update"
	public void start(Stage primaryStage) throws Exception {
// The place of the GUI details sets into String variables
		String title = "Questions";
		String srcFXML = "/application/prototype.fxml";
		String srcCSS = "/application/application.css";
		
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
// Event of closing window -> close the application
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});

	}
	
	
	
// After change the answer of the question update the true answer ; return the question with updated details
	private Question getCmbSelectedQuestion()
	{
		Question q = null;
		int selectedIndex = ques_box.getSelectionModel().getSelectedIndex();
		if (selectedIndex > -1) 
			q = client.getqList().get(selectedIndex);
		else //error case -> message of wrong step from user side
			displayAlert(AlertType.ERROR,"Question Selection" , "No Question Selected" , "Please choose item from the combobox to continue");
		
		return q;
	}	
	
	
// After The changing the answer to the question -> clean all fields of GUI from the last question into default mode
	private void clearComponents()
	{
		qus_textbox.clear();	//clean the question list
		author_text.clear();	//clean the author window
		ans1_radio.setSelected(false);	//set the first answer -> false	
		ans2_radio.setSelected(false);	//set the second answer -> false	
		ans3_radio.setSelected(false);	//set the third answer -> false	
		ans4_radio.setSelected(false);	//set the fourth answer -> false	
		ques_box.getSelectionModel().select(-1);	//set the question list window to the default message -> 'Select Question'
		ans1_radio.setText("Answer 1");		//clean the first answer text field
		ans2_radio.setText("Answer 2");		//clean the second answer text field
		ans3_radio.setText("Answer 3");		//clean the third answer text field
		ans4_radio.setText("Answer 4");		//clean the fourth answer text field
	}
	
// After choosing specific question -> set the correct answer into GUI window
	public int getCorrectAnswer()
	{
		int correct = -1;
		
		if (ans1_radio.isSelected())
			correct = 1;
		else if (ans2_radio.isSelected())
			correct = 2;
		else if (ans3_radio.isSelected())
			correct = 3;
		else if (ans4_radio.isSelected())
			correct = 4;
		
		return correct;
	}
	
// function that check and save the question after the user press on 'Update' ;the function is listener
	public void onSaveClicked(ActionEvent event) throws Exception
	{
		Question question = getCmbSelectedQuestion();
		int correct_Answer = getCorrectAnswer(); //get the correct answer into int variable for future checks
		if (correct_Answer == -1) // wrong case -> print message and return
		{
			displayAlert(AlertType.ERROR,"Incorrect Information" , "Invalid Input" , "Please enter valid details");
			return;	
		}
			//set the new correct answer
			updateQuestion(question, correct_Answer);
			
			try {// send the new correct answer to the server (for updating into DB)
					client.sendToServer(question);
					displayAlert(AlertType.INFORMATION, "Save Question", "Save Success", String.format("Question: %s has been updated successfuly", question.toString()));
					clearComponents();
				}
				catch (Exception e) {
					// TODO: handle exception
					displayAlert(AlertType.ERROR, "Connection Error", "Faild to connect", e.getMessage());
				}
		}
	
// function that sets the correct answer
	private void updateQuestion(Question question, int correct_ans)
	{
		// TODO Auto-generated method stub
		question.setTcurrent_ans(correct_ans);
	}
// After Updating in DB this function pops a new window that tell us that the process has finished successfully 
	private void displayAlert(AlertType type , String title , String header , String content)
	{
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
// Event for other cases -> not change the 'true answer' 
	private void setCmbSelectedIndexChange() {
		ques_box.valueProperty().addListener(new ChangeListener<Question>() {
	
			@Override
			public void changed(ObservableValue<? extends Question> observable, Question oldValue, Question newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					setQuestionDetails(newValue);
				}
			}
		});
	}
	
	
// Create connection to the server
	private SystemClient initClient() {
		String host;
		int port;
		try {
			File cfgFile = new File(System.getProperty("user.dir")+"/" +"cfgClient.txt");
			FileReader fileReader = new FileReader(cfgFile);
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			host = bufferedReader.readLine();
			port = Integer.parseInt(bufferedReader.readLine());
		} catch (Exception e) {
			// TODO: handle exception
			host = "localhost";
			port = DEFUALT_PORT;
		}

		return new SystemClient(host, port);

	}
	
// Give all the question to the user
	private void initClientConnection() {	
		client = initClient();
		try {
			client.sendToServer(msgToServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// Wait until the server will finish the loading the list
	private void syncClientQuestions()
	{
		synchronized (client) {
			if (client.getqList() == null) {
				try {
					client.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

// Auto event of the controller. This event must be in each controller
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		initClientConnection();
		syncClientQuestions();
		initComboBox();
		setCmbSelectedIndexChange();
	}
}



	
	
	
	
	
	
	
	

