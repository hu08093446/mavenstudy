package disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements WorkHandler<TradeTransaction> {
    private static AtomicInteger temp = new AtomicInteger(0);

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(100);
        if(temp.incrementAndGet() % 2 == 0) {
            throw new RuntimeException("==============================test");
        }
        System.out.println("消费者C1消费了一条消息");
    }

}