package net.gamerdragon525.sf_plus.recipes;

import net.gamerdragon525.sf_plus.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber
public class LeatherGlue implements IBrewingRecipe {
    @SubscribeEvent
    public static void init(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addRecipe(new LeatherGlue());
    }

    @Override
    public boolean isInput(ItemStack input) {
        return Ingredient.of(new ItemStack(Items.GLASS_BOTTLE)).test(input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return Ingredient.of(new ItemStack(Items.LEATHER)).test(ingredient);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            return new ItemStack((ItemLike) ModItems.LEATHER_GLUE);
        }
        return ItemStack.EMPTY;
    }

}
