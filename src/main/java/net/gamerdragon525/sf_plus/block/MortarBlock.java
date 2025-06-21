package net.gamerdragon525.sf_plus.block;

//import net.gamerdragon525.wisp_of_the_lanterns.actions.PlaceHauntedPumpkinAction;
import net.gamerdragon525.sf_plus.actions.block.MortarBlockActions;
import net.gamerdragon525.sf_plus.dragon_lib.data.entity.Get;
import net.gamerdragon525.sf_plus.entity.ShellEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
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
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.gamerdragon525.sf_plus.block.entity.MortarBlockEntity;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MortarBlock extends Block implements EntityBlock {
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    int tickRate = 1;


    public MortarBlock() {
        super(Properties.of()
                .sound(SoundType.WOOD)
                .strength(1f, 10f)
                .pushReaction(PushReaction.BLOCK)
                .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH));

    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(BLOCKSTATE) == 1) {
            return switch (state.getValue(FACING)) {
                default -> Shapes.or(box(14, 0, 0, 16, 16, 16), box(0, 0, 0, 2, 16, 16), box(2, 0, 0, 14, 6, 1.5), box(2, 0, 1.5, 14, 1, 16));
                case NORTH -> Shapes.or(box(0, 0, 0, 2, 16, 16), box(14, 0, 0, 16, 16, 16), box(2, 0, 14.5, 14, 6, 16), box(2, 0, 0, 14, 1, 14.5));
                case EAST -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(0, 0, 14, 16, 16, 16), box(0, 0, 2, 1.5, 6, 14), box(1.5, 0, 2, 16, 1, 14));
                case WEST -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(0, 0, 0, 16, 16, 2), box(14.5, 0, 2, 16, 6, 14), box(0, 0, 2, 14.5, 1, 14));
            };
        }
        if (state.getValue(BLOCKSTATE) == 2) {
            return switch (state.getValue(FACING)) {
                default -> Shapes.or(box(14, 0, 0, 16, 16, 16), box(0, 0, 0, 2, 16, 16), box(2, 0, 0, 14, 6, 1.5), box(2, 0, 1.5, 14, 1, 16));
                case NORTH -> Shapes.or(box(0, 0, 0, 2, 16, 16), box(14, 0, 0, 16, 16, 16), box(2, 0, 14.5, 14, 6, 16), box(2, 0, 0, 14, 1, 14.5));
                case EAST -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(0, 0, 14, 16, 16, 16), box(0, 0, 2, 1.5, 6, 14), box(1.5, 0, 2, 16, 1, 14));
                case WEST -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(0, 0, 0, 16, 16, 2), box(14.5, 0, 2, 16, 6, 14), box(0, 0, 2, 14.5, 1, 14));
            };
        }
        if (state.getValue(BLOCKSTATE) == 3) {
            return switch (state.getValue(FACING)) {
                default -> Shapes.or(box(14, 0, 0, 16, 16, 16), box(0, 0, 0, 2, 16, 16), box(2, 0, 0, 14, 6, 1.5), box(2, 0, 1.5, 14, 2.25, 16));
                case NORTH -> Shapes.or(box(0, 0, 0, 2, 16, 16), box(14, 0, 0, 16, 16, 16), box(2, 0, 14.5, 14, 6, 16), box(2, 0, 0, 14, 2.25, 14.5));
                case EAST -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(0, 0, 14, 16, 16, 16), box(0, 0, 2, 1.5, 6, 14), box(1.5, 0, 2, 16, 2.25, 14));
                case WEST -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(0, 0, 0, 16, 16, 2), box(14.5, 0, 2, 16, 6, 14), box(0, 0, 2, 14.5, 2.25, 14));
            };
        }
        return switch (state.getValue(FACING)) {
            default -> Shapes.or(box(14, 0, 0, 16, 16, 16), box(0, 0, 0, 2, 16, 16), box(2, 0, 0, 14, 6, 1.5), box(2, 0, 1.5, 14, 1, 16));
            case NORTH -> Shapes.or(box(0, 0, 0, 2, 16, 16), box(14, 0, 0, 16, 16, 16), box(2, 0, 14.5, 14, 6, 16), box(2, 0, 0, 14, 1, 14.5));
            case EAST -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(0, 0, 14, 16, 16, 16), box(0, 0, 2, 1.5, 6, 14), box(1.5, 0, 2, 16, 1, 14));
            case WEST -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(0, 0, 0, 16, 16, 2), box(14.5, 0, 2, 16, 6, 14), box(0, 0, 2, 14.5, 1, 14));
        };
    }


    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BLOCKSTATE);
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        //return super.getStateForPlacement(context).setValue(BLOCKSTATE, 0);
        return super.getStateForPlacement(context).setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite()).setValue(BLOCKSTATE, 0);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(HorizontalDirectionalBlock.FACING, rot.rotate(state.getValue(HorizontalDirectionalBlock.FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(HorizontalDirectionalBlock.FACING)));
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

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MortarBlockEntity(pos, state);
    }

    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
        super.triggerEvent(state, world, pos, eventID, eventParam);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        world.scheduleTick(pos, this, tickRate);

        MortarBlockActions.ManageShellEntity(world, pos.getX() + 0.5, pos.getY() + 0.075, pos.getZ() + 0.5, blockstate);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, tickRate);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MortarBlockEntity be) {
                Containers.dropContents(world, pos, (Container) be);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        super.useWithoutItem(blockstate, world, pos, entity, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        double hitX = hit.getLocation().x;
        double hitY = hit.getLocation().y;
        double hitZ = hit.getLocation().z;
        Direction direction = hit.getDirection();
        if(world.getBlockEntity(pos) instanceof MortarBlockEntity MortarBlockEntity) {
            if (entity.isCrouching()) {
                if(!world.isClientSide()) {
                    ((ServerPlayer) entity).openMenu(new SimpleMenuProvider(MortarBlockEntity, Component.literal("mortar")), pos);
                }
            } else {
                //MortarBlockActions.AimMortar(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
                MortarBlockActions.fire(world, Get.closestEntityOfTypeInRange(world, ShellEntity.class, pos.getX(), pos.getY(), pos.getZ(), 1), blockstate, 2, 15);
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof MortarBlockEntity be)
        return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
        return 0;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
        super.setPlacedBy(world, pos, blockstate, entity, itemstack);

    }
}


