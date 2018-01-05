package server;

public class MainTrain {

	public static void main(String[] args) {
		
		SingleClientServer myServer = new SingleClientServer(6400, new StringSingleClientHandler(new GameCacheManager()));
		try {
			myServer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // should be in a different thread…
		// ... wait for administrator to close
		myServer.stop();
	}

}
