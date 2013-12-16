package game.ld28.officerage.utils;

import game.ld28.officerage.level.TileType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapIO {

    private MapIO() {}
    
    public static void save(String fileName, TileType[][] map) {
        long t1 = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            File f = new File(fileName);
            System.out.println(f.getAbsolutePath());
            bw = new BufferedWriter(new FileWriter(new File(fileName)));
            for (int i = 0; i < map.length; i++) {
                bw.write(nextLine(map[i]));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(MapIO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(MapIO.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
        System.out.println("Saving took: " + (System.currentTimeMillis()-t1) + " ms.");
    }
    
    private static String nextLine(TileType[] data) {
        StringBuilder sb = new StringBuilder(data.length);
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i].notation);
        }
        return sb.toString();
    }
    
}