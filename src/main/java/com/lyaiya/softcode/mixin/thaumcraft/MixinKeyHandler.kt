@file:JvmName("MixinKeyHandler")
@file:Mixin(KeyHandler::class, remap = false)

package com.lyaiya.softcode.mixin.thaumcraft

import com.lyaiya.softcode.*
import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import thaumcraft.common.lib.events.*

@ModifyConstant(
    method = ["<clinit>"],
    constant = [Constant(stringValue = "Change Caster Focus")]
)
private fun modifyConstantKeyF(text: String): String {
    return I18n.format(getTranslationKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "change_caster_focus"))
}

@ModifyConstant(
    method = ["<clinit>"],
    constant = [Constant(stringValue = "Misc Caster Toggle")]
)
private fun modifyConstantKeyG(text: String): String {
    return I18n.format(getTranslationKey(ModIdConstant.THAUMCRAFT, KeyConstant.KEY, "misc_caster_toggle"))
}