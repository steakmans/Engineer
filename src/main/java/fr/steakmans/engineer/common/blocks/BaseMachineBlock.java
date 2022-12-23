package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.common.blocks.blockentities.ModBlockEntities;
import fr.steakmans.engineer.common.blocks.blockentities.TestMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BaseMachineBlock extends Block implements EntityBlock {

    public BaseMachineBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.TEST_MACHINE_BLOCK_ENTITY.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> type) {
        return type == ModBlockEntities.TEST_MACHINE_BLOCK_ENTITY.get() ? TestMachineBlockEntity::tick : null;
    }
}
