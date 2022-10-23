package com.lyaiya.softcode.mixin.immersiveengineering

import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.*
import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.*

@Mixin(ArcFurnaceRecipeWrapper::class, remap = false)
abstract class MixinArcFurnaceRecipeWrapper {
    @ModifyConstant(
        method = ["drawInfo"],
        constant = [Constant(stringValue = " IF/t")]
    )
    private fun modifyConstantDrawInfo0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "unit_per_tick"))
    }
    
    @ModifyConstant(
        method = ["drawInfo"],
        constant = [Constant(stringValue = " Seconds")]
    )
    private fun modifyConstantDrawInfo1(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "seconds"))
    }
}