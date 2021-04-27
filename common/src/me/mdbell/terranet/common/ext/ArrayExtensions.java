package me.mdbell.terranet.common.ext;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class ArrayExtensions {
    
    public void copyTo(int[] source, int[] dest) {
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    public void copyTo(byte[] source, byte[] dest) {
        System.arraycopy(source, 0, dest, 0, source.length);
    }

    public <T, V> V[] map(T[] source, Class<V> dest, Function<T, V> mapper){
        V[] res = (V[]) Array.newInstance(dest, source.length);
        for(int i = 0; i < source.length; i++){
            res[i] = mapper.apply(source[i]);
        }
        return res;
    }

    public <T> T[] fill(T[] arr, Supplier<T> supplier) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = supplier.get();
        }
        return arr;
    }

}
