package net.gamerdragon525.sf_plus.dragon_lib.action.entity;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;

public class ScareTargetAsEntity {

    public static void execute0(Entity target, Entity entity, double distance) {

        double coolDown = 0;

        if (target == null || entity == null)
            return;

        if (entity.getPersistentData().getDouble("scareCoolDown") <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", 30);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

    public static void execute1(Entity target, Entity entity, double distance, double freezeTimer) {

        if (target == null || entity == null)
            return;

        double coolDown = 0;

        if (entity.getPersistentData().getDouble("scareCoolDown") <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", freezeTimer);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

    public static void execute2(Entity target, Entity entity, double distance, double freezeTimer, double coolDown) {

        if (target == null || entity == null)
            return;

        if (entity.getPersistentData().getDouble("scareCoolDown") <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", freezeTimer);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

}

