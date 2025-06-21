package net.gamerdragon525.sf_plus.block.entity;

import net.gamerdragon525.sf_plus.block.ModBlocks;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.gamerdragon525.sf_plus.SimpleFireworksPlus;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SimpleFireworksPlus.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> MORTAR = register("mortar", ModBlocks.MORTAR, MortarBlockEntity::new);

    private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        //event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MORTAR.get(), (blockEntity, side) -> ((MortarBlockEntity) blockEntity).getItemHandler());
    }
}
