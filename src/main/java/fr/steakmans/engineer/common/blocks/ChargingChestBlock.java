package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.common.blocks.blockentities.ChargingChestBlockEntity;
import fr.steakmans.engineer.common.blocks.blockentities.ModBlockEntities;
import fr.steakmans.engineer.common.container.ChargingChestContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ChargingChestBlock extends Block implements EntityBlock {
    public ChargingChestBlock() {
        super(Properties.copy(Blocks.ENDER_CHEST).lightLevel(light -> 0));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

        if(!level.isClientSide && level.getBlockEntity(pos) instanceof final ChargingChestBlockEntity chest) {
            MenuProvider container = new SimpleMenuProvider(ChargingChestContainer.getServerContainer(chest, pos), ChargingChestBlockEntity.TITLE);
            NetworkHooks.openScreen((ServerPlayer) player, container, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.CHARGING_CHEST_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean p_60519_) {
        if(!level.isClientSide) {
            if(level.getBlockEntity(pos) instanceof final ChargingChestBlockEntity chest) {
                for (int i = 0; i < chest.inventory.getSlots(); i++) {
                    level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), chest.getItemInSlot(i)));
                }
            }
        }
        super.onRemove(state, level, pos, state1, p_60519_);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModBlockEntities.CHARGING_CHEST_BLOCK_ENTITY.get() ? ChargingChestBlockEntity::tick : null;
    }
}
