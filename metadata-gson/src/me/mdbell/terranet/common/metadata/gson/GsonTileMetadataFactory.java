package me.mdbell.terranet.common.metadata.gson;


import com.google.gson.Gson;
import me.mdbell.terranet.metadata.TileMetadata;
import me.mdbell.terranet.metadata.TileMetadataFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonTileMetadataFactory extends TileMetadataFactory {

    private static final Gson gson = new Gson();
    private static TileMetadata[] cached;

    @Override
    public TileMetadata[] getMetadata() {
        if(cached == null) {
            InputStream in = TileMetadataFactory.class.getResourceAsStream("/tiles.json");
            InputStreamReader reader = new InputStreamReader(in);
            return cached = gson.fromJson(reader, TileMetadata[].class);
        }
        return cached;
    }
}
