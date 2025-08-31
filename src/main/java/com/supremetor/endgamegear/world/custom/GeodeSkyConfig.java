package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record GeodeSkyConfig(
        BlockStateProvider outerBlock,
        BlockStateProvider innerBlock,
        BlockStateProvider specialBlock,
        IntProvider radius,
        IntProvider specialCount
) implements FeatureConfig {
    public static final Codec<GeodeSkyConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("outer_block").forGetter(GeodeSkyConfig::outerBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("inner_block").forGetter(GeodeSkyConfig::innerBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("special_block").forGetter(GeodeSkyConfig::specialBlock),
            IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(GeodeSkyConfig::radius),
            IntProvider.VALUE_CODEC.fieldOf("special_count").forGetter(GeodeSkyConfig::specialCount)
    ).apply(instance, GeodeSkyConfig::new));
}
