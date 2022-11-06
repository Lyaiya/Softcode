package com.lyaiya.softcode.mixin.advancedrocketry

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.advancedRocketry.integration.jei.*

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