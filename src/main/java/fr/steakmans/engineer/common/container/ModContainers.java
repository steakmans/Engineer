package fr.steakmans.engineer.common.container;

import fr.steakmans.engineer.Main;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MODID);

    public static final RegistryObject<MenuType<ChargingChestContainer>> CHARGING_CHEST_CONTAINER = CONTAINERS.register("charging_chest", () -> new MenuType<>(ChargingChestContainer::new));

}
