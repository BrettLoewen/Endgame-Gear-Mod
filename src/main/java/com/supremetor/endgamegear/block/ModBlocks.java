package com.supremetor.endgamegear.block;

import com.supremetor.endgamegear.EndgameGear;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block PRISMARINE_BLOCK = registerBlock("prismarine_block",
            properties -> new Block(properties.mapColor(MapColor.CYAN).requiresTool()
                    .strength(50.0F, 1200.0F).sounds(BlockSoundGroup.NETHERITE)));
    public static final Block PRISMATIC_DEBRIS = registerBlock("prismatic_debris",
            properties -> new Block(properties.mapColor(MapColor.CYAN).requiresTool()
                    .strength(30.0F, 1200.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS)));
    public static final Block PRISMARINE_CRYSTAL = registerBlock("prismarine_crystal",
            properties -> new Block(properties.mapColor(MapColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool().strength(1F).luminance(state -> 2)));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(EndgameGear.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EndgameGear.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EndgameGear.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        EndgameGear.LOGGER.info("Registering Mod Blocks for " + EndgameGear.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PRISMARINE_BLOCK);
        });
    }
}
