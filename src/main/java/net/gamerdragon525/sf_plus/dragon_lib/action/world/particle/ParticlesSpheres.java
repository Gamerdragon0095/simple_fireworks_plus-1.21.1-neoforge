package net.gamerdragon525.sf_plus.dragon_lib.action.world.particle;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class ParticlesSpheres {
    public static void spawnParticleSphere(LevelAccessor world, double x, double y, double z, String particle, double radius, double speed, boolean force, int amount, double forceRadius) {
        double xRadius = 0;
        double zRadius = 0;
        double particleAmount = ((double) amount);
        double loop = particleAmount / 4;
        double yRadius = 0;
        double masterRadius = radius;

        String command = "";

        if (force == true) {
            if (forceRadius <= 0) {
                command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1 force @a";
            } else {
                command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1 force @a[distance=.." + (forceRadius) + "]";
            }
        } else {
            command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1";
        }

        while (loop < particleAmount) {
            yRadius = masterRadius;
            if (((loop >= particleAmount / 2 - 0.5) == (loop <= particleAmount / 2 + particleAmount / 4)) == false) {
                if (world instanceof ServerLevel _level)
                    _level.getServer().getCommands().performPrefixedCommand(
                            new CommandSourceStack(CommandSource.NULL,
                                    new Vec3((x + 0.5 + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * zRadius), (y + Math.sin(((Math.PI * 2) / particleAmount) * loop) * yRadius),
                                            (z + 0.5 + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * xRadius)),
                                    Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                            command);
            }
            xRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            zRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            loop = loop + 1;
        }
    }

    public static void spawnExpandingParticleSphere(LevelAccessor world, double x, double y, double z, String particle, double radius, double speed, boolean force, int amount, double forceRadius) {
        double xRadius = 0;
        double zRadius = 0;
        double particleAmount = ((double) amount);
        double loop = particleAmount / 4;
        double yRadius = 0;
        double masterRadius = radius;

        String command = "";

        while (loop < particleAmount) {
            if (force == true) {
                if (forceRadius <= 0) {
                    command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                            + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0 force @a";
                } else {
                    command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                            + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0 force @a[distance=.." + (forceRadius) + "]";
                }
            } else {
                command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                        + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0";
            }

            yRadius = masterRadius;
            if (((loop >= particleAmount / 2 - 0.5) == (loop <= particleAmount / 2 + particleAmount / 4)) == false) {
                if (world instanceof ServerLevel _level)
                    _level.getServer().getCommands().performPrefixedCommand(
                            new CommandSourceStack(CommandSource.NULL,
                                    new Vec3((x + 0.5 + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * zRadius), ((y + 0.5) + Math.sin(((Math.PI * 2) / particleAmount) * loop) * yRadius),
                                            (z + 0.5 + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * xRadius)),
                                    Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                            command);
            }
            xRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            zRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            loop = loop + 1;
        }
    }

    public static void spawnParticleSphereWithNoise(LevelAccessor world, double x, double y, double z, String particle, double radius, double speed, boolean force, int amount, double forceRadius, int noiseSeed) {
        double xRadius = 0;
        double zRadius = 0;
        double particleAmount = ((double) amount);
        double loop = particleAmount / 4;
        double yRadius = 0;
        double masterRadius = radius;

        String command = "";

        Long seed = (long) noiseSeed;
        RandomSource noiseSource = RandomSource.create();

        if (force == true) {
            if (forceRadius <= 0) {
                command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1 force @a";
            } else {
                command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1 force @a[distance=.." + (forceRadius) + "]";
            }
        } else {
            command = "particle " + (particle) + " ~ ~ ~ 0 0 0 " + (speed) + " 1";
        }

        while (loop < particleAmount) {
            noiseSource.setSeed(seed++);
            double noise1 =  Mth.nextDouble(noiseSource, -0.5, 0.5);
            double noise2 =  Mth.nextDouble(noiseSource, -0.5, 0.5);
            double noise3 =  Mth.nextDouble(noiseSource, -0.5, 0.5);

            yRadius = masterRadius;
            if (((loop >= particleAmount / 2 - 0.5) == (loop <= particleAmount / 2 + particleAmount / 4)) == false) {
                if (world instanceof ServerLevel _level)
                    _level.getServer().getCommands().performPrefixedCommand(
                            new CommandSourceStack(CommandSource.NULL,
                                    new Vec3((x + 0.5 + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * zRadius) + noise1, (y + Math.sin(((Math.PI * 2) / particleAmount) * loop) * yRadius) + noise2,
                                            (z + 0.5 + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * xRadius) + noise3),
                                    Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                            command);
            }
            xRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            zRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            loop = loop + 1;
        }
    }

    public static void spawnExpandingParticleSphereWithNoise(LevelAccessor world, double x, double y, double z, String particle, double radius, double speed, boolean force, int amount, double forceRadius, int noiseSeed) {
        double xRadius = 0;
        double zRadius = 0;
        double particleAmount = ((double) amount);
        double loop = particleAmount / 4;
        double yRadius = 0;
        double masterRadius = radius;

        String command = "";

        Long seed = (long) noiseSeed;
        RandomSource noiseSource = RandomSource.create();

        while (loop < particleAmount) {
            noiseSource.setSeed(seed++);
            double noise1 =  Mth.nextDouble(noiseSource, -0.5, 0.5);
            double noise2 =  Mth.nextDouble(noiseSource, -0.5, 0.5);
            double noise3 =  Mth.nextDouble(noiseSource, -0.5, 0.5);

            if (force == true) {
                if (forceRadius <= 0) {
                    command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                            + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0 force @a";
                } else {
                    command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                            + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0 force @a[distance=.." + (forceRadius) + "]";
                }
            } else {
                command = "particle " + (particle) + " ~ ~ ~ " + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * (zRadius / 1.5) + " " + Math.sin(((Math.PI * 2) / particleAmount) * loop) * (yRadius / 1.5) + " "
                        + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * (xRadius / 1.5) + " " + (speed) + " 0";
            }

            yRadius = masterRadius;
            if (((loop >= particleAmount / 2 - 0.5) == (loop <= particleAmount / 2 + particleAmount / 4)) == false) {
                if (world instanceof ServerLevel _level)
                    _level.getServer().getCommands().performPrefixedCommand(
                            new CommandSourceStack(CommandSource.NULL,
                                    new Vec3((x + 0.5 + Math.cos(((Math.PI * 10 * zRadius) / particleAmount) * loop) * zRadius) + noise1, (y + Math.sin(((Math.PI * 2) / particleAmount) * loop) * yRadius) + noise2,
                                            (z + 0.5 + Math.sin(((Math.PI * 10 * xRadius) / particleAmount) * loop) * xRadius) + noise3),
                                    Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                            command);
            }
            xRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            zRadius = Math.cos(((Math.PI * 2) / particleAmount) * loop) * yRadius;
            loop = loop + 1;
        }
    }
}
