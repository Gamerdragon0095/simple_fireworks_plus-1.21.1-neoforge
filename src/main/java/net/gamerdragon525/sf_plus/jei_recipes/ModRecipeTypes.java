package net.gamerdragon525.sf_plus.jei_recipes;

import net.gamerdragon525.sf_plus.SimpleFireworksPlus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;

@EventBusSubscriber(modid = SimpleFireworksPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "sf_plus");
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "sf_plus");

    @SubscribeEvent
    public static void register(FMLConstructModEvent event) {
        IEventBus bus = ModList.get().getModContainerById("sf_plus").get().getEventBus();
        event.enqueueWork(() -> {
            RECIPE_TYPES.register(bus);
            SERIALIZERS.register(bus);
            RECIPE_TYPES.register("pyro_table_type", () -> PyroTableRecipe.Type.INSTANCE);
            SERIALIZERS.register("pyro_table_type", () -> PyroTableRecipe.Serializer.INSTANCE);
            System.out.println("test-2");
        });
    }
}

