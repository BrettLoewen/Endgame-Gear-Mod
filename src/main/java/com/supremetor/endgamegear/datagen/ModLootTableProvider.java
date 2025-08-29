package com.supremetor.endgamegear.datagen;

import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PRISMARINE_BLOCK);
        addDrop(ModBlocks.PRISMATIC_DEBRIS);
        addDrop(ModBlocks.PRISMARINE_CRYSTAL);

        addDrop(ModBlocks.DRAGONITE_BLOCK);
        addDrop(ModBlocks.VOID_DEBRIS);
        addDrop(ModBlocks.CHAROITE_BLOCK, LootTable.builder().pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(ModItems.CHAROITE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4))))));
    }
}
