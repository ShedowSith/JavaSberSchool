package ru.sbt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedClassLoader extends ClassLoader{
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Path.of(dir.getPath()+"\\" + name + ".class");
        byte[] bytes = new byte[0];
        File f = new File(path.toUri());
        if(!f.isFile())
            throw new ClassNotFoundException("Нет такого класса " + name);

       bytes = decodeFileToBytes(f.toPath(), key);
       Class<?> cl = defineClass(name, bytes, 0, bytes.length);
        return cl;
    }
    private static byte[] decodeFileToBytes(Path path, String key){
        byte[] byteKey = key.getBytes();
        byte[] bFile = new byte[0];
        try {
            bFile = Files.readAllBytes(path);
            for (int i = 0; i < bFile.length; i++) {
                bFile[i] = (byte) (bFile[i] ^ byteKey[i % byteKey.length]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bFile;
    }

}
