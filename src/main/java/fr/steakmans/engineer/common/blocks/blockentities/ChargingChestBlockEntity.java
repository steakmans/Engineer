package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.items.ModItems;
import fr.steakmans.engineer.common.util.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChargingChestBlockEntity extends EnergyInventoryBlockEntity {

    public static final Component TITLE = Component.translatable("container." + Main.MODID + ".charging_chest");

    public ChargingChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHARGING_CHEST_BLOCK_ENTITY.get(), pos, state, 18, 36000, Integer.MAX_VALUE, 200);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    public void tick() {
        this.timer++;
        if(requiresUpdate && this.level != null) {
            update();
            this.requiresUpdate = false;
        }
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            ItemStack stack = getItemInSlot(slot);
            if(!stack.isEmpty() && stack.is(ModItems.Tags.ELECTRIC_TOOL_TAG)) {
                stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(iEnergyStorage -> {
                    int toSend = this.storage.extractEnergy(100, false);
                    int received = iEnergyStorage.receiveEnergy(toSend, false);
                    this.storage.setEnergy(this.getEnergyStored() + toSend - received);
                });
            }
        }
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {
        if(level.getBlockEntity(pos) instanceof final ChargingChestBlockEntity chest) chest.tick();
    }
}
