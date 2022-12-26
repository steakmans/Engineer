package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.util.CableUpgradeLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    //resource blocks

    //ores
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> TITANIUM_DEEPSLATE_ORE = BLOCKS.register("titanium_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> ALUMINUM_DEEPSLATE_ORE = BLOCKS.register("aluminum_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> SILVER_DEEPSLATE_ORE = BLOCKS.register("silver_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));


    //machines
    public static final RegistryObject<Block> TEST_MACHINE = BLOCKS.register("test_machine", () -> new BaseMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CHARGING_CHEST = BLOCKS.register("charging_chest", () -> new ChargingChestBlock());

    //cables
    public static final RegistryObject<Block> PRIMITIVE_CABLE = BLOCKS.register("primitive_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.PRIMITIVE));
    public static final RegistryObject<Block> BASE_CABLE = BLOCKS.register("base_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.BASIC));
    public static final RegistryObject<Block> UPGRADED_CABLE = BLOCKS.register("upgraded_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.UPGRADED));
    public static final RegistryObject<Block> ADVANCED_CABLE = BLOCKS.register("advanced_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.ADVANCED));
    public static final RegistryObject<Block> OVERCLOCKED_CABLE = BLOCKS.register("overclocked_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.OVERCLOCKED));
    public static final RegistryObject<Block> MAX_CABLE = BLOCKS.register("max_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.MAX));
    public static final RegistryObject<Block> INFINITE_CABLE = BLOCKS.register("infinite_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), CableUpgradeLevel.INFINITE));

    //power banks
    public static final RegistryObject<Block> INFINITE_POWER_BANK = BLOCKS.register("infinite_power_bank", () -> new InfinitePowerBank());

    public static class Tags {

        public static final TagKey<Block> CABLE_BLOCK_TAG = createBlock("cables");
        public static final TagKey<Block> MACHINE_BLOCK_TAG = createBlock("machines");

        private static TagKey<Block> createBlock(String location) {
            return BlockTags.create(new ResourceLocation(Main.MODID, location));
        }

    }

}
