package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArg
import zmaster587.libVulpes.tile.TileInventoriedForgePowerMachine
import zmaster587.libVulpes.tile.energy.TileCoalGenerator

@Mixin(TileCoalGenerator::class, remap = false)
abstract class MixinTileCoalGenerator(energy: Int, invSize: Int) : TileInventoriedForgePowerMachine(energy, invSize) {
    @ModifyArg(
        method = ["update"],
        at = At(value = "INVOKE", target = "setText(Ljava/lang/String;)V"),
        index = 0
    )
    private fun injectUpdate(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "coal_generator", "generating"), this.lastAmtGenerated)
    }
}