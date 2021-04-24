package me.mdbell.terranet.files.metadata.gson;

import com.google.gson.Gson;
import me.mdbell.terranet.files.metadata.TileMetadata;
import me.mdbell.terranet.files.metadata.TileMetadataFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonTileMetadataFactory extends TileMetadataFactory {

    private static final Gson gson = new Gson();

    @Override
    public TileMetadata[] getMetadata() {
        InputStream in = TileMetadataFactory.class.getResourceAsStream("/tiles.json");
        InputStreamReader reader = new InputStreamReader(in);
        return gson.fromJson(reader, TileMetadata[].class);
    }
}
