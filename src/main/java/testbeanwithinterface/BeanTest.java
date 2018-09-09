package testbeanwithinterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testbeanwithinterface/bean.xml"})
// 要想使spring的注解起作用，那么这个类本身必须作为bean加载到spring容器中，交由spring来管理
// 就是说这个类不能是new出来的，new出来的类跟spring没有关系
public class BeanTest {

    @Autowired
    private WatchIngTv watchIngTv;

    // 这样是会报错的，因为Spring并不知道要把哪个Play实例传给play
//    @Autowired
//    private Play play;

    @Autowired
    private PlayDota playDota;

    @Autowired
    private PlayLoL playLoL;

    @Test
    public void testA() {
        watchIngTv.watchDouyu();
        watchIngTv.watchPanda();
    }

//    @Test
//    public void testB() {
//        System.out.println(play.gameName());
//        System.out.println(play.gameTime());
//    }

    @Test
    public void testC() {
        System.out.println(playDota.gameName());
        System.out.println(playDota.gameTime());

        System.out.println(playLoL.gameName());
        System.out.println(playLoL.gameTime());
    }

}
