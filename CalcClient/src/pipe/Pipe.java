package pipe;

public interface Pipe {

	public boolean put(Object obj);

	public Object get() throws InterruptedException;

}
