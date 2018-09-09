package testbeanwithinterface;

import org.springframework.stereotype.Component;

@Component
public class PlayDota implements Play {

    @Override
    public String gameName() {
        return "dota";
    }

    @Override
    public Integer gameTime() {
        return 1;
    }
}
