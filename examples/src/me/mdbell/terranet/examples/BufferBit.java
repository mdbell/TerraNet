package me.mdbell.terranet.examples;

import me.mdbell.terranet.common.io.Buffer;

import java.nio.ByteBuffer;

public class BufferBit {

    public static void main(String[] args) {
        Buffer<ByteBuffer> buffer = Buffer.allocate(2);
        buffer.writeShort(0b1111111100000100);
        for (int i = 0; i < 16; i++) {
            System.out.print(buffer.readBit() ? 1 : 0);
        }
        System.out.println();
    }
}
