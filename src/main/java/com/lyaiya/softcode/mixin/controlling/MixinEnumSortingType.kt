package com.lyaiya.softcode.mixin.controlling

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import us.getfluxed.controlsearch.client.gui.EnumSortingType

@Mixin(EnumSortingType::class, remap = false)
abstract class MixinEnumSortingType {
    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "Default")]
    )
    private fun modifyConstantGetName0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "default"))
    }

    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "A->Z")]
    )
    private fun modifyConstantGetName1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "a_z"))
    }

    @ModifyConstant(
        method = ["getName"],
        constant = [Constant(stringValue = "Z->A")]
    )
    private fun modifyConstantGetName2(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.CONTROLLING, KeyConstant.KEY, "z_a"))
    }
}