package net.gamerdragon525.sf_plus.screen;

import net.gamerdragon525.sf_plus.block.entity.MortarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class MortarMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public MortarBlockEntity blockEntity;
    private  Level level;

    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public  Level world;
    public  Player entity;
    public int x, y, z;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private IItemHandler internal;
    private boolean bound = false;
    private Supplier<Boolean> boundItemMatcher = null;
    private Entity boundEntity = null;

    public MortarMenu(int containerId, Inventory inv, FriendlyByteBuf extraData){
        super(ModMenuTypes.MORTAR_MENU.get(), containerId);

        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = new ItemStackHandler(1);
        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            access = ContainerLevelAccess.create(world, pos);
        }

        if (pos != null) {
             { // might be bound to block
                this.blockEntity = (MortarBlockEntity) this.world.getBlockEntity(pos);
                if (this.blockEntity instanceof BaseContainerBlockEntity baseContainerBlockEntity) {
                    this.internal = new InvWrapper(baseContainerBlockEntity);
                    this.bound = true;
                }
            }
        }
        customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 79, 55){
        }));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

    }

    @Override
    public boolean stillValid(Player player) {
        if (this.bound) {
            if (this.boundItemMatcher != null)
                return this.boundItemMatcher.get();
            else if (this.blockEntity != null)
                return AbstractContainerMenu.stillValid(this.access, player, this.blockEntity.getBlockState().getBlock());
            else if (this.boundEntity != null)
                return this.boundEntity.isAlive();
        }
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 1) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true))
                    return ItemStack.EMPTY;
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                if (index < 1 + 27) {
                    if (!this.moveItemStackTo(itemstack1, 1 + 27, this.slots.size(), true))
                        return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(itemstack1, 1, 1 + 27, false))
                        return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
        boolean flag = false;
        int i = p_38905_;
        if (p_38907_) {
            i = p_38906_ - 1;
        }
        if (p_38904_.isStackable()) {
            while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
                Slot slot = this.slots.get(i);
                ItemStack itemstack = slot.getItem();
                if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
                    int j = itemstack.getCount() + p_38904_.getCount();
                    int k = slot.getMaxStackSize(itemstack);
                    if (j <= k) {
                        p_38904_.setCount(0);
                        itemstack.setCount(j);
                        slot.set(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < k) {
                        p_38904_.shrink(k - itemstack.getCount());
                        itemstack.setCount(k);
                        slot.set(itemstack);
                        flag = true;
                    }
                }
                if (p_38907_) {
                    i--;
                } else {
                    i++;
                }
            }
        }
        if (!p_38904_.isEmpty()) {
            if (p_38907_) {
                i = p_38906_ - 1;
            } else {
                i = p_38905_;
            }
            while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
                Slot slot1 = this.slots.get(i);
                ItemStack itemstack1 = slot1.getItem();
                if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
                    int l = slot1.getMaxStackSize(p_38904_);
                    slot1.setByPlayer(p_38904_.split(Math.min(p_38904_.getCount(), l)));
                    slot1.setChanged();
                    flag = true;
                    break;
                }
                if (p_38907_) {
                    i--;
                } else {
                    i++;
                }
            }
        }
        return flag;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
                for (int j = 0; j < internal.getSlots(); ++j) {
                    playerIn.drop(internal.getStackInSlot(j), false);
                    if (internal instanceof IItemHandlerModifiable ihm)
                        ihm.setStackInSlot(j, ItemStack.EMPTY);
                }
            } else {
                for (int i = 0; i < internal.getSlots(); ++i) {
                    playerIn.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
                    if (internal instanceof IItemHandlerModifiable ihm)
                        ihm.setStackInSlot(i, ItemStack.EMPTY);
                }
            }
        }
    }


    public Map<Integer, Slot> get() {
        return customSlots;
    }


}
