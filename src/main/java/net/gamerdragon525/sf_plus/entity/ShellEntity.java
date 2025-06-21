package net.gamerdragon525.sf_plus.entity;

//import net.gamerdragon525.wisp_of_the_lanterns.entity.goal.InfestPumpkinGoal;
import net.gamerdragon525.sf_plus.actions.entity.ShellActions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class ShellEntity extends PathfinderMob {
    public static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(ShellEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> BURST_SIZE = SynchedEntityData.defineId(ShellEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LIFT = SynchedEntityData.defineId(ShellEntity.class, EntityDataSerializers.INT);

    //public static final EntityDataAccessor<CompoundTag> STARS = SynchedEntityData.defineId(ShellEntity.class, EntityDataSerializers.COMPOUND_TAG);


    public ShellEntity(EntityType<ShellEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        //this.moveControl = new FlyingMoveControl(this, 10, true);
        refreshDimensions();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        ShellActions.handleFuseTick(this.level(), this);
    }


    @Override
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());

        super.mobInteract(sourceentity, hand);

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity entity = this;
        Level world = this.level();

        return retval;
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUSE, -1);
        builder.define(BURST_SIZE, 0);
        builder.define(LIFT, 0);
        //builder.define(STARS, stars);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity entity) {
        return super.getPassengerRidingPosition(entity).add(0, -0.35F, 0);
    }

    /* @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.soul_sand.break"));
    } */

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.is(DamageTypes.FALL))
            return false;
        return super.hurt(damagesource, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Fuse", this.entityData.get(FUSE));
        compound.putInt("BurstSize", this.entityData.get(BURST_SIZE));
        compound.putInt("Lift", this.entityData.get(LIFT));
        //compound.put("Stars", stars);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Fuse"))
            this.entityData.set(FUSE, compound.getInt("Fuse"));
        if (compound.contains("BurstSize"))
            this.entityData.set(BURST_SIZE, compound.getInt("BurstSize"));
        if (compound.contains("Lift"))
            this.entityData.set(LIFT, compound.getInt("Lift"));
        //if (compound.contains("Stars"))
        //    this.entityData.set(STARS, compound.getCompound("Stars"));
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        return super.getDefaultDimensions(pose).scale(0.5f);
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 10);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        return builder;
    }
}
