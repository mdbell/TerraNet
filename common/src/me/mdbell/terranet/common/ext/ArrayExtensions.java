package me.mdbell.terranet.common.ext;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ArrayExtensions {
    public void copyTo(int[] source, int[] dest) {
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    public void copyTo(byte[] source, byte[] dest) {
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    public <T> T[] fill(T[] arr, Supplier<T> supplier) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = supplier.get();
        }
        return arr;
    }

}
