package pipe;

import java.util.ArrayList;
import java.util.List;

public class PipeImpl implements Pipe {

	private List<Object> buffer = null;

	public PipeImpl() {
		buffer = new ArrayList<Object>();
	}

	@Override
	public synchronized boolean put(Object obj) {
		boolean bAdded = buffer.add(obj);
		notify();
		return bAdded;
	}

	@Override
	public synchronized Object get() throws InterruptedException {
		while (buffer.isEmpty()) {
			wait();
		}

		Object obj = buffer.remove(0);
		return obj;
	}

}
