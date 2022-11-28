package net.jenot.genies.datagen;

import net.jenot.genies.block.ModBlocks;
import net.jenot.genies.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModBlocksStateProvider extends RecipeProvider implements IConditionBuilder {
    public ModBlocksStateProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }


    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.EBONY_DOOR.get())
                .define('E', ModBlocks.EBONY_PLANKS.get())
                .pattern("EE")
                .pattern("EE")
                .pattern("EE")
                .unlockedBy("has_ebony_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.EBONY_PLANKS.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModItems.CITRINE.get())
                .requires(ModBlocks.CITRINE_BLOCK.get())
                .unlockedBy("has_citrine_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.CITRINE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.CITRINE_BLOCK.get())
                .define('C', ModBlocks.EBONY_PLANKS.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .unlockedBy("has_citrine", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CITRINE.get()).build()))
                .save(pFinishedRecipeConsumer);


    }
}
