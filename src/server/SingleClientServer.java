package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleClientServer implements Server {
	private int port;
	private ClientHandler<?> ch;
	@SuppressWarnings("unused")
	private volatile boolean stop;
	
	public SingleClientServer(int port, ClientHandler<?> ch) {
		this.port = port;
		this.ch = ch;
		stop=false;
	}
	
	@Override
	public void start() throws Exception {
		runServer();
	}

	private void runServer() throws Exception {
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(3000);
			Socket aClient = server.accept(); 
			
			ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
			
		//	aClient.getInputStream().close();
		//	aClient.getOutputStream().close();
			aClient.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		stop=true;
	}

}
