package com.supremetor.endgamegear.trim;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> DRAGON = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(EndgameGear.MOD_ID, "dragon"));

    public static void bootstrap(Registerable<ArmorTrimPattern> context) {
        register(context, ModItems.DRAGON_SMITHING_TEMPLATE, DRAGON);
    }

    private static void register(Registerable<ArmorTrimPattern> context, Item item, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern trimPattern = new ArmorTrimPattern(key.getValue(),
                Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);

        context.register(key, trimPattern);
    }
}
