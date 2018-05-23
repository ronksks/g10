/*     */ package ocsf.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
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
/*     */ public class ObservableClient
/*     */   extends Observable
/*     */ {
/*     */   public static final String CONNECTION_EXCEPTION = "#OC:Connection error.";
/*     */   public static final String CONNECTION_CLOSED = "#OC:Connection closed.";
/*     */   public static final String CONNECTION_ESTABLISHED = "#OC:Connection established.";
/*     */   private AdaptableClient service;
/*     */   
/*     */   public ObservableClient(String host, int port)
/*     */   {
/*  51 */     this.service = new AdaptableClient(host, port, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void openConnection()
/*     */     throws IOException
/*     */   {
/*  61 */     this.service.openConnection();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void closeConnection()
/*     */     throws IOException
/*     */   {
/*  69 */     this.service.closeConnection();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void sendToServer(Object msg)
/*     */     throws IOException
/*     */   {
/*  80 */     this.service.sendToServer(msg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final boolean isConnected()
/*     */   {
/*  90 */     return this.service.isConnected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getPort()
/*     */   {
/*  98 */     return this.service.getPort();
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
/* 109 */     this.service.setPort(port);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getHost()
/*     */   {
/* 117 */     return this.service.getHost();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setHost(String host)
/*     */   {
/* 128 */     this.service.setHost(host);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final InetAddress getInetAddress()
/*     */   {
/* 136 */     return this.service.getInetAddress();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void handleMessageFromServer(Object message)
/*     */   {
/* 148 */     setChanged();
/* 149 */     notifyObservers(message);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void connectionClosed()
/*     */   {
/* 157 */     setChanged();
/* 158 */     notifyObservers("#OC:Connection closed.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void connectionException(Exception exception)
/*     */   {
/* 169 */     setChanged();
/* 170 */     notifyObservers("#OC:Connection error.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void connectionEstablished()
/*     */   {
/* 178 */     setChanged();
/* 179 */     notifyObservers("#OC:Connection established.");
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\ocsf\client\ObservableClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */