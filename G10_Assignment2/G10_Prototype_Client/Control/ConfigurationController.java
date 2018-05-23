/*     */ package Control;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Alert.AlertType;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigurationController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private TextField txtPort;
/*     */   @FXML
/*     */   private Button btnSave;
/*     */   @FXML
/*     */   private TextField txtAddress;
/*     */   
/*     */   public void displayAlert(Alert.AlertType type, String title, String header, String content)
/*     */   {
/*  45 */     Alert alert = new Alert(type);
/*  46 */     alert.setTitle(title);
/*  47 */     alert.setHeaderText(header);
/*  48 */     alert.setContentText(content);
/*  49 */     alert.showAndWait();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void saveConfigFields(ActionEvent event)
/*     */   {
/*     */     try
/*     */     {
/*  59 */       QuestionsController.address = this.txtAddress.getText();
/*  60 */       QuestionsController.port = Integer.valueOf(this.txtPort.getText()).intValue();
/*     */       
/*  62 */       Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
/*  63 */       primaryStage.close();
/*  64 */       QuestionsController questionController = new QuestionsController();
/*  65 */       questionController.start(primaryStage);
/*     */     }
/*     */     catch (NumberFormatException e)
/*     */     {
/*  69 */       displayAlert(Alert.AlertType.ERROR, "Error", "Invalid Port", "The Port must be a valid Number!");
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */ 
/*  75 */       displayAlert(Alert.AlertType.ERROR, "Error", "Error", e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void start(Stage primaryStage)
/*     */     throws Exception
/*     */   {
/*  87 */     String title = "Configuration";
/*  88 */     String srcFXML = "/application/Configuration.fxml";
/*     */     try
/*     */     {
/*  91 */       FXMLLoader loader = new FXMLLoader();
/*  92 */       loader.setLocation(getClass().getResource(srcFXML));
/*  93 */       Parent root = (Parent)loader.load();
/*  94 */       Scene scene = new Scene(root);
/*  95 */       primaryStage.setTitle(title);
/*  96 */       primaryStage.setScene(scene);
/*  97 */       primaryStage.setResizable(false);
/*  98 */       primaryStage.show();
/*     */     }
/*     */     catch (Exception e) {
/* 101 */       System.out.println(e);
/* 102 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void initialize(URL arg0, ResourceBundle arg1)
/*     */   {
/* 108 */     this.txtAddress.setText(QuestionsController.address);
/* 109 */     this.txtPort.setText(QuestionsController.port);
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\Control\ConfigurationController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */