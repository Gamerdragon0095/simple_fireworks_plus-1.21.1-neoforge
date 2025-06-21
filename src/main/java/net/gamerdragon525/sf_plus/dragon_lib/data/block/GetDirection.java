package net.gamerdragon525.sf_plus.dragon_lib.data.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class GetDirection {
    public static Direction getOppositeDirectionOfBlock(BlockState blockState) {
        /* Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
        if (blockState != null && prop instanceof DirectionProperty) {
            return prop.getOpposite();
        }
        return Direction.NORTH.getOpposite(); */

       Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
        if (prop instanceof DirectionProperty dp)
            return blockState.getValue(dp).getOpposite();
        prop = blockState.getBlock().getStateDefinition().getProperty("axis");
        return (prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH).getOpposite();
    }

    public static Direction getDirectionOfBlock(BlockState blockState) {
        Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
        if (prop instanceof DirectionProperty dp)
            return blockState.getValue(dp);
        prop = blockState.getBlock().getStateDefinition().getProperty("axis");
        return (prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH);
    }

}
