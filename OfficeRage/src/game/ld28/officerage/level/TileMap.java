package game.ld28.officerage.level;

import game.ld28.officerage.entities.StaticEntity;
import game.ld28.officerage.gfx.Renderable;
import java.awt.Graphics2D;

public class TileMap implements Renderable {
    private TileType[][] tileMap;
    private Camera camera;
    public final int tileSize;

    public TileMap(int width, int height, int tileSize, Camera camera) {
        this.camera = camera;
        tileMap = new TileType[width][height];
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                if (i == 6 || i == tileMap.length-7) {
                    tileMap[i][j] = TileType.STRUCTURE;
                }else if((j-6) % 6 == 0) {
                    tileMap[i][j] = TileType.STRUCTURE;
                }else {
                    tileMap[i][j] = TileType.BACK_WALL;
                }
            }
        }
        this.tileSize = tileSize;
    }
    
    public void setTile(int x, int y, TileType type) {
        x = (int) ((x + camera.getX()) / tileSize);
        y = (int) ((y + camera.getY()) / tileSize);
        if (x > -1 && y > -1 && x < camera.getWidth() && y < camera.getHeight()) {
            tileMap[x][y] = type;
        }
    }
    
    public TileType[][] getData() {
        return tileMap;
    }

    @Override
    public void render(Graphics2D g2d) {
        int startX = Math.max(0, (int) (camera.getX() / tileSize));
        int startY = Math.max(0, (int) (camera.getY() / tileSize));
        int xCap = Math.min(tileMap.length, startX + camera.getWidth() / tileSize + 1);
        int yCap = Math.min(tileMap[0].length, startY + camera.getHeight() / tileSize + 2);
        for (int i = startX; i < xCap; i++) {
            for (int j = startY; j < yCap; j++) {
                tileMap[i][j].tileEntity.setPos(i * tileSize, j * tileSize);
                tileMap[i][j].tileEntity.render(g2d);
            }
        }
    }
    
    public boolean testCollision(StaticEntity e) {
        int x1 = Math.max(0, (int) (e.getX() / tileSize));
        int y1 = Math.max(0, (int) (e.getY() / tileSize));
        int x2 = Math.min(tileMap.length, (int) ((e.getX()+e.getWidth()) / tileSize + 1));
        int y2 = Math.min(tileMap[0].length, (int) ((e.getY()+e.getHeight()) / tileSize + 1));
        
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (tileMap[i][j].solidity) {
                    return true;
                }
            }
        }
        return false;
    }
}