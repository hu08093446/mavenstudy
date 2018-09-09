package disruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer {
    private RingBuffer<TradeTransaction> ringBuffer;

    public Producer(RingBuffer<TradeTransaction> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData() {
        // 可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();
        try {

        } finally {
            System.out.println("生产者发送了一条消息");
            ringBuffer.publish(sequence);
        }
    }
}
