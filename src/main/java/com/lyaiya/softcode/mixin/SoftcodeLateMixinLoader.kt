package com.lyaiya.softcode.mixin

import com.lyaiya.softcode.Softcode
import com.lyaiya.softcode.constant.ModIdConstant
import net.minecraftforge.fml.common.Loader
import zone.rong.mixinbooter.ILateMixinLoader

class SoftcodeLateMixinLoader : ILateMixinLoader {
    private val mixinJsons = buildMap {
        putMixinJson(ModIdConstant.IN_WORLD_CRAFTING)
        putMixinJson(ModIdConstant.EX_NIHILO_CREATIO)
        putMixinJson(ModIdConstant.THAUMCRAFT)
        putMixinJson(ModIdConstant.THAUMIC_JEI)
        putMixinJson(ModIdConstant.IMMERSIVE_ENGINEERING)
        putMixinJson(ModIdConstant.BLOCK_DROPS)
        putMixinJson(ModIdConstant.IC2)
        putMixinJson(ModIdConstant.CONTROLLING)
        putMixinJson(ModIdConstant.LIB_VULPES)
        putMixinJson(ModIdConstant.ADVANCED_ROCKETRY)
        putMixinJson(ModIdConstant.SERENES_SEASONS)
        putMixinJson(ModIdConstant.JUST_ENOUGH_RESOURCES)
        putMixinJson(ModIdConstant.INDUSTRIAL_FOREGOING)
    }

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

    private fun MutableMap<String, String>.putMixinJson(modid: String) {
        this[modid] = "mixins.${Softcode.MOD_ID}.${modid}.json"
    }
}