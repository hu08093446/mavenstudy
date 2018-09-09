package traverseAllCatlog;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraverseCatlog {

    /**
     * 包路径的起始索引位置，示例：java\traverseAllCatlog\InvokeGetSetFuntion
     */
    private static final int BEGIN_INDEX = 5;

    public static void main(String[] args) throws IOException {
        invokeGetFunctionOfAllFolderClass();
    }

    private static void invokeGetFunctionOfAllFolderClass() throws IOException {
        //        traverseFolder2();
        String codePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java";
        System.out.println(codePath);

        traverseFolder(codePath);
    }

    /**
     * 遍历目录（递归遍历）
     * @param path 初始文件路径
     */
    private static void traverseFolder(String path) {
        try
        {
            File originFile = new File(path);
            if (originFile.exists())
            {
                File[] fileArr = originFile.listFiles();
                if (null == fileArr)
                {
                    return;
                }

                if (fileArr.length == 0)
                {
                    System.out.println("目录下啥子都没有!");
                }
                else
                {
                    for (File file : fileArr) {
                        if (file.isDirectory()) {
//                            System.out.println("文件夹:" + file.getAbsolutePath());
                            traverseFolder(file.getAbsolutePath());
                        } else {
                            System.out.println("文件:" + file.getAbsolutePath());
//                            System.out.println("fileName: " + file.getName());
                            // 如果有get方法，那么调用
                            InvokeGetSetFuntion.invokeGetSet(transferFilePathToPackagePath(file.getAbsolutePath()));
                        }
                    }
                }
            }
            else
            {
                System.out.println("文件不存在!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 匹配".java"结尾的文件名，也就是Java类文件
     * @param fileName 文件全名
     * @return 是否是Java文件
     */
    private static boolean isFileEndWithJava(String fileName)
    {
        String rule = "\\.java$";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(fileName);

        return m.find();
    }

    /**
     * 将类的绝对路径转换为包路径
     * @param filePath 类的绝对路径
     * @return 类的包路径
     */
    private static String transferFilePathToPackagePath(String filePath)
    {
        String packagePath = "";

        String rule = "java\\\\.*\\.";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(filePath);

        if (m.find())
        {
            String regexResult = m.group(0);
            packagePath = regexResult.substring(BEGIN_INDEX, regexResult.length() - 1).replace("\\", ".");
        }
        else
        {
            System.out.println("not found.....................................................");
        }

        return packagePath;
    }

}
