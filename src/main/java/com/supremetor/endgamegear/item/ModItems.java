package com.supremetor.endgamegear.item;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.item.custom.ModArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final Item PRISMARINE_INGOT = registerItem("prismarine_ingot", Item::new);
    public static final Item PRISMARINE_SCRAP = registerItem("prismarine_scrap", Item::new);

    public static final Item PRISMARINE_SWORD = registerItem("prismarine_sword",
            setting -> new Item(setting.sword(ModToolMaterials.PRISMARINE, 3.0f, -2.4f)));
    public static final Item PRISMARINE_PICKAXE = registerItem("prismarine_pickaxe",
            setting -> new Item(setting.pickaxe(ModToolMaterials.PRISMARINE, 1, -2.8f)));
    public static final Item PRISMARINE_AXE = registerItem("prismarine_axe",
            setting -> new AxeItem(ModToolMaterials.PRISMARINE, 5.0f, -3.0f, setting));
    public static final Item PRISMARINE_SHOVEL = registerItem("prismarine_shovel",
            setting -> new ShovelItem(ModToolMaterials.PRISMARINE, 1.5f, -3.0f, setting));
    public static final Item PRISMARINE_HOE = registerItem("prismarine_hoe",
            setting -> new HoeItem(ModToolMaterials.PRISMARINE, -4.0f, 0f, setting));

    public static final Item PRISMARINE_HELMET = registerItem("prismarine_helmet",
            setting -> new ModArmorItem(setting.armor(ModArmorMaterials.PRISMARINE_ARMOR_MATERIAL, EquipmentType.HELMET)));
    public static final Item PRISMARINE_CHESTPLATE = registerItem("prismarine_chestplate",
            setting -> new Item(setting.armor(ModArmorMaterials.PRISMARINE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)));
    public static final Item PRISMARINE_LEGGINGS = registerItem("prismarine_leggings",
            setting -> new Item(setting.armor(ModArmorMaterials.PRISMARINE_ARMOR_MATERIAL, EquipmentType.LEGGINGS)));
    public static final Item PRISMARINE_BOOTS = registerItem("prismarine_boots",
            setting -> new Item(setting.armor(ModArmorMaterials.PRISMARINE_ARMOR_MATERIAL, EquipmentType.BOOTS)));

    public static final Item PRISMARINE_UPGRADE_SMITHING_TEMPLATE = registerItem("prismarine_upgrade_smithing_template",
            setting -> new SmithingTemplateItem(
                    Text.translatable("item.endgame-gear.smithing_template.prismarine_upgrade.applies_to").formatted(Formatting.BLUE),
                    Text.translatable("item.endgame-gear.smithing_template.prismarine_upgrade.ingredients").formatted(Formatting.BLUE),
                    Text.translatable("item.endgame-gear.smithing_template.prismarine_upgrade.base_slot_description"),
                    Text.translatable("item.endgame-gear.smithing_template.prismarine_upgrade.additions_slot_description"),
                    getPrismarineUpgradeEmptyBaseSlotTextures(),
                    getPrismarineUpgradeEmptyAdditionsSlotTextures(),
                    setting.rarity(Rarity.UNCOMMON)));

    public static final Item DRAGONITE_HELMET = registerItem("dragonite_helmet",
            setting -> new ModArmorItem(setting.armor(ModArmorMaterials.DRAGONITE_ARMOR_MATERIAL, EquipmentType.HELMET)));
    public static final Item DRAGONITE_CHESTPLATE = registerItem("dragonite_chestplate",
            setting -> new Item(setting.armor(ModArmorMaterials.DRAGONITE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                    .component(DataComponentTypes.GLIDER, Unit.INSTANCE)));
    public static final Item DRAGONITE_LEGGINGS = registerItem("dragonite_leggings",
            setting -> new Item(setting.armor(ModArmorMaterials.DRAGONITE_ARMOR_MATERIAL, EquipmentType.LEGGINGS)));
    public static final Item DRAGONITE_BOOTS = registerItem("dragonite_boots",
            setting -> new Item(setting.armor(ModArmorMaterials.DRAGONITE_ARMOR_MATERIAL, EquipmentType.BOOTS)));

    // From SmithingTemplateItem.java
    private static List<Identifier> getPrismarineUpgradeEmptyBaseSlotTextures() {
        return List.of(
                Identifier.ofVanilla("container/slot/helmet"),
                Identifier.ofVanilla("container/slot/chestplate"),
                Identifier.ofVanilla("container/slot/leggings"),
                Identifier.ofVanilla("container/slot/boots"),
                Identifier.ofVanilla("container/slot/sword"),
                Identifier.ofVanilla("container/slot/pickaxe"),
                Identifier.ofVanilla("container/slot/axe"),
                Identifier.ofVanilla("container/slot/hoe"),
                Identifier.ofVanilla("container/slot/shovel")
        );
    }

    // From SmithingTemplateItem.java
    private static List<Identifier> getPrismarineUpgradeEmptyAdditionsSlotTextures() {
        return List.of(Identifier.ofVanilla("container/slot/ingot"));
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        EndgameGear.LOGGER.info("Registering item: {}", name);
        return Registry.register(Registries.ITEM, Identifier.of(EndgameGear.MOD_ID, name), function.apply(
                new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EndgameGear.MOD_ID, name)))));
    }

    public static void registerModItems() {
        EndgameGear.LOGGER.info("Registering Mod Items for " + EndgameGear.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(PRISMARINE_HELMET);
            entries.add(PRISMARINE_CHESTPLATE);
            entries.add(PRISMARINE_LEGGINGS);
            entries.add(PRISMARINE_BOOTS);

            entries.add(PRISMARINE_SWORD);
            entries.add(PRISMARINE_AXE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PRISMARINE_INGOT);
            entries.add(PRISMARINE_UPGRADE_SMITHING_TEMPLATE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(PRISMARINE_PICKAXE);
            entries.add(PRISMARINE_AXE);
            entries.add(PRISMARINE_SHOVEL);
            entries.add(PRISMARINE_HOE);
        });
    }
}
