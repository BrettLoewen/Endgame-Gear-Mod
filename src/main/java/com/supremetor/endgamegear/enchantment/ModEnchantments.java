package com.supremetor.endgamegear.enchantment;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> KINETIC_DEFENSE = RegistryKey.of(RegistryKeys.ENCHANTMENT,
            Identifier.of(EndgameGear.MOD_ID, "kinetic_defense"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, KINETIC_DEFENSE,
                Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        5,
                        4,
                        Enchantment.leveledCost(5, 6),
                        Enchantment.leveledCost(11, 6),
                        2,
                        AttributeModifierSlot.HEAD))
                .addEffect(
                        EnchantmentEffectComponentTypes.DAMAGE_PROTECTION,
                        new AddEnchantmentEffect(
                                new EnchantmentLevelBasedValue.Linear(4.0F, 4.0F)),
                                DamageSourcePropertiesLootCondition.builder(
                                        DamageSourcePredicate.Builder.create()
                                                .tag(TagPredicate.expected(ModTags.DamageTypes.ELYTRA_COLLISION))
                                                .tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY))
                        )));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
