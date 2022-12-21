package fr.steakmans.engineer.common.worldgeneration;

import fr.steakmans.engineer.common.blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

public class OreFeature {

    public static Holder<PlacedFeature> OVERWORLD_OREGEN;

    public static void registerOreFeatures() {
        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState(), 20);
        OVERWORLD_OREGEN = registerPlacedOreFeature("overworld_titanium_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig), CountPlacement.of(5), InSquarePlacement.spread(), BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(25)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedOreFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

}
