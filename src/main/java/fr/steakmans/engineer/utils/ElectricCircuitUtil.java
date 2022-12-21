package fr.steakmans.engineer.utils;

import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ElectricCircuitUtil {

    private List<BlockPos> machines;
    private List<BlockPos> cables;

    public ElectricCircuitUtil(List<BlockPos> machines, List<BlockPos> cables) {
        this.machines = machines;
        this.cables = cables;
    }

    public void addCable(Level level, BlockPos pos) {
        if(level.getBlockState(pos).is(ModBlocks.Tags.CABLE_BLOCK_TAG) && !cables.contains(pos)) {
            cables.add(pos);
        }
    }

    public void addMachine(Level level, BlockPos pos) {
        if(level.getBlockState(pos).is(ModBlocks.Tags.MACHINE_BLOC_TAG) && !machines.contains(pos)) {
            machines.add(pos);
        }
    }

    public void removeCable(BlockPos pos) {
        cables.remove(pos);
    }

    public void removeMachine(BlockPos pos) {
        machines.remove(pos);
    }

    public List<BlockPos> getMachines() {
        return machines;
    }

    public List<BlockPos> getCables() {
        return cables;
    }
}
