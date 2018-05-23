/*    */ package Boundary;
/*    */ 
/*    */ import Control.ConfigurationController;
/*    */ import javafx.application.Application;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ public class QuestionUI
/*    */   extends Application
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 12 */     launch(args);
/*    */   }
/*    */   
/*    */ 
/*    */   public void start(Stage arg0)
/*    */     throws Exception
/*    */   {
/* 19 */     ConfigurationController confController = new ConfigurationController();
/* 20 */     confController.start(arg0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\Boundary\QuestionUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */