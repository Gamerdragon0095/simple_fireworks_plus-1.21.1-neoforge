package net.gamerdragon525.sf_plus.recipes;

import net.gamerdragon525.sf_plus.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@JeiPlugin
public class ModBrewingRecipes implements IModPlugin {
    int listLoop = 0;

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.parse("sf_plus:brewing_recipes");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
        List<IJeiBrewingRecipe> brewingRecipes = new ArrayList<>();
        ItemStack potion = new ItemStack(Items.POTION);
        ItemStack potion2 = new ItemStack(Items.POTION);
        List<ItemStack> ingredientStack = new ArrayList<>();
        List<ItemStack> inputStack = new ArrayList<>();

        //Leather Glue Recipe
        ingredientStack.add(new ItemStack(Items.LEATHER));
        inputStack.add(new ItemStack(Items.GLASS_BOTTLE));
        brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), new ItemStack((ItemLike) ModItems.LEATHER_GLUE)));
        inputStack.clear();
        ingredientStack.clear();

        //Spider Eye Glue Recipe
        ingredientStack.add(new ItemStack(Items.SPIDER_EYE));
        inputStack.add(new ItemStack(Items.GLASS_BOTTLE));
        brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), List.copyOf(inputStack), new ItemStack((ItemLike) ModItems.SPIDER_EYE_GLUE)));
        inputStack.clear();
        ingredientStack.clear();

        //Weak Glue Recipe
        while (listLoop < BuiltInRegistries.ITEM.getOrCreateTag(ItemTags.create(ResourceLocation.parse("sf_plus:glue"))).size()) {
            ingredientStack.add(BuiltInRegistries.ITEM.getOrCreateTag(ItemTags.create(ResourceLocation.parse("sf_plus:glue"))).get(listLoop).value().asItem().getDefaultInstance());
            listLoop += 1;
        }
        potion.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER));
        brewingRecipes.add(factory.createBrewingRecipe(List.copyOf(ingredientStack), potion.copy(), new ItemStack((ItemLike) ModItems.WEAK_GLUE)));
        ingredientStack.clear();
        registration.addRecipes(RecipeTypes.BREWING, brewingRecipes);
    }
}
