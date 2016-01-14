package hello;

import io.iron.ironmq.Client;
import io.iron.ironmq.Queue;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class IronMQWriter<T> implements ItemWriter<T> {

	private Client client = new Client();
	Queue queue;

	public IronMQWriter() {
		queue = client.queue("test-queue");
	}

	@Override
	public void write(List<? extends T> items) throws Exception {

		List<String> pushMessages = new ArrayList<String>();
		for (int i = 0; items.size() > i; i++) {
			Domain obj = (Domain) items.get(i);
			pushMessages.add(obj.getId() + ":" + obj.getDomain());
		}
		queue.pushMessages(pushMessages.toArray(new String[pushMessages.size()]));
		
	}
}