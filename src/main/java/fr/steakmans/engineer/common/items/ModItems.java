package fr.steakmans.engineer.common.items;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    //resources
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));

    //blocks
    public static final RegistryObject<Item> TEST_MACHINE_ITEM = ITEMS.register("test_machine_block", () -> new BlockItem(ModBlocks.TEST_MACHINE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> BASE_CABLE_ITEM = ITEMS.register("base_cable_block", () -> new BlockItem(ModBlocks.BASE_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> CREATIVE_CABLE_ITEM = ITEMS.register("creative_cable_item", () -> new BlockItem(ModBlocks.CREATIVE_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_ORE_ITEM = ITEMS.register("titanium_ore", () -> new BlockItem(ModBlocks.TITANIUM_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_DEEPSLATE_ORE_ITEM = ITEMS.register("titanium_deepslate_ore", () -> new BlockItem(ModBlocks.TITANIUM_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ITEMS.register("silver_ore", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_DEEPSLATE_ORE_ITEM = ITEMS.register("silver_deepslate_ore", () -> new BlockItem(ModBlocks.SILVER_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ALUMINUM_ORE_ITEM = ITEMS.register("aluminum_ore", () -> new BlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ALUMINUM_DEEPSLATE_ORE_ITEM = ITEMS.register("aluminum_deepslate_ore", () -> new BlockItem(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> CHARGING_CHEST_ITEM = ITEMS.register("charging_chest_block", () -> new BlockItem(ModBlocks.CHARGING_CHEST.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> INFINITE_POWER_BANK_ITEM = ITEMS.register("infinite_power_bank_block", () -> new BlockItem(ModBlocks.INFINITE_POWER_BANK.get(), new Item.Properties().tab(Main.TAB)));

    //armor

}
