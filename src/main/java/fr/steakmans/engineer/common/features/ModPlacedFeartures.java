package fr.steakmans.engineer.common.features;

import fr.steakmans.engineer.Main;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeartures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Main.MODID);

    public static final RegistryObject<PlacedFeature> TITANIUM_OVERWORLD_ORE = PLACED_FEATURES.register("titanium_overworld_ore", () -> new PlacedFeature(ModConfiguredFeatures.TITANIUM_OVERWORLD_ORE.getHolder().get(), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(20)))));
    public static final RegistryObject<PlacedFeature> ALUMINUM_OVERWORLD_ORE = PLACED_FEATURES.register("aluminum_overworld_ore", () -> new PlacedFeature(ModConfiguredFeatures.ALUMINUM_OVERWORLD_ORE.getHolder().get(), commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(20), VerticalAnchor.absolute(60)))));
    public static final RegistryObject<PlacedFeature> SILVER_OVERWORLD_ORE = PLACED_FEATURES.register("silver_overworld_ore", () -> new PlacedFeature(ModConfiguredFeatures.SILVER_OVERWORLD_ORE.getHolder().get(), commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(40)))));

    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

    private static List<PlacementModifier> orePlacement(CountPlacement count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

}
