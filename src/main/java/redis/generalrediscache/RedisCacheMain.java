package redis.generalrediscache;

import java.util.concurrent.TimeUnit;

public class RedisCacheMain {
    public static void main(String[] args) {
        RedisCacheMain cacheMain = new RedisCacheMain();
        cacheMain.testSetDelCacheForRedis();
    }

    public void testSetDelCacheForRedis() {
        RedisCacheOperate.INSTANCE.setCache("cuixiaoyu", "a girl", 60);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RedisCacheOperate.INSTANCE.delCache("cuixiaoyu");
    }
}
