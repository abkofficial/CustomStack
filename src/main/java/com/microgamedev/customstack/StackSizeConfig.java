package com.microgamedev.customstack;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.map.MapCodec;

import java.util.HashMap;
import java.util.Map;

public class StackSizeConfig {

    public static final BuilderCodec<StackSizeConfig> CODEC = BuilderCodec
            .builder(StackSizeConfig.class, StackSizeConfig::new)
            .append(new KeyedCodec<>("ItemIds", new MapCodec<>(Codec.INTEGER, HashMap::new), true),
                    (i, v) -> i.itemIds = v, i -> i.itemIds)
            .add()
            .append(new KeyedCodec<>("HytaleVersion", Codec.STRING, true),
                    (i, v) -> i.hytaleVersion = v, i -> i.hytaleVersion)
            .add()
            .build();

    protected Map<String, Integer> itemIds = new HashMap<>();
    protected Map<String, Integer> patterns = new HashMap<>();
    protected String hytaleVersion = "unknown";

    public StackSizeConfig() {
        itemIds.put("Rail", 300);

        patterns.put("Ingredient_.*", 300);
        patterns.put("Ore_.*", 100);
        patterns.put("Plant_Petals_.*", 600);
        patterns.put("Plant_Seeds_.*", 300);
        patterns.put("Plant_Crop_.*", 300);
        patterns.put("Plant_Flower_.*", 300);
        patterns.put("Plant_Fruit_.*", 300);
        patterns.put("Furniture_.*", 150);
        patterns.put("Cloth_.*", 150);
        patterns.put("Rock_.*", 300);
        patterns.put("Rubble_.*", 300);
        patterns.put("Soil_.*", 300);
        patterns.put("Wood_.*", 300);
        patterns.put("Metal_.*", 300);
        patterns.put("Weapon_Arrow_.*", 300);
        patterns.put(".*_Torch", 600);
        patterns.put(".*_Egg", 150);
        patterns.put(".*_Eggsacks", 150);
        patterns.put("Egg_.*", 150);
    }
}