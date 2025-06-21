package net.gamerdragon525.sf_plus.block;

//import net.gamerdragon525.wisp_of_the_lanterns.actions.PlaceHauntedPumpkinAction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

//import net.gamerdragon525.wisp_of_the_lanterns.block.entity.JackOSoulLanternBlockEntity;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FountainBlock extends Block {
    //public static final BooleanProperty TEST = BooleanProperty.create("test");
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 17);


    public FountainBlock() {
        super(Properties.of()
                .sound(SoundType.WOOD)
                .strength(1f, 10f)
                .pushReaction(PushReaction.BLOCK)
                .noOcclusion()
        );

        //this.registerDefaultState(this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH));

    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(BLOCKSTATE) == 1) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 2) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 3) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 4) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 5) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 6) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 7) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 8) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 9) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 10) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 11) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 12) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 13) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 14) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 15) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 16) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        if (state.getValue(BLOCKSTATE) == 17) {
            return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25),
                    box(6.25, 0, 5.5, 9.75, 9, 10.5), box(6.5, 0, 5.25, 9.5, 9, 10.75));
        }
        return Shapes.or(box(6.75, 0, 5, 9.25, 9, 11), box(5, 0, 6.75, 11, 9, 9.25), box(5.25, 0, 6.5, 10.75, 9, 9.5), box(5.5, 0, 6.25, 10.5, 9, 9.75), box(5.75, 0, 6, 10.25, 9, 10), box(6, 0, 5.75, 10, 9, 10.25), box(6.25, 0, 5.5, 9.75, 9, 10.5),
                box(6.5, 0, 5.25, 9.5, 9, 10.75));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BLOCKSTATE);
        //builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BLOCKSTATE, 0);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {

    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);

        //DispenseWispAction.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity, blockstate);
        return retval;
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);
        //DispenseWispAction.execute(world, pos.getX(), pos.getY(), pos.getZ(), null, null);
    }


    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    /* @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new JackOSoulLanternBlockEntity(pos, state);
    } */

    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
        super.triggerEvent(state, world, pos, eventID, eventParam);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            /* if (blockEntity instanceof JackOSoulLanternBlockEntity be) {
                Containers.dropContents(world, pos, be);
                world.updateNeighbourForOutputSignal(pos, this);
            } */
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        //if (tileentity instanceof JackOSoulLanternBlockEntity be)
        //return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        //else
        return 0;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
        super.setPlacedBy(world, pos, blockstate, entity, itemstack);

    }
}

