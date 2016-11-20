package zipbomb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

class BombMaker implements Runnable{
    private static AtomicInteger counter = new AtomicInteger(0);
    public void run(){
        int number;
        synchronized(counter){
            number = counter.incrementAndGet();
        }
        Random rand = new Random();
        byte[] bytes = new byte[1024];
        for(int i = 0; i < bytes.length; i++){
            rand.nextBytes(bytes);
        }
        try{
            Files.write(Paths.get("Plik" + number + ".bin"), bytes);
        }catch(IOException e){
            Logger.getLogger(BombMaker.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
public class ZipBomb {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(new BombMaker()).start();
        }
    }
}
