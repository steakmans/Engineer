package fr.steakmans.engineer.common.items;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
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
    public static final RegistryObject<Item> TEST_MACHINE_ITEM = ITEMS.register("test_machine", () -> new BlockItem(ModBlocks.TEST_MACHINE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> PRIMITIVE_CABLE_ITEM = ITEMS.register("primitive_cable", () -> new BlockItem(ModBlocks.PRIMITIVE_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> BASE_CABLE_ITEM = ITEMS.register("base_cable", () -> new BlockItem(ModBlocks.BASE_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> UPGRADED_CABLE_ITEM = ITEMS.register("upgraded_cable", () -> new BlockItem(ModBlocks.UPGRADED_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ADVANCED_CABLE_ITEM = ITEMS.register("advanced_cable", () -> new BlockItem(ModBlocks.ADVANCED_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> OVERCLOCKED_CABLE_ITEM = ITEMS.register("overclocked_cable", () -> new BlockItem(ModBlocks.OVERCLOCKED_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> MAX_CABLE_ITEM = ITEMS.register("max_cable", () -> new BlockItem(ModBlocks.MAX_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> INFINITE_CABLE_ITEM = ITEMS.register("creative_cable", () -> new BlockItem(ModBlocks.INFINITE_CABLE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_ORE_ITEM = ITEMS.register("titanium_ore", () -> new BlockItem(ModBlocks.TITANIUM_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> TITANIUM_DEEPSLATE_ORE_ITEM = ITEMS.register("titanium_deepslate_ore", () -> new BlockItem(ModBlocks.TITANIUM_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ITEMS.register("silver_ore", () -> new BlockItem(ModBlocks.SILVER_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> SILVER_DEEPSLATE_ORE_ITEM = ITEMS.register("silver_deepslate_ore", () -> new BlockItem(ModBlocks.SILVER_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ALUMINUM_ORE_ITEM = ITEMS.register("aluminum_ore", () -> new BlockItem(ModBlocks.ALUMINUM_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> ALUMINUM_DEEPSLATE_ORE_ITEM = ITEMS.register("aluminum_deepslate_ore", () -> new BlockItem(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> CHARGING_CHEST_ITEM = ITEMS.register("charging_chest", () -> new BlockItem(ModBlocks.CHARGING_CHEST.get(), new Item.Properties().tab(Main.TAB)));
    public static final RegistryObject<Item> INFINITE_POWER_BANK_ITEM = ITEMS.register("infinite_power_bank", () -> new BlockItem(ModBlocks.INFINITE_POWER_BANK.get(), new Item.Properties().tab(Main.TAB)));

    //armor

    //tools
    public static final RegistryObject<Item> ELECTRIC_PICKAXE = ITEMS.register("electric_pickaxe", () -> new ElectricPickaxe());
    public static final RegistryObject<Item> ELECTRIC_AXE = ITEMS.register("electric_axe", () -> new ElectricAxe());
    public static final RegistryObject<Item> ELECTRIC_SHOVEL = ITEMS.register("electric_shovel", () -> new ElectricShovel());
    public static final RegistryObject<Item> ELECTRIC_HOE = ITEMS.register("electric_hoe", () -> new ElectricHoe());
    public static final RegistryObject<Item> ELECTRIC_SWORD = ITEMS.register("electric_sword", () -> new ElectricSword());


    public static class Tiers {
        public static final Tier ELECTRIC = new ForgeTier(4, 4096, 2, 4, 0, null, () -> Ingredient.EMPTY);
    }

    public static class Tags {

        public static final TagKey<Item> ELECTRIC_TOOL_TAG = createItem("electric_tools");

        private static TagKey<Item> createItem(String location) {
            return ItemTags.create(new ResourceLocation(Main.MODID, location));
        }

    }

}
