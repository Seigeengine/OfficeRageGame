package game.ld28.officerage.level;

import game.ld28.officerage.entities.TestEntity;

public enum TileType {
    SKY(false, new TestEntity(-100, -100, 32, 32, 0x0000FF)),
    STRUCTURE(true, new TestEntity(-100, -100, 32, 32, 0xAAAAAA)),
    BACK_WALL(false, new TestEntity(-100, -100, 32, 32, 0x222222));
    
    
    public final boolean solidity;
    public final TestEntity tileEntity;
    
    private TileType(boolean solidity, TestEntity tileEntity) {
        this.solidity = solidity;
        this.tileEntity = tileEntity;
    }
}
