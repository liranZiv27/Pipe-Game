package search;

public interface Parser<T,E> {
	public T parse(E problem);
}
