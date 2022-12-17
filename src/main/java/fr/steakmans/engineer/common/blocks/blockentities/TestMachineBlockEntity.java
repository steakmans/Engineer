package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.List;

public class TestMachineBlockEntity extends BlockEntity {

    //TODO Faire le système de lien entre machine par cable, et a l'avenir un système sans fil.
    //private List<HashMap<BlockPos, >>

    public TestMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEST_MACHINE_BLOCKE_ENTITY.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        Main.getLogger().info("tick");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }
}
