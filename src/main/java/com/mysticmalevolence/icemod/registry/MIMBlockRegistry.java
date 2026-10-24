package com.mysticmalevolence.icemod.registry;

import com.mysticmalevolence.icemod.IceMod;
import com.mysticmalevolence.icemod.block.IceBricksBlock;
import com.mysticmalevolence.icemod.block.IcePillarBlock;
import com.mysticmalevolence.icemod.block.SnowsaicBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MIMBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(IceMod.MODID);

    public static final DeferredBlock<Block> ICE_BRICKS = registerBlock("ice_bricks",
            () -> new IceBricksBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.ICE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.98F)
                    .strength(0.5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
            ));
    public static final DeferredBlock<StairBlock> ICE_BRICK_STAIRS = registerBlock("ice_brick_stairs",
            () -> new StairBlock(ICE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(
                    ICE_BRICKS.get())
            ));
    public static final DeferredBlock<SlabBlock> ICE_BRICK_SLAB = registerBlock("ice_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(
                    ICE_BRICKS.get())
            ));
    public static final DeferredBlock<WallBlock> ICE_BRICK_WALL = registerBlock("ice_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(
                    ICE_BRICKS.get())
            ));

    public static final DeferredBlock<Block> CRACKED_ICE_BRICKS = registerBlock("cracked_ice_bricks",
            () -> new IceBricksBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.ICE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.98F)
                    .strength(0.3F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
            ));
    public static final DeferredBlock<Block> SNOW_BRICKS = registerBlock("snow_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .strength(0.25F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.SNOW)
            ));

    public static final DeferredBlock<StairBlock> SNOW_BRICK_STAIRS = registerBlock("snow_brick_stairs",
            () -> new StairBlock(SNOW_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(
                    SNOW_BRICKS.get())
            ));
    public static final DeferredBlock<SlabBlock> SNOW_BRICK_SLAB = registerBlock("snow_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(
                    SNOW_BRICKS.get())
            ));
    public static final DeferredBlock<WallBlock> SNOW_BRICK_WALL = registerBlock("snow_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(
                    SNOW_BRICKS.get())
            ));

    public static final DeferredBlock<Block> SNOWSAIC = registerBlock("snowsaic",
            () -> new SnowsaicBlock(BlockBehaviour.Properties.ofFullCopy(
                    SNOW_BRICKS.get())
            ));
    public static final DeferredBlock<Block> POWDER_SNOWSAIC = registerBlock("powder_snowsaic",
            () -> new PowderSnowBlock(BlockBehaviour.Properties.ofFullCopy(
                    SNOW_BRICKS.get()).noLootTable()
            ));

    public static final DeferredBlock<Block> CHISELED_ICE = registerBlock("chiseled_ice",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(
                    ICE_BRICKS.get())
            ));
    public static final DeferredBlock<ButtonBlock> CHISELED_ICE_BUTTON = registerBlock("chiseled_ice_button",
            () -> new ButtonBlock(BlockSetType.IRON,10, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.ICE)
                    .strength(0.5F)
                    .requiresCorrectToolForDrops()
                    .noCollission()
            ));

    public static final DeferredBlock<Block> ICE_PILLAR = registerBlock("ice_pillar",
            () -> new IcePillarBlock(BlockBehaviour.Properties.ofFullCopy(
                    ICE_BRICKS.get())
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        MIMItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
