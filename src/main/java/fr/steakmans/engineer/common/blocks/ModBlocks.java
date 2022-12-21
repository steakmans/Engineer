package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    //resource blocks

    //ores
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    //machines
    public static final RegistryObject<Block> TEST_MACHINE = BLOCKS.register("test_machine", () -> new BaseMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //cables
    public static final RegistryObject<Block> TEST_CABLE = BLOCKS.register("test_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    public static class Tags {

        public static final TagKey<Block> CABLE_BLOCK_TAG = createBlock("cables");
        public static final TagKey<Block> MACHINE_BLOC_TAG = createBlock("machines");


        private static TagKey<Block> createBlock(String location) {
            return BlockTags.create(new ResourceLocation(Main.MODID, location));
        }

        private static TagKey<Item> createItem(String location) {
            return ItemTags.create(new ResourceLocation(Main.MODID, location));
        }

    }

}
