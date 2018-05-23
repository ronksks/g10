/*     */ package Control;
/*     */ 
/*     */ import Entity.Question;
/*     */ import Entity.SystemClient;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.application.Platform;
/*     */ import javafx.beans.property.ObjectProperty;
/*     */ import javafx.beans.value.ChangeListener;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ListChangeListener;
/*     */ import javafx.collections.ListChangeListener.Change;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Alert.AlertType;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ComboBox;
/*     */ import javafx.scene.control.RadioButton;
/*     */ import javafx.scene.control.SingleSelectionModel;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ public class QuestionsController implements javafx.fxml.Initializable
/*     */ {
/*  36 */   final int DEFUALT_PORT = 5555;
/*     */   
/*     */ 
/*     */   static final String msgToServer = "getQuestion";
/*     */   
/*     */ 
/*     */   @FXML
/*     */   private TextField qus_textbox;
/*     */   
/*     */   @FXML
/*     */   private Button upd_button;
/*     */   
/*     */   @FXML
/*     */   private RadioButton ans2_radio;
/*     */   
/*     */   @FXML
/*     */   private RadioButton ans1_radio;
/*     */   
/*     */   @FXML
/*     */   private TextField author_text;
/*     */   
/*     */   @FXML
/*     */   private ComboBox<Question> ques_box;
/*     */   
/*     */   @FXML
/*     */   private RadioButton ans3_radio;
/*     */   
/*     */   @FXML
/*     */   private RadioButton ans4_radio;
/*     */   
/*  66 */   public static String address = "localhost";
/*  67 */   public static int port = 5555;
/*     */   
/*     */ 
/*     */   public static SystemClient client;
/*     */   
/*     */   public static ObservableList<Question> observelist;
/*     */   
/*     */   private ArrayList<Question> questionbservable;
/*     */   
/*     */ 
/*     */   private void setQuestionList(ArrayList<Question> qList)
/*     */   {
/*  79 */     this.questionbservable = new ArrayList();
/*     */     
/*  81 */     for (Question q : qList) {
/*  82 */       this.questionbservable.add(q);
/*     */     }
/*  84 */     observelist = FXCollections.observableArrayList(this.questionbservable);
/*  85 */     observelist.addListener(new ListChangeListener()
/*     */     {
/*     */ 
/*     */ 
/*     */       public void onChanged(ListChangeListener.Change<? extends Question> c)
/*     */       {
/*     */ 
/*  92 */         QuestionsController.this.clearComponents();
/*  93 */         QuestionsController.this.ques_box.setItems(QuestionsController.observelist);
/*     */       }
/*     */       
/*     */ 
/*  97 */     });
/*  98 */     this.ques_box.setItems(observelist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void handleListUpdateFromClient(ArrayList<Question> qList)
/*     */   {
/* 106 */     Platform.runLater(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 110 */         QuestionsController.observelist.clear();
/* 111 */         for (Question q : QuestionsController.this) {
/* 112 */           QuestionsController.observelist.add(q);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void initComboBox()
/*     */   {
/* 120 */     setQuestionList(client.getqList());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setQuestionDetails(Question question)
/*     */   {
/* 127 */     this.qus_textbox.setText(String.format("%s", new Object[] { question.getques() }));
/* 128 */     this.author_text.setText(question.getauthor());
/*     */     
/* 130 */     int correct = question.getcurrent_ans();
/*     */     
/*     */ 
/* 133 */     if (correct == 1)
/*     */     {
/* 135 */       this.ans1_radio.setSelected(true);
/* 136 */       this.ans2_radio.setSelected(false);
/* 137 */       this.ans3_radio.setSelected(false);
/* 138 */       this.ans4_radio.setSelected(false);
/*     */     }
/* 140 */     else if (correct == 2)
/*     */     {
/* 142 */       this.ans1_radio.setSelected(false);
/* 143 */       this.ans2_radio.setSelected(true);
/* 144 */       this.ans3_radio.setSelected(false);
/* 145 */       this.ans4_radio.setSelected(false);
/*     */     }
/* 147 */     else if (correct == 3)
/*     */     {
/* 149 */       this.ans1_radio.setSelected(false);
/* 150 */       this.ans2_radio.setSelected(false);
/* 151 */       this.ans3_radio.setSelected(true);
/* 152 */       this.ans4_radio.setSelected(false);
/*     */     }
/* 154 */     else if (correct == 4)
/*     */     {
/* 156 */       this.ans1_radio.setSelected(false);
/* 157 */       this.ans2_radio.setSelected(false);
/* 158 */       this.ans3_radio.setSelected(false);
/* 159 */       this.ans4_radio.setSelected(true);
/*     */     }
/*     */     
/* 162 */     this.ans1_radio.setText(question.getpos_ans()[0]);
/* 163 */     this.ans2_radio.setText(question.getpos_ans()[1]);
/* 164 */     this.ans3_radio.setText(question.getpos_ans()[2]);
/* 165 */     this.ans4_radio.setText(question.getpos_ans()[3]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void start(Stage primaryStage)
/*     */     throws Exception
/*     */   {
/* 173 */     String title = "Questions";
/* 174 */     String srcFXML = "/application/prototype.fxml";
/* 175 */     String srcCSS = "/application/application.css";
/*     */     try
/*     */     {
/* 178 */       FXMLLoader loader = new FXMLLoader();
/* 179 */       loader.setLocation(getClass().getResource(srcFXML));
/* 180 */       Parent root = (Parent)loader.load();
/* 181 */       Scene scene = new Scene(root);
/* 182 */       scene.getStylesheets().add(getClass().getResource(srcCSS).toExternalForm());
/* 183 */       primaryStage.setTitle(title);
/* 184 */       primaryStage.setScene(scene);
/* 185 */       primaryStage.show();
/*     */     }
/*     */     catch (Exception e) {
/* 188 */       System.out.println(e);
/* 189 */       e.printStackTrace();
/*     */     }
/*     */     
/* 192 */     primaryStage.setOnCloseRequest(new EventHandler()
/*     */     {
/*     */       public void handle(WindowEvent event) {}
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Question getCmbSelectedQuestion()
/*     */   {
/* 208 */     Question q = null;
/* 209 */     int selectedIndex = this.ques_box.getSelectionModel().getSelectedIndex();
/* 210 */     if (selectedIndex > -1) {
/* 211 */       q = (Question)client.getqList().get(selectedIndex);
/*     */     } else {
/* 213 */       displayAlert(Alert.AlertType.ERROR, "Question Selection", "No Question Selected", "Please choose item from the combobox to continue");
/*     */     }
/* 215 */     return q;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void clearComponents()
/*     */   {
/* 222 */     this.qus_textbox.clear();
/* 223 */     this.author_text.clear();
/* 224 */     this.ans1_radio.setSelected(false);
/* 225 */     this.ans2_radio.setSelected(false);
/* 226 */     this.ans3_radio.setSelected(false);
/* 227 */     this.ans4_radio.setSelected(false);
/* 228 */     this.ques_box.getSelectionModel().select(-1);
/* 229 */     this.ans1_radio.setText("Answer 1");
/* 230 */     this.ans2_radio.setText("Answer 2");
/* 231 */     this.ans3_radio.setText("Answer 3");
/* 232 */     this.ans4_radio.setText("Answer 4");
/*     */   }
/*     */   
/*     */ 
/*     */   public int getCorrectAnswer()
/*     */   {
/* 238 */     int correct = -1;
/*     */     
/* 240 */     if (this.ans1_radio.isSelected()) {
/* 241 */       correct = 1;
/* 242 */     } else if (this.ans2_radio.isSelected()) {
/* 243 */       correct = 2;
/* 244 */     } else if (this.ans3_radio.isSelected()) {
/* 245 */       correct = 3;
/* 246 */     } else if (this.ans4_radio.isSelected()) {
/* 247 */       correct = 4;
/*     */     }
/* 249 */     return correct;
/*     */   }
/*     */   
/*     */   public void onSaveClicked(ActionEvent event)
/*     */     throws Exception
/*     */   {
/* 255 */     Question question = getCmbSelectedQuestion();
/* 256 */     int correct_Answer = getCorrectAnswer();
/* 257 */     if (correct_Answer == -1)
/*     */     {
/* 259 */       displayAlert(Alert.AlertType.ERROR, "Incorrect Information", "Invalid Input", "Please enter valid details");
/* 260 */       return;
/*     */     }
/*     */     
/* 263 */     updateQuestion(question, correct_Answer);
/*     */     try
/*     */     {
/* 266 */       client.sendToServer(question);
/* 267 */       displayAlert(Alert.AlertType.INFORMATION, "Save Question", "Save Success", String.format("Question: %s has been updated successfuly", new Object[] { question.toString() }));
/* 268 */       clearComponents();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 272 */       displayAlert(Alert.AlertType.ERROR, "Connection Error", "Faild to connect", e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void updateQuestion(Question question, int correct_ans)
/*     */   {
/* 280 */     question.setTcurrent_ans(correct_ans);
/*     */   }
/*     */   
/*     */   private void displayAlert(Alert.AlertType type, String title, String header, String content)
/*     */   {
/* 285 */     Alert alert = new Alert(type);
/* 286 */     alert.setTitle(title);
/* 287 */     alert.setHeaderText(header);
/* 288 */     alert.setContentText(content);
/* 289 */     alert.showAndWait();
/*     */   }
/*     */   
/*     */   private void setCmbSelectedIndexChange()
/*     */   {
/* 294 */     this.ques_box.valueProperty().addListener(new ChangeListener()
/*     */     {
/*     */ 
/*     */       public void changed(ObservableValue<? extends Question> observable, Question oldValue, Question newValue)
/*     */       {
/* 299 */         if (newValue != null) {
/* 300 */           QuestionsController.this.setQuestionDetails(newValue);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initClientConnection()
/*     */   {
/* 311 */     client = new SystemClient(address, port);
/*     */     try {
/* 313 */       client.sendToServer("getQuestion");
/*     */     }
/*     */     catch (IOException e) {
/* 316 */       Platform.exit();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void syncClientQuestions()
/*     */   {
/* 323 */     synchronized (client) {
/* 324 */       if (client.getqList() == null) {
/*     */         try {
/* 326 */           client.wait();
/*     */         }
/*     */         catch (InterruptedException e) {
/* 329 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize(URL arg0, ResourceBundle arg1)
/*     */   {
/* 341 */     initClientConnection();
/* 342 */     syncClientQuestions();
/* 343 */     initComboBox();
/* 344 */     setCmbSelectedIndexChange();
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\Control\QuestionsController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */