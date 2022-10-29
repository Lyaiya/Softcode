package com.lyaiya.softcode.mixin.controlling

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import us.getfluxed.controlsearch.client.gui.*

@Mixin(EnumSortingType::class, remap = false)
abstract class MixinEnumSortingType {
    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "Default")]
    )
    private fun modifyConstantGetName0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "default"))
    }

    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "A->Z")]
    )
    private fun modifyConstantGetName1(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "a_z"))
    }

    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "Z->A")]
    )
    private fun modifyConstantGetName2(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "z_a"))
    }
}