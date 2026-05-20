package io.github.sunrisecoding

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.ModConfigSpec
import net.neoforged.neoforge.common.ModConfigSpec.IntValue

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
object Config {
    private val BUILDER = ModConfigSpec.Builder()

    val LOG_DIRT_BLOCK: ModConfigSpec.BooleanValue = BUILDER
        .comment("Whether to log the dirt block on common setup")
        .define("logDirtBlock", true)

    val MAGIC_NUMBER: IntValue = BUILDER
        .comment("A magic number")
        .defineInRange("magicNumber", 42, 0, Int.MAX_VALUE)

    val MAGIC_NUMBER_INTRODUCTION: ModConfigSpec.ConfigValue<String?> = BUILDER
        .comment("What you want the introduction message to be for the magic number")
        .define<String?>("magicNumberIntroduction", "The magic number is... ")

    // a list of strings that are treated as resource locations for item
    val ITEM_STRINGS: ModConfigSpec.ConfigValue<MutableList<out String?>?> = BUILDER
        .comment("A list of item to log on common setup.")
        .defineListAllowEmpty<String?>(
            "item",
            mutableListOf<String?>("minecraft:iron_ingot"),
            { "" },
            { obj: Any? -> validateItemName(obj) })

    val SPEC: ModConfigSpec = BUILDER.build()

    private fun validateItemName(obj: Any?): Boolean {
        return obj is String && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(obj))
    }
}