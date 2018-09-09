package learnproxy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class ResultKeeper {
    private static Map<String, FutureTask<Object>> dataKeeper = new HashMap<>();

}
