/*    */ package Entity;
/*    */ 
/*    */ import Control.QuestionsController;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import javafx.scene.control.Alert;
/*    */ import javafx.scene.control.Alert.AlertType;
/*    */ import ocsf.client.AbstractClient;
/*    */ 
/*    */ public class SystemClient extends AbstractClient
/*    */ {
/*    */   private ArrayList<Question> qList;
/*    */   
/*    */   public ArrayList<Question> getqList()
/*    */   {
/* 16 */     return this.qList;
/*    */   }
/*    */   
/*    */   public void setqList(ArrayList<Question> qList) {
/* 20 */     this.qList = qList;
/*    */   }
/*    */   
/*    */   public SystemClient(String host, int port) {
/* 24 */     super(host, port);
/*    */     try
/*    */     {
/* 27 */       openConnection();
/*    */     }
/*    */     catch (IOException e) {
/* 30 */       displayAlert(Alert.AlertType.ERROR, "Error", "Network is unreachable", "Could Not Connect");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void handleMessageFromServer(Object msg)
/*    */   {
/* 37 */     synchronized (this) {
/* 38 */       this.qList = ((ArrayList)msg);
/* 39 */       if (QuestionsController.observelist != null)
/* 40 */         QuestionsController.handleListUpdateFromClient(this.qList);
/* 41 */       notifyAll();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private void displayAlert(Alert.AlertType type, String title, String header, String content)
/*    */   {
/* 50 */     Alert alert = new Alert(type);
/* 51 */     alert.setTitle(title);
/* 52 */     alert.setHeaderText(header);
/* 53 */     alert.setContentText(content);
/* 54 */     alert.showAndWait();
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\Entity\SystemClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */