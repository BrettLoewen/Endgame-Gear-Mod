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

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.PRISMARINE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.PRISMARINE_PICKAXE);
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.PRISMARINE_AXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.PRISMARINE_SHOVEL);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.PRISMARINE_HOE);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PRISMARINE_HELMET)
                .add(ModItems.PRISMARINE_CHESTPLATE)
                .add(ModItems.PRISMARINE_LEGGINGS)
                .add(ModItems.PRISMARINE_BOOTS);

        valueLookupBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.PRISMARINE_INGOT);
    }
}
