package com.mysticmalevolence.icemod.item;

import com.mysticmalevolence.icemod.IceMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IceMod.MODID);

    /*public static final DeferredItem<Item> ICEBRICK = ITEMS.register("ice_brick",
            () -> new Item(new Item.Properties()));*/

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
