@file:JvmName("MixinModFertility")
@file:Mixin(ModFertility::class, remap = false)

package com.lyaiya.softcode.mixin.sereneseasons

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import sereneseasons.init.ModFertility

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = "Fertile Seasons:")]
)
private fun modifyConstantSetupTooltips0(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "fertile_seasons"))
}

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = " Year-Round")]
)
private fun modifyConstantSetupTooltips1(text: String): String {
    return " " + I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "year_round"))
}

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = " Spring")]
)
private fun modifyConstantSetupTooltips2(text: String): String {
    return " " + I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "spring"))
}

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = " Summer")]
)
private fun modifyConstantSetupTooltips3(text: String): String {
    return " " + I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "summer"))
}

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = " Autumn")]
)
private fun modifyConstantSetupTooltips4(text: String): String {
    return " " + I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "autumn"))
}

@ModifyConstant(
    method = ["setupTooltips"],
    constant = [Constant(stringValue = " Winter")]
)
private fun modifyConstantSetupTooltips5(text: String): String {
    return " " + I18n.format(createTranslationKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "winter"))
}
