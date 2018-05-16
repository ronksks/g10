
package Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Entity.Question;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class SystemServer extends AbstractServer {

	public static String user;
	public static String password;
	final String msgFromClient = "getQuestion";

    
	public SystemServer(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	
	

	protected void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		// TODO Auto-generated method stub

		if(msg.equals(msgFromClient)) {
			try {
				Connection con = connectToDB();
				client.sendToClient( getQuestion(con) );
				
				con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msg instanceof Question)
		{
			try {
			Connection con = connectToDB();
			updateQuestion(con, (Question)msg);
			
			
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	
	private void updateQuestion(Connection con, Question q)
	{

		try {
			String qry = "UPDATE questions SET correct_answer = ? WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, q.getcurrent_ans());
			pstmt.setInt(2, q.getid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// take all the question from data base
		private ArrayList<Question> getQuestion(Connection con) {
			Statement stmt;
			ArrayList<Question> qList = new ArrayList<>();
			try {
				stmt = con.createStatement();
				String qry = "SELECT * FROM questions";
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next())
				{
					int id = rs.getInt(1);
					String author = rs.getString(2);
					String ques = rs.getString(3);
					String ans1 = rs.getString(4);
					String ans2 = rs.getString(5);
					String ans3 = rs.getString(6);
					String ans4 = rs.getString(7);
					int corrent_ans = rs.getInt(8);
					
					String[] ans = {ans1, ans2 , ans3 , ans4 };
					
					qList.add(new Question(id,ques,author,ans,corrent_ans));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return qList;
		}
		
		public Connection connectToDB() throws SQLException {
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (Exception ex) {/* handle the error */
				System.out.println(ex.getMessage());
			}
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/prototype", user, password);
			
			return conn;
		}
	}

