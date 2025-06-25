package net.gamerdragon525.sf_plus.jei_recipes;

import net.gamerdragon525.sf_plus.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;


import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

public class PyroTableRecipeCategory implements IRecipeCategory<PyroTableRecipe> {
    public final static ResourceLocation UID = ResourceLocation.parse("sp_plus:pyro_table_type");
    public final static ResourceLocation TEXTURE = ResourceLocation.parse("sp_plus:textures/screens/pyro_table_jei.png");
    private final IDrawable background;
    private final IDrawable icon;

    public PyroTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 92, 139);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PYRO_TABLE.get().asItem()));
    }

    @Override
    public mezz.jei.api.recipe.RecipeType<PyroTableRecipe> getRecipeType() {
        return ModJeiPlugin.PYRO_TABLE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Pyro Table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public int getWidth() {
        return this.background.getWidth();
    }

    @Override
    public int getHeight() {
        return this.background.getHeight();
    }

    @Override
    public void draw(PyroTableRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PyroTableRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 2).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 2).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 2).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 2).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 20).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 20).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 20).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 20).addIngredients(recipe.getIngredients().get(8));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 20).addIngredients(recipe.getIngredients().get(9));
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 38).addIngredients(recipe.getIngredients().get(10));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 38).addIngredients(recipe.getIngredients().get(11));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 38).addIngredients(recipe.getIngredients().get(12));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 38).addIngredients(recipe.getIngredients().get(13));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 38).addIngredients(recipe.getIngredients().get(14));
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 56).addIngredients(recipe.getIngredients().get(15));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 56).addIngredients(recipe.getIngredients().get(16));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 56).addIngredients(recipe.getIngredients().get(17));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 56).addIngredients(recipe.getIngredients().get(18));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 56).addIngredients(recipe.getIngredients().get(19));
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 74).addIngredients(recipe.getIngredients().get(20));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 74).addIngredients(recipe.getIngredients().get(21));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 74).addIngredients(recipe.getIngredients().get(22));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 74).addIngredients(recipe.getIngredients().get(23));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 74).addIngredients(recipe.getIngredients().get(24));
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 92).addIngredients(recipe.getIngredients().get(25));
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 92).addIngredients(recipe.getIngredients().get(26));
        builder.addSlot(RecipeIngredientRole.INPUT, 38, 92).addIngredients(recipe.getIngredients().get(27));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 92).addIngredients(recipe.getIngredients().get(28));
        builder.addSlot(RecipeIngredientRole.INPUT, 74, 92).addIngredients(recipe.getIngredients().get(29));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 38, 121).addItemStack(recipe.getResultItem(null));
    }
}
