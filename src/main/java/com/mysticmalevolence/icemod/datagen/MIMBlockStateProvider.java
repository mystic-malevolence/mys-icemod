package com.mysticmalevolence.icemod.datagen;

import com.mysticmalevolence.icemod.IceMod;
import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MIMBlockStateProvider extends BlockStateProvider {

    public MIMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IceMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockCube(MIMBlockRegistry.ICE_BRICKS);
        blockCube(MIMBlockRegistry.CRACKED_ICE_BRICKS);
        blockCube(MIMBlockRegistry.CHISELED_ICE);
        blockCube(MIMBlockRegistry.SNOW_BRICKS);
        blockCube(MIMBlockRegistry.SNOWSAIC);
        blockCube(MIMBlockRegistry.POWDER_SNOWSAIC);

        stairsBlock(MIMBlockRegistry.ICE_BRICK_STAIRS.get(), blockTexture(MIMBlockRegistry.ICE_BRICKS.get()));
        blockItem(MIMBlockRegistry.ICE_BRICK_STAIRS);
        slabBlock(MIMBlockRegistry.ICE_BRICK_SLAB.get(), blockTexture(MIMBlockRegistry.ICE_BRICKS.get()),blockTexture(MIMBlockRegistry.ICE_BRICKS.get()));
        blockItem(MIMBlockRegistry.ICE_BRICK_SLAB);
        wallBlock(MIMBlockRegistry.ICE_BRICK_WALL.get(), blockTexture(MIMBlockRegistry.ICE_BRICKS.get()));
        blockItem(MIMBlockRegistry.ICE_BRICK_WALL);

        buttonBlock(MIMBlockRegistry.CHISELED_ICE_BUTTON.get(), blockTexture(MIMBlockRegistry.CHISELED_ICE.get()));

        stairsBlock(MIMBlockRegistry.SNOW_BRICK_STAIRS.get(), blockTexture(MIMBlockRegistry.SNOW_BRICKS.get()));
        blockItem(MIMBlockRegistry.SNOW_BRICK_STAIRS);
        slabBlock(MIMBlockRegistry.SNOW_BRICK_SLAB.get(), blockTexture(MIMBlockRegistry.SNOW_BRICKS.get()),blockTexture(MIMBlockRegistry.SNOW_BRICKS.get()));
        blockItem(MIMBlockRegistry.SNOW_BRICK_SLAB);
        wallBlock(MIMBlockRegistry.SNOW_BRICK_WALL.get(), blockTexture(MIMBlockRegistry.SNOW_BRICKS.get()));
        blockItem(MIMBlockRegistry.SNOW_BRICK_WALL);

    }

    private void blockCube(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("mysicemod:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("mysicemod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
