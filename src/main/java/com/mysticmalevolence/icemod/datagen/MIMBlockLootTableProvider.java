package com.mysticmalevolence.icemod.datagen;

import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class MIMBlockLootTableProvider extends BlockLootSubProvider {

    protected MIMBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),registries);
    }

    @Override
    protected void generate() {

        add(
                MIMBlockRegistry.ICE_BRICKS.get(),
                block -> createSingleItemTableWithSilkTouch(MIMBlockRegistry.ICE_BRICKS.get(), MIMBlockRegistry.CRACKED_ICE_BRICKS.get())
            );
        dropWhenSilkTouch(MIMBlockRegistry.CRACKED_ICE_BRICKS.get());
        dropWhenSilkTouch(MIMBlockRegistry.ICE_BRICK_STAIRS.get());
        add(
                MIMBlockRegistry.ICE_BRICK_SLAB.get(),
                block -> createSilkTouchSlabItemTable(MIMBlockRegistry.ICE_BRICK_SLAB.get())
            );
        dropWhenSilkTouch(MIMBlockRegistry.ICE_BRICK_WALL.get());

        dropWhenSilkTouch(MIMBlockRegistry.CHISELED_ICE.get());
        dropWhenSilkTouch(MIMBlockRegistry.ICE_PILLAR.get());

        dropSelf(MIMBlockRegistry.CHISELED_ICE_BUTTON.get());

        dropSelf(MIMBlockRegistry.SNOW_BRICKS.get());
        dropSelf(MIMBlockRegistry.SNOW_BRICK_STAIRS.get());
        add(
                MIMBlockRegistry.SNOW_BRICK_SLAB.get(),
                block -> createSlabItemTable(MIMBlockRegistry.SNOW_BRICK_SLAB.get())
        );
        dropSelf(MIMBlockRegistry.SNOW_BRICK_WALL.get());
        dropSelf(MIMBlockRegistry.SNOWSAIC.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MIMBlockRegistry.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createSilkTouchSlabItemTable(Block block) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool().when(this.hasSilkTouch())
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                                                block,
                                                LootItem.lootTableItem(block)
                                                        .apply(
                                                                SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                                        .when(
                                                                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))
                                                                        )
                                                        )
                                        )
                                )
                );
    }
}
