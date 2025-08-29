package com.supremetor.endgamegear.datagen;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.PRISMARINE_BLOCK)
                .add(ModBlocks.PRISMATIC_DEBRIS)
                .add(ModBlocks.PRISMARINE_CRYSTAL)
                .add(ModBlocks.DRAGONITE_BLOCK)
                .add(ModBlocks.VOID_DEBRIS)
                .add(ModBlocks.CHAROITE_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PRISMARINE_BLOCK)
                .add(ModBlocks.PRISMATIC_DEBRIS)
                .add(ModBlocks.DRAGONITE_BLOCK)
                .add(ModBlocks.VOID_DEBRIS);

        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.PRISMARINE_BLOCK)
                .add(ModBlocks.DRAGONITE_BLOCK);

        valueLookupBuilder(TagKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, "prismarine_pillar_surfaces")))
                .add(Blocks.GRAVEL)
                .add(Blocks.SAND);
    }
}
