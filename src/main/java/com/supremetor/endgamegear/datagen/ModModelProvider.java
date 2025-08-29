package com.supremetor.endgamegear.datagen;

import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.item.ModArmorMaterials;
import com.supremetor.endgamegear.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PRISMARINE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PRISMATIC_DEBRIS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PRISMARINE_CRYSTAL);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DRAGONITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VOID_DEBRIS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHAROITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PRISMARINE_SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRISMARINE_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.DRAGONITE_SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRAGONITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHAROITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.PRISMARINE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRISMARINE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRISMARINE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRISMARINE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRISMARINE_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.DRAGONITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DRAGONITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DRAGONITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DRAGONITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DRAGONITE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(ModItems.PRISMARINE_HELMET, ModArmorMaterials.PRISMARINE_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.PRISMARINE_CHESTPLATE, ModArmorMaterials.PRISMARINE_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.PRISMARINE_LEGGINGS, ModArmorMaterials.PRISMARINE_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.PRISMARINE_BOOTS, ModArmorMaterials.PRISMARINE_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.registerArmor(ModItems.DRAGONITE_HELMET, ModArmorMaterials.DRAGONITE_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.DRAGONITE_CHESTPLATE, ModArmorMaterials.DRAGONITE_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.DRAGONITE_LEGGINGS, ModArmorMaterials.DRAGONITE_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.DRAGONITE_BOOTS, ModArmorMaterials.DRAGONITE_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

        itemModelGenerator.register(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
    }
}
