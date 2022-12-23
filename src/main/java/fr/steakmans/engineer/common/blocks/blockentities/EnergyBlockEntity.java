package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.util.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyBlockEntity extends BlockEntity {

    public final CustomEnergyStorage storage;
    private LazyOptional<CustomEnergyStorage> energy;

    public int capacity, maxRecieve, maxExtract;
    private int timer = 0;
    protected boolean requiresUpdate;

    public EnergyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int capacity, int maxRecieve, int maxExtract) {
        super(type, pos, state);
        this.capacity = capacity;
        this.maxRecieve = maxRecieve;
        this.maxExtract = maxExtract;
        this.storage = createEnergyStorage();
        this.energy = LazyOptional.of(() -> this.storage);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ENERGY ? this.energy.cast() : super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.energy.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Energy", getEnergyStored());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.storage.setEnergy(tag.getInt("Energy"));
    }

    public void update() {
        requestModelDataUpdate();
        setChanged();
        if(this.level != null) {
            this.level.setBlockAndUpdate(this.worldPosition, getBlockState());
        }
    }

    public void tick() {
        this.timer++;
        outputEnergy();
        if(requiresUpdate && this.level != null) {
            update();
            this.requiresUpdate = false;
        }
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
        if (this.storage.getEnergyStored() >= this.maxExtract && this.storage.canExtract()) {
            for (final var direction : Direction.values()) {
                final BlockEntity be = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if(be == null) continue;
                be.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(energyStorage -> {
                    if(be != EnergyBlockEntity.this && energyStorage.getEnergyStored() < energyStorage.getMaxEnergyStored()) {
                        final int toSend = EnergyBlockEntity.this.storage.extractEnergy(EnergyBlockEntity.this.maxExtract, false);
                        final int recieved = energyStorage.receiveEnergy(toSend, false);
                        this.storage.setEnergy(EnergyBlockEntity.this.getEnergyStored() + toSend - recieved);
                    }
                });
            }
        }
    }

}
