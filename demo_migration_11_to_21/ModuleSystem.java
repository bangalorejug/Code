/* JEP 396 - Do strong encapsulation for JDK internals by default breaks this (kind of) tests */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ModuleSystem {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Exception handling elided
        Class<?> clz = Class.forName("sun.invoke.util.BytecodeName");
        Method method = clz.getDeclaredMethod("parseBytecodeName", String.class);
        Object res = method.invoke(null, "java/lang/String");
        System.out.println(Arrays.toString((Object[])res));
    }
}
