package net.gamerdragon525.sf_plus.actions.entity;

import net.gamerdragon525.sf_plus.SimpleFireworksPlus;
import net.gamerdragon525.sf_plus.block.ModBlocks;
import net.gamerdragon525.sf_plus.block.MortarBlock;
import net.gamerdragon525.sf_plus.dragon_lib.action.world.particle.ParticlesSpheres;
import net.gamerdragon525.sf_plus.dragon_lib.data.entity.Get;
import net.gamerdragon525.sf_plus.dragon_lib.data.entity.NBTTimerLoop;
import net.gamerdragon525.sf_plus.entity.ShellEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class ShellActions {
    public static void handleFuseTick(LevelAccessor world, Entity shell) {
        if (shell != null) {
            double x = shell.getX();
            double y = shell.getY();
            double z = shell.getZ();

            if (shell.getEntityData().get(ShellEntity.FUSE) >= 1) {

                System.out.println("test-8 - " + (shell.fallDistance));

                if (shell.fallDistance > 0)  {
                    int fuse = shell.getEntityData().get(ShellEntity.FUSE);

                    shell.getEntityData().set(ShellEntity.FUSE, fuse - 1);

                    System.out.println("test-5 - " + (fuse - 1));
                } else {
                    System.out.println("test-6 - " + (shell.getEntityData().get(ShellEntity.FUSE)));
                    SimpleFireworksPlus.queueServerWork(3, () -> {
                        System.out.println("test-7");
                        Entity _shell = Get.closestEntityOfTypeInRange(world, ShellEntity.class, x, y, z, 1);
                        if (_shell != null) {
                            if (!(_shell.fallDistance > 0)) {
                                double _x = _shell.getX();
                                double _y = _shell.getY();
                                double _z = _shell.getZ();

                                if (BlockPos.containing(x, y, z).equals(BlockPos.containing(_x, _y, _z))) {
                                    _shell.getEntityData().set(ShellEntity.FUSE, 0);
                                }
                            }
                        }
                    });
                }
            } else if (shell.getEntityData().get(ShellEntity.FUSE) == 0) {
                explodeShell(world, shell);
            }
        }
    }

    public static void explodeShell(LevelAccessor world, Entity shell) {
        if (shell != null) {
            double x = shell.getX();
            double y = shell.getY();
            double z = shell.getZ();

            if (NBTTimerLoop.getCurrentTimeOfLoop(shell, "particleTrail") != 0) {
                if (NBTTimerLoop.getCurrentIsPausedOfLoop(shell, "particleTrail")) {
                    net.gamerdragon525.sf_plus.dragon_lib.action.entity.NBTTimerLoop.setIsPausedOfTimerTag(shell, "particleTrail", false);
                } else {
                    if (!shell.isNoGravity()) {
                        shell.setNoGravity(true);
                    }
                    SimpleFireworksPlus.queueServerWork(1, () -> {
                        shell.teleportTo(x, y, z);
                    });
                    if (!shell.isInvisible()) {
                        shell.setInvisible(true);
                    }
                    ParticlesSpheres.spawnExpandingParticleSphereWithNoise(world, x, y, z, "flame", 3, 0.5, true, 1000, 0, 3621);
                }

            } else {
                ParticlesSpheres.spawnExpandingParticleSphereWithNoise(world, x, y, z, "flame", 3, 0.5, true, 1000, 0, 3621);
                shell.discard();
            }

        }
    }
}
