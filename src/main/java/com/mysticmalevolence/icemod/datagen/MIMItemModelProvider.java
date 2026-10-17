package com.mysticmalevolence.icemod.datagen;

import com.mysticmalevolence.icemod.IceMod;
import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MIMItemModelProvider extends ItemModelProvider {

    public MIMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IceMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wallItem(MIMBlockRegistry.ICE_BRICK_WALL,MIMBlockRegistry.ICE_BRICKS);
        buttonItem(MIMBlockRegistry.CHISELED_ICE_BUTTON,MIMBlockRegistry.CHISELED_ICE);
        wallItem(MIMBlockRegistry.SNOW_BRICK_WALL,MIMBlockRegistry.SNOW_BRICKS);
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(IceMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(IceMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

}

