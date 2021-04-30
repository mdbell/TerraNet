package me.mdbell.terranet.metadata;

import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

@Slf4j
public abstract class TileMetadataFactory {

    private static TileMetadataFactory defaultFactory;

    static{
        initDefault();
    }

    private static void initDefault() {
        ServiceLoader<TileMetadataFactoryProvider> loader = ServiceLoader.load(TileMetadataFactoryProvider.class);

        for(TileMetadataFactoryProvider provider : loader){
            defaultFactory = provider.newInstance();
            return;
        }
        log.error("No {} found!", TileMetadataFactory.class.getName());
    }

    public static TileMetadataFactory getDefaultFactory(){
        return defaultFactory;
    }

    public static void setDefaultFactory(TileMetadataFactory factory){
        defaultFactory = factory;
    }

    public abstract TileMetadata[] getMetadata();

}
