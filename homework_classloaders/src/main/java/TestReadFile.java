import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestReadFile {
    public static void main(String[] args){
        Path path = Paths.get("C:\\temp\\ModuleImpl.class");
        encodeFile(path, "key");

}
    public static void encodeFile(Path path, String key){
        byte[] byteKey = key.getBytes();
        try {
            byte[] bFile = Files.readAllBytes(path);
            for (int i = 0; i < bFile.length; i++) {
                bFile[i] = (byte) (bFile[i] ^ byteKey[i % byteKey.length]);
            }
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void decodeFile(Path path, String key){
        byte[] byteKey = key.getBytes();
        try {
            byte[] bFile = Files.readAllBytes(path);
            for (int i = 0; i < bFile.length; i++) {
                bFile[i] = (byte) (bFile[i] ^ byteKey[i % byteKey.length]);
            }
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static byte[] decodeFileToBytes(Path path, String key){
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
