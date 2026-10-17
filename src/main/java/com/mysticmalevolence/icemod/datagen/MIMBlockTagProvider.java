package com.mysticmalevolence.icemod.datagen;

import com.mysticmalevolence.icemod.IceMod;
import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MIMBlockTagProvider extends BlockTagsProvider {

    public MIMBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IceMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MIMBlockRegistry.ICE_BRICKS.get())
                .add(MIMBlockRegistry.CRACKED_ICE_BRICKS.get())
                .add(MIMBlockRegistry.ICE_BRICK_STAIRS.get())
                .add(MIMBlockRegistry.ICE_BRICK_SLAB.get())
                .add(MIMBlockRegistry.ICE_BRICK_WALL.get())
                .add(MIMBlockRegistry.CHISELED_ICE.get())
                .add(MIMBlockRegistry.ICE_PILLAR.get())
                .add(MIMBlockRegistry.CHISELED_ICE_BUTTON.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(MIMBlockRegistry.SNOW_BRICKS.get())
                .add(MIMBlockRegistry.SNOWSAIC.get())
                .add(MIMBlockRegistry.POWDER_SNOWSAIC.get())
                .add(MIMBlockRegistry.SNOW_BRICK_STAIRS.get())
                .add(MIMBlockRegistry.SNOW_BRICK_SLAB.get())
                .add(MIMBlockRegistry.SNOW_BRICK_WALL.get());

        tag(BlockTags.WALLS)
                .add(MIMBlockRegistry.ICE_BRICK_WALL.get())
                .add(MIMBlockRegistry.SNOW_BRICK_WALL.get());
    }
}
