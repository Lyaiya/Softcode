@file:JvmName("MixinKeyboardClient")
@file:Mixin(KeyboardClient::class, remap = false)

package com.lyaiya.softcode.mixin.ic2

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import ic2.core.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*

@ModifyConstant(
    method = ["<init>"],
    constant = [Constant(stringValue = "ALT Key")]
)
private fun modifyConstantKey0(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.IC2, KeyConstant.KEY, "alt_key"))
}

@ModifyConstant(
    method = ["<init>"],
    constant = [Constant(stringValue = "Boost Key")]
)
private fun modifyConstantKey1(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.IC2, KeyConstant.KEY, "boost_key"))
}

@ModifyConstant(
    method = ["<init>"],
    constant = [Constant(stringValue = "Mode Switch Key")]
)
private fun modifyConstantKey2(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.IC2, KeyConstant.KEY, "mode_switch_key"))
}

@ModifyConstant(
    method = ["<init>"],
    constant = [Constant(stringValue = "Side Inventory Key")]
)
private fun modifyConstantKey3(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.IC2, KeyConstant.KEY, "side_inventory_key"))
}

@ModifyConstant(
    method = ["<init>"],
    constant = [Constant(stringValue = "Hub Expand Key")]
)
private fun modifyConstantKey4(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.IC2, KeyConstant.KEY, "hud_expand_key"))
}