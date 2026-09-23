package com.mysticmalevolence.icemod.registry;

import com.mysticmalevolence.icemod.IceMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MIMCreativeModeRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB,IceMod.MODID);

    public static final Supplier<CreativeModeTab> ICE_TAB = CREATIVE_MODE_TAB.register("ice_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MIMBlockRegistry.ICE_BRICKS.get()))
                    .title(Component.translatable("creativetab.mysicemod.ice_tab"))
                    .displayItems((itemDisplayParameters, output )-> {
                        //output.accept(ModItems.ICEBRICK);
                        output.accept(MIMBlockRegistry.ICE_BRICKS);
                        output.accept(MIMBlockRegistry.CRACKED_ICE_BRICKS);

                    }).build());

    /*public static final Supplier<CreativeModeTab> ICE_MOD_2_TAB = CREATIVE_MODE_TAB.register("ice_mod_2_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ICE_BRICKS.get()))
                    //Required to add more tabs with proper sorting
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(IceMod.MODID,"ice_mod_tab"))
                    .title(Component.translatable("creativetab.icemod.ice_mod_2"))
                    .displayItems((itemDisplayParameters, output )-> {
                        output.accept(ModItems.ICEBRICK);
                        output.accept(ModBlocks.ICE_BRICKS);

                    }).build());*/


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
