package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class InfinitePowerBankBlockEntity extends EnergyBlockEntity{
    public InfinitePowerBankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFINITE_POWER_BANK_BLOCK_ENTITY.get(), pos, state, 1000000, 0, 1000);
    }

    @Override
    public void tick() {
        super.tick();
        if(getEnergyStored() < getMaxEnergyStorable()) {
            storage.setEnergy(getMaxEnergyStorable());
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final InfinitePowerBankBlockEntity be) be.tick();
    }

}
