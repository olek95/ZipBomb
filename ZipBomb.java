package zipbomb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa <code>ZipBomb</code> reprezentuje program tworzący jeden plik w folderze 
 * z programem, o rozmiarze ok. 1 MB. Plik ten tworzy 10 wątków.
 * @author AleksanderSklorz
 */
public class ZipBomb {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(new Runnable(){
                public void run(){
                    Random rand = new Random();
                    byte[] bytes = new byte[100000]; // zakładam że 1 MB = 1000000 B, ale podzielone na 10 wątków to 100000
                    for(int i = 0; i < bytes.length; i++){
                        rand.nextBytes(bytes);
                    }
                    try{
                        Files.write(Paths.get("Plik.bin"), bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND); 
                    }catch (IOException e){
                        Logger.getLogger(ZipBomb.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }).start();
        }
    }
}
