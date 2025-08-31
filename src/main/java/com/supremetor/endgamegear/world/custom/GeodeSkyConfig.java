package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record GeodeSkyConfig(
        BlockStateProvider outerBlock,      // The block(s) for the outer layer of the geode
        BlockStateProvider innerBlock,      // The block(s) for the inner layer of the geode
        BlockStateProvider specialBlock,    // Will be interspersed throughout the inner layer based on specialCount
        IntProvider radius,                 // The radius of the geode
        IntProvider specialCount,           // Determines the amount of specialBlock that will spawn
        IntProvider crackCount,             // Determines the number of cracks in the geode
        IntProvider crackWidth              // Determines the width of the cracks (in degrees)
) implements FeatureConfig {
    public static final Codec<GeodeSkyConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("outer_block").forGetter(GeodeSkyConfig::outerBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("inner_block").forGetter(GeodeSkyConfig::innerBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("special_block").forGetter(GeodeSkyConfig::specialBlock),
            IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(GeodeSkyConfig::radius),
            IntProvider.VALUE_CODEC.fieldOf("special_count").forGetter(GeodeSkyConfig::specialCount),
            IntProvider.VALUE_CODEC.fieldOf("crack_count").forGetter(GeodeSkyConfig::crackCount),
            IntProvider.VALUE_CODEC.fieldOf("crack_width").forGetter(GeodeSkyConfig::crackWidth)
    ).apply(instance, GeodeSkyConfig::new));
}
