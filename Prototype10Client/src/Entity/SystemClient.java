package Entity;

import java.io.IOException;
import java.util.ArrayList;

import Control.QuestionsController;
import ocsf.client.AbstractClient;
//Client Entity
public class SystemClient extends AbstractClient {
//Create ArrayList for the list of questions
	private ArrayList<Question> qList;
// Getter for question list
	public ArrayList<Question> getqList() {
		return qList;
	}
// Setter for question list
	public void setqList(ArrayList<Question> qList) {
		this.qList = qList;
	}
//function define the host and port of client and open the connection
	public SystemClient(String host, int port) {
		super(host, port); // send host and port of the client to constructor of 'AbstractClient'
		// TODO Auto-generated constructor stub
		try {
			openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// Function handling messages from the server
	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		synchronized (this) {
			this.qList = (ArrayList<Question>) msg;
			if(QuestionsController.observelist != null) // observable list already initialized
				QuestionsController.handleListUpdateFromClient(qList);
			notifyAll();
		}
	}
	
}