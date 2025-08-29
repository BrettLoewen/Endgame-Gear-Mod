package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record GeodeSkyConfig(
        BlockState outerBlock,
        BlockState innerBlock,
        BlockState specialBlock,
        IntProvider radius,       // radius range (2–4 is good)
        IntProvider specialCount  // number of special blocks inside
) implements FeatureConfig {
    public static final Codec<GeodeSkyConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("outer_block").forGetter(GeodeSkyConfig::outerBlock),
            BlockState.CODEC.fieldOf("inner_block").forGetter(GeodeSkyConfig::innerBlock),
            BlockState.CODEC.fieldOf("special_block").forGetter(GeodeSkyConfig::specialBlock),
            IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(GeodeSkyConfig::radius),
            IntProvider.VALUE_CODEC.fieldOf("special_count").forGetter(GeodeSkyConfig::specialCount)
    ).apply(instance, GeodeSkyConfig::new));
}
