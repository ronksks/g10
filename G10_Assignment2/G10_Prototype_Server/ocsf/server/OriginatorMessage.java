/*    */ package ocsf.server;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OriginatorMessage
/*    */ {
/*    */   private ConnectionToClient originator;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private Object message;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public OriginatorMessage(ConnectionToClient originator, Object message)
/*    */   {
/* 36 */     this.originator = originator;
/* 37 */     this.message = message;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ConnectionToClient getOriginator()
/*    */   {
/* 49 */     return this.originator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object getMessage()
/*    */   {
/* 59 */     return this.message;
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\OriginatorMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */