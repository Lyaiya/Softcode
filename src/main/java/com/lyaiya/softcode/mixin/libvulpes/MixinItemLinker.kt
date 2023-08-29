package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant
import zmaster587.libVulpes.items.ItemLinker

@Mixin(ItemLinker::class, remap = false)
abstract class MixinItemLinker {
    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Coords unset!")]
    )
    private fun modifyConstantAddInformation0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "coords_unset"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "X: ")]
    )
    private fun modifyConstantAddInformation1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "x"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Y: ")]
    )
    private fun modifyConstantAddInformation2(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "y"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Z: ")]
    )
    private fun modifyConstantAddInformation3(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "z"))
    }

    @ModifyConstant(
        method = ["addInformation"],
        constant = [Constant(stringValue = "Dim: ")]
    )
    private fun modifyConstantAddInformation4(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "linker", "dim"))
    }
}