package game.ld28.officerage.utils;

import game.ld28.officerage.level.TileType;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapIO {

    private static final Map<Character, TileType> tileRefMap = new HashMap<Character, TileType>();
    
    static {
        TileType[] types = TileType.values();
        for (int i = 0; i < types.length; i++) {
            tileRefMap.put(types[i].notation, types[i]);
        }
    }
    
    private MapIO() {}
    
    public static void save(String fileName, TileType[][] map) {
        long t1 = System.currentTimeMillis();
        BufferedWriter bw = null;
        try {
            File f = new File(fileName);
            System.out.println("Writing to: " + f.getAbsolutePath());
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
    
    public static TileType[][] load(String fileName){
        long t1 = System.currentTimeMillis();
        List<TileType[]> subArrays = new LinkedList<TileType[]>();
        
        BufferedReader br = null;
        try {
            File f = new File(fileName);
            System.out.println("Reading fron: " + f.getAbsolutePath());
//            br = new BufferedReader(new FileReader(new File(fileName)));
             br = new BufferedReader(new FileReader(new File(MapIO.class.getResource(fileName).toURI())));
            String line = null;
            while ((line = br.readLine()) != null) {
                subArrays.add(parseLine(line));
            }
        } catch (IOException ex) {
            Logger.getLogger(MapIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MapIO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(MapIO.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
        TileType[][] typeMap = new TileType[subArrays.size()][0];
        for (int i = 0; i < typeMap.length; i++) {
            typeMap[i] = subArrays.remove(0);
        }
        System.out.println("Loading took: " + (System.currentTimeMillis()-t1) + " ms.");
        return typeMap;
    }
    
    private static String nextLine(TileType[] data) {
        StringBuilder sb = new StringBuilder(data.length);
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i].notation);
        }
        return sb.toString();
    }
    
    private static TileType[] parseLine(String line) {
        TileType[] data = new TileType[line.length()];
        for (int i = 0; i < data.length; i++) {
            data[i] = tileRefMap.get(line.charAt(i));
            System.out.print(line.charAt(i));
        }
        System.out.println();
        return data;
    }
    
}