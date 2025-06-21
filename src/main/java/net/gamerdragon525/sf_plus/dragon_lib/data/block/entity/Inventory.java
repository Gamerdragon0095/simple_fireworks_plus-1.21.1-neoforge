package net.gamerdragon525.sf_plus.dragon_lib.data.block.entity;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Inventory {
    public static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof Container container) {
            ItemStack stack = container.getItem(slot);
            if (stack != null) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

}
