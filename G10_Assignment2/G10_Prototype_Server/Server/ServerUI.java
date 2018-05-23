/*    */ package Server;
/*    */ 
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ public class ServerUI extends javafx.application.Application
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  9 */     launch(args);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void start(Stage primaryStage)
/*    */     throws Exception
/*    */   {
/* 19 */     ServerController ser = new ServerController();
/* 20 */     ser.start(primaryStage);
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\Server\ServerUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */