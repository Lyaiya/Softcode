package com.lyaiya.softcode.mixin.immersiveengineering

import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.ArcFurnaceRecipeWrapper
import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@Mixin(ArcFurnaceRecipeWrapper::class, remap = false)
abstract class MixinArcFurnaceRecipeWrapper {
    @ModifyConstant(
        method = ["drawInfo"],
        constant = [Constant(stringValue = " IF/t")]
    )
    private fun modifyConstantDrawInfo0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "unit_per_tick"))
    }

    @ModifyConstant(
        method = ["drawInfo"],
        constant = [Constant(stringValue = " Seconds")]
    )
    private fun modifyConstantDrawInfo1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.IMMERSIVE_ENGINEERING, KeyConstant.GUI, "seconds"))
    }
}