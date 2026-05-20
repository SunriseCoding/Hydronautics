package io.github.sunrisecoding.item

import io.github.sunrisecoding.HydronauticsMod
import net.minecraft.world.item.Item
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredItem

import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModItems {
    val ITEMS : DeferredRegister.Items = DeferredRegister.createItems(HydronauticsMod.MODID)

    val TEST_ITEM : DeferredItem<Item> = ITEMS.register(
        "test_item",
        Supplier<Item> {
            Item(Item.Properties())
        }
    )

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}