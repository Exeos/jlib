package me.exeos.jlib.reflection;

import java.lang.reflect.Field;

public class ObjectCloner {

    public static Object deepClone(Object original) {
        try {
            Class<?> clazz = original.getClass();
            Object clone = clazz.getDeclaredConstructor().newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(original);
                field.set(clone, value);
            }
            return clone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
