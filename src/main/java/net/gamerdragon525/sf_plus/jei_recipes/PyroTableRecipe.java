package net.gamerdragon525.sf_plus.jei_recipes;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.DataResult;

public class PyroTableRecipe implements Recipe<RecipeInput> {
    private static ItemStack output;
    private static NonNullList<Ingredient> recipeItems;

    public PyroTableRecipe(ItemStack output, NonNullList<Ingredient> recipeItems) {
        //this(output, recipeItems);
        this.output = output;
        this.recipeItems = recipeItems;
        System.out.println("test-1");
    }

    @Override
    public boolean matches(RecipeInput pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(RecipeInput input, HolderLookup.Provider holder) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Type implements RecipeType<PyroTableRecipe> {
        private Type() {
        }

        public static final RecipeType<PyroTableRecipe> INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<PyroTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        private static final MapCodec<PyroTableRecipe> CODEC = RecordCodecBuilder
                .mapCodec(builder -> builder.group(ItemStack.STRICT_CODEC.fieldOf("output").forGetter(recipe -> recipe.output), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap(ingredients -> {
                    Ingredient[] aingredient = ingredients.toArray(Ingredient[]::new); // Skip the empty check and create the array.
                    if (aingredient.length == 0) {
                        return DataResult.error(() -> "No ingredients found in custom recipe");
                    } else {
                        return DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                    }
                }, DataResult::success).forGetter(recipe -> recipe.recipeItems)).apply(builder, PyroTableRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, PyroTableRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<PyroTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PyroTableRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static PyroTableRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            inputs.replaceAll(ingredients -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            return new PyroTableRecipe(ItemStack.STREAM_CODEC.decode(buf), inputs);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, PyroTableRecipe recipe) {
            buf.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.getResultItem(null));
        }

    }
}

