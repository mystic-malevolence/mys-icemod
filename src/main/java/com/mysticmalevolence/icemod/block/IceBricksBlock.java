package com.mysticmalevolence.icemod.block;

import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class IceBricksBlock extends Block {
    public IceBricksBlock(Properties properties) {
        super(properties);
    }

    public static boolean canEntityDamage(Level level, Entity entity){
        return entity.getBoundingBox().getSize() > 0.5
                && (entity instanceof LivingEntity
                && (entity instanceof Player || net.neoforged.neoforge.event.EventHooks.canEntityGrief(level, entity))
                || entity instanceof FallingBlockEntity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!level.isClientSide
                && !entity.isSteppingCarefully()
                ///Would like this to account for feather falling but can't figure it out atm
                && fallDistance > 4
                && canEntityDamage(level, entity)){
            if(state.is(MIMBlockRegistry.ICE_BRICKS)){
                level.setBlockAndUpdate(pos, MIMBlockRegistry.CRACKED_ICE_BRICKS.get().defaultBlockState());
            }
            else{
                level.destroyBlock(pos, false,entity);
            }
            level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }
}
