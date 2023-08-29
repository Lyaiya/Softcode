@file:JvmName("MixinKeyHandler")
@file:Mixin(KeyHandler::class, remap = false)

package com.lyaiya.softcode.mixin.thaumcraft

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import thaumcraft.common.lib.events.KeyHandler

@ModifyConstant(
    method = ["<clinit>"],
    constant = [Constant(stringValue = "Change Caster Focus")]
)
private fun modifyConstantKeyF(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "change_caster_focus"))
}

@ModifyConstant(
    method = ["<clinit>"],
    constant = [Constant(stringValue = "Misc Caster Toggle")]
)
private fun modifyConstantKeyG(text: String): String {
    return I18n.format(createTranslationKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "misc_caster_toggle"))
}