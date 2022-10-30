package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.tile.*
import zmaster587.libVulpes.tile.energy.*

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