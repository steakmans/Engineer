package fr.steakmans.engineer.common.items;

import fr.steakmans.engineer.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ElectricShovel extends ShovelItem {
    public ElectricShovel() {
        super(ModItems.Tiers.ELECTRIC, 1, 1, new Properties().tab(Main.TAB).stacksTo(1));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        AtomicReference<Float> speedBreak = new AtomicReference<>(1.0f);
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(iEnergyStorage -> {
                if(iEnergyStorage.getEnergyStored() >= 10) {
                    speedBreak.set(speed);
                } else {
                    speedBreak.set(0.5f);
                }
            });
        }
        return speedBreak.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(iEnergyStorage -> {
            components.add(Component.translatable("item.engineer.electric_tools.tooltip").withStyle(ChatFormatting.GREEN).append(Component.literal(": " + iEnergyStorage.getEnergyStored() + "/" + iEnergyStorage.getMaxEnergyStored() + "FE").withStyle(ChatFormatting.YELLOW)));
        });

        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity p_41002_) {
        if(!level.isClientSide && state.getDestroySpeed(level, pos) != 0f) {
            stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(iEnergyStorage -> {
                iEnergyStorage.extractEnergy(10, false);
            });
        }
        return super.mineBlock(stack, level, state, pos, p_41002_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity p_40995_, LivingEntity p_40996_) {
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(iEnergyStorage -> {
            iEnergyStorage.extractEnergy(20, false);
        });
        return super.hurtEnemy(stack, p_40995_, p_40996_);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilitySerializable<>() {

            EnergyStorage storage = createEnergyStorage();
            LazyOptional<EnergyStorage> energy = LazyOptional.of(() -> storage);

            @Override
            public Tag serializeNBT() {
                return storage.serializeNBT();
            }

            @Override
            public void deserializeNBT(Tag nbt) {
                storage.deserializeNBT(nbt);
            }

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return cap == ForgeCapabilities.ENERGY ? energy.cast() : null;
            }
        };
    }

    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(24000, 20, 20);
    }
}
