package Entity;

import java.io.Serializable;
//Question Entity
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id ;
	private String  ques;
	private String author;
	private int corrent_ans;
	private String[] pos_ans = new String [4];
	//private String score_ques;
	
	
	
	/* Constructor for a question
	The function gets 
	- Question id
	- The question itself
	- Question author
	- 4 Possible answers
	- 1 Correct answer
	*/
	public Question (int id, String ques, String author, String[] pos_ans, int corrent_ans) {
		this.id = id;
		this.ques = ques;
		this.author = author;
		this.pos_ans = pos_ans;
		this.corrent_ans = corrent_ans;
	}
											/* Geters and Setters */
// ID getter
	public int getid()
	{
		return id;
	}
// True answer getter
	public String[] getpos_ans()
	{
		
		return pos_ans;
	}
// Question getter
	public String getques() {
		return ques;
	}
// Question setter
	public void setques(String ques) {
		this.ques = ques;
	}
// Author getter
	public String getauthor() {
		return author;
	}
// Author setter
	public void setauthor(String author) {
		this.author = author;
	}
// Current answer getter
	public int getcurrent_ans() {
		return corrent_ans ;
	}
// Current answer setter
	public void setTcurrent_ans(int corrent_ans) {
		this.corrent_ans = corrent_ans;
	}

	//The format inside the combobox : ID Question
	@Override
	public String toString() {
		return String.format("(%d) %s",id,ques);
	}
}
