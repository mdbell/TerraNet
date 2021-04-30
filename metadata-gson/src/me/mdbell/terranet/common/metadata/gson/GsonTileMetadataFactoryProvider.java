package me.mdbell.terranet.common.metadata.gson;


import me.mdbell.terranet.metadata.TileMetadataFactory;
import me.mdbell.terranet.metadata.TileMetadataFactoryProvider;

public class GsonTileMetadataFactoryProvider implements TileMetadataFactoryProvider {
    @Override
    public TileMetadataFactory newInstance() {
        return new GsonTileMetadataFactory();
    }
}
