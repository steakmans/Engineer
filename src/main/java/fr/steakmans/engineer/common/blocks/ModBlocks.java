package fr.steakmans.engineer.common.blocks;

import fr.steakmans.engineer.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    //resource blocks

    //machines
    public static final RegistryObject<Block> TEST_MACHINE = BLOCKS.register("test_machine", () -> new BaseMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //cables
    public static final RegistryObject<Block> TEST_CABLE = BLOCKS.register("test_cable", () -> new BaseCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

}
