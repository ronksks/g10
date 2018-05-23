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
/*     */ public class ObservableOriginatorServer
/*     */   extends ObservableServer
/*     */ {
/*     */   public ObservableOriginatorServer(int port)
/*     */   {
/*  35 */     super(port);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void handleMessageFromClient(Object message, ConnectionToClient client)
/*     */   {
/*  52 */     setChanged();
/*  53 */     notifyObservers(new OriginatorMessage(client, message));
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
/*     */ 
/*     */   protected synchronized void clientConnected(ConnectionToClient client)
/*     */   {
/*  67 */     setChanged();
/*  68 */     notifyObservers(new OriginatorMessage(client, "#OS:Client connected."));
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
/*     */ 
/*     */   protected synchronized void clientDisconnected(ConnectionToClient client)
/*     */   {
/*  82 */     setChanged();
/*  83 */     notifyObservers(new OriginatorMessage(client, "#OS:Client disconnected."));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void clientException(ConnectionToClient client, Throwable exception)
/*     */   {
/* 102 */     setChanged();
/* 103 */     notifyObservers(
/* 104 */       new OriginatorMessage(client, 
/* 105 */       "#OS:Client exception." + exception.getMessage()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void listeningException(Throwable exception)
/*     */   {
/* 121 */     setChanged();
/* 122 */     notifyObservers(
/* 123 */       new OriginatorMessage(null, 
/* 124 */       "#OS:Listening exception." + exception.getMessage()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverStarted()
/*     */   {
/* 136 */     setChanged();
/* 137 */     notifyObservers(new OriginatorMessage(null, "#OS:Server started."));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverStopped()
/*     */   {
/* 149 */     setChanged();
/* 150 */     notifyObservers(new OriginatorMessage(null, "#OS:Server stopped."));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverClosed()
/*     */   {
/* 162 */     setChanged();
/* 163 */     notifyObservers(new OriginatorMessage(null, "#OS:Server closed."));
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\ObservableOriginatorServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */