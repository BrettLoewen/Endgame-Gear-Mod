package com.supremetor.endgamegear.world.gen;

import com.supremetor.endgamegear.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.PRISMARINE_PILLAR_PLACED_KEY);

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.END_BARRENS, BiomeKeys.END_MIDLANDS, BiomeKeys.END_HIGHLANDS),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.CHAROITE_PILLAR_PLACED_KEY);

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.END_BARRENS, BiomeKeys.END_MIDLANDS, BiomeKeys.END_HIGHLANDS, BiomeKeys.SMALL_END_ISLANDS),
                GenerationStep.Feature.RAW_GENERATION,
                ModPlacedFeatures.DRAGONITE_GEODE_PLACED_KEY);
    }
}
