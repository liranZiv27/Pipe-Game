package server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler<T>{
	void handleClient(InputStream inFromClient, OutputStream outToClient);
}	
