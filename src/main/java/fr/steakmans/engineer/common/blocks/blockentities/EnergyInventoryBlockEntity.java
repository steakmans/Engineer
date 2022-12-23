package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.common.util.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyInventoryBlockEntity extends BlockEntity {

    public final int size;
    protected int timer;
    protected boolean requiresUpdate;
    public final ItemStackHandler inventory;
    protected LazyOptional<ItemStackHandler> handler;
    public final CustomEnergyStorage storage;
    private LazyOptional<CustomEnergyStorage> energy;

    private int capacity, maxRecieve, maxExtract;

    public EnergyInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size, int capacity, int maxRecieve, int maxExtract) {
        super(type, pos, state);
        if(size <= 0) size = 1;
        this.size = size;
        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> this.inventory);
        this.capacity = capacity;
        this.maxRecieve = maxRecieve;
        this.maxExtract = maxExtract;
        this.storage = createEnergyStorage();
        this.energy = LazyOptional.of(() -> this.storage);
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(size) {
            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                EnergyInventoryBlockEntity.this.update();
                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                EnergyInventoryBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStack extractItem(int slot) {
        final int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        ItemStack copy = stack.copy();
        stack.shrink(copy.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.insertItem(slot, copy, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack getItemInSlot(int slot) {
        return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public void tick() {
        this.timer++;
        outputEnergy();
        if(requiresUpdate && this.level != null) {
            update();
            this.requiresUpdate = false;
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) return this.handler.cast();
        if(cap == ForgeCapabilities.ENERGY) return this.energy.cast();
        return super.getCapability(cap, side);
    }

    public LazyOptional<ItemStackHandler> getHandler() {
        return handler;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", this.inventory.serializeNBT());
        tag.putInt("Energy", getEnergyStored());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.inventory.deserializeNBT(tag.getCompound("Inventory"));
        this.storage.setEnergy(tag.getInt("Energy"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.handler.invalidate();
        this.energy.invalidate();
    }

    public void update() {
        requestModelDataUpdate();
        setChanged();
        if(this.level != null) {
            this.level.setBlockAndUpdate(this.worldPosition, getBlockState());
        }
    }

    public int getEnergyStored() {
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStorable() {
        return this.storage.getMaxEnergyStored();
    }

    private CustomEnergyStorage createEnergyStorage() {
        return new CustomEnergyStorage(this, capacity, maxRecieve, maxExtract);
    }

    public void outputEnergy() {
        if (this.storage.getEnergyStored() >= EnergyInventoryBlockEntity.this.maxExtract && EnergyInventoryBlockEntity.this.storage.canExtract()) {
            for (final var direction : Direction.values()) {
                final BlockEntity be = EnergyInventoryBlockEntity.this.level.getBlockEntity(this.worldPosition.relative(direction));
                if(be == null) continue;

                be.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(energyStorage -> {
                    if(be != EnergyInventoryBlockEntity.this && energyStorage.getEnergyStored() < energyStorage.getMaxEnergyStored()) {
                        final int toSend = EnergyInventoryBlockEntity.this.storage.extractEnergy(EnergyInventoryBlockEntity.this.maxExtract, false);
                        final int recieved = energyStorage.receiveEnergy(toSend, false);


                        this.storage.setEnergy(EnergyInventoryBlockEntity.this.getEnergyStored() + toSend - recieved);
                    }
                });
            }
        }
    }

}
