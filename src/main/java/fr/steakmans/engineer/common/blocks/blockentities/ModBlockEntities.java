package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MODID);

    //machines
    public static final RegistryObject<BlockEntityType<TestMachineBlockEntity>> TEST_MACHINE_BLOCKE_ENTITY = BLOCK_ENTITIES.register("test_machine_block_entity", () -> BlockEntityType.Builder.<TestMachineBlockEntity>of(TestMachineBlockEntity::new, ModBlocks.TEST_MACHINE.get()).build(null));

}
