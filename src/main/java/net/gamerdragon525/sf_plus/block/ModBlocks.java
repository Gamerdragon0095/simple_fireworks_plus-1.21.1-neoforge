package net.gamerdragon525.sf_plus.block;

import net.gamerdragon525.sf_plus.SimpleFireworksPlus;
import net.gamerdragon525.sf_plus.item.ModItems;
//import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SimpleFireworksPlus.MODID);

    public static final DeferredBlock<Block> PYRO_TABLE = registerBlock("pyro_table", () -> new PyroTableBlock());
    public static final DeferredBlock<Block> FOUNTAIN = registerBlock("fountain", () -> new FountainBlock());
    public static final DeferredBlock<Block> MORTAR = registerBlock("mortar", () -> new MortarBlock());


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
