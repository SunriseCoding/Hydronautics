package io.github.sunrisecoding

import com.mojang.logging.LogUtils
import io.github.sunrisecoding.item.ModItems

import net.minecraft.world.item.CreativeModeTabs
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.event.server.ServerStartingEvent
import org.slf4j.Logger

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HydronauticsMod.MODID)
class HydronauticsMod(modEventBus: IEventBus, modContainer: ModContainer) {
    init {
        modEventBus.addListener { event: FMLCommonSetupEvent? -> this.commonSetup(event) }

        NeoForge.EVENT_BUS.register(this)

        ModItems.register(modEventBus)

        // Register the item to a creative tab
        modEventBus.addListener { event: BuildCreativeModeTabContentsEvent? ->
            this.addCreative(
                event!!
            )
        }

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC)
    }

    private fun commonSetup(event: FMLCommonSetupEvent?) {

    }

    // Add the example block item to the building blocks tab
    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.INGREDIENTS) {
            event.accept( ModItems.TEST_ITEM )
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent?) {
        
    }

    companion object {
        const val MODID: String = "hydronautics"

        val LOGGER: Logger = LogUtils.getLogger()
    }
}