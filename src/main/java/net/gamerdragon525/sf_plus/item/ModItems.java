package net.gamerdragon525.sf_plus.item;

import net.gamerdragon525.sf_plus.SimpleFireworksPlus;
//import net.gamerdragon525.sf_plus.entity.ModEntities;
import net.gamerdragon525.sf_plus.dragon_lib.item.NullItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
//import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SimpleFireworksPlus.MODID);

    //public static final DeferredItem<Item> WISP_SPAWN_EGG = ITEMS.register("wisp_spawn_egg", () -> new DeferredSpawnEggItem(ModEntities.WISP, -6241587, -13921357, new Item.Properties()));
    //public static final DeferredItem<Item> PUMPKIN_MASK = ITEMS.register("pumpkin_mask", PumpkinMaskItem.Helmet::new);

        public static final DeferredItem<Item> REINFORCED_PAPER = ITEMS.register("reinforced_paper", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> BLANK_CHARGE = ITEMS.register("blank_charge", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> TIMER_FUSE = ITEMS.register("timer_fuse", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> BLANK_SHELL_HALF = ITEMS.register("blank_shell_half", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> MINI_FIREWORK_STARS = ITEMS.register("mini_firework_stars", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> LEATHER_GLUE = ITEMS.register("leather_glue", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> SPIDER_EYE_GLUE = ITEMS.register("spider_eye_glue", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> FIREWORK_SHELL = ITEMS.register("firework_shell", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> FIREWORK_SHELL_WITH_CHARGE = ITEMS.register("firework_shell_with_charge", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> FIREWORK_FAN_SHELL = ITEMS.register("firework_fan_shell", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> FIREWORK_FAN_SHELL_WITH_CHARGE = ITEMS.register("firework_fan_shell_with_charge", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> SPARKLER = ITEMS.register("sparkler", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> WEAK_GLUE = ITEMS.register("weak_glue", () -> new Item(new Item.Properties()));

        //ignore this...
        //public static final DeferredItem<Item> NULL_ITEM = ITEMS.register("null", () -> new NullItem(new Item.Properties()));



    /*public static final DeferredItem<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_JADE = ITEMS.register("raw_jade",
            () -> new Item(new Item.Properties()));*/

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
