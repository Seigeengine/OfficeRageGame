package game.ld28.officerage.level;

import game.ld28.officerage.entities.TileEntity;
import game.ld28.officerage.gfx.SpriteSheet;
import game.ld28.officerage.utils.AssetLoader;

public enum TileType {
    SKY(false, 'a', new TileEntity(-100, -100, 32, 32, null)),
    STRUCTURE(true, 'b', new TileEntity(-100, -100, 32, 32, null)),
    BACK_WALL(false,'c', new TileEntity(-100, -100, 32, 32, null));
    
    private static final SpriteSheet tileSheet = new SpriteSheet(AssetLoader.getBufferedImage("/game/ld28/officerage/assets/graphics/TileSheet.png"), 32);
    
    public final boolean solidity;
    public final char notation;
    public final TileEntity tileEntity;
    
    private TileType(boolean solidity, char notation, TileEntity tileEntity) {
        this.solidity = solidity;
        this.notation = notation;
        this.tileEntity = tileEntity;
    }
    
    public static void load() {
        SKY.tileEntity.setImg(tileSheet.getSprite(1, 0));
        STRUCTURE.tileEntity.setImg(tileSheet.getSprite(0, 0));
        BACK_WALL.tileEntity.setImg(tileSheet.getSprite(2, 0));
    }
}
