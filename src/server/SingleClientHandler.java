package server;

import java.io.InputStream;
import java.io.OutputStream;

public class SingleClientHandler implements ClientHandler{
	
	private State<?> state;
	
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
	}
	

}
