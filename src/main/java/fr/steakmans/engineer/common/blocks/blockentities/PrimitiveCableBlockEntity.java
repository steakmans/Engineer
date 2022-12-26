package fr.steakmans.engineer.common.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PrimitiveCableBlockEntity extends CableBlockEntity{
    public PrimitiveCableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRIMITIVE_CABLE_BLOC_ENTITY.get(), pos, state, 32 * 2, 32, 32);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final PrimitiveCableBlockEntity be) be.tick();
    }
}
