package fr.steakmans.engineer.common.items;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITag;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    //resources
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(Main.TAB)));

    //blocks
    public static final RegistryObject<Item> TEST_MACHINE_BLOCK_ITEM = ITEMS.register("test_machine_block_item", () -> new BlockItem(ModBlocks.TEST_MACHINE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TEST_CABLE_BLOCK_ITEM = ITEMS.register("test_cable_block_item", () -> new BlockItem(ModBlocks.TEST_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_ORE_ITEM = ITEMS.register("titanium_ore_item", () -> new BlockItem(ModBlocks.TITANIUM_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ITEMS.register("silver_ore_item", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ALUMINUM_ORE_ITEM = ITEMS.register("aluminum_ore_item", () -> new BlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties().tab(Main.TAB)));


    //armor

}
