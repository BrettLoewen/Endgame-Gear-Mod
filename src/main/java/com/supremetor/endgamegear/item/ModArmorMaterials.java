package com.supremetor.endgamegear.item;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.util.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class ModArmorMaterials {
    static RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> PRISMARINE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(EndgameGear.MOD_ID, "prismarine"));

    public static final ArmorMaterial PRISMARINE_ARMOR_MATERIAL =
            new ArmorMaterial(37, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.BODY, 11);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,3.0f,0.1f, ModTags.Items.PRISMARINE_REPAIR, PRISMARINE_KEY);
}
