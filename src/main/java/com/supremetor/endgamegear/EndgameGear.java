package com.supremetor.endgamegear;

import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.item.ModItemGroups;
import com.supremetor.endgamegear.item.ModItems;
import com.supremetor.endgamegear.util.ModLootTableModifiers;
import com.supremetor.endgamegear.world.ModFeatures;
import com.supremetor.endgamegear.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndgameGear implements ModInitializer {
	public static final String MOD_ID = "endgame-gear";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
        ModLootTableModifiers.modifyLootTables();
        ModFeatures.registerModFeatures();
        ModWorldGeneration.generateModWorldGen();
    }
}