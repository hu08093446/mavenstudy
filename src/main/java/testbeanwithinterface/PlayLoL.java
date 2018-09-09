package testbeanwithinterface;

import org.springframework.stereotype.Component;

@Component
public class PlayLoL implements Play {
    @Override
    public String gameName() {
        return "LOL";
    }

    @Override
    public Integer gameTime() {
        return 2;
    }
}
