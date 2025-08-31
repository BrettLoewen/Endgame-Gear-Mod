package com.supremetor.endgamegear.world;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.world.custom.BlockPillarGrowthConfig;
import com.supremetor.endgamegear.world.custom.BlockPillarGrowthFeature;
import com.supremetor.endgamegear.world.custom.GeodeSkyConfig;
import com.supremetor.endgamegear.world.custom.GeodeSkyFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

public class ModFeatures {
    public static final Feature<BlockPillarGrowthConfig> BLOCK_PILLAR_GROWTH = new BlockPillarGrowthFeature(BlockPillarGrowthConfig.CODEC);
    public static final Feature<GeodeSkyConfig> GEODE_SKY = new GeodeSkyFeature(GeodeSkyConfig.CODEC);

    public static void registerModFeatures() {
        Registry.register(Registries.FEATURE, Identifier.of(EndgameGear.MOD_ID, "block_pillar_growth"), BLOCK_PILLAR_GROWTH);
        Registry.register(Registries.FEATURE, Identifier.of(EndgameGear.MOD_ID, "geode_sky"), GEODE_SKY);
    }
}
