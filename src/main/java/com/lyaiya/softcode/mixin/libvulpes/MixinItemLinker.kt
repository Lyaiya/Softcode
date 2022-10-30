package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.items.*

@Mixin(ItemLinker::class, remap = false)
abstract class MixinItemLinker {
    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Coords unset!")]
    )
    private fun modifyConstantAddInformation0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "coords_unset"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "X: ")]
    )
    private fun modifyConstantAddInformation1(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "x"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Y: ")]
    )
    private fun modifyConstantAddInformation2(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "y"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Z: ")]
    )
    private fun modifyConstantAddInformation3(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "z"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Dim: ")]
    )
    private fun modifyConstantAddInformation4(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "dim"))
    }
}