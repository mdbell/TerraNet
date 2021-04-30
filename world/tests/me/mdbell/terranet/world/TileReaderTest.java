package me.mdbell.terranet.world;

import me.mdbell.terranet.common.game.scene.LiquidType;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.reader.TileReader;
import me.mdbell.terranet.world.tree.TileNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileReaderTest implements WorldFileConstants {

    @Test
    public void testActiveDecode() {
        byte[] data = {TILE_ACTIVE_MASK, 0};
        TileNode node = loadTile(data);
        assertTrue(node.isActive());
    }

    @Test
    public void testIdDecode() {
        byte id = 2;
        byte[] data = {TILE_ACTIVE_MASK, id};
        TileNode node = loadTile(data);
        assertEquals(node.getType(), id);
    }

    @Test
    public void testIdDecodeWide() {
        byte[] data = {TILE_ACTIVE_MASK | TILE_WIDE_TYPE_MASK, 0x37, 0x13};
        TileNode node = loadTile(data);
        assertEquals(0x1337, node.getType());
    }

    @Test
    public void testImportantDecode() {
        int flag1 = TILE_ACTIVE_MASK;
        int id = 42; // Hanging Lanterns
        int frameX = 100;
        int frameY = 123;
        byte[] data = new byte[6];
        Buffer<?> buffer = Buffer.wrap(data);
        buffer.writeByte(flag1);
        buffer.writeByte(id);
        buffer.writeShortLE(frameX);
        buffer.writeShortLE(frameY);

        TileNode node = loadTile(data);
        assertEquals(frameX, node.getFrameX());
        assertEquals(frameY, node.getFrameY());
    }

    @Test
    public void testColorDecode() {
        byte flag1 = TILE_ACTIVE_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_COLOR_MASK;
        byte color = 45;
        byte id = 0; // dirt
        byte[] data = {flag1, flag2, flag3, id, color};
        TileNode node = loadTile(data);
        assertEquals(color, node.getColor());
    }

    @Test
    public void testWallDecode() {
        byte flag1 = TILE_WALL_MASK;
        byte[] data = {flag1, 0x4A};
        TileNode node = loadTile(data);
        assertEquals(0x4A, node.getWall());
    }

    @Test
    public void testWideWallDecode() {
        byte flag1 = TILE_WALL_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_WALL_EXTENDED_ID_MASK;
        byte[] data = {flag1, flag2, flag3, (byte) 0x30, (byte) 0x01};
        TileNode node = loadTile(data);
        assertEquals(0x0130, node.getWall());
    }

    @Test
    public void testWallColorDecode() {
        byte flag1 = TILE_WALL_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_WALL_COLOR_MASK;
        byte[] data = {flag1, flag2, flag3, 0x4A, 0x78};
        TileNode node = loadTile(data);
        assertEquals(0x4A, node.getWall());
        assertEquals(0x78, node.getWallColor());
    }

    @Test
    public void testWideWallWithColorDecode() {
        byte flag1 = TILE_WALL_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_WALL_EXTENDED_ID_MASK | TILE_WALL_COLOR_MASK;
        byte[] data = {flag1, flag2, flag3, (byte) 0x30, 0x4A, (byte) 0x01};
        TileNode node = loadTile(data);
        assertEquals(0x0130, node.getWall());
        assertEquals(0x4A, node.getWallColor());
    }

    @Test
    public void testLiquidDecodeWater() {
        byte flag1 = (byte) (1 << 3);
        byte quantity = 4;
        byte[] data = {flag1, quantity};
        TileNode node = loadTile(data);
        assertEquals(LiquidType.WATER, node.getLiquidType());
        assertEquals(quantity, node.getLiquid());
    }

    @Test
    public void testLiquidDecodeLava() {
        byte flag1 = (byte) (2 << 3);
        byte quantity = 4;
        byte[] data = {flag1, quantity};
        TileNode node = loadTile(data);
        assertEquals(LiquidType.LAVA, node.getLiquidType());
        assertEquals(quantity, node.getLiquid());
    }

    @Test
    public void testLiquidDecodeHoney() {
        byte flag1 = (byte) (3 << 3);
        byte quantity = 4;
        byte[] data = {flag1, quantity};
        TileNode node = loadTile(data);
        assertEquals(LiquidType.HONEY, node.getLiquidType());
        assertEquals(quantity, node.getLiquid());
    }

    @Test
    public void testHalfBrickDecode() {
        byte flag1 = TILE_ACTIVE_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_SLOPE_BRICK_MASK;
        byte id = 0;
        byte[] data = {flag1, flag2, id};
        TileNode node = loadTile(data);
        assertEquals(0, node.getType());
        assertTrue(node.isHalfBrick());
    }

    @Test
    public void testSlopeDecode() {
        int slopeId = 3;

        byte flag1 = TILE_ACTIVE_MASK | TILE_EXT_FLAGS_MASK;
        byte flag2 = (byte) (slopeId + 1 << 4);
        byte id = 0;
        byte[] data = {flag1, flag2, id};
        TileNode node = loadTile(data);

        assertEquals(id, node.getType());
        assertFalse(node.isHalfBrick());
        assertEquals(slopeId, node.getSlope());
    }

    @Test
    public void testActuatorDecode() {
        byte flag1 = TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_ACTUATOR_MASK;
        byte[] data = {flag1, flag2, flag3};
        TileNode node = loadTile(data);

        assertTrue(node.isActuator());
        assertFalse(node.isInActive());
    }

    @Test
    public void testActuatedDecode() {
        byte flag1 = TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_ACTUATOR_MASK | TILE_ACTUATED_MASK;
        byte[] data = {flag1, flag2, flag3};
        TileNode node = loadTile(data);

        assertTrue(node.isActuator());
        assertTrue(node.isInActive());
    }

    @Test
    public void testWire1Decode() {
        byte flag1 = TILE_EXT_FLAGS_2_MASK;
        byte flag2 = TILE_WIRE_1_MASK;
        byte[] data = {flag1, flag2};
        TileNode node = loadTile(data);
        assertTrue(node.isWire());
        assertFalse(node.isWire2());
        assertFalse(node.isWire3());
        assertFalse(node.isWire4());
    }

    @Test
    public void testWire2Decode() {
        byte flag1 = TILE_EXT_FLAGS_2_MASK;
        byte flag2 = TILE_WIRE_2_MASK;
        byte[] data = {flag1, flag2};
        TileNode node = loadTile(data);
        assertFalse(node.isWire());
        assertTrue(node.isWire2());
        assertFalse(node.isWire3());
        assertFalse(node.isWire4());
    }

    @Test
    public void testWire3Decode() {
        byte flag1 = TILE_EXT_FLAGS_2_MASK;
        byte flag2 = TILE_WIRE_3_MASK;
        byte[] data = {flag1, flag2};
        TileNode node = loadTile(data);
        assertFalse(node.isWire());
        assertFalse(node.isWire2());
        assertTrue(node.isWire3());
        assertFalse(node.isWire4());
    }

    @Test
    public void testWire4() {
        byte flag1 = TILE_EXT_FLAGS_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK;
        byte flag3 = TILE_WIRE_4_MASK;
        byte[] data = {flag1, flag2, flag3};
        TileNode node = loadTile(data);
        assertFalse(node.isWire());
        assertFalse(node.isWire2());
        assertFalse(node.isWire3());
        assertTrue(node.isWire4());
    }

    @Test
    public void testComplexDecode() {
        byte flag1 = TILE_ACTIVE_MASK | TILE_EXT_FLAGS_MASK | TILE_WIDE_TYPE_MASK;
        byte flag2 = TILE_EXT_FLAGS_2_MASK | TILE_WIRE_2_MASK;
        byte flag3 = TILE_WIRE_4_MASK | TILE_COLOR_MASK;
        byte[] data = {flag1, flag2, flag3, 0, 1, 0x4A};

        TileNode node = loadTile(data);
        assertTrue(node.isActive());
        assertEquals(256, node.getType());
        assertEquals(0x4A, node.getColor());
        assertEquals(0, node.getWall());
        assertEquals(0, node.getWallColor());
        assertEquals(LiquidType.NONE, node.getLiquidType());
        assertEquals(0, node.getLiquid());
        assertEquals(0, node.getSlope());
        assertFalse(node.isHalfBrick());
        assertFalse(node.isWire());
        assertTrue(node.isWire2());
        assertFalse(node.isWire3());
        assertTrue(node.isWire4());
        assertFalse(node.isActuator());
        assertFalse(node.isInActive());
    }

    @Test
    public void testRLEDecode() {
        int compressedTiles = 0;
        byte[] tileData = {0x40, (byte) compressedTiles};
        Buffer<?> buffer = Buffer.wrap(tileData);

        TileReader reader = new TileReader(buffer);
        reader.setSize(1, 1);

        List<TileNode> nodes = new LinkedList<>();
        reader.setVisitor(new TileDataVisitor() {
            @Override
            public void visitStart() {

            }

            @Override
            public void visitTileX(int x) {

            }

            @Override
            public TileVisitor visitTile(int x, int y) {
                TileNode node = new TileNode();
                nodes.add(node);
                return node;
            }

            @Override
            public void visitEnd() {

            }
        });
        reader.visitAllTiles();
        assertEquals(compressedTiles + 1, nodes.size());
    }

    public TileNode loadTile(byte[] data) {
        Buffer<?> buffer = Buffer.wrap(data);

        TileNode node = new TileNode();
        TileReader reader = initReader(buffer);
        reader.setVisitor(visitorForNode(node));
        reader.visitAllTiles();
        return node;
    }

    private TileDataVisitor visitorForNode(TileNode node) {
        return new TileDataVisitor() {
            @Override
            public void visitStart() {

            }

            @Override
            public void visitTileX(int x) {

            }

            @Override
            public TileVisitor visitTile(int x, int y) {
                return node;
            }

            @Override
            public void visitEnd() {

            }
        };
    }

    private TileReader initReader(Buffer<?> buffer) {
        TileReader reader = new TileReader(buffer);
        reader.setSize(1, 1);
        reader.setImportant(IMPORTANT_FLAGS);
        return reader;
    }
}
