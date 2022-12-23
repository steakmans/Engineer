package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.common.blocks.blockentities.ChargingChestBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.InfinitePowerBankBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class InfinitePowerBank extends Block implements EntityBlock {

    public InfinitePowerBank() {
        super(Properties.copy(Blocks.IRON_BLOCK));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.INFINITE_POWER_BANK_BLOCK_ENTITY.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModBlockEntities.INFINITE_POWER_BANK_BLOCK_ENTITY.get() ? InfinitePowerBankBlockEntity::tick : null;
    }


}
