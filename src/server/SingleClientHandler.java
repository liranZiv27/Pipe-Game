package server;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class SingleClientHandler<T> implements ClientHandler<T>{
	CacheManager<String> cacheManager;
	@Override
	public abstract void handleClient(InputStream inFromClient, OutputStream outToClient);
}
