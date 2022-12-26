package fr.steakmans.engineer.common.blocks.blockentities;

import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.BaseCableBlock;
import fr.steakmans.engineer.common.blocks.InfinitePowerBank;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MODID);

    //machines
    public static final RegistryObject<BlockEntityType<TestMachineBlockEntity>> TEST_MACHINE_BLOCK_ENTITY = BLOCK_ENTITIES.register("test_machine_block_entity", () -> BlockEntityType.Builder.<TestMachineBlockEntity>of(TestMachineBlockEntity::new, ModBlocks.TEST_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ChargingChestBlockEntity>> CHARGING_CHEST_BLOCK_ENTITY = BLOCK_ENTITIES.register("charging_chest_block_entity", () -> BlockEntityType.Builder.<ChargingChestBlockEntity>of(ChargingChestBlockEntity::new, ModBlocks.CHARGING_CHEST.get()).build(null));

    //cables
    public static final RegistryObject<BlockEntityType<PrimitiveCableBlockEntity>> PRIMITIVE_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("primitive_cable_block_entity", () -> BlockEntityType.Builder.<PrimitiveCableBlockEntity>of(PrimitiveCableBlockEntity::new, ModBlocks.PRIMITIVE_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BaseCableBlockEntity>> BASE_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("base_cable_block_entity", () -> BlockEntityType.Builder.<BaseCableBlockEntity>of(BaseCableBlockEntity::new, ModBlocks.BASE_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<UpgradedCableBlockEntity>> UPGRADED_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("upgraded_cable_block_entity", () -> BlockEntityType.Builder.<UpgradedCableBlockEntity>of(UpgradedCableBlockEntity::new, ModBlocks.UPGRADED_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedCableBlockEntity>> ADVANCED_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("advanced_cable_block_entity", () -> BlockEntityType.Builder.<AdvancedCableBlockEntity>of(AdvancedCableBlockEntity::new, ModBlocks.ADVANCED_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<OverclockedCableBlockEntity>> OVERCLOCKED_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("overclocked_cable_block_entity", () -> BlockEntityType.Builder.<OverclockedCableBlockEntity>of(OverclockedCableBlockEntity::new, ModBlocks.OVERCLOCKED_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<MaxCableBlockEntity>> MAX_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("max_cable_block_entity", () -> BlockEntityType.Builder.<MaxCableBlockEntity>of(MaxCableBlockEntity::new, ModBlocks.MAX_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CreativeCableBlockEntity>> INFINITE_CABLE_BLOC_ENTITY = BLOCK_ENTITIES.register("creative_cable_block_entity", () -> BlockEntityType.Builder.<CreativeCableBlockEntity>of(CreativeCableBlockEntity::new, ModBlocks.INFINITE_CABLE.get()).build(null));


    //power banks
    public static final RegistryObject<BlockEntityType<InfinitePowerBankBlockEntity>> INFINITE_POWER_BANK_BLOCK_ENTITY = BLOCK_ENTITIES.register("infinite_power_bank_block_entity", () -> BlockEntityType.Builder.<InfinitePowerBankBlockEntity>of(InfinitePowerBankBlockEntity::new, ModBlocks.INFINITE_POWER_BANK.get()).build(null));
}
