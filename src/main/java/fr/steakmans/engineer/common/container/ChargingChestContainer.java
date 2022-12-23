package fr.steakmans.engineer.common.container;

import fr.steakmans.engineer.client.screen.ChargingChestScreen;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import fr.steakmans.engineer.common.blocks.blockentities.ChargingChestBlockEntity;
import fr.steakmans.engineer.common.container.data.ChargingChestContainerData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class ChargingChestContainer extends AbstractContainerMenu {

    private static ChargingChestBlockEntity blockEntity;
    private final ContainerLevelAccess containerAccess;
    public final ContainerData data;

    //Client constructor
    public ChargingChestContainer(int id, Inventory playerInv) {
        this(id, playerInv, new ItemStackHandler(18), BlockPos.ZERO, new SimpleContainerData(2));
    }

    //Server constructor
    public ChargingChestContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(ModContainers.CHARGING_CHEST_CONTAINER.get(), id);
        this.containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.data = data;

        final int slotSizePlus2 = 18, startX = 8, startY = 84, hotbarY = 142, inventoryY = 18;

        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new SlotItemHandler(slots, row * 9 + column, startX + column * slotSizePlus2, inventoryY + row * slotSizePlus2));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2, startY + row * slotSizePlus2));
            }
        }

        for(int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
        }

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = this.getSlot(index);

        if(slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if(index < 18) {
                if(!moveItemStackTo(item, 18, this.slots.size(), true)) return ItemStack.EMPTY;
            } else if (!moveItemStackTo(item, 0, 18, false)) {
                return ItemStack.EMPTY;
            }

            if(item.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }

        return retStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(containerAccess, player, ModBlocks.CHARGING_CHEST.get());
    }

    public static MenuConstructor getServerContainer(ChargingChestBlockEntity chest, BlockPos pos) {
        return (id, playerInv, player) -> new ChargingChestContainer(id, playerInv, chest.inventory, pos, new ChargingChestContainerData(chest, 2));
    }
}
