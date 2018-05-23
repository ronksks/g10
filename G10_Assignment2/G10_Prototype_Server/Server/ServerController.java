/*     */ package Server;
/*     */ 
/*     */ import java.awt.Label;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Inet4Address;
/*     */ import java.net.InetAddress;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Alert.AlertType;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.PasswordField;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.text.Text;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerController
/*     */   extends Application
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private Button conbut;
/*     */   @FXML
/*     */   private Text ipAdd;
/*     */   @FXML
/*     */   private PasswordField passtxt;
/*     */   @FXML
/*     */   private Label iptxt;
/*     */   @FXML
/*     */   private Button DisconBtn;
/*     */   @FXML
/*     */   private TextField usertxt;
/*     */   @FXML
/*     */   private TextField porttxt;
/*     */   private static SystemServer sc;
/*     */   
/*     */   public void start(Stage primaryStage)
/*     */     throws Exception
/*     */   {
/*  58 */     String title = "Server";
/*  59 */     String srcFXML = "/Server/ServerApp.fxml";
/*  60 */     String srcCSS = "/Server/application.css";
/*     */     try
/*     */     {
/*  63 */       FXMLLoader loader = new FXMLLoader();
/*  64 */       loader.setLocation(getClass().getResource(srcFXML));
/*  65 */       Parent root = (Parent)loader.load();
/*  66 */       Scene scene = new Scene(root);
/*  67 */       scene.getStylesheets().add(getClass().getResource(srcCSS).toExternalForm());
/*  68 */       primaryStage.setTitle(title);
/*  69 */       primaryStage.setScene(scene);
/*  70 */       primaryStage.show();
/*     */     }
/*     */     catch (Exception e) {
/*  73 */       System.out.println(e);
/*  74 */       e.printStackTrace();
/*     */     }
/*     */     
/*  77 */     primaryStage.setOnCloseRequest(new EventHandler()
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
/*     */   public void onClicked(ActionEvent event)
/*     */   {
/*  90 */     int port = Integer.valueOf(this.porttxt.getText()).intValue();
/*  91 */     SystemServer.user = this.usertxt.getText();
/*  92 */     SystemServer.password = this.passtxt.getText();
/*  93 */     sc = new SystemServer(port);
/*     */     try
/*     */     {
/*  96 */       SystemServer.connectToDB();
/*  97 */       sc.listen();
/*  98 */       this.DisconBtn.setVisible(true);
/*  99 */       this.conbut.setVisible(false);
/* 100 */       displayAlert(Alert.AlertType.INFORMATION, "Database Connection ", "Connected", 
/* 101 */         String.format("Server has started listening on port: %d", new Object[] { Integer.valueOf(port) }));
/*     */     }
/*     */     catch (SQLException ex) {
/* 104 */       displayAlert(Alert.AlertType.ERROR, "Database Connection Error", "Error!", "Could not connect to sql!");
/*     */     } catch (IOException ex) {
/* 106 */       displayAlert(Alert.AlertType.ERROR, "Connection Error", "Error!", "Could not listen for clients!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void DisconBtn(ActionEvent event) throws Exception
/*     */   {
/* 112 */     this.DisconBtn.setVisible(false);
/* 113 */     this.conbut.setVisible(true);
/* 114 */     sc.stopListening();
/* 115 */     sc.close();
/* 116 */     this.usertxt.clear();
/* 117 */     this.passtxt.clear();
/* 118 */     displayAlert(Alert.AlertType.INFORMATION, "Server Message ", "Disconnected", "Server disconnected successfully ");
/*     */   }
/*     */   
/*     */   private void displayAlert(Alert.AlertType type, String title, String header, String content) {
/* 122 */     Alert alert = new Alert(type);
/* 123 */     alert.setTitle(title);
/* 124 */     alert.setHeaderText(header);
/* 125 */     alert.setContentText(content);
/* 126 */     alert.showAndWait();
/*     */   }
/*     */   
/*     */   public void initialize(URL arg0, ResourceBundle arg1)
/*     */   {
/* 131 */     this.DisconBtn.setVisible(false);
/*     */     try {
/* 133 */       this.ipAdd.setText(Inet4Address.getLocalHost().getHostAddress());
/*     */     } catch (UnknownHostException e) {
/* 135 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\Server\ServerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */