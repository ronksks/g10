/*    */ package org.eclipse.jdt.internal.jarinjarloader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLStreamHandler;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RsrcURLStreamHandler
/*    */   extends URLStreamHandler
/*    */ {
/*    */   private ClassLoader classLoader;
/*    */   
/*    */   public RsrcURLStreamHandler(ClassLoader classLoader)
/*    */   {
/* 34 */     this.classLoader = classLoader;
/*    */   }
/*    */   
/*    */ 
/* 38 */   protected URLConnection openConnection(URL u) throws IOException { return new RsrcURLConnection(u, this.classLoader); }
/*    */   
/*    */   protected void parseURL(URL url, String spec, int start, int limit) {
/*    */     String file;
/*    */     String file;
/* 43 */     if (spec.startsWith("rsrc:")) {
/* 44 */       file = spec.substring(5); } else { String file;
/* 45 */       if (url.getFile().equals("./")) {
/* 46 */         file = spec; } else { String file;
/* 47 */         if (url.getFile().endsWith("/")) {
/* 48 */           file = url.getFile() + spec; } else { String file;
/* 49 */           if ("#runtime".equals(spec)) {
/* 50 */             file = url.getFile();
/*    */           } else
/* 52 */             file = spec; } } }
/* 53 */     setURL(url, "rsrc", "", -1, null, null, file, null, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Static\Desktop\G10_Prototype_Server.jar!\org\eclipse\jdt\internal\jarinjarloader\RsrcURLStreamHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */