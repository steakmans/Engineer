package fr.steakmans.engineer.common.features;

import com.google.common.base.Suppliers;
import fr.steakmans.engineer.Main;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Main.MODID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>> TITANIUM_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.TITANIUM_DEEPSLATE_ORE.get().defaultBlockState())));

    private static Supplier<List<OreConfiguration.TargetBlockState>> ALUMINUM_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ALUMINUM_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.ALUMINUM_DEEPSLATE_ORE.get().defaultBlockState())));

    private static Supplier<List<OreConfiguration.TargetBlockState>> SILVER_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.SILVER_DEEPSLATE_ORE.get().defaultBlockState())));

    //new BlockMatchTest() pour l'end a la place de STone ore replaceables

    public static final RegistryObject<ConfiguredFeature<?, ?>> TITANIUM_OVERWORLD_ORE = CONFIGURED_FEATURES.register("titanium_overworld_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TITANIUM_OVERWORLD_REPLACEMENT.get(), 3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ALUMINUM_OVERWORLD_ORE = CONFIGURED_FEATURES.register("aluminum_overworld_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ALUMINUM_OVERWORLD_REPLACEMENT.get(), 10)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_OVERWORLD_ORE = CONFIGURED_FEATURES.register("silver_overworld_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SILVER_OVERWORLD_REPLACEMENT.get(), 7)));

}
