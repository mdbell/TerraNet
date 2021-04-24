package me.mdbell.terranet.files.metadata.gson;

import me.mdbell.terranet.files.metadata.TileMetadataFactory;
import me.mdbell.terranet.files.metadata.TileMetadataFactoryProvider;

public class GsonTileMetadataFactoryProvider implements TileMetadataFactoryProvider {
    @Override
    public TileMetadataFactory newInstance() {
        return new GsonTileMetadataFactory();
    }
}
