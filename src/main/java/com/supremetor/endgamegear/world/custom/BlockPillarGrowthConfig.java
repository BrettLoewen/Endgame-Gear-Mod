package com.supremetor.endgamegear.world.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BlockPillarGrowthConfig(BlockStateProvider innerBlock,    // Determines the block(s) inside the pillar (the pillar's core)
                                      BlockStateProvider shellBlock,    // Determines the block(s) outside the pillar (the pillar's shell)
                                      RuleTest surfaceTest,             // Determines what block(s) the pillar can be placed on
                                      IntProvider height,               // Determines how many (inner) blocks the pillar will include
                                      Boolean mustBeUnderwater          // If true, the pillar must spawn in water. If false, this is not required.
) implements FeatureConfig {
    public static final Codec<BlockPillarGrowthConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("inner_block").forGetter(BlockPillarGrowthConfig::innerBlock),
            BlockStateProvider.TYPE_CODEC.fieldOf("shell_block").forGetter(BlockPillarGrowthConfig::shellBlock),
            RuleTest.TYPE_CODEC.fieldOf("surface_test").forGetter(BlockPillarGrowthConfig::surfaceTest),
            IntProvider.VALUE_CODEC.fieldOf("height").forGetter(BlockPillarGrowthConfig::height),
            Codec.BOOL.fieldOf("must_be_underwater").forGetter(BlockPillarGrowthConfig::mustBeUnderwater)
    ).apply(instance, BlockPillarGrowthConfig::new));
}
