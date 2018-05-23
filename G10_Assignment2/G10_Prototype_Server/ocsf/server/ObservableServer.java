/*     */ package ocsf.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Observable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObservableServer
/*     */   extends Observable
/*     */ {
/*     */   public static final String CLIENT_CONNECTED = "#OS:Client connected.";
/*     */   public static final String CLIENT_DISCONNECTED = "#OS:Client disconnected.";
/*     */   public static final String CLIENT_EXCEPTION = "#OS:Client exception.";
/*     */   public static final String LISTENING_EXCEPTION = "#OS:Listening exception.";
/*     */   public static final String SERVER_CLOSED = "#OS:Server closed.";
/*     */   public static final String SERVER_STARTED = "#OS:Server started.";
/*     */   public static final String SERVER_STOPPED = "#OS:Server stopped.";
/*     */   private AdaptableServer service;
/*     */   
/*     */   public ObservableServer(int port)
/*     */   {
/*  82 */     this.service = new AdaptableServer(port, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void listen()
/*     */     throws IOException
/*     */   {
/*  92 */     this.service.listen();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void stopListening()
/*     */   {
/* 100 */     this.service.stopListening();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void close()
/*     */     throws IOException
/*     */   {
/* 108 */     this.service.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sendToAllClients(Object msg)
/*     */   {
/* 118 */     this.service.sendToAllClients(msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final boolean isListening()
/*     */   {
/* 128 */     return this.service.isListening();
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
/*     */   public final Thread[] getClientConnections()
/*     */   {
/* 142 */     return this.service.getClientConnections();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getNumberOfClients()
/*     */   {
/* 150 */     return this.service.getNumberOfClients();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getPort()
/*     */   {
/* 158 */     return this.service.getPort();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setPort(int port)
/*     */   {
/* 169 */     this.service.setPort(port);
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
/*     */   public final void setTimeout(int timeout)
/*     */   {
/* 182 */     this.service.setTimeout(timeout);
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
/*     */   public final void setBacklog(int backlog)
/*     */   {
/* 196 */     this.service.setBacklog(backlog);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void clientConnected(ConnectionToClient client)
/*     */   {
/* 207 */     setChanged();
/* 208 */     notifyObservers("#OS:Client connected.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void clientDisconnected(ConnectionToClient client)
/*     */   {
/* 219 */     setChanged();
/* 220 */     notifyObservers("#OS:Client disconnected.");
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
/*     */   protected synchronized void clientException(ConnectionToClient client, Throwable exception)
/*     */   {
/* 236 */     setChanged();
/* 237 */     notifyObservers("#OS:Client exception.");
/*     */     try
/*     */     {
/* 240 */       client.close();
/*     */     }
/*     */     catch (Exception localException) {}
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
/*     */   protected synchronized void listeningException(Throwable exception)
/*     */   {
/* 256 */     setChanged();
/* 257 */     notifyObservers("#OS:Listening exception.");
/* 258 */     stopListening();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverStopped()
/*     */   {
/* 268 */     setChanged();
/* 269 */     notifyObservers("#OS:Server stopped.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverClosed()
/*     */   {
/* 278 */     setChanged();
/* 279 */     notifyObservers("#OS:Server closed.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void serverStarted()
/*     */   {
/* 288 */     setChanged();
/* 289 */     notifyObservers("#OS:Server started.");
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
/* 306 */     setChanged();
/* 307 */     notifyObservers(message);
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\ObservableServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */