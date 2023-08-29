package com.lyaiya.softcode.mixin.advancedrocketry

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArg
import zmaster587.advancedRocketry.integration.jei.MachineRecipe

@Mixin(MachineRecipe::class, remap = false)
abstract class MixinMachineRecipe {
    @Shadow
    abstract fun getEnergy(): Int

    @Shadow
    abstract fun getTime(): Int

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawString(Ljava/lang/String;III)I", ordinal = 0)
    )
    private fun modifyArgDrawInfo0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.ADVANCED_ROCKETRY, KeyConstant.GUI, "power"), getEnergy())
    }

    @ModifyArg(
        method = ["drawInfo"],
        at = At(value = "INVOKE", target = "drawString(Ljava/lang/String;III)I", ordinal = 1)
    )
    private fun modifyArgDrawInfo1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.ADVANCED_ROCKETRY, KeyConstant.GUI, "time"), getTime() / 20)
    }
}