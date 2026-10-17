package com.mysticmalevolence.icemod.datagen;

import com.mysticmalevolence.icemod.IceMod;
import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

public class MIMRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public MIMRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.CHISELED_ICE.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', Blocks.ICE)
                .unlockedBy("has_ice", has(Blocks.ICE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.ICE_BRICKS.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', MIMBlockRegistry.CHISELED_ICE.get())
                .unlockedBy("has_ice", has(Blocks.ICE))
                .save(recipeOutput);

        stairBuilder(MIMBlockRegistry.ICE_BRICK_STAIRS.get(),Ingredient.of(MIMBlockRegistry.ICE_BRICKS.get()))
                .group("ice_bricks")
                .unlockedBy("has_ice", has(Blocks.ICE))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.ICE_BRICK_SLAB.get(), MIMBlockRegistry.ICE_BRICKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.ICE_BRICK_WALL.get(), MIMBlockRegistry.ICE_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.ICE_PILLAR.get(), 6)
                .pattern("$#$")
                .pattern(" # ")
                .pattern("$#$")
                .define('#', MIMBlockRegistry.CHISELED_ICE.get())
                .define('$', Blocks.ICE)
                .unlockedBy("has_ice", has(Blocks.ICE))
                .save(recipeOutput);

        buttonBuilder(MIMBlockRegistry.CHISELED_ICE_BUTTON.get(), Ingredient.of(MIMBlockRegistry.CHISELED_ICE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.SNOW_BRICKS.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', Blocks.SNOW_BLOCK)
                .unlockedBy("has_snow_block", has(Blocks.SNOW_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.SNOWSAIC.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', MIMBlockRegistry.SNOW_BRICKS.get())
                .unlockedBy("has_snow_block", has(Blocks.SNOW_BLOCK))
                .save(recipeOutput);

        stairBuilder(MIMBlockRegistry.SNOW_BRICK_STAIRS.get(),Ingredient.of(MIMBlockRegistry.SNOW_BRICKS.get()))
                .group("ice_bricks")
                .unlockedBy("has_ice", has(Blocks.ICE))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.SNOW_BRICK_SLAB.get(), MIMBlockRegistry.SNOW_BRICKS.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.SNOW_BRICK_WALL.get(), MIMBlockRegistry.SNOW_BRICKS.get());

        /// Powder snow buckets don't return a bucket when crafted; moved to world interaction
        /*ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MIMBlockRegistry.POWDER_SNOWSAIC.get(), 4)
                .pattern("#@#")
                .pattern("@#@")
                .pattern("#@#")
                .define('#', MIMBlockRegistry.SNOWSAIC.get())
                .define('@', Items.POWDER_SNOW_BUCKET)
                .unlockedBy("has_snow_block", has(Blocks.SNOW_BLOCK))
                .save(recipeOutput);*/

        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICKS.get(),Blocks.ICE);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.CHISELED_ICE.get(),Blocks.ICE);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_PILLAR.get(),Blocks.ICE);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICKS.get(),MIMBlockRegistry.CHISELED_ICE.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_PILLAR.get(),MIMBlockRegistry.CHISELED_ICE.get());

        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_STAIRS.get(),Blocks.ICE);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_SLAB.get(),Blocks.ICE,2);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_WALL.get(),Blocks.ICE);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_STAIRS.get(),MIMBlockRegistry.CHISELED_ICE.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_SLAB.get(),MIMBlockRegistry.CHISELED_ICE.get(),2);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_WALL.get(),MIMBlockRegistry.CHISELED_ICE.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_STAIRS.get(),MIMBlockRegistry.ICE_BRICKS.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_SLAB.get(),MIMBlockRegistry.ICE_BRICKS.get(),2);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_WALL.get(),MIMBlockRegistry.ICE_BRICKS.get());

        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICKS.get(),Blocks.PACKED_ICE,9);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.CHISELED_ICE.get(),Blocks.PACKED_ICE,9);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_PILLAR.get(),Blocks.PACKED_ICE,9);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_STAIRS.get(),Blocks.PACKED_ICE,9);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_SLAB.get(),Blocks.PACKED_ICE,18);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.ICE_BRICK_WALL.get(),Blocks.PACKED_ICE,9);

        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICKS.get(),Blocks.SNOW_BLOCK);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_STAIRS.get(),Blocks.SNOW_BLOCK);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_SLAB.get(),Blocks.SNOW_BLOCK,2);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_WALL.get(),Blocks.SNOW_BLOCK);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_STAIRS.get(),MIMBlockRegistry.SNOW_BRICKS.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_SLAB.get(),MIMBlockRegistry.SNOW_BRICKS.get(),2);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOW_BRICK_WALL.get(),MIMBlockRegistry.SNOW_BRICKS.get());
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOWSAIC.get(),Blocks.SNOW_BLOCK);
        stonecutterResultFromBase(recipeOutput,RecipeCategory.BUILDING_BLOCKS,MIMBlockRegistry.SNOWSAIC.get(),MIMBlockRegistry.SNOW_BRICKS.get());
    }

    /**
     * Overwrite vanilla functions to generate recipes in the correct namespace
     */
    protected static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, @NotNull ItemLike result, @NotNull ItemLike material){
        stonecutterResultFromBase(recipeOutput,category,result,material,1);
    }

    protected static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, @NotNull ItemLike result, @NotNull ItemLike material, int resultCount){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, IceMod.MODID + ":stonecutting/" + getConversionRecipeName(result, material) + "_stonecutting");
    }
}
