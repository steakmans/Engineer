package fr.steakmans.engineer.common.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MaxCableBlockEntity extends CableBlockEntity{
    public MaxCableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MAX_CABLE_BLOC_ENTITY.get(), pos, state, 32768 * 2, 32768, 32768);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final MaxCableBlockEntity be) be.tick();
    }
}
