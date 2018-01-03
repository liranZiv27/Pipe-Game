package server;

public interface CacheManager {
	public String getSolution();//doesn't have to be generic
	public void saveSolution(String solution);
}
