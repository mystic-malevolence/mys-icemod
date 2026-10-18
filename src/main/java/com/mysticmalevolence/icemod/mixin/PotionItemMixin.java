package com.mysticmalevolence.icemod.mixin;

import com.mysticmalevolence.icemod.registry.MIMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Allow water bottles to repair cracked ice bricks, like how they can turn dirt to mud
 */
@Mixin(PotionItem.class)
public class PotionItemMixin {
    //@Inject(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionContents;is(Lnet/minecraft/core/Holder;)Z"),
    @Inject(method = "useOn", at = @At(value = "HEAD"),
    cancellable = true
    )
    public void mysicemod_useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir){
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        PotionContents potionContents = itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        BlockState blockState = level.getBlockState(blockPos);

        if(context.getClickedFace() != Direction.DOWN
                && blockState.is(MIMBlockRegistry.CRACKED_ICE_BRICKS.get())
                && potionContents.is(Potions.WATER)){
            level.playSound(null, blockPos, SoundEvents.PLAYER_HURT_FREEZE, SoundSource.BLOCKS,1.0F,1.0F);
            player.setItemInHand(context.getHand(), ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            //Counts to stats twice if this is uncommented; seems like a bug with vanilla dirt -> mud that I almost replicated
            //player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if(!level.isClientSide){
                ServerLevel serverLevel = (ServerLevel)level;
                for(int i=0;i<5;i++){
                    serverLevel.sendParticles(
                            ParticleTypes.SNOWFLAKE,
                            (double)blockPos.getX() + (level.random.nextDouble()/3.0),
                            (double)(blockPos.getY() + 1),
                            (double)blockPos.getZ() + (level.random.nextDouble()/3.0),
                            1,
                            0.0,
                            0.0,
                            0.0,
                            0.8
                    );
                }
            }

            level.playSound(null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,1.0F,1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            level.setBlockAndUpdate(blockPos, MIMBlockRegistry.ICE_BRICKS.get().defaultBlockState());

            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}
