/*    */ package ocsf.client;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class AdaptableClient
/*    */   extends AbstractClient
/*    */ {
/*    */   private ObservableClient client;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AdaptableClient(String host, int port, ObservableClient client)
/*    */   {
/* 38 */     super(host, port);
/* 39 */     this.client = client;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected final void connectionClosed()
/*    */   {
/* 49 */     this.client.connectionClosed();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected final void connectionException(Exception exception)
/*    */   {
/* 60 */     this.client.connectionException(exception);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected final void connectionEstablished()
/*    */   {
/* 68 */     this.client.connectionEstablished();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected final void handleMessageFromServer(Object msg)
/*    */   {
/* 78 */     this.client.handleMessageFromServer(msg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\ocsf\client\AdaptableClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */