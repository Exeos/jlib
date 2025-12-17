package me.exeos.jlib.reflection;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;

public class ClassDefiner extends ClassLoader {

    public HashMap<String, byte[]> classes = new HashMap<>();
    public HashMap<String, byte[]> resources = new HashMap<>();


    public ClassDefiner(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = classes.get(name);
        if (classData == null) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(name, classData, 0, classData.length);
    }

    @Override
    protected URL findResource(String name) {
        try {
            byte[] resourceData = resources.get(name);
            if (resourceData != null) {
                File temp = File.createTempFile("temp", null);
                temp.deleteOnExit();

                FileOutputStream fos = new FileOutputStream(temp);
                fos.write(resourceData);

                return temp.toURI().toURL();
            }
        } catch (Exception e) {
            System.out.println("Failed with e: ");
            e.printStackTrace();
        }

        return null;
    }
}
