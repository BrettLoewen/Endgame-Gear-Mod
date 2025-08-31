package com.supremetor.endgamegear.world;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.world.custom.BlockPillarGrowthConfig;
import com.supremetor.endgamegear.world.custom.GeodeSkyConfig;
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
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHAROITE_PILLAR_KEY = registerKey("charoite_pillar");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DRAGONITE_GEODE_KEY = registerKey("dragonite_geode");

    // ===== Prismarine Pillar =====
    private static final WeightedBlockStateProvider PRISMARINE_PILLAR_INNER_BLOCKS = new WeightedBlockStateProvider(Pool.<BlockState>builder()
            .add(Blocks.PRISMARINE.getDefaultState(), 3)
            .add(ModBlocks.PRISMARINE_CRYSTAL.getDefaultState(), 3)
            .add(ModBlocks.PRISMATIC_DEBRIS.getDefaultState(), 1)
            .build());

    private static final RuleTest PRISMARINE_PILLAR_SURFACE_TEST =
            new TagMatchRuleTest(TagKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, "prismarine_pillar_surfaces")));

    private static final IntProvider PRISMARINE_PILLAR_HEIGHT_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(2), 1)
            .add(ConstantIntProvider.create(3), 2)
            .add(ConstantIntProvider.create(4), 4)
            .add(ConstantIntProvider.create(5), 10)
            .add(ConstantIntProvider.create(7), 2)
            .add(ConstantIntProvider.create(10), 1)
            .build());

    // ===== Charoite Pillar =====
    private static final WeightedBlockStateProvider CHAROITE_PILLAR_INNER_BLOCKS = new WeightedBlockStateProvider(Pool.<BlockState>builder()
            .add(ModBlocks.CHAROITE_BLOCK.getDefaultState(), 1)
            .build());

    private static final RuleTest CHAROITE_PILLAR_SURFACE_TEST =
            new TagMatchRuleTest(TagKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, "charoite_pillar_surfaces")));

    private static final IntProvider CHAROITE_PILLAR_HEIGHT_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(4), 1)
            .add(ConstantIntProvider.create(5), 2)
            .add(ConstantIntProvider.create(7), 4)
            .add(ConstantIntProvider.create(10), 10)
            .add(ConstantIntProvider.create(12), 2)
            .add(ConstantIntProvider.create(15), 1)
            .build());

    // ===== Dragonite Geode =====
    private static final WeightedBlockStateProvider DRAGONITE_GEODE_INNER_BLOCKS = new WeightedBlockStateProvider(Pool.<BlockState>builder()
            .add(ModBlocks.CHAROITE_BLOCK.getDefaultState(), 3)
            .add(Blocks.SMOOTH_BASALT.getDefaultState(), 1)
            .build());

    private static final IntProvider DRAGONITE_GEODE_RADIUS_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(5), 6)
            .add(ConstantIntProvider.create(7), 3)
            .add(ConstantIntProvider.create(9), 1)
            .build());

    private static final IntProvider DRAGONITE_GEODE_SPECIAL_COUNT_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(1), 1)
            .add(ConstantIntProvider.create(2), 2)
            .add(ConstantIntProvider.create(3), 5)
            .add(ConstantIntProvider.create(4), 2)
            .build());

    private static final IntProvider DRAGONITE_GEODE_CRACK_COUNT_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(1), 13)
            .add(ConstantIntProvider.create(2), 6)
            .add(ConstantIntProvider.create(3), 1)
            .build());

    private static final IntProvider DRAGONITE_GEODE_CRACK_WIDTH_PROVIDER = new WeightedListIntProvider(Pool.<IntProvider>builder()
            .add(ConstantIntProvider.create(20), 1)
            .add(ConstantIntProvider.create(25), 6)
            .add(ConstantIntProvider.create(30), 3)
            .build());

    // ===== Registration =====
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PRISMARINE_PILLAR_KEY, ModFeatures.BLOCK_PILLAR_GROWTH, new BlockPillarGrowthConfig(
                PRISMARINE_PILLAR_INNER_BLOCKS,
                BlockStateProvider.of(Blocks.TUFF),
                PRISMARINE_PILLAR_SURFACE_TEST,
                PRISMARINE_PILLAR_HEIGHT_PROVIDER,
                true
        ));

        register(context, CHAROITE_PILLAR_KEY, ModFeatures.BLOCK_PILLAR_GROWTH, new BlockPillarGrowthConfig(
                CHAROITE_PILLAR_INNER_BLOCKS,
                BlockStateProvider.of(Blocks.SMOOTH_BASALT),
                CHAROITE_PILLAR_SURFACE_TEST,
                CHAROITE_PILLAR_HEIGHT_PROVIDER,
                false
        ));

        register(context, DRAGONITE_GEODE_KEY, ModFeatures.GEODE_SKY, new GeodeSkyConfig(
                BlockStateProvider.of(Blocks.OBSIDIAN),
                DRAGONITE_GEODE_INNER_BLOCKS,
                BlockStateProvider.of(ModBlocks.VOID_DEBRIS),
                DRAGONITE_GEODE_RADIUS_PROVIDER,
                DRAGONITE_GEODE_SPECIAL_COUNT_PROVIDER,
                DRAGONITE_GEODE_CRACK_COUNT_PROVIDER,
                DRAGONITE_GEODE_CRACK_WIDTH_PROVIDER
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
