package net.gamerdragon525.sf_plus.dragon_lib.data.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;

public class Get {

    public static Entity closestEntityOfTypeInRange(LevelAccessor world, Class<? extends Entity> type, double x, double y, double z, double range) {
        return (Entity) world.getEntitiesOfClass(type, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
    }

}
