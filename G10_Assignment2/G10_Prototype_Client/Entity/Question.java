/*    */ package Entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Question
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int id;
/*    */   private String ques;
/*    */   private String author;
/*    */   private int corrent_ans;
/* 15 */   private String[] pos_ans = new String[4];
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ ///
/*    */ 
/*    */ 
/*    */   public Question(int id, String ques, String author, String[] pos_ans, int corrent_ans)
/*    */   {
/* 29 */     this.id = id;
/* 30 */     this.ques = ques;
/* 31 */     this.author = author;
/* 32 */     this.pos_ans = pos_ans;
/* 33 */     this.corrent_ans = corrent_ans;
/*    */   }
/*    */   
/*    */ 
/*    */   public int getid()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */   
/*    */ 
/*    */   public String[] getpos_ans()
/*    */   {
/* 45 */     return this.pos_ans;
/*    */   }
/*    */   
/*    */   public String getques() {
/* 49 */     return this.ques;
/*    */   }
/*    */   
/*    */   public void setques(String ques) {
/* 53 */     this.ques = ques;
/*    */   }
/*    */   
/*    */   public String getauthor() {
/* 57 */     return this.author;
/*    */   }
/*    */   
/*    */   public void setauthor(String author) {
/* 61 */     this.author = author;
/*    */   }
/*    */   
/*    */   public int getcurrent_ans() {
/* 65 */     return this.corrent_ans;
/*    */   }
/*    */   
/*    */   public void setTcurrent_ans(int corrent_ans) {
/* 69 */     this.corrent_ans = corrent_ans;
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 75 */     return String.format("(%d) %s", new Object[] { Integer.valueOf(this.id), this.ques });
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Client.jar!\Entity\Question.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */