package com.supremetor.endgamegear.datagen;

import com.supremetor.endgamegear.item.ModItems;
import com.supremetor.endgamegear.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.PRISMARINE_REPAIR)
                .add(ModItems.PRISMARINE_INGOT);

        valueLookupBuilder(ModTags.Items.DRAGONITE_REPAIR)
                .add(ModItems.DRAGONITE_INGOT);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.PRISMARINE_SWORD)
                .add(ModItems.DRAGONITE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.PRISMARINE_PICKAXE)
                .add(ModItems.DRAGONITE_PICKAXE);
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.PRISMARINE_AXE)
                .add(ModItems.DRAGONITE_AXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.PRISMARINE_SHOVEL)
                .add(ModItems.DRAGONITE_SHOVEL);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.PRISMARINE_HOE)
                .add(ModItems.DRAGONITE_HOE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.PRISMARINE_HELMET)
                .add(ModItems.DRAGONITE_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.PRISMARINE_CHESTPLATE)
                .add(ModItems.DRAGONITE_CHESTPLATE);
        valueLookupBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.PRISMARINE_LEGGINGS)
                .add(ModItems.DRAGONITE_LEGGINGS);
        valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.PRISMARINE_BOOTS)
                .add(ModItems.DRAGONITE_BOOTS);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PRISMARINE_HELMET)
                .add(ModItems.PRISMARINE_CHESTPLATE)
                .add(ModItems.PRISMARINE_LEGGINGS)
                .add(ModItems.PRISMARINE_BOOTS)
                .add(ModItems.DRAGONITE_HELMET)
                .add(ModItems.DRAGONITE_CHESTPLATE)
                .add(ModItems.DRAGONITE_LEGGINGS)
                .add(ModItems.DRAGONITE_BOOTS);

        valueLookupBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.PRISMARINE_INGOT)
                .add(ModItems.DRAGONITE_INGOT);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.DRAGONITE_ROCKET);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.CHAROITE);
    }
}
