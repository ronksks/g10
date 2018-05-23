/*     */ package ocsf.server;
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
/*     */ 
/*     */ class AdaptableServer
/*     */   extends AbstractServer
/*     */ {
/*     */   private ObservableServer server;
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
/*     */ 
/*     */   public AdaptableServer(int port, ObservableServer server)
/*     */   {
/*  40 */     super(port);
/*  41 */     this.server = server;
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
/*     */   protected final void clientConnected(ConnectionToClient client)
/*     */   {
/*  54 */     this.server.clientConnected(client);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void clientDisconnected(ConnectionToClient client)
/*     */   {
/*  64 */     this.server.clientDisconnected(client);
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
/*     */   protected final void clientException(ConnectionToClient client, Throwable exception)
/*     */   {
/*  77 */     this.server.clientException(client, exception);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void listeningException(Throwable exception)
/*     */   {
/*  88 */     this.server.listeningException(exception);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void serverStopped()
/*     */   {
/*  97 */     this.server.serverStopped();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void serverStarted()
/*     */   {
/* 106 */     this.server.serverStarted();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void serverClosed()
/*     */   {
/* 114 */     this.server.serverClosed();
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
/*     */   protected final void handleMessageFromClient(Object msg, ConnectionToClient client)
/*     */   {
/* 127 */     this.server.handleMessageFromClient(msg, client);
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\AdaptableServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */