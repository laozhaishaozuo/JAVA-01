package top.shaozuo.geektime.java01.week01;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;

/**
 * 查找指定路径下的xlass文件并加载
 * 
 * @author shaozuo
 *
 */
public class XlassClassLoader extends ClassLoader {

    private static final String XLASS_SUFFIX = ".xlass";
    private String searchPath;

    public XlassClassLoader(String path) {
        this.searchPath = path;
    }

    @Override
    protected Class<?> findClass(String classname) throws ClassNotFoundException {
        String classFilename = getClassName(classname);
        //System.out.println(classFilename);
        //System.out.println(searchPath);
        byte[] buf = getBytesFromResource(classFilename);
        if (buf != null) {
            return defineClass(classname, buf, 0, buf.length);
        }
        throw new ClassNotFoundException(classname);
    }

    private byte[] getBytesFromResource(String classFilename) {
        File file = new File(searchPath, classFilename);
        if (file.exists()) {
            try {
                byte[] buf = Files.readAllBytes(file.toPath());
                convert(buf);
                return buf;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.err.println(file.getAbsolutePath() + " not exists");
        }
        return null;
    }

    private String getClassName(String classname) {
        return classname.replace('.', '/') + XLASS_SUFFIX;
    }

    private void convert(byte[] buf) {
        for (int i = 0, len = buf.length; i < len; i++) {
            buf[i] = (byte) (255 - buf[i]);
        }
    }

    public static void main(String[] args) {
        URL path = XlassClassLoader.class.getResource("/");
        XlassClassLoader classLoader = new XlassClassLoader(path.getPath());
        try {
            Class<?> clazz = classLoader.findClass("Hello");
            Method declaredMethod = clazz.getDeclaredMethod("hello");
            declaredMethod.invoke(clazz.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}