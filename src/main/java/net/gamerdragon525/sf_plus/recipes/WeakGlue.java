package net.gamerdragon525.sf_plus.recipes;

import net.gamerdragon525.sf_plus.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.Holder;

import java.util.Optional;

@EventBusSubscriber
public class WeakGlue implements IBrewingRecipe {
    @SubscribeEvent
    public static void init(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addRecipe(new WeakGlue());
    }

    @Override
    public boolean isInput(ItemStack input) {
        Item inputItem = input.getItem();
        Optional<Holder<Potion>> optionalPotion = input.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
        return inputItem == Items.POTION && optionalPotion.isPresent() && optionalPotion.get().is(Potions.WATER);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return Ingredient.of(ItemTags.create(ResourceLocation.parse("sf_plus:glue"))).test(ingredient);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            return new ItemStack((ItemLike) ModItems.WEAK_GLUE);
        }
        return ItemStack.EMPTY;
    }

}
