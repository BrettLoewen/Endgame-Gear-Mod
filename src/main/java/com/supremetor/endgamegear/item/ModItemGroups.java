package com.supremetor.endgamegear.item;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PRISMARINE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EndgameGear.MOD_ID, "prismarine_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PRISMARINE_INGOT))
                    .displayName(Text.translatable("itemgroup.endgame-gear.prismarine_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.PRISMATIC_DEBRIS);
                        entries.add(ModItems.PRISMARINE_SCRAP);
                        entries.add(ModItems.PRISMARINE_INGOT);
                        entries.add(ModBlocks.PRISMARINE_BLOCK);

                        entries.add(ModItems.PRISMARINE_SWORD);
                        entries.add(ModItems.PRISMARINE_PICKAXE);
                        entries.add(ModItems.PRISMARINE_SHOVEL);
                        entries.add(ModItems.PRISMARINE_AXE);
                        entries.add(ModItems.PRISMARINE_HOE);

                        entries.add(ModItems.PRISMARINE_HELMET);
                        entries.add(ModItems.PRISMARINE_CHESTPLATE);
                        entries.add(ModItems.PRISMARINE_LEGGINGS);
                        entries.add(ModItems.PRISMARINE_BOOTS);

                        entries.add(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE);

                        entries.add(ModBlocks.PRISMARINE_CRYSTAL);

                        entries.add(Items.PRISMARINE_SHARD);
                        entries.add(Items.PRISMARINE_CRYSTALS);
                        entries.add(Blocks.PRISMARINE);
                        entries.add(Blocks.PRISMARINE_SLAB);
                        entries.add(Blocks.PRISMARINE_STAIRS);
                        entries.add(Blocks.PRISMARINE_WALL);
                        entries.add(Blocks.PRISMARINE_BRICKS);
                        entries.add(Blocks.PRISMARINE_BRICK_SLAB);
                        entries.add(Blocks.PRISMARINE_BRICK_STAIRS);
                        entries.add(Blocks.DARK_PRISMARINE);
                        entries.add(Blocks.DARK_PRISMARINE_SLAB);
                        entries.add(Blocks.DARK_PRISMARINE_STAIRS);
                        entries.add(Items.TRIDENT);
                    }).build());

    public static final ItemGroup DRAGONITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EndgameGear.MOD_ID, "dragonite_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DRAGONITE_INGOT))
                    .displayName(Text.translatable("itemgroup.endgame-gear.dragonite_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.VOID_DEBRIS);
                        entries.add(ModItems.DRAGONITE_SCRAP);
                        entries.add(ModItems.DRAGONITE_INGOT);
                        entries.add(ModBlocks.DRAGONITE_BLOCK);
                        entries.add(ModBlocks.CHAROITE_BLOCK);
                        entries.add(ModItems.CHAROITE);

                        entries.add(ModItems.DRAGONITE_HELMET);
                        entries.add(ModItems.DRAGONITE_CHESTPLATE);
                        entries.add(ModItems.DRAGONITE_LEGGINGS);
                        entries.add(ModItems.DRAGONITE_BOOTS);

                        entries.add(ModItems.DRAGONITE_SWORD);
                        entries.add(ModItems.DRAGONITE_PICKAXE);
                        entries.add(ModItems.DRAGONITE_AXE);
                        entries.add(ModItems.DRAGONITE_SHOVEL);
                        entries.add(ModItems.DRAGONITE_HOE);

                        entries.add(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE);
                    }).build());

    public static void registerItemGroups() {
    }
}
