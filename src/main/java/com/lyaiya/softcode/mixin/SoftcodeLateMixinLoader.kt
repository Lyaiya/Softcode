package com.lyaiya.softcode.mixin

import com.lyaiya.softcode.*
import com.lyaiya.softcode.constant.*
import net.minecraftforge.fml.common.*
import zone.rong.mixinbooter.*

class SoftcodeLateMixinLoader : ILateMixinLoader {
    private inline val String.mixinJson
        get() = "mixins.${Softcode.MOD_ID}.${this}.json"

    private inline val String.mixinJsonPair
        get() = this to this.mixinJson

    private val mixinJsons = mapOf(
        ModIdConstant.IN_WORLD_CRAFTING.mixinJsonPair,
        ModIdConstant.EX_NIHILO_CREATIO.mixinJsonPair,
        ModIdConstant.THAUMCRAFT.mixinJsonPair,
        ModIdConstant.THAUMIC_JEI.mixinJsonPair,
        ModIdConstant.IMMERSIVE_ENGINEERING.mixinJsonPair
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