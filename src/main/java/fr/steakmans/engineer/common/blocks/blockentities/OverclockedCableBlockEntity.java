package fr.steakmans.engineer.common.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OverclockedCableBlockEntity extends CableBlockEntity{
    public OverclockedCableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OVERCLOCKED_CABLE_BLOC_ENTITY.get(), pos, state, 8192 * 2, 8192, 8192);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final OverclockedCableBlockEntity be) be.tick();
    }
}
