package basiclearn.learnautowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/basiclearn/basic.xml"})
public class Main {
    private Person perso;

    @Resource
    public void setPerso(Person perso) {
        this.perso = perso;
    }

    @Test
    public void testMethod() {
        perso.print();
    }
}
