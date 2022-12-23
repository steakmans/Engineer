package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.common.blocks.blockentities.BaseCableBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.CreativeCableBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.InfinitePowerBankBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.ModBlockEntities;
import fr.steakmans.engineer.common.util.CableUpgradeLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseCableBlock extends Block implements EntityBlock {

    private final CableUpgradeLevel level;

    public BaseCableBlock(BlockBehaviour.Properties copy, CableUpgradeLevel level) {
        super(copy);
        this.level = level;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return switch (level) {
            case BASIC -> ModBlockEntities.BASE_CABLE_BLOC_ENTITY.get().create(pos, state);
            case CREATIVE -> ModBlockEntities.CREATIVE_CABLE_BLOC_ENTITY.get().create(pos, state);
            default -> null;
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> type) {
        if(type == ModBlockEntities.BASE_CABLE_BLOC_ENTITY.get()) return BaseCableBlockEntity::tick;
        if(type == ModBlockEntities.CREATIVE_CABLE_BLOC_ENTITY.get()) return CreativeCableBlockEntity::tick;
        return null;
    }
}
