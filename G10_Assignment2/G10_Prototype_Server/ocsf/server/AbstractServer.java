/*     */ package ocsf.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
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
/*     */ public abstract class AbstractServer
/*     */   implements Runnable
/*     */ {
/*  42 */   private ServerSocket serverSocket = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Thread connectionListener;
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
/*  61 */   private int timeout = 500;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */   private int backlog = 10;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ThreadGroup clientThreadGroup;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */   private boolean readyToStop = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractServer(int port)
/*     */   {
/*  92 */     this.port = port;
/*     */     
/*  94 */     this.clientThreadGroup = 
/*  95 */       new ThreadGroup("ConnectionToClient threads")
/*     */       {
/*     */ 
/*     */ 
/*     */         public void uncaughtException(Thread thread, Throwable exception)
/*     */         {
/*     */ 
/* 102 */           AbstractServer.this.clientException((ConnectionToClient)thread, exception);
/*     */         }
/*     */       };
/*     */     }
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
/*     */     public final void listen()
/*     */       throws IOException
/*     */     {
/* 120 */       if (!isListening())
/*     */       {
/* 122 */         if (this.serverSocket == null)
/*     */         {
/* 124 */           this.serverSocket = new ServerSocket(getPort(), this.backlog);
/*     */         }
/*     */         
/* 127 */         this.serverSocket.setSoTimeout(this.timeout);
/* 128 */         this.readyToStop = false;
/* 129 */         this.connectionListener = new Thread(this);
/* 130 */         this.connectionListener.start();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public final void stopListening()
/*     */     {
/* 139 */       this.readyToStop = true;
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public final synchronized void close()
/*     */       throws IOException
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield 25	ocsf/server/AbstractServer:serverSocket	Ljava/net/ServerSocket;
/*     */       //   4: ifnonnull +4 -> 8
/*     */       //   7: return
/*     */       //   8: aload_0
/*     */       //   9: invokevirtual 81	ocsf/server/AbstractServer:stopListening	()V
/*     */       //   12: aload_0
/*     */       //   13: getfield 25	ocsf/server/AbstractServer:serverSocket	Ljava/net/ServerSocket;
/*     */       //   16: invokevirtual 83	java/net/ServerSocket:close	()V
/*     */       //   19: goto +48 -> 67
/*     */       //   22: astore_1
/*     */       //   23: aload_0
/*     */       //   24: invokevirtual 85	ocsf/server/AbstractServer:getClientConnections	()[Ljava/lang/Thread;
/*     */       //   27: astore_2
/*     */       //   28: iconst_0
/*     */       //   29: istore_3
/*     */       //   30: goto +20 -> 50
/*     */       //   33: aload_2
/*     */       //   34: iload_3
/*     */       //   35: aaload
/*     */       //   36: checkcast 89	ocsf/server/ConnectionToClient
/*     */       //   39: invokevirtual 91	ocsf/server/ConnectionToClient:close	()V
/*     */       //   42: goto +5 -> 47
/*     */       //   45: astore 4
/*     */       //   47: iinc 3 1
/*     */       //   50: iload_3
/*     */       //   51: aload_2
/*     */       //   52: arraylength
/*     */       //   53: if_icmplt -20 -> 33
/*     */       //   56: aload_0
/*     */       //   57: aconst_null
/*     */       //   58: putfield 25	ocsf/server/AbstractServer:serverSocket	Ljava/net/ServerSocket;
/*     */       //   61: aload_0
/*     */       //   62: invokevirtual 92	ocsf/server/AbstractServer:serverClosed	()V
/*     */       //   65: aload_1
/*     */       //   66: athrow
/*     */       //   67: aload_0
/*     */       //   68: invokevirtual 85	ocsf/server/AbstractServer:getClientConnections	()[Ljava/lang/Thread;
/*     */       //   71: astore_2
/*     */       //   72: iconst_0
/*     */       //   73: istore_3
/*     */       //   74: goto +20 -> 94
/*     */       //   77: aload_2
/*     */       //   78: iload_3
/*     */       //   79: aaload
/*     */       //   80: checkcast 89	ocsf/server/ConnectionToClient
/*     */       //   83: invokevirtual 91	ocsf/server/ConnectionToClient:close	()V
/*     */       //   86: goto +5 -> 91
/*     */       //   89: astore 4
/*     */       //   91: iinc 3 1
/*     */       //   94: iload_3
/*     */       //   95: aload_2
/*     */       //   96: arraylength
/*     */       //   97: if_icmplt -20 -> 77
/*     */       //   100: aload_0
/*     */       //   101: aconst_null
/*     */       //   102: putfield 25	ocsf/server/AbstractServer:serverSocket	Ljava/net/ServerSocket;
/*     */       //   105: aload_0
/*     */       //   106: invokevirtual 92	ocsf/server/AbstractServer:serverClosed	()V
/*     */       //   109: return
/*     */       // Line number table:
/*     */       //   Java source line #156	-> byte code offset #0
/*     */       //   Java source line #157	-> byte code offset #7
/*     */       //   Java source line #158	-> byte code offset #8
/*     */       //   Java source line #161	-> byte code offset #12
/*     */       //   Java source line #162	-> byte code offset #19
/*     */       //   Java source line #164	-> byte code offset #22
/*     */       //   Java source line #166	-> byte code offset #23
/*     */       //   Java source line #167	-> byte code offset #28
/*     */       //   Java source line #171	-> byte code offset #33
/*     */       //   Java source line #172	-> byte code offset #42
/*     */       //   Java source line #174	-> byte code offset #45
/*     */       //   Java source line #167	-> byte code offset #47
/*     */       //   Java source line #176	-> byte code offset #56
/*     */       //   Java source line #177	-> byte code offset #61
/*     */       //   Java source line #178	-> byte code offset #65
/*     */       //   Java source line #166	-> byte code offset #67
/*     */       //   Java source line #167	-> byte code offset #72
/*     */       //   Java source line #171	-> byte code offset #77
/*     */       //   Java source line #172	-> byte code offset #86
/*     */       //   Java source line #174	-> byte code offset #89
/*     */       //   Java source line #167	-> byte code offset #91
/*     */       //   Java source line #176	-> byte code offset #100
/*     */       //   Java source line #177	-> byte code offset #105
/*     */       //   Java source line #179	-> byte code offset #109
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	110	0	this	AbstractServer
/*     */       //   22	44	1	localObject	Object
/*     */       //   27	25	2	clientThreadList	Thread[]
/*     */       //   71	25	2	clientThreadList	Thread[]
/*     */       //   29	22	3	i	int
/*     */       //   73	22	3	i	int
/*     */       //   45	1	4	localException	Exception
/*     */       //   89	1	4	localException1	Exception
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   12	22	22	finally
/*     */       //   33	42	45	java/lang/Exception
/*     */       //   77	86	89	java/lang/Exception
/*     */     }
/*     */     
/*     */     public void sendToAllClients(Object msg)
/*     */     {
/* 194 */       Thread[] clientThreadList = getClientConnections();
/*     */       
/* 196 */       for (int i = 0; i < clientThreadList.length; i++)
/*     */       {
/*     */         try
/*     */         {
/* 200 */           ((ConnectionToClient)clientThreadList[i]).sendToClient(msg);
/*     */         }
/*     */         catch (Exception localException) {}
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public final boolean isListening()
/*     */     {
/* 216 */       return this.connectionListener != null;
/*     */     }
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
/*     */     public final synchronized Thread[] getClientConnections()
/*     */     {
/* 233 */       Thread[] clientThreadList = new Thread[
/* 234 */         this.clientThreadGroup.activeCount()];
/*     */       
/* 236 */       this.clientThreadGroup.enumerate(clientThreadList);
/*     */       
/* 238 */       return clientThreadList;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public final int getNumberOfClients()
/*     */     {
/* 248 */       return this.clientThreadGroup.activeCount();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public final int getPort()
/*     */     {
/* 258 */       return this.port;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public final void setPort(int port)
/*     */     {
/* 270 */       this.port = port;
/*     */     }
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
/*     */     public final void setTimeout(int timeout)
/*     */     {
/* 284 */       this.timeout = timeout;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public final void setBacklog(int backlog)
/*     */     {
/* 297 */       this.backlog = backlog;
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public final void run()
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual 124	ocsf/server/AbstractServer:serverStarted	()V
/*     */       //   4: goto +38 -> 42
/*     */       //   7: aload_0
/*     */       //   8: getfield 25	ocsf/server/AbstractServer:serverSocket	Ljava/net/ServerSocket;
/*     */       //   11: invokevirtual 127	java/net/ServerSocket:accept	()Ljava/net/Socket;
/*     */       //   14: astore_1
/*     */       //   15: aload_0
/*     */       //   16: dup
/*     */       //   17: astore_2
/*     */       //   18: monitorenter
/*     */       //   19: new 89	ocsf/server/ConnectionToClient
/*     */       //   22: dup
/*     */       //   23: aload_0
/*     */       //   24: getfield 42	ocsf/server/AbstractServer:clientThreadGroup	Ljava/lang/ThreadGroup;
/*     */       //   27: aload_1
/*     */       //   28: aload_0
/*     */       //   29: invokespecial 131	ocsf/server/ConnectionToClient:<init>	(Ljava/lang/ThreadGroup;Ljava/net/Socket;Locsf/server/AbstractServer;)V
/*     */       //   32: astore_3
/*     */       //   33: aload_2
/*     */       //   34: monitorexit
/*     */       //   35: goto +7 -> 42
/*     */       //   38: aload_2
/*     */       //   39: monitorexit
/*     */       //   40: athrow
/*     */       //   41: astore_1
/*     */       //   42: aload_0
/*     */       //   43: getfield 31	ocsf/server/AbstractServer:readyToStop	Z
/*     */       //   46: ifeq -39 -> 7
/*     */       //   49: aload_0
/*     */       //   50: invokevirtual 134	ocsf/server/AbstractServer:serverStopped	()V
/*     */       //   53: goto +51 -> 104
/*     */       //   56: astore_1
/*     */       //   57: aload_0
/*     */       //   58: getfield 31	ocsf/server/AbstractServer:readyToStop	Z
/*     */       //   61: ifne +11 -> 72
/*     */       //   64: aload_0
/*     */       //   65: aload_1
/*     */       //   66: invokevirtual 137	ocsf/server/AbstractServer:listeningException	(Ljava/lang/Throwable;)V
/*     */       //   69: goto +7 -> 76
/*     */       //   72: aload_0
/*     */       //   73: invokevirtual 134	ocsf/server/AbstractServer:serverStopped	()V
/*     */       //   76: aload_0
/*     */       //   77: iconst_1
/*     */       //   78: putfield 31	ocsf/server/AbstractServer:readyToStop	Z
/*     */       //   81: aload_0
/*     */       //   82: aconst_null
/*     */       //   83: putfield 73	ocsf/server/AbstractServer:connectionListener	Ljava/lang/Thread;
/*     */       //   86: goto +28 -> 114
/*     */       //   89: astore 4
/*     */       //   91: aload_0
/*     */       //   92: iconst_1
/*     */       //   93: putfield 31	ocsf/server/AbstractServer:readyToStop	Z
/*     */       //   96: aload_0
/*     */       //   97: aconst_null
/*     */       //   98: putfield 73	ocsf/server/AbstractServer:connectionListener	Ljava/lang/Thread;
/*     */       //   101: aload 4
/*     */       //   103: athrow
/*     */       //   104: aload_0
/*     */       //   105: iconst_1
/*     */       //   106: putfield 31	ocsf/server/AbstractServer:readyToStop	Z
/*     */       //   109: aload_0
/*     */       //   110: aconst_null
/*     */       //   111: putfield 73	ocsf/server/AbstractServer:connectionListener	Ljava/lang/Thread;
/*     */       //   114: return
/*     */       // Line number table:
/*     */       //   Java source line #309	-> byte code offset #0
/*     */       //   Java source line #315	-> byte code offset #4
/*     */       //   Java source line #320	-> byte code offset #7
/*     */       //   Java source line #325	-> byte code offset #15
/*     */       //   Java source line #327	-> byte code offset #19
/*     */       //   Java source line #328	-> byte code offset #23
/*     */       //   Java source line #327	-> byte code offset #29
/*     */       //   Java source line #325	-> byte code offset #33
/*     */       //   Java source line #331	-> byte code offset #41
/*     */       //   Java source line #315	-> byte code offset #42
/*     */       //   Java source line #339	-> byte code offset #49
/*     */       //   Java source line #340	-> byte code offset #53
/*     */       //   Java source line #341	-> byte code offset #56
/*     */       //   Java source line #343	-> byte code offset #57
/*     */       //   Java source line #346	-> byte code offset #64
/*     */       //   Java source line #347	-> byte code offset #69
/*     */       //   Java source line #350	-> byte code offset #72
/*     */       //   Java source line #355	-> byte code offset #76
/*     */       //   Java source line #356	-> byte code offset #81
/*     */       //   Java source line #354	-> byte code offset #89
/*     */       //   Java source line #355	-> byte code offset #91
/*     */       //   Java source line #356	-> byte code offset #96
/*     */       //   Java source line #357	-> byte code offset #101
/*     */       //   Java source line #355	-> byte code offset #104
/*     */       //   Java source line #356	-> byte code offset #109
/*     */       //   Java source line #358	-> byte code offset #114
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	115	0	this	AbstractServer
/*     */       //   14	14	1	clientSocket	java.net.Socket
/*     */       //   41	1	1	localInterruptedIOException	java.io.InterruptedIOException
/*     */       //   56	10	1	exception	IOException
/*     */       //   17	22	2	Ljava/lang/Object;	Object
/*     */       //   32	1	3	localConnectionToClient	ConnectionToClient
/*     */       //   89	13	4	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   19	35	38	finally
/*     */       //   38	40	38	finally
/*     */       //   7	41	41	java/io/InterruptedIOException
/*     */       //   4	53	56	java/io/IOException
/*     */       //   4	76	89	finally
/*     */     }
/*     */     
/*     */     protected void clientConnected(ConnectionToClient client) {}
/*     */     
/*     */     protected synchronized void clientDisconnected(ConnectionToClient client) {}
/*     */     
/*     */     protected synchronized void clientException(ConnectionToClient client, Throwable exception) {}
/*     */     
/*     */     protected void listeningException(Throwable exception) {}
/*     */     
/*     */     protected void serverStarted() {}
/*     */     
/*     */     protected void serverStopped() {}
/*     */     
/*     */     protected void serverClosed() {}
/*     */     
/*     */     protected abstract void handleMessageFromClient(Object paramObject, ConnectionToClient paramConnectionToClient);
/*     */     
/*     */     final synchronized void receiveMessageFromClient(Object msg, ConnectionToClient client)
/*     */     {
/* 456 */       handleMessageFromClient(msg, client);
/*     */     }
/*     */   }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\ocsf\server\AbstractServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */