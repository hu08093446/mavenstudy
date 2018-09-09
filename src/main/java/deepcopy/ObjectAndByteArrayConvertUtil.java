package deepcopy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

public class ObjectAndByteArrayConvertUtil {
    // 做日志用
    private static Logger logger = LoggerFactory.getLogger(ObjectAndByteArrayConvertUtil.class);

    // 对象转化为字节时候的缓存字节大小
    public static int default_byte_array_init_size = 4096;

    /**
     * 将字节数组转换为对象
     *
     * @param bytes
     * @return
     */
    public static Object ByteArrayToObject(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            logger.info("ByteArrayToObject字节对象转换为对象时，出现错误--" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将对象转换为字节数组
     *
     * @param obj
     * @return
     */
    public static byte[] ObjectToByteArray(Object obj) {
        if (obj == null) {
            return null;
        }
        byte[] bytes = null;
        try {
            // object to byteArray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("ObjectToByteArray字节对象转换为对象时，出现错误--"
                    + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    public static byte[] getByteArrayOutputStream(InputStream is) {
        ByteArrayOutputStream bios = new ByteArrayOutputStream();
        byte[] byteArray = new byte[default_byte_array_init_size];
        try {
            int once_len = 0;
            while ((once_len = is.read(byteArray, 0,
                    default_byte_array_init_size)) != -1) {
                bios.write(byteArray, 0, once_len);
                byteArray = new byte[default_byte_array_init_size];
                // System.out.println("read once!");
            }
            byteArray = bios.toByteArray();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bios != null) {
                try {
                    bios.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String[] strArray = { "str1", "str2", "str3" };

        System.out.println("原始字符串数组=" + Arrays.toString(strArray));

        byte[] byte_array = ObjectToByteArray(strArray);
        String[] strArray_copy = (String[]) ByteArrayToObject(byte_array);

        System.out.println("转换后的字符串数组=" + Arrays.toString(strArray_copy));
    }
}
