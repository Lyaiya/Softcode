package com.lyaiya.softcode.mixin

import com.lyaiya.softcode.*
import net.minecraftforge.fml.common.*
import zone.rong.mixinbooter.*

class SoftcodeLateMixinLoader : ILateMixinLoader {

    private inline val String.mixinJson
        get() = "mixins.${Softcode.MOD_ID}.${this}.json"

    private val mixinJsons = mapOf(
        ModId.IN_WORLD_CRAFTING to ModId.IN_WORLD_CRAFTING.mixinJson,
        ModId.EX_NIHILO_CREATIO to ModId.EX_NIHILO_CREATIO.mixinJson
    )

    override fun getMixinConfigs(): List<String> {
        return mixinJsons.values.toList()
    }

    override fun shouldMixinConfigQueue(mixinConfig: String): Boolean {
        return mixinIfModLoaded(mixinConfig)
    }

    private fun mixinIfModLoaded(mixinConfig: String): Boolean {
        for ((modid, mixinJson) in mixinJsons) {
            if (mixinConfig == mixinJson) {
                return Loader.isModLoaded(modid)
            }
        }
        return false
    }

}