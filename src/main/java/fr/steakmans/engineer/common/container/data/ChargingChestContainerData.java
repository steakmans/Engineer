package fr.steakmans.engineer.common.container.data;

import fr.steakmans.engineer.common.blocks.blockentities.ChargingChestBlockEntity;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class ChargingChestContainerData extends SimpleContainerData {

    private final ChargingChestBlockEntity chest;
    public ChargingChestContainerData(ChargingChestBlockEntity chest, int i) {
        super(i);
        this.chest = chest;
    }

    @Override
    public int get(int key) {
        return switch (key) {
            case 0 -> this.chest.getEnergyStored();
            case 1 -> this.chest.getMaxEnergyStorable();
            default -> throw new UnsupportedOperationException("There are no value corresponding to this key");
        };
    }

    @Override
    public void set(int key, int value) {
        switch (key) {
            case 0 -> this.chest.storage.setEnergy(value);
            default -> throw new UnsupportedOperationException("There are no value corresponding to this key");
        }
    }
}
