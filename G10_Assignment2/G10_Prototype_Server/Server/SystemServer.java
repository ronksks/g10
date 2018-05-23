/*     */ package Server;
/*     */ 
/*     */ import Entity.Question;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import ocsf.server.AbstractServer;
/*     */ import ocsf.server.ConnectionToClient;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemServer
/*     */   extends AbstractServer
/*     */ {
/*     */   public static String user;
/*     */   public static String password;
/*  23 */   final String msgFromClient = "getQuestion";
/*     */   
/*     */ 
/*  26 */   public static Connection conn = null;
/*     */   
/*  28 */   public SystemServer(int port) { super(port); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void handleMessageFromClient(Object msg, ConnectionToClient client)
/*     */   {
/*  35 */     if (msg.equals("getQuestion")) {
/*     */       try {
/*  37 */         Connection con = connectToDB();
/*  38 */         client.sendToClient(getQuestion(con));
/*     */         
/*  40 */         con.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  43 */         e.printStackTrace();
/*     */       }
/*     */       catch (IOException e) {
/*  46 */         e.printStackTrace();
/*     */       }
/*  48 */     } else if ((msg instanceof Question)) {
/*     */       try {
/*  50 */         Connection con = connectToDB();
/*  51 */         updateQuestion(con, (Question)msg);
/*     */         
/*  53 */         con.close();
/*     */       }
/*     */       catch (SQLException e) {
/*  56 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateQuestion(Connection con, Question q)
/*     */   {
/*     */     try {
/*  64 */       String qry = "UPDATE questions SET correct_answer = ? WHERE id = ?";
/*  65 */       PreparedStatement pstmt = con.prepareStatement(qry);
/*  66 */       pstmt.setInt(1, q.getcurrent_ans());
/*  67 */       pstmt.setInt(2, q.getid());
/*  68 */       pstmt.executeUpdate();
/*     */     }
/*     */     catch (SQLException e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private ArrayList<Question> getQuestion(Connection con)
/*     */   {
/*  78 */     ArrayList<Question> qList = new ArrayList();
/*     */     try {
/*  80 */       Statement stmt = con.createStatement();
/*  81 */       String qry = "SELECT * FROM questions";
/*  82 */       ResultSet rs = stmt.executeQuery(qry);
/*  83 */       while (rs.next()) {
/*  84 */         int id = rs.getInt(1);
/*  85 */         String author = rs.getString(2);
/*  86 */         String ques = rs.getString(3);
/*  87 */         String ans1 = rs.getString(4);
/*  88 */         String ans2 = rs.getString(5);
/*  89 */         String ans3 = rs.getString(6);
/*  90 */         String ans4 = rs.getString(7);
/*  91 */         int corrent_ans = rs.getInt(8);
/*     */         
/*  93 */         String[] ans = { ans1, ans2, ans3, ans4 };
/*     */         
/*  95 */         qList.add(new Question(id, ques, author, ans, corrent_ans));
/*     */       }
/*     */     } catch (SQLException e) {
/*  98 */       e.printStackTrace();
/*     */     }
/* 100 */     return qList;
/*     */   }
/*     */   
/*     */   public static Connection connectToDB() throws SQLException
/*     */   {
/*     */     try {
/* 106 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/*     */     } catch (Exception ex) {
/* 108 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/* 111 */     conn = DriverManager.getConnection("jdbc:mysql://localhost/prototype", user, password);
/* 112 */     return conn;
/*     */   }
/*     */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\Server\SystemServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */