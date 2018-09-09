package deepcopy;

import java.io.*;

public class DeepCopyUtil {

    // 这种方式要求所有的类型都实现Serializable接口
    // 那如果涉及到的类型很多很多，那该多麻烦
    // 使用fastjson进行序列化再反序列化来进行深度拷贝的速度比这个要快很多，既然这样，直接用fastjson就好了
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T o) throws IOException, ClassNotFoundException {
        //先序列化，写入到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(o);
        //然后反序列化，从流里读取出来，即完成复制
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (T)oi.readObject();
    }
}
