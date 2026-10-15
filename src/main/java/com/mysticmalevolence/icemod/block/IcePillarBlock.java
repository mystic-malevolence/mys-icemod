package com.mysticmalevolence.icemod.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class IcePillarBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    public static final MapCodec<IcePillarBlock> CODEC = simpleCodec(IcePillarBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty BASE = BooleanProperty.create("base");
    public static final BooleanProperty CAP = BooleanProperty.create("cap");

    public static final Map<Direction.Axis, VoxelShape> PILLAR_SHAPE;
    public static final Map<Direction.Axis, VoxelShape> BASE_SHAPE;
    public static final Map<Direction.Axis, VoxelShape> CAP_SHAPE;


    public IcePillarBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition()
                .any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(BASE, true)
                .setValue(CAP, true)
                .setValue(WATERLOGGED,false));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction.Axis axis = state.getValue(AXIS);
        VoxelShape shape = PILLAR_SHAPE.get(axis);

        if(state.getValue(BASE)){
            shape = Shapes.or(shape, BASE_SHAPE.get(axis));
        }
        if(state.getValue(CAP)){
            shape = Shapes.or(shape, CAP_SHAPE.get(axis));
        }
        return shape;
    }

    @Override
    public MapCodec<? extends IcePillarBlock> codec() {
            return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        Direction.Axis axis = context.getClickedFace().getAxis();
        FluidState fluidState = level.getFluidState(pos);

        return this.getPillarState(level, pos, axis)
                .setValue(AXIS, axis)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }


    /**
     * Contains special case for bottom block of vertical pillar.
     */
    private BlockState getPillarState(BlockGetter level, BlockPos pos, Direction.Axis axis) {

        boolean hasNeighborAbove = level.getBlockState(pos.relative(axis,1)).getBlock() == this;
        boolean hasNeighborBelow = level.getBlockState(pos.relative(axis, -1)).getBlock() == this;

        if(axis.isVertical() && hasNeighborAbove && !hasNeighborBelow){
            return defaultBlockState().setValue(CAP, true).setValue(BASE, true);
        }

        return defaultBlockState().setValue(CAP, !hasNeighborAbove).setValue(BASE, !hasNeighborBelow);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BASE, CAP, WATERLOGGED);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(state.getValue(WATERLOGGED)){
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return getPillarState(level, pos, state.getValue(AXIS))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED))
                .setValue(AXIS, state.getValue(AXIS));
    }

    static {
        var pillarBuilder = ImmutableMap.<Direction.Axis, VoxelShape>builder();
        pillarBuilder.put(Direction.Axis.X, Block.box(0,2,2,16,14,14));
        pillarBuilder.put(Direction.Axis.Y, Block.box(2,0,2,14,16,14));
        pillarBuilder.put(Direction.Axis.Z, Block.box(2,2,0,14,14,16));
        PILLAR_SHAPE = pillarBuilder.build();

        var baseBuilder = ImmutableMap.<Direction.Axis, VoxelShape>builder();
        baseBuilder.put(Direction.Axis.X, Block.box(0,0,0,3,16,16));
        baseBuilder.put(Direction.Axis.Y, Block.box(0,0,0,16,3,16));
        baseBuilder.put(Direction.Axis.Z, Block.box(0,0,0,16,16,3));
        BASE_SHAPE = baseBuilder.build();

        var capBuilder = ImmutableMap.<Direction.Axis, VoxelShape>builder();
        capBuilder.put(Direction.Axis.X, Block.box(13,0,0,16,16,16));
        capBuilder.put(Direction.Axis.Y, Block.box(0,13,0,16,16,16));
        capBuilder.put(Direction.Axis.Z, Block.box(0,0,13,16,16,16));
        CAP_SHAPE = capBuilder.build();
    }
}
