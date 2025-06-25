package net.gamerdragon525.sf_plus.jei_recipes;

import net.gamerdragon525.sf_plus.block.ModBlocks;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;


@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    public static mezz.jei.api.recipe.RecipeType<PyroTableRecipe> PYRO_TABLE_TYPE = new mezz.jei.api.recipe.RecipeType<>(PyroTableRecipeCategory.UID, PyroTableRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.parse("sf_plus:jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PyroTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<PyroTableRecipe> PyroTableRecipes = recipeManager.getAllRecipesFor(PyroTableRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
        registration.addRecipes(PYRO_TABLE_TYPE, PyroTableRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.PYRO_TABLE.get().asItem()), PYRO_TABLE_TYPE);
    }
}

