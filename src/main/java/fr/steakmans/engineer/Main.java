package fr.steakmans.engineer;

import com.mojang.logging.LogUtils;
import fr.steakmans.engineer.common.blocks.ModBlocks;
import fr.steakmans.engineer.common.blocks.blockentities.ModBlockEntities;
import fr.steakmans.engineer.common.features.ModConfiguredFeatures;
import fr.steakmans.engineer.common.features.ModPlacedFeartures;
import fr.steakmans.engineer.common.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main {

    public static final String MODID = "engineer";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab TAB = new CreativeModeTab("engineering") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.TITANIUM_INGOT.get().getDefaultInstance();
        }
    };

    public static Logger getLogger() {
        return LOGGER;
    }

    public Main() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        ModPlacedFeartures.PLACED_FEATURES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.LevelTickEvent event) {

    }

}
