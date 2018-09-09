package usedisruptor;


import com.lmax.disruptor.EventHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class LongEventHandler implements EventHandler<LongEvent> {

    private int index;

    private static AtomicInteger temp = new AtomicInteger(0);


    public LongEventHandler(int index) {
        this.index = index;
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        if(temp.incrementAndGet() % 2 == 0) {
            throw new RuntimeException("==============================test");
        }
        System.out.println(String.format("index=%s, sequence=%s, Event=%s endOfBatch=%s",
                index, sequence, event.getValue(), endOfBatch));
//        Thread.sleep(100);
    }
}
