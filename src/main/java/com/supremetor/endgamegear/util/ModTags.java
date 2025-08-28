package com.supremetor.endgamegear.util;

import com.supremetor.endgamegear.EndgameGear;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_PRISMARINE_TOOL = createTag("needs_prismarine_tool");
        public static final TagKey<Block> INCORRECT_FOR_PRISMARINE_TOOL = createTag("incorrect_for_prismarine_tool");
        public static final TagKey<Block> INCORRECT_FOR_DRAGONITE_TOOL = createTag("incorrect_for_dragonite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(EndgameGear.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PRISMARINE_REPAIR = createTag("prismarine_repair");
        public static final TagKey<Item> DRAGONITE_REPAIR = createTag("dragonite_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(EndgameGear.MOD_ID, name));
        }
    }
}
