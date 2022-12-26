package fr.steakmans.engineer.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import fr.steakmans.engineer.common.blocks.blockentities.*;
import fr.steakmans.engineer.common.util.CableUpgradeLevel;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class BaseCableBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (p_55164_) -> {
        p_55164_.put(Direction.NORTH, NORTH);
        p_55164_.put(Direction.EAST, EAST);
        p_55164_.put(Direction.SOUTH, SOUTH);
        p_55164_.put(Direction.WEST, WEST);
        p_55164_.put(Direction.UP, UP);
        p_55164_.put(Direction.DOWN, DOWN);
    }));

    public final VoxelShape[] shapes;

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final CableUpgradeLevel level;

    public BaseCableBlock(BlockBehaviour.Properties copy, CableUpgradeLevel level) {
        super(copy);
        this.level = level;
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH, false).setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false).setValue(WATERLOGGED, false));
        shapes = new VoxelShape[]{Block.box(5, 5, 5, 11, 11, 11), Block.box(5.5, 5.5, 0, 10.5, 10.5, 5), Block.box(5.5, 5.5, 11, 10.5, 10.5, 16), Block.box(11, 5.5, 5.5, 16, 10.5, 10.5), Block.box(0, 5.5, 5.5, 5, 10.5, 10.5), Block.box(5.5, 11, 5.5, 10.5, 16, 10.5), Block.box(5.5, 0, 5.5, 10.5, 5, 10.5)};
        }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case FRONT_BACK -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(NORTH));
            case LEFT_RIGHT -> state.setValue(EAST, state.getValue(WEST)).setValue(WEST, state.getValue(EAST));
            default -> super.mirror(state, mirror);
        };
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            default -> super.rotate(state, rotation);
            case CLOCKWISE_90 -> state.setValue(NORTH, state.getValue(WEST)).setValue(WEST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(EAST)).setValue(EAST, state.getValue(NORTH));
            case CLOCKWISE_180 -> state.setValue(NORTH, state.getValue(SOUTH)).setValue(WEST, state.getValue(EAST)).setValue(SOUTH, state.getValue(NORTH)).setValue(EAST, state.getValue(WEST));
            case COUNTERCLOCKWISE_90 -> state.setValue(NORTH, state.getValue(EAST)).setValue(EAST, state.getValue(SOUTH)).setValue(SOUTH, state.getValue(WEST)).setValue(WEST, state.getValue(NORTH));
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape = shapes[0];
        if(state.getValue(NORTH)) {
            shape = Shapes.or(shape, shapes[1]);
        }
        if(state.getValue(SOUTH)) {
            shape = Shapes.or(shape, shapes[2]);
        }
        if(state.getValue(EAST)) {
            shape = Shapes.or(shape, shapes[3]);
        }
        if(state.getValue(WEST)) {
            shape = Shapes.or(shape, shapes[4]);
        }
        if(state.getValue(UP)) {
            shape = Shapes.or(shape, shapes[5]);
        }
        if(state.getValue(DOWN)) {
            shape = Shapes.or(shape, shapes[6]);
        }
        return shape;
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_60479_, BlockGetter p_60480_, BlockPos p_60481_, CollisionContext p_60482_) {
        return getShape(p_60479_, p_60480_, p_60481_, p_60482_);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return getShape(p_60572_, p_60573_, p_60574_, p_60575_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter getter = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos pos1 = pos.north();
        BlockPos pos2 = pos.west();
        BlockPos pos3 = pos.south();
        BlockPos pos4 = pos.east();
        BlockPos pos5 = pos.above();
        BlockPos pos6 = pos.below();
        BlockState state1 = getter.getBlockState(pos1);
        BlockState state2 = getter.getBlockState(pos2);
        BlockState state3 = getter.getBlockState(pos3);
        BlockState state4 = getter.getBlockState(pos4);
        BlockState state5 = getter.getBlockState(pos5);
        BlockState state6 = getter.getBlockState(pos6);
        return super.getStateForPlacement(context).setValue(NORTH, this.connectsTo(state1)).setValue(WEST, this.connectsTo(state2)).setValue(SOUTH, this.connectsTo(state3)).setValue(EAST, this.connectsTo(state4)).setValue(UP, this.connectsTo(state5)).setValue(DOWN, this.connectsTo(state6)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }

    public boolean connectsTo(BlockState state) {
        return (state.is(ModBlocks.Tags.CABLE_BLOCK_TAG) && state.getBlock() == this.defaultBlockState().getBlock()) || state.is(ModBlocks.Tags.MACHINE_BLOCK_TAG);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
            return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter p_49929_, BlockPos p_49930_) {
        return !state.getValue(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return switch (level) {
            case PRIMITIVE -> ModBlockEntities.PRIMITIVE_CABLE_BLOC_ENTITY.get().create(pos, state);
            case BASIC -> ModBlockEntities.BASE_CABLE_BLOC_ENTITY.get().create(pos, state);
            case UPGRADED -> ModBlockEntities.UPGRADED_CABLE_BLOC_ENTITY.get().create(pos, state);
            case ADVANCED -> ModBlockEntities.ADVANCED_CABLE_BLOC_ENTITY.get().create(pos, state);
            case OVERCLOCKED -> ModBlockEntities.OVERCLOCKED_CABLE_BLOC_ENTITY.get().create(pos, state);
            case MAX -> ModBlockEntities.MAX_CABLE_BLOC_ENTITY.get().create(pos, state);
            case INFINITE -> ModBlockEntities.INFINITE_CABLE_BLOC_ENTITY.get().create(pos, state);
            default -> null;
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> type) {
        if(type == ModBlockEntities.PRIMITIVE_CABLE_BLOC_ENTITY.get()) return PrimitiveCableBlockEntity::tick;
        if(type == ModBlockEntities.BASE_CABLE_BLOC_ENTITY.get()) return BaseCableBlockEntity::tick;
        if(type == ModBlockEntities.UPGRADED_CABLE_BLOC_ENTITY.get()) return UpgradedCableBlockEntity::tick;
        if(type == ModBlockEntities.ADVANCED_CABLE_BLOC_ENTITY.get()) return AdvancedCableBlockEntity::tick;
        if(type == ModBlockEntities.OVERCLOCKED_CABLE_BLOC_ENTITY.get()) return OverclockedCableBlockEntity::tick;
        if(type == ModBlockEntities.MAX_CABLE_BLOC_ENTITY.get()) return MaxCableBlockEntity::tick;
        if(type == ModBlockEntities.INFINITE_CABLE_BLOC_ENTITY.get()) return CreativeCableBlockEntity::tick;
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        if(state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        return state.setValue(PROPERTY_BY_DIRECTION.get(direction), this.connectsTo(state1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
        def.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, WATERLOGGED);
    }
}