@file:JvmName("MixinModFertility")
@file:Mixin(ModFertility::class, remap = false)

package com.lyaiya.softcode.mixin.sereneseasons

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import sereneseasons.init.*

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
