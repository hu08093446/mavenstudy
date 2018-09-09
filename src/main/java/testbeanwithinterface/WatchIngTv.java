package testbeanwithinterface;

import org.springframework.stereotype.Component;

@Component
public class WatchIngTv {

    public void watchDouyu() {
        System.out.println("watching douyu, douyu is very interesting.");
    }

    public void watchPanda() {
        System.out.println("watching panda, panda is very interesting.");
    }
}
