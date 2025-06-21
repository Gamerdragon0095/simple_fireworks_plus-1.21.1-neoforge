package net.gamerdragon525.sf_plus.actions.block;

import net.gamerdragon525.sf_plus.dragon_lib.action.entity.NBTTimerLoop;
import net.gamerdragon525.sf_plus.dragon_lib.data.entity.Get;
import net.gamerdragon525.sf_plus.dragon_lib.data.block.GetDirection;
import net.gamerdragon525.sf_plus.entity.ModEntities;
import net.gamerdragon525.sf_plus.entity.ShellEntity;
import net.gamerdragon525.sf_plus.item.ModItems;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Direction;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.Comparator;

import static net.gamerdragon525.sf_plus.dragon_lib.data.block.GetDirection.getOppositeDirectionOfBlock;
import static net.gamerdragon525.sf_plus.dragon_lib.data.block.entity.Inventory.itemFromBlockInventory;

public class MortarBlockActions {
    public static void AimMortar(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        double shellOffSet = 0;
        if ((blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1) < 3) {
            {
                int _value = (int) ((blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip3 ? blockstate.getValue(_getip3) : -1) + 1);
                BlockPos _pos = BlockPos.containing(x, y, z);
                BlockState _bs = world.getBlockState(_pos);
                if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
                    world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
            }
        } else {
            {
                int _value = 0;
                BlockPos _pos = BlockPos.containing(x, y, z);
                BlockState _bs = world.getBlockState(_pos);
                if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
                    world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
            }
        }
    }

    public static void SummonShell(LevelAccessor world, double x, double y, double z) {
        if (world instanceof ServerLevel _level) {
            Entity entityToSpawn = ModEntities.SHELL.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
            if (entityToSpawn != null) {
                entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
            }
        }
    }

    public static void ManageShellEntity(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        Entity shell = Get.closestEntityOfTypeInRange(world, ShellEntity.class, x, y, z, 1);
        Item mortarItem = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy().getItem());

        if (mortarItem == ModItems.FIREWORK_SHELL_WITH_CHARGE.asItem()) {
            if (shell == null) {
                SummonShell(world, x, y, z);
            } else {
                shell.teleportTo(x, y, z);
            }

        } else {
            if (!(shell == null)) {
                if (!shell.level().isClientSide())
                    shell.discard();
            }

        }
    }

    public static void fire(LevelAccessor world, Entity shell, BlockState mortar, int power, int effectTrailLength) {
        Direction fireDirection = getOppositeDirectionOfBlock(mortar);

        int _power = 0;

        if (power > 0) {
            _power = power;
        } else {
            _power = 1;
        }

        if (shell != null && mortar != null) {
            if (shell instanceof ShellEntity) {
                if ((shell.getEntityData().get(ShellEntity.FUSE) == -1)) {
                    shell.push((_power * fireDirection.getStepX()), _power, (_power * fireDirection.getStepZ()));

                    int fuse = Mth.nextInt(RandomSource.create(), 5, 30);

                    shell.getEntityData().set(ShellEntity.FUSE, fuse);

                    NBTTimerLoop.createNewTimerTag(shell, "particleTrail", effectTrailLength, true, true);
                    System.out.println("test-1 - " + (fireDirection));
                    System.out.println("test-2 - " + (fuse));
                    System.out.println("test-3 - " + (_power * fireDirection.getStepX()));
                    System.out.println("test-4 - " + (_power * fireDirection.getStepZ()));
                }
            }
        }
    }
}
