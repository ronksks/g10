/*     */ package ocsf.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
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
/*     */ public abstract class AbstractClient
/*     */   implements Runnable
/*     */ {
/*     */   private Socket clientSocket;
/*     */   private ObjectOutputStream output;
/*     */   private ObjectInputStream input;
/*     */   private Thread clientReader;
/*  65 */   private boolean readyToStop = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String host;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int port;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractClient(String host, int port)
/*     */   {
/*  88 */     this.host = host;
/*  89 */     this.port = port;
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
/*     */   public final void openConnection()
/*     */     throws IOException
/*     */   {
/* 103 */     if (isConnected()) {
/* 104 */       return;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 109 */       this.clientSocket = new Socket(this.host, this.port);
/* 110 */       this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
/* 111 */       this.input = new ObjectInputStream(this.clientSocket.getInputStream());
/*     */ 
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*     */ 
/*     */       try
/*     */       {
/* 119 */         closeAll();
/*     */       }
/*     */       catch (Exception localException) {}
/*     */       
/* 123 */       throw ex;
/*     */     }
/*     */     
/* 126 */     this.clientReader = new Thread(this);
/* 127 */     this.readyToStop = false;
/* 128 */     this.clientReader.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void sendToServer(Object msg)
/*     */     throws IOException
/*     */   {
/* 140 */     if ((this.clientSocket == null) || (this.output == null)) {
/* 141 */       throw new SocketException("socket does not exist");
/*     */     }
/* 143 */     this.output.writeObject(msg);
/* 144 */     this.output.reset();
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public final void closeConnection()
/*     */     throws IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: iconst_1
/*     */     //   2: putfield 27	ocsf/client/AbstractClient:readyToStop	Z
/*     */     //   5: aload_0
/*     */     //   6: invokespecial 73	ocsf/client/AbstractClient:closeAll	()V
/*     */     //   9: goto +10 -> 19
/*     */     //   12: astore_1
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual 109	ocsf/client/AbstractClient:connectionClosed	()V
/*     */     //   17: aload_1
/*     */     //   18: athrow
/*     */     //   19: aload_0
/*     */     //   20: invokevirtual 109	ocsf/client/AbstractClient:connectionClosed	()V
/*     */     //   23: return
/*     */     // Line number table:
/*     */     //   Java source line #155	-> byte code offset #0
/*     */     //   Java source line #159	-> byte code offset #5
/*     */     //   Java source line #160	-> byte code offset #9
/*     */     //   Java source line #162	-> byte code offset #12
/*     */     //   Java source line #164	-> byte code offset #13
/*     */     //   Java source line #165	-> byte code offset #17
/*     */     //   Java source line #164	-> byte code offset #19
/*     */     //   Java source line #166	-> byte code offset #23
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	24	0	this	AbstractClient
/*     */     //   12	6	1	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   5	12	12	finally
/*     */   }
/*     */   
/*     */   public final boolean isConnected()
/*     */   {
/* 175 */     return (this.clientReader != null) && (this.clientReader.isAlive());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getPort()
/*     */   {
/* 183 */     return this.port;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setPort(int port)
/*     */   {
/* 195 */     this.port = port;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getHost()
/*     */   {
/* 203 */     return this.host;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setHost(String host)
/*     */   {
/* 215 */     this.host = host;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final InetAddress getInetAddress()
/*     */   {
/* 225 */     return this.clientSocket.getInetAddress();
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public final void run()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual 129	ocsf/client/AbstractClient:connectionEstablished	()V
/*     */     //   4: goto +16 -> 20
/*     */     //   7: aload_0
/*     */     //   8: getfield 71	ocsf/client/AbstractClient:input	Ljava/io/ObjectInputStream;
/*     */     //   11: invokevirtual 132	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
/*     */     //   14: astore_1
/*     */     //   15: aload_0
/*     */     //   16: aload_1
/*     */     //   17: invokevirtual 136	ocsf/client/AbstractClient:handleMessageFromServer	(Ljava/lang/Object;)V
/*     */     //   20: aload_0
/*     */     //   21: getfield 27	ocsf/client/AbstractClient:readyToStop	Z
/*     */     //   24: ifeq -17 -> 7
/*     */     //   27: goto +42 -> 69
/*     */     //   30: astore_2
/*     */     //   31: aload_0
/*     */     //   32: getfield 27	ocsf/client/AbstractClient:readyToStop	Z
/*     */     //   35: ifne +16 -> 51
/*     */     //   38: aload_0
/*     */     //   39: invokespecial 73	ocsf/client/AbstractClient:closeAll	()V
/*     */     //   42: goto +4 -> 46
/*     */     //   45: astore_3
/*     */     //   46: aload_0
/*     */     //   47: aload_2
/*     */     //   48: invokevirtual 139	ocsf/client/AbstractClient:connectionException	(Ljava/lang/Exception;)V
/*     */     //   51: aload_0
/*     */     //   52: aconst_null
/*     */     //   53: putfield 81	ocsf/client/AbstractClient:clientReader	Ljava/lang/Thread;
/*     */     //   56: goto +18 -> 74
/*     */     //   59: astore 4
/*     */     //   61: aload_0
/*     */     //   62: aconst_null
/*     */     //   63: putfield 81	ocsf/client/AbstractClient:clientReader	Ljava/lang/Thread;
/*     */     //   66: aload 4
/*     */     //   68: athrow
/*     */     //   69: aload_0
/*     */     //   70: aconst_null
/*     */     //   71: putfield 81	ocsf/client/AbstractClient:clientReader	Ljava/lang/Thread;
/*     */     //   74: return
/*     */     // Line number table:
/*     */     //   Java source line #237	-> byte code offset #0
/*     */     //   Java source line #246	-> byte code offset #4
/*     */     //   Java source line #251	-> byte code offset #7
/*     */     //   Java source line #255	-> byte code offset #15
/*     */     //   Java source line #246	-> byte code offset #20
/*     */     //   Java source line #257	-> byte code offset #27
/*     */     //   Java source line #258	-> byte code offset #30
/*     */     //   Java source line #260	-> byte code offset #31
/*     */     //   Java source line #264	-> byte code offset #38
/*     */     //   Java source line #265	-> byte code offset #42
/*     */     //   Java source line #266	-> byte code offset #45
/*     */     //   Java source line #268	-> byte code offset #46
/*     */     //   Java source line #273	-> byte code offset #51
/*     */     //   Java source line #272	-> byte code offset #59
/*     */     //   Java source line #273	-> byte code offset #61
/*     */     //   Java source line #274	-> byte code offset #66
/*     */     //   Java source line #273	-> byte code offset #69
/*     */     //   Java source line #275	-> byte code offset #74
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	75	0	this	AbstractClient
/*     */     //   14	3	1	msg	Object
/*     */     //   30	18	2	exception	Exception
/*     */     //   45	1	3	localException1	Exception
/*     */     //   59	8	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   4	27	30	java/lang/Exception
/*     */     //   38	42	45	java/lang/Exception
/*     */     //   4	51	59	finally
/*     */   }
/*     */   
/*     */   protected void connectionClosed() {}
/*     */   
/*     */   protected void connectionException(Exception exception) {}
/*     */   
/*     */   protected void connectionEstablished() {}
/*     */   
/*     */   protected abstract void handleMessageFromServer(Object paramObject);
/*     */   
/*     */   /* Error */
/*     */   private void closeAll()
/*     */     throws IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 49	ocsf/client/AbstractClient:clientSocket	Ljava/net/Socket;
/*     */     //   4: ifnull +10 -> 14
/*     */     //   7: aload_0
/*     */     //   8: getfield 49	ocsf/client/AbstractClient:clientSocket	Ljava/net/Socket;
/*     */     //   11: invokevirtual 145	java/net/Socket:close	()V
/*     */     //   14: aload_0
/*     */     //   15: getfield 60	ocsf/client/AbstractClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   18: ifnull +10 -> 28
/*     */     //   21: aload_0
/*     */     //   22: getfield 60	ocsf/client/AbstractClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   25: invokevirtual 148	java/io/ObjectOutputStream:close	()V
/*     */     //   28: aload_0
/*     */     //   29: getfield 71	ocsf/client/AbstractClient:input	Ljava/io/ObjectInputStream;
/*     */     //   32: ifnull +31 -> 63
/*     */     //   35: aload_0
/*     */     //   36: getfield 71	ocsf/client/AbstractClient:input	Ljava/io/ObjectInputStream;
/*     */     //   39: invokevirtual 149	java/io/ObjectInputStream:close	()V
/*     */     //   42: goto +21 -> 63
/*     */     //   45: astore_1
/*     */     //   46: aload_0
/*     */     //   47: aconst_null
/*     */     //   48: putfield 60	ocsf/client/AbstractClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   51: aload_0
/*     */     //   52: aconst_null
/*     */     //   53: putfield 71	ocsf/client/AbstractClient:input	Ljava/io/ObjectInputStream;
/*     */     //   56: aload_0
/*     */     //   57: aconst_null
/*     */     //   58: putfield 49	ocsf/client/AbstractClient:clientSocket	Ljava/net/Socket;
/*     */     //   61: aload_1
/*     */     //   62: athrow
/*     */     //   63: aload_0
/*     */     //   64: aconst_null
/*     */     //   65: putfield 60	ocsf/client/AbstractClient:output	Ljava/io/ObjectOutputStream;
/*     */     //   68: aload_0
/*     */     //   69: aconst_null
/*     */     //   70: putfield 71	ocsf/client/AbstractClient:input	Ljava/io/ObjectInputStream;
/*     */     //   73: aload_0
/*     */     //   74: aconst_null
/*     */     //   75: putfield 49	ocsf/client/AbstractClient:clientSocket	Ljava/net/Socket;
/*     */     //   78: return
/*     */     // Line number table:
/*     */     //   Java source line #326	-> byte code offset #0
/*     */     //   Java source line #327	-> byte code offset #7
/*     */     //   Java source line #330	-> byte code offset #14
/*     */     //   Java source line #331	-> byte code offset #21
/*     */     //   Java source line #334	-> byte code offset #28
/*     */     //   Java source line #335	-> byte code offset #35
/*     */     //   Java source line #336	-> byte code offset #42
/*     */     //   Java source line #338	-> byte code offset #45
/*     */     //   Java source line #343	-> byte code offset #46
/*     */     //   Java source line #344	-> byte code offset #51
/*     */     //   Java source line #345	-> byte code offset #56
/*     */     //   Java source line #346	-> byte code offset #61
/*     */     //   Java source line #343	-> byte code offset #63
/*     */     //   Java source line #344	-> byte code offset #68
/*     */     //   Java source line #345	-> byte code offset #73
/*     */     //   Java source line #347	-> byte code offset #78
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	79	0	this	AbstractClient
/*     */     //   45	17	1	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	45	45	finally
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\ocsf\client\AbstractClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */