package com.supremetor.endgamegear.world;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.world.custom.BlockPillarGrowthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.collection.Pool;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> PRISMARINE_PILLAR_KEY = registerKey("prismarine_pillar");

    private static final WeightedBlockStateProvider PRISMARINE_PILLAR_INNER_BLOCKS = new WeightedBlockStateProvider(Pool.<BlockState>builder()
            .add(Blocks.PRISMARINE.getDefaultState(), 3)
            .add(ModBlocks.PRISMARINE_CRYSTAL.getDefaultState(), 3)
            .add(ModBlocks.PRISMATIC_DEBRIS.getDefaultState(), 1)
            .build());

    private static final RuleTest PRISMARINE_PILLAR_SURFACE_TEST =
            new TagMatchRuleTest(TagKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, "prismarine_pillar_surfaces")));

    private static final IntProvider  PILLAR_HEIGHT_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(2), 1)
            .add(ConstantIntProvider.create(3), 2)
            .add(ConstantIntProvider.create(4), 4)
            .add(ConstantIntProvider.create(5), 10)
            .add(ConstantIntProvider.create(7), 2)
            .add(ConstantIntProvider.create(10), 1)
            .build());

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PRISMARINE_PILLAR_KEY, EndgameGear.BLOCK_PILLAR_GROWTH, new BlockPillarGrowthConfig(
                PRISMARINE_PILLAR_INNER_BLOCKS,
                BlockStateProvider.of(Blocks.TUFF),
                PRISMARINE_PILLAR_SURFACE_TEST,
                PILLAR_HEIGHT_PROVIDER,
                true
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EndgameGear.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
