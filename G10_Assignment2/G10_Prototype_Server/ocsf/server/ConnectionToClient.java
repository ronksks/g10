/*     */ package ocsf.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.util.HashMap;
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
/*     */ public class ConnectionToClient
/*     */   extends Thread
/*     */ {
/*     */   private AbstractServer server;
/*     */   private Socket clientSocket;
/*     */   private ObjectInputStream input;
/*     */   private ObjectOutputStream output;
/*     */   private boolean readyToStop;
/*  66 */   private HashMap savedInfo = new HashMap(10);
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
/*     */   ConnectionToClient(ThreadGroup group, Socket clientSocket, AbstractServer server)
/*     */     throws IOException
/*     */   {
/*  84 */     super(group, null);
/*     */     
/*  86 */     this.clientSocket = clientSocket;
/*  87 */     this.server = server;
/*     */     
/*  89 */     clientSocket.setSoTimeout(0);
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  94 */       this.input = new ObjectInputStream(clientSocket.getInputStream());
/*  95 */       this.output = new ObjectOutputStream(clientSocket.getOutputStream());
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*     */       try
/*     */       {
/* 101 */         closeAll();
/*     */       }
/*     */       catch (Exception localException) {}
/*     */       
/* 105 */       throw ex;
/*     */     }
/*     */     
/* 108 */     this.readyToStop = false;
/* 109 */     start();
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
/*     */   public final void sendToClient(Object msg)
/*     */     throws IOException
/*     */   {
/* 123 */     if ((this.clientSocket == null) || (this.output == null)) {
/* 124 */       throw new SocketException("socket does not exist");
/*     */     }
/* 126 */     this.output.writeObject(msg);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public final void close()
/*     */     throws IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: iconst_1
/*     */     //   2: putfield 68	ocsf/server/ConnectionToClient:readyToStop	Z
/*     */     //   5: aload_0
/*     */     //   6: invokespecial 64	ocsf/server/ConnectionToClient:closeAll	()V
/*     */     //   9: goto +14 -> 23
/*     */     //   12: astore_1
/*     */     //   13: aload_0
/*     */     //   14: getfield 35	ocsf/server/ConnectionToClient:server	Locsf/server/AbstractServer;
/*     */     //   17: aload_0
/*     */     //   18: invokevirtual 103	ocsf/server/AbstractServer:clientDisconnected	(Locsf/server/ConnectionToClient;)V
/*     */     //   21: aload_1
/*     */     //   22: athrow
/*     */     //   23: aload_0
/*     */     //   24: getfield 35	ocsf/server/ConnectionToClient:server	Locsf/server/AbstractServer;
/*     */     //   27: aload_0
/*     */     //   28: invokevirtual 103	ocsf/server/AbstractServer:clientDisconnected	(Locsf/server/ConnectionToClient;)V
/*     */     //   31: return
/*     */     // Line number table:
/*     */     //   Java source line #138	-> byte code offset #0
/*     */     //   Java source line #142	-> byte code offset #5
/*     */     //   Java source line #143	-> byte code offset #9
/*     */     //   Java source line #145	-> byte code offset #12
/*     */     //   Java source line #146	-> byte code offset #13
/*     */     //   Java source line #147	-> byte code offset #21
/*     */     //   Java source line #146	-> byte code offset #23
/*     */     //   Java source line #148	-> byte code offset #31
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	32	0	this	ConnectionToClient
/*     */     //   12	10	1	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   5	12	12	finally
/*     */   }
/*     */   
/*     */   public final InetAddress getInetAddress()
/*     */   {
/* 159 */     return this.clientSocket == null ? null : this.clientSocket.getInetAddress();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 169 */     return 
/*     */     
/* 171 */       this.clientSocket.getInetAddress().getHostName() + " (" + this.clientSocket.getInetAddress().getHostAddress() + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInfo(String infoType, Object info)
/*     */   {
/* 183 */     this.savedInfo.put(infoType, info);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getInfo(String infoType)
/*     */   {
/* 194 */     return this.savedInfo.get(infoType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void run()
/*     */   {
/* 206 */     this.server.clientConnected(this);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 215 */       while (!this.readyToStop)
/*     */       {
/*     */ 
/*     */ 
/* 219 */         Object msg = this.input.readObject();
/* 220 */         this.server.receiveMessageFromClient(msg, this);
/*     */       }
/*     */     }
/*     */     catch (Exception exception)
/*     */     {
/* 225 */       if (!this.readyToStop)
/*     */       {
/*     */         try
/*     */         {
/* 229 */           closeAll();
/*     */         }
/*     */         catch (Exception localException1) {}
/*     */         
/* 233 */         this.server.clientException(this, exception);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private void closeAll()
/*     */     throws IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 33	ocsf/server/ConnectionToClient:clientSocket	Ljava/net/Socket;
/*     */     //   4: ifnull +10 -> 14
/*     */     //   7: aload_0
/*     */     //   8: getfield 33	ocsf/server/ConnectionToClient:clientSocket	Ljava/net/Socket;
/*     */     //   11: invokevirtual 175	java/net/Socket:close	()V
/*     */     //   14: aload_0
/*     */     //   15: getfield 62	ocsf/server/ConnectionToClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   18: ifnull +10 -> 28
/*     */     //   21: aload_0
/*     */     //   22: getfield 62	ocsf/server/ConnectionToClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   25: invokevirtual 177	java/io/ObjectOutputStream:close	()V
/*     */     //   28: aload_0
/*     */     //   29: getfield 51	ocsf/server/ConnectionToClient:input	Ljava/io/ObjectInputStream;
/*     */     //   32: ifnull +31 -> 63
/*     */     //   35: aload_0
/*     */     //   36: getfield 51	ocsf/server/ConnectionToClient:input	Ljava/io/ObjectInputStream;
/*     */     //   39: invokevirtual 178	java/io/ObjectInputStream:close	()V
/*     */     //   42: goto +21 -> 63
/*     */     //   45: astore_1
/*     */     //   46: aload_0
/*     */     //   47: aconst_null
/*     */     //   48: putfield 62	ocsf/server/ConnectionToClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   51: aload_0
/*     */     //   52: aconst_null
/*     */     //   53: putfield 51	ocsf/server/ConnectionToClient:input	Ljava/io/ObjectInputStream;
/*     */     //   56: aload_0
/*     */     //   57: aconst_null
/*     */     //   58: putfield 33	ocsf/server/ConnectionToClient:clientSocket	Ljava/net/Socket;
/*     */     //   61: aload_1
/*     */     //   62: athrow
/*     */     //   63: aload_0
/*     */     //   64: aconst_null
/*     */     //   65: putfield 62	ocsf/server/ConnectionToClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   68: aload_0
/*     */     //   69: aconst_null
/*     */     //   70: putfield 51	ocsf/server/ConnectionToClient:input	Ljava/io/ObjectInputStream;
/*     */     //   73: aload_0
/*     */     //   74: aconst_null
/*     */     //   75: putfield 33	ocsf/server/ConnectionToClient:clientSocket	Ljava/net/Socket;
/*     */     //   78: return
/*     */     // Line number table:
/*     */     //   Java source line #251	-> byte code offset #0
/*     */     //   Java source line #252	-> byte code offset #7
/*     */     //   Java source line #255	-> byte code offset #14
/*     */     //   Java source line #256	-> byte code offset #21
/*     */     //   Java source line #259	-> byte code offset #28
/*     */     //   Java source line #260	-> byte code offset #35
/*     */     //   Java source line #261	-> byte code offset #42
/*     */     //   Java source line #263	-> byte code offset #45
/*     */     //   Java source line #268	-> byte code offset #46
/*     */     //   Java source line #269	-> byte code offset #51
/*     */     //   Java source line #270	-> byte code offset #56
/*     */     //   Java source line #271	-> byte code offset #61
/*     */     //   Java source line #268	-> byte code offset #63
/*     */     //   Java source line #269	-> byte code offset #68
/*     */     //   Java source line #270	-> byte code offset #73
/*     */     //   Java source line #272	-> byte code offset #78
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	79	0	this	ConnectionToClient
/*     */     //   45	17	1	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	45	45	finally
/*     */   }
/*     */   
/*     */   protected void finalize()
/*     */   {
/*     */     try
/*     */     {
/* 281 */       closeAll();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\ConnectionToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */