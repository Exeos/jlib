package me.exeos.jlib.reflection;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class ResourceDefiner extends URLClassLoader {

    public ResourceDefiner(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addURL(String name, byte[] data) throws Exception {
        File tempFile = new File(name);
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        fileOutputStream.write(data);
        tempFile.deleteOnExit();

        super.addURL(tempFile.toURI().toURL());
    }
}
