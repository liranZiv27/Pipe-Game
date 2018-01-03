package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SingleClientHandler implements ClientHandler{
	CacheManager cacheManager;

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		String line;
		char[][] problem;
		BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(inFromClient));
		try {
			line = bufferedReader.readLine();
			while(!line.equals("done")){
				problems
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
