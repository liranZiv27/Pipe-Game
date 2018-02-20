package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SingleClientServer implements Server {
	private int port;
	//@SuppressWarnings("unused")
	protected volatile boolean stop;
	
	public SingleClientServer(int port) {
		this.port = port;
		stop=false;
	}
	
	@Override
	public void start(ClientHandler ch) {
		new Thread(()->{
			try {
				runServer(ch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}).start();
	}

	private void runServer(ClientHandler ch) throws Exception {
	ServerSocket server=new ServerSocket(port);
	server.setSoTimeout(1000);

	while(!stop){ 

		try{ 
			Socket aClient=server.accept(); // blocking call try 
			try {
				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
				aClient.getInputStream().close();
				aClient.getOutputStream().close();
				aClient.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		catch(SocketTimeoutException ste) {
			
		}
	}
	server.close();
}
	@Override
	public void stop() {
		stop=true;
	}
}
