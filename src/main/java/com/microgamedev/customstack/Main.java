package com.microgamedev.customstack;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent;
import com.hypixel.hytale.assetstore.map.DefaultAssetMap;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.util.Config;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Pattern;

public class Main extends JavaPlugin {

    private final Config<StackSizeConfig> cfg;
    private final String serverVersion;

    public Main(@Nonnull JavaPluginInit init) {
        super(init);
        this.serverVersion = com.hypixel.hytale.server.core.HytaleServer.class.getPackage()
                .getImplementationVersion();
        this.cfg = this.withConfig("StackSizes_" + serverVersion, StackSizeConfig.CODEC);
    }

    @Override
    protected void setup() {
        this.getEventRegistry().register(LoadedAssetsEvent.class, Item.class, this::onItemAssetLoad);
        this.cfg.get().hytaleVersion = serverVersion != null ? serverVersion : "unknown";
        super.setup();
    }

    private void onItemAssetLoad(@Nonnull LoadedAssetsEvent<String, Item, DefaultAssetMap<String, Item>> onItemsLoad) {
        try {
            final Map<String, Item> assets = onItemsLoad.getLoadedAssets();
            final Field maxStackSize = Item.class.getDeclaredField("maxStack");
            maxStackSize.setAccessible(true);

            StackSizeConfig configState = this.cfg.get();

            for (Map.Entry<String, Integer> entry : configState.itemIds.entrySet()) {
                final Item item = assets.get(entry.getKey());
                if (item != null) {
                    maxStackSize.set(item, entry.getValue());
                }
            }
            for (Map.Entry<String, Integer> entry : configState.patterns.entrySet()) {
                final Pattern pattern = Pattern.compile(entry.getKey());
                for (String id : assets.keySet()) {
                    if (pattern.matcher(id).matches()) {
                        maxStackSize.set(assets.get(id), entry.getValue());
                        configState.itemIds.putIfAbsent(id, entry.getValue());
                    }
                }
            }
            System.out.println("[CustomStack] Ready to save! ItemIds size is now: " + configState.itemIds.size());
            this.cfg.save();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
