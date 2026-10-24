package com.mysticmalevolence.icemod.block;

import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class SnowsaicBlock extends Block {

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(stack.getItem() == Items.POWDER_SNOW_BUCKET){
            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
            /*if(!level.isClientSide){
                ServerLevel serverLevel = (ServerLevel)level;

            }*/
            //level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            level.setBlockAndUpdate(pos, MIMBlockRegistry.POWDER_SNOWSAIC.get().defaultBlockState());
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    public SnowsaicBlock(Properties properties) {
        super(properties);
    }
}
