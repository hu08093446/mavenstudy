package learndynamicprop;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PropertyUtils {

    private static Map<String, PropertiesConfiguration> CONF_MAP = new HashMap<>(10);

    // 文件扫描的时间间隔
    private static long FILE_SCAN_PEROOD = 100L;

    public static void regisiterProp(File file) {
        String filePath = file.getPath();
        System.out.println("fileName is: " + file.getName() + " filePath is: "+ filePath);
        if(CONF_MAP.containsKey(filePath)) {
            return;
        }

        PropertiesConfiguration pc = null;
        try {
            pc = new PropertiesConfiguration(new File(filePath));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
        fileChangedReloadingStrategy.setRefreshDelay(FILE_SCAN_PEROOD);

        if(null != pc) {
            pc.setReloadingStrategy(fileChangedReloadingStrategy);
            CONF_MAP.put(filePath, pc);
        }

    }

    public static String getString(String key) {
        return getString(key, "default");
    }

    public static String getString(String key, String defaultValue) {
        String result = null;
        if(StringUtils.isEmpty(key)) {
            return result;
        }

        for(PropertiesConfiguration pc : CONF_MAP.values()) {
            result = pc.getString(key);
            if(null != result) {
                break;
            }
        }

        if(null == result) {
            result = defaultValue;
        }

        return result;
    }
}
