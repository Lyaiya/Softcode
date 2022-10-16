package com.lyaiya.softcode.mixin

import com.lyaiya.softcode.*
import net.minecraftforge.fml.common.*
import zone.rong.mixinbooter.*

class SoftcodeLateMixinLoader : ILateMixinLoader {
    private inline val String.mixinJson
        get() = "mixins.${Softcode.MOD_ID}.${this}.json"

    private inline val String.toMixinJsonPair
        get() = this to this.mixinJson

    private val mixinJsons = mapOf(
        ModId.IN_WORLD_CRAFTING.toMixinJsonPair,
        ModId.EX_NIHILO_CREATIO.toMixinJsonPair,
        ModId.THAUMCRAFT.toMixinJsonPair,
        ModId.THAUMIC_JEI.toMixinJsonPair
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