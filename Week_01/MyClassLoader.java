import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            Class<?> cls = new MyClassLoader().findClass("Hello.xlass");
            Object object = cls.newInstance();
            Method method = cls.getMethod("hello");
            method.invoke(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = MyClassLoader.class.getClassLoader().getResourceAsStream(name);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            while (true) {
                int b = in.read();
                if (b == -1) {
                    break;
                }
                byteStream.write((byte) (255 - b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = byteStream.toByteArray();
        return defineClass(name.substring(0, name.indexOf(".")), bytes, 0, bytes.length);
    }
}
