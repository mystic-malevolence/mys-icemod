package com.mysticmalevolence.icemod.registry;

import com.mysticmalevolence.icemod.IceMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MIMItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IceMod.MODID);

    /*public static final DeferredItem<Item> ICEBRICK = ITEMS.register("ice_brick",
            () -> new Item(new Item.Properties()));*/

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
