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
/*    */ 
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
/*    */   public int getid()
/*    */   {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */ 
/*    */   public String[] getpos_ans()
/*    */   {
/* 44 */     return this.pos_ans;
/*    */   }
/*    */   
/*    */   public String getques() {
/* 48 */     return this.ques;
/*    */   }
/*    */   
/* 51 */   public void setques(String ques) { this.ques = ques; }
/*    */   
/*    */   public String getauthor() {
/* 54 */     return this.author;
/*    */   }
/*    */   
/* 57 */   public void setauthor(String author) { this.author = author; }
/*    */   
/*    */   public int getcurrent_ans() {
/* 60 */     return this.corrent_ans;
/*    */   }
/*    */   
/* 63 */   public void setTcurrent_ans(int corrent_ans) { this.corrent_ans = corrent_ans; }
/*    */   
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 69 */     return String.format("(%d) %s", new Object[] { Integer.valueOf(this.id), this.ques });
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\Entity\Question.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */