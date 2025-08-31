package com.supremetor.endgamegear.datagen;

import com.supremetor.endgamegear.EndgameGear;
import com.supremetor.endgamegear.block.ModBlocks;
import com.supremetor.endgamegear.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                // Prismarine ingot recipes
                List<ItemConvertible> PRISMATIC_DEBRIS = List.of(ModBlocks.PRISMATIC_DEBRIS);
                offerSmelting(PRISMATIC_DEBRIS, RecipeCategory.MISC, ModItems.PRISMARINE_SCRAP, 2.0f, 200, "prismarine_scrap");
                offerBlasting(PRISMATIC_DEBRIS, RecipeCategory.MISC, ModItems.PRISMARINE_SCRAP, 2.0f, 100, "prismarine_scrap");

                offerReversibleCompactingRecipesWithReverseRecipeGroup(
                        RecipeCategory.MISC, ModItems.PRISMARINE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BLOCK,
                        "prismarine_ingot_from_prismarine_block", "prismarine_ingot"
                );
                createShapeless(RecipeCategory.MISC, ModItems.PRISMARINE_INGOT)
                        .input(ModItems.PRISMARINE_SCRAP, 4)
                        .input(Items.IRON_INGOT, 4)
                        .criterion("has_prismarine_scrap", this.conditionsFromItem(ModItems.PRISMARINE_SCRAP))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, Items.TRIDENT)
                        .pattern(" SS")
                        .pattern(" IS")
                        .pattern("I  ")
                        .input('I', ModItems.PRISMARINE_INGOT)
                        .input('S', Items.PRISMARINE_SHARD)
                        .criterion(hasItem(ModItems.PRISMARINE_INGOT), conditionsFromItem(ModItems.PRISMARINE_INGOT))
                        .offerTo(exporter);


                // Dragonite ingot recipes
                List<ItemConvertible> VOID_DEBRIS = List.of(ModBlocks.VOID_DEBRIS);
                offerSmelting(VOID_DEBRIS, RecipeCategory.MISC, ModItems.DRAGONITE_SCRAP, 2.0f, 200, "dragonite_scrap");
                offerBlasting(VOID_DEBRIS, RecipeCategory.MISC, ModItems.DRAGONITE_SCRAP, 2.0f, 100, "dragonite_scrap");

                offerReversibleCompactingRecipesWithReverseRecipeGroup(
                        RecipeCategory.MISC, ModItems.DRAGONITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRAGONITE_BLOCK,
                        "dragonite_ingot_from_dragonite_block", "dragonite_ingot"
                );
                createShapeless(RecipeCategory.MISC, ModItems.DRAGONITE_INGOT)
                        .input(ModItems.DRAGONITE_SCRAP, 4)
                        .input(ModItems.CHAROITE, 4)
                        .criterion("has_dragonite_scrap", this.conditionsFromItem(ModItems.DRAGONITE_SCRAP))
                        .offerTo(exporter);


                // Block recipes
                createShapeless(RecipeCategory.MISC, Items.PRISMARINE_SHARD, 4)
                        .input(Items.PRISMARINE, 1)
                        .criterion("has_prismarine", conditionsFromItem(Items.PRISMARINE))
                        .offerTo(exporter);

                offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_CRYSTAL, Items.PRISMARINE_CRYSTALS);
                createShapeless(RecipeCategory.MISC, Items.PRISMARINE_CRYSTALS, 4)
                        .input(ModBlocks.PRISMARINE_CRYSTAL, 1)
                        .criterion("has_prismarine_crystal", conditionsFromItem(ModBlocks.PRISMARINE_CRYSTAL))
                        .offerTo(exporter);

                offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHAROITE_BLOCK, ModItems.CHAROITE);
                createShapeless(RecipeCategory.MISC, ModItems.CHAROITE, 4)
                        .input(ModBlocks.CHAROITE_BLOCK, 1)
                        .criterion("has_charoite_block", conditionsFromItem(ModBlocks.CHAROITE_BLOCK))
                        .offerTo(exporter);


                // Prismarine ingot smithing table recipes
                offerSmithingTemplateCopyingRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.PRISMARINE);

                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HELMET, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_HELMET, "prismarine_upgrade_helmet");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_CHESTPLATE, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_CHESTPLATE, "prismarine_upgrade_chestplate");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_LEGGINGS, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_LEGGINGS, "prismarine_upgrade_leggings");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_BOOTS, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_BOOTS, "prismarine_upgrade_boots");

                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_SWORD, "prismarine_upgrade_sword");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.TOOLS, ModItems.PRISMARINE_PICKAXE, "prismarine_upgrade_pickaxe");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.COMBAT, ModItems.PRISMARINE_AXE, "prismarine_upgrade_axe");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.TOOLS, ModItems.PRISMARINE_SHOVEL, "prismarine_upgrade_shovel");
                generateSmithingTransformRecipe(ModItems.PRISMARINE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.PRISMARINE_INGOT,
                        RecipeCategory.TOOLS, ModItems.PRISMARINE_HOE, "prismarine_upgrade_hoe");


                // Dragonite ingot smithing table recipes
                offerSmithingTemplateCopyingRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);

                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HELMET, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_HELMET, "dragonite_upgrade_helmet");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_CHESTPLATE, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_CHESTPLATE, "dragonite_upgrade_chestplate");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_LEGGINGS, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_LEGGINGS, "dragonite_upgrade_leggings");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_BOOTS, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_BOOTS, "dragonite_upgrade_boots");

                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_SWORD, "dragonite_upgrade_sword");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.TOOLS, ModItems.DRAGONITE_PICKAXE, "dragonite_upgrade_pickaxe");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.COMBAT, ModItems.DRAGONITE_AXE, "dragonite_upgrade_axe");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.TOOLS, ModItems.DRAGONITE_SHOVEL, "dragonite_upgrade_shovel");
                generateSmithingTransformRecipe(ModItems.DRAGONITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.DRAGONITE_INGOT,
                        RecipeCategory.TOOLS, ModItems.DRAGONITE_HOE, "dragonite_upgrade_hoe");
            }

            private void generateSmithingTransformRecipe(Item template, Item appliesTo, Item ingredient, RecipeCategory recipeCategory,
                                                         Item output, String recipeId) {
                SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template),
                        Ingredient.ofItems(appliesTo),
                        Ingredient.ofItems(ingredient),
                                recipeCategory, output
                )
                        .criterion(hasItem(template), conditionsFromItem(template))
                        .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                        .criterion(hasItem(appliesTo), conditionsFromItem(appliesTo))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(EndgameGear.MOD_ID, recipeId)));
            }
        };
    }

    @Override
    public String getName() {
        return "Endgame Gear Recipes";
    }
}
