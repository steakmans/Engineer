package fr.steakmans.engineer.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseCableBlock extends Block {
    public BaseCableBlock(BlockBehaviour.Properties copy) {
        super(copy);
    }
}
