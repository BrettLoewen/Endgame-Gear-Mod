package com.supremetor.endgamegear.trim;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> CHAROITE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(EndgameGear.MOD_ID, "charoite"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, CHAROITE, Registries.ITEM.getEntry(ModItems.CHAROITE),
                Style.EMPTY.withColor(TextColor.parse("#d200d2").getOrThrow()));

    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                 RegistryEntry<Item> item, Style style) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(ArmorTrimAssets.of("charoite"),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }
}
