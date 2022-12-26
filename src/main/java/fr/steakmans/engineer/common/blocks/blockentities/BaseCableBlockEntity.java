package fr.steakmans.engineer.common.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseCableBlockEntity extends CableBlockEntity{
    public BaseCableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASE_CABLE_BLOC_ENTITY.get(), pos, state, 128 * 2, 128, 128);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final BaseCableBlockEntity be) be.tick();
    }
}
